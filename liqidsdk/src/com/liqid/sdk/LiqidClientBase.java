//
// Copyright (c) 2022-2023 by Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk;

import com.bearsnake.klog.Level;
import com.bearsnake.klog.Logger;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Base64;
import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.Collectors;

public abstract class LiqidClientBase {

    private static final String ASYNC_STATUS_PENDING = "Pending";
    private static final String ASYNC_STATUS_FAILURE = "FAILURE";
    private static final String ASYNC_STATUS_SUCCESS = "SUCCESS";

    public static final String API_BASE_PATH = "/liqid/api/v2";

    protected final boolean _secureHTTP;                //  true for HTTPS, false for HTTP
    protected final String _hostAddress;                //  DNS name or IP address (not including scheme/protocol)
    protected final int _portNumber;                    //  API port number
    protected final boolean _ignoreCertificates;
    protected final int _timeoutInSeconds;
    protected final boolean _retryOnServerError;        //  true -> retry requests which returned a 5xx response
    protected final int _retryLimit;                    //  number of times to retry 5xx responses
    protected final int _retryDelayInSeconds;           //  number of seconds to pause before a retry
    protected final boolean _waitForAsyncCompletion;    //  true -> wait for async tasks to complete on 202 response
    protected final int _maxAsyncWaitTimeInSeconds;     //  max time to wait for async completion

    protected final String _scheme;                     //  auto-set (by us) to "http" or "https"
    protected final HttpClient _httpClient;

    protected Logger _logger;
    protected String _base64Credentials = null;
    protected String _authLabel = null;
    protected String _authToken = null;

    protected boolean _presetsAreSet = false;
    protected Coordinates _presetCoordinates;
    protected int _presetFabricId;

    private int _lastHttpResponseCode;
    private String _lastHttpResponseMessage;
    private int _lastRetryCount;
    private String _lastAsyncRequestStatus;
    private String _lastAsyncReference;

    protected LiqidClientBase(final boolean secureHttp,
                              final String hostAddress,
                              final int portNumber,
                              final boolean ignoreCertificates,
                              final int timeoutInSeconds,
                              final boolean retryOnServerError,
                              final int retryCount,
                              final int retryDelayInSeconds,
                              final boolean waitForAsyncCompletion,
                              final int maxAsyncWaitTimeInSeconds) {
        _secureHTTP = secureHttp;
        _hostAddress = hostAddress;
        _portNumber = portNumber;
        _ignoreCertificates = ignoreCertificates;
        _timeoutInSeconds = timeoutInSeconds;
        _retryOnServerError = retryOnServerError;
        _retryLimit = retryCount;
        _retryDelayInSeconds = retryDelayInSeconds;
        _waitForAsyncCompletion = waitForAsyncCompletion;
        _maxAsyncWaitTimeInSeconds = maxAsyncWaitTimeInSeconds;

        _scheme = secureHttp ? "https" : "http";
        _logger = new Logger("liqidSdk", Level.ERROR);

        _httpClient = HttpClient.newBuilder()
                                .version(HttpClient.Version.HTTP_1_1)
                                .followRedirects(HttpClient.Redirect.NORMAL)
                                .build();
    }

    protected String getFullPath(final String partialPath) {
        return _scheme + "://" + _hostAddress + ":" + _portNumber + API_BASE_PATH + "/" + partialPath;
    }

    public String getHostAddress() { return _hostAddress; }
    public boolean getIgnoreCertificates() { return _ignoreCertificates; }
    public String getLastAsyncReference() { return _lastAsyncReference; }
    public int getLastHttpResponseCode() { return _lastHttpResponseCode; }
    public String getLastHttpResponseMessage() { return _lastHttpResponseMessage; }
    public int getLastRetryCount() { return _lastRetryCount; }
    public String getLastAsyncRequestStatus() { return _lastAsyncRequestStatus; }
    public int getMaxAsyncWaitTimeInSeconds() { return _maxAsyncWaitTimeInSeconds; }
    public int getPortNumber() { return _portNumber; }
    public boolean getRetryOnServerError() { return _retryOnServerError; }
    public int getRetryLimit() { return _retryLimit; }
    public int getRetryDelayInSeconds() { return _retryDelayInSeconds; }
    public boolean getSecureHTTP() { return _secureHTTP; }
    public int getTimeoutInSeconds() { return _timeoutInSeconds; }
    public boolean getWaitForAsyncCompletion() { return _waitForAsyncCompletion; }
    public void setLogger(Logger logger) { _logger = logger; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class AsyncLinks {

        @JsonProperty("href")
        private String _reference = null;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class AsyncApplicationStruct {

        private static class AsyncWrapper extends Wrapper<AsyncApplicationStruct>{}

        @JsonProperty("links")
        private AsyncLinks[] _links = null;
    }

    /**
     * Handles the life-cycle of an HTTP request, including retries and waits for async tasks.
     * @param method GET, DELETE, etc
     * @param partialPath the portion of the URL which is not pre-determined
     * @param requestHttpBodyType describes the format of the request body
     * @param body request body
     * @param responseHttpBodyType describes the expected format of the response body
     * @return An HttpResponse object
     * @throws LiqidException if we detect a problem of one sort or another
     */
    protected HttpResponse<?> send(String method,
                                   String partialPath,
                                   HttpBodyType requestHttpBodyType,
                                   Object body,
                                   HttpBodyType responseHttpBodyType)
        throws LiqidException {

        _lastAsyncRequestStatus = null;
        _lastHttpResponseCode = 0;
        _lastHttpResponseMessage = null;
        _lastRetryCount = 0;

        var response = _send(method, partialPath, requestHttpBodyType, body, responseHttpBodyType);
        _lastHttpResponseCode = response.statusCode();
        _lastHttpResponseMessage = response.toString();

        while (_retryOnServerError
            && (_lastHttpResponseCode >= 500)
            && (_lastHttpResponseCode <= 599)
            && (_lastRetryCount < _retryLimit)) {

            try {
                var millis = _retryDelayInSeconds * 1000;
                Thread.sleep(millis, 0);
            } catch (InterruptedException ex) {
                _logger.catching(ex);
            }

            _lastRetryCount++;
            response = _send(method, partialPath, requestHttpBodyType, body, responseHttpBodyType);
            _lastHttpResponseCode = response.statusCode();
            _lastHttpResponseMessage = response.toString();
        }

        if (_waitForAsyncCompletion && (_lastHttpResponseCode == 202)) {
            try {
                var mapper = new ObjectMapper();
                var wrapper = mapper.readValue((String) response.body(), AsyncApplicationStruct.AsyncWrapper.class);
                var asyncStruct = wrapper.getResponse().getData().getFirst();
                _lastAsyncReference = asyncStruct._links[0]._reference;
            } catch (IOException ex) {
                _logger.catching(ex);
                var msg = String.format("Caught:%s while deciphering async result", ex);
                throw new LiqidException(msg);
            }

            _lastAsyncRequestStatus = ASYNC_STATUS_PENDING;
            var timeLimit = System.currentTimeMillis() + (long)_maxAsyncWaitTimeInSeconds * 1000;
            while (_lastAsyncRequestStatus.equals(ASYNC_STATUS_PENDING)
                   && System.currentTimeMillis() <= timeLimit) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    _logger.catching(ex);
                }

                var builder = HttpRequest.newBuilder()
                                         .uri(URI.create(_lastAsyncReference))
                                         .timeout(Duration.ofSeconds(_timeoutInSeconds))
                                         .GET();
                applyAuthentication(builder);
                var httpRequest = builder.build();

                _logger.trace("===>GET:%s", _lastAsyncReference);
                if (body != null) {
                    _logger.trace("===>%s", body);
                }

                try {
                    var responseHandler = HttpResponse.BodyHandlers.ofString();
                    var pollResponse = _httpClient.send(httpRequest, responseHandler);
                    _logger.trace("<===%s", pollResponse.toString());
                    if (pollResponse.body() != null) {
                        _logger.trace("<===%s", pollResponse.body());
                    }

                    if (pollResponse.statusCode() == 404) {
                        _lastAsyncRequestStatus = ASYNC_STATUS_SUCCESS;
                        break;
                    } else if (pollResponse.statusCode() != 200) {
                        throw new LiqidException("Cannot determine result of async operation:" + pollResponse);
                    }

                    var pollMapper = new ObjectMapper();
                    var wrapper = pollMapper.readValue(pollResponse.body(),
                                                       AsynchronousStatus.AsynchronousStatusWrapper.class);
                    var exState = wrapper.getResponse().getData().getFirst().getExecutionState();
                    if (exState != null) {
                        _lastAsyncRequestStatus = wrapper.getResponse().getData().getFirst().getExecutionState();
                    }
                } catch (InterruptedException | IOException ex) {
                    _logger.catching(ex);
                    throw new LiqidException("Caught", ex);
                }
            }
        }

        return response;
    }

    /**
     * Functional process of sending one instance of an HTTP request.
     * Wrapped by an upper-level send method which is responsible for handling
     * retries and async waits.
     */
    private HttpResponse<?> _send(String method,
                                  String partialPath,
                                  HttpBodyType requestHttpBodyType,
                                  Object body,
                                  HttpBodyType responseHttpBodyType)
        throws LiqidException {

        var fullPath = getFullPath(partialPath);
        var builder = HttpRequest.newBuilder()
                                 .uri(URI.create(fullPath))
                                 .timeout(Duration.ofSeconds(_timeoutInSeconds));

        if (method.equalsIgnoreCase("DELETE")) {
            // This is needed because the group/machine relator DELETE operations
            // require the presentation of the deviceStatus, group, and (maybe) machine objects
            // in the body. It is known that presenting a body with DELETE is essentially
            // discouraged; the extant recommendations state that such a body is not forbidden,
            // but servers should ignore them. The API certainly doesn't ignore them...
            try {
                if (requestHttpBodyType == HttpBodyType.Json) {
                    var objMapper = new ObjectMapper();
                    var bodyStr = objMapper.writeValueAsString(body);
                    var publisher = HttpRequest.BodyPublishers.ofString(bodyStr);
                    builder.method("DELETE", publisher);
                    builder.header("Content-type", "application/json");
                } else {
                    builder.DELETE();
                }
            } catch (JsonProcessingException ex) {
                _logger.catching(ex);
                throw new LiqidException("JSON exception", ex);
            }
        } else if (method.equalsIgnoreCase("GET")) {
            builder.GET();
        } else {
            try {
                HttpRequest.BodyPublisher publisher = null;
                switch (requestHttpBodyType) {
                    case None -> {
                        publisher = HttpRequest.BodyPublishers.noBody();
                        builder.header("Content-type", "text/plain");
                    }
                    case OctetStream -> {
                        publisher = HttpRequest.BodyPublishers.ofByteArray((byte[]) body);
                        builder.header("Content-type", "application/octet-stream");
                    }
                    case Json -> {
                        var objMapper = new ObjectMapper();
                        var bodyStr = objMapper.writeValueAsString(body);
                        publisher = HttpRequest.BodyPublishers.ofString(bodyStr);
                        builder.header("Content-type", "application/json");
                    }
                }

                if (method.equalsIgnoreCase("POST")) {
                    builder.POST(publisher);
                } else if (method.equalsIgnoreCase("PUT")) {
                    builder.PUT(publisher);
                }
            } catch (JsonProcessingException ex) {
                _logger.catching(ex);
                throw new LiqidException("JSON exception", ex);
            }
        }

        applyAuthentication(builder);
        var httpRequest = builder.build();

        _logger.trace("===>%s:%s", method, fullPath);
        if (body != null) {
            _logger.trace("===>%s", body);
        }

        var responseHandler =
            switch (responseHttpBodyType) {
                case None -> HttpResponse.BodyHandlers.discarding();
                case OctetStream -> HttpResponse.BodyHandlers.ofByteArray();
                case Json -> HttpResponse.BodyHandlers.ofString();
            };

        try {
            var response = _httpClient.send(httpRequest, responseHandler);
            _logger.trace("<===%s", response.toString());
            if (response.body() != null) {
                _logger.trace("<===%s", response.body().toString());
            }
            return response;
        } catch (InterruptedException | IOException ex) {
            _logger.catching(ex);
            throw new LiqidException("Caught", ex);
        }
    }

    //  Presets ----------------------------------------------------------------

    protected void loadPresets() throws LiqidException {
        _presetCoordinates = getDefaultCoordinates();
        _presetFabricId = getCurrentFabricId();
        _presetsAreSet = true;
    }

    protected int getPresetFabricId() throws LiqidException {
        if (!_presetsAreSet) {
            loadPresets();
        }
        return _presetFabricId;
    }

    protected int getPresetRack() throws LiqidException {
        if (!_presetsAreSet) {
            loadPresets();
        }
        return _presetCoordinates.getRack();
    }

    protected int getPresetShelf() throws LiqidException {
        if (!_presetsAreSet) {
            loadPresets();
        }
        return _presetCoordinates.getShelf();
    }

    protected int getPresetNode() throws LiqidException {
        if (!_presetsAreSet) {
            loadPresets();
        }
        return _presetCoordinates.getNode();
    }

    //  Credentials ------------------------------------------------------------

    private static String generateBasicAuthString(String username, String password) {
        String composite = String.format("%s:%s", username, password);
        return Base64.getEncoder().encodeToString(composite.getBytes());
    }

    /**
     * Un-establishes basic authentication credentials.
     * Once invoked, we no longer send basic auth headers with HTTP requests.
     */
    public void clearCredentials() throws LiqidException {
        var fn = "clearCredentials()";
        _logger.trace("Entering %s", fn);

        if (!hasCredentials()) {
            var ex = new LiqidException("Credentials are not established");
            _logger.throwing(ex);
            throw ex;
        }

        _base64Credentials = null;
        _logger.trace("%s returning", fn);
    }

    public void setCredentials(String username, String password) throws LiqidException {
        var fn = "setCredentials";
        _logger.trace("Entering %s", fn);

        if (hasCredentials()) {
            var ex = new LiqidException("Credentials already established");
            _logger.throwing(ex);
            throw ex;
        } else if (isLoggedIn()) {
            var ex = new LiqidException("Session is logged in");
            _logger.throwing(ex);
            throw ex;
        }

        _base64Credentials = generateBasicAuthString(username, password);
        _logger.trace("%s returning", fn);
    }

    public boolean hasCredentials() {
        var fn = "hasCredentials";
        _logger.trace("Entering %s", fn);

        var result = _base64Credentials != null;
        _logger.trace("%s returning %s", fn, result);
        return result;
    }

    public void login(String label, String username, String password) throws LiqidException {
        var fn = "login";
        _logger.trace("Entering %s label:%s", fn, label);

        if (hasCredentials()) {
            var ex = new LiqidException("Credentials already established");
            _logger.throwing(ex);
            throw ex;
        } else if (isLoggedIn()) {
            var ex = new LiqidException("Session is logged in");
            _logger.throwing(ex);
            throw ex;
        } else if (label.equalsIgnoreCase("slurm")) {
            var ex = new LiqidException("Permission denied");
            _logger.throwing(ex);
            throw ex;
        } else if (label.contains(" ")) {
            var ex = new LiqidException("Invalid label");
            _logger.throwing(ex);
            throw ex;
        }

        setCredentials(username, password);
        try {
            deleteMessageDigest(label);
        } catch (LiqidException ex) {
            //  do nothing
        }

        var digestInfo = createMessageDigest(label);
        clearCredentials();
        _authLabel = label;
        _authToken = digestInfo.getDigestKey();
        _logger.trace("%s returning", fn);
    }

    public void logout() throws LiqidException {
        var fn = "logout";
        _logger.trace("Entering %s", fn);

        if (!isLoggedIn()) {
            var ex = new LiqidException("Not logged in");
            _logger.throwing(ex);
            throw ex;
        }

        _authToken = null;
        _authLabel = null;
        _logger.trace("%s returning", fn);
    }

    public boolean isLoggedIn() {
        return _authToken != null;
    }

    protected void applyAuthentication(HttpRequest.Builder builder) {
        if (hasCredentials()) {
            builder.setHeader("Authorization", "Basic " + _base64Credentials);
        } else if (isLoggedIn()) {
            builder.setHeader("X-API-Key", _authToken);
        }
    }

    //  Things which the generated code might need -----------------------------

    public static Integer hexStringToInteger(final String source) {
        try {
            if (source == null) {
                return 0;
            } else if (source.startsWith("0x") || source.startsWith("0X")) {
                return Integer.parseInt(source.substring(2), 16);
            } else {
                return Integer.parseInt(source, 16);
            }
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public static Long hexStringToLong(final String source) {
        try {
            if (source == null) {
                return 0L;
            } else if (source.startsWith("0x") || source.startsWith("0X")) {
                return Long.parseLong(source.substring(2), 16);
            } else {
                return Long.parseLong(source, 16);
            }
        } catch (NumberFormatException ex) {
            return 0L;
        }
    }

    //  Hard-coded special cases -----------------------------------------------

    //  methods we need to invoke from this class, which must be implemented by the base class
    public abstract Group createGroupWithId(Integer groupId, String groupName) throws LiqidException;
    public abstract Machine createMachineWithId(Integer groupId, Integer machineId, String machineName) throws LiqidException;
    public abstract DigestInfo createMessageDigest(String label) throws LiqidException;

    public abstract GroupComputeDeviceRelator addComputeDeviceToGroup(Integer deviceId, Integer GroupId) throws LiqidException;
    public abstract GroupFPGADeviceRelator addFPGADeviceToGroup(Integer deviceId, Integer GroupId) throws LiqidException;
    public abstract GroupGPUDeviceRelator addGPUDeviceToGroup(Integer deviceId, Integer GroupId) throws LiqidException;
    public abstract GroupMemoryDeviceRelator addMemoryDeviceToGroup(Integer deviceId, Integer GroupId) throws LiqidException;
    public abstract GroupNetworkDeviceRelator addNetworkDeviceToGroup(Integer deviceId, Integer GroupId) throws LiqidException;
    public abstract GroupStorageDeviceRelator addStorageDeviceToGroup(Integer deviceId, Integer GroupId) throws LiqidException;

    public abstract MachineComputeDeviceRelator addComputeDeviceToMachine(Integer deviceId,
                                                                          Integer groupId,
                                                                          Integer machineId) throws LiqidException;
    public abstract MachineFPGADeviceRelator addFPGADeviceToMachine(Integer deviceId,
                                                                    Integer GroupId,
                                                                    Integer machineId) throws LiqidException;
    public abstract MachineGPUDeviceRelator addGPUDeviceToMachine(Integer deviceId,
                                                                  Integer GroupId,
                                                                  Integer machineId) throws LiqidException;
    public abstract MachineMemoryDeviceRelator addMemoryDeviceToMachine(Integer deviceId,
                                                                        Integer GroupId,
                                                                        Integer machineId) throws LiqidException;
    public abstract MachineNetworkDeviceRelator addNetworkDeviceToMachine(Integer deviceId,
                                                                          Integer GroupId,
                                                                          Integer machineId) throws LiqidException;
    public abstract MachineStorageDeviceRelator addStorageDeviceToMachine(Integer deviceId,
                                                                          Integer GroupId,
                                                                          Integer machineId) throws LiqidException;

    public abstract LinkedList<DeviceStatus> getAllDevicesStatus() throws LiqidException;
    public abstract Integer getCurrentFabricId() throws LiqidException;
    public abstract Coordinates getDefaultCoordinates() throws LiqidException;
    public abstract LinkedList<PreDevice> getPreDevices(DeviceQueryType deviceType, Integer groupId, Integer machineId) throws LiqidException;
    public abstract Integer getNextGroupId() throws LiqidException;
    public abstract String getNextMachineId() throws LiqidException;

    public abstract LinkedList<ComputeDeviceInfo> getComputeDeviceInfo() throws LiqidException;
    public abstract LinkedList<FPGADeviceInfo> getFPGADeviceInfo() throws LiqidException;
    public abstract LinkedList<GPUDeviceInfo> getGPUDeviceInfo() throws LiqidException;
    public abstract LinkedList<MemoryDeviceInfo> getMemoryDeviceInfo() throws LiqidException;
    public abstract LinkedList<NetworkDeviceInfo> getNetworkDeviceInfo() throws LiqidException;
    public abstract LinkedList<StorageDeviceInfo> getStorageDeviceInfo() throws LiqidException;

    public abstract GroupComputeDeviceRelator removeComputeDeviceFromGroup(Integer deviceId, Integer GroupId) throws LiqidException;
    public abstract GroupFPGADeviceRelator removeFPGADeviceFromGroup(Integer deviceId, Integer GroupId) throws LiqidException;
    public abstract GroupGPUDeviceRelator removeGPUDeviceFromGroup(Integer deviceId, Integer GroupId) throws LiqidException;
    public abstract GroupMemoryDeviceRelator removeMemoryDeviceFromGroup(Integer deviceId, Integer GroupId) throws LiqidException;
    public abstract GroupNetworkDeviceRelator removeNetworkDeviceFromGroup(Integer deviceId, Integer GroupId) throws LiqidException;
    public abstract GroupStorageDeviceRelator removeStorageDeviceFromGroup(Integer deviceId, Integer GroupId) throws LiqidException;

    public abstract MachineComputeDeviceRelator removeComputeDeviceFromMachine(Integer deviceId,
                                                                               Integer groupId,
                                                                               Integer machineId) throws LiqidException;
    public abstract MachineFPGADeviceRelator removeFPGADeviceFromMachine(Integer deviceId,
                                                                         Integer GroupId,
                                                                         Integer machineId) throws LiqidException;
    public abstract MachineGPUDeviceRelator removeGPUDeviceFromMachine(Integer deviceId,
                                                                       Integer GroupId,
                                                                       Integer machineId) throws LiqidException;
    public abstract MachineMemoryDeviceRelator removeMemoryDeviceFromMachine(Integer deviceId,
                                                                             Integer GroupId,
                                                                             Integer machineId) throws LiqidException;
    public abstract MachineNetworkDeviceRelator removeNetworkDeviceFromMachine(Integer deviceId,
                                                                               Integer GroupId,
                                                                               Integer machineId) throws LiqidException;
    public abstract MachineStorageDeviceRelator removeStorageDeviceFromMachine(Integer deviceId,
                                                                               Integer GroupId,
                                                                               Integer machineId) throws LiqidException;

    public void checkParameterNotNull(Object value, String parameterName, String functionName) throws LiqidException {
        if (value == null) {
            throw new LiqidException(String.format("Parameter %s in %s() cannot be null", parameterName, functionName));
        }
    }

    public Group createGroup(String groupName) throws LiqidException {
        var fn = "createGroup";
        _logger.trace("Entering %s groupName:%s", fn, groupName);
        var groupId = getNextGroupId();
        return createGroupWithId(groupId, groupName);
    }

    public Machine createMachine(Integer groupId, String machineName) throws LiqidException {
        var fn = "createMachine";
        _logger.trace("Entering %s groupId:%s machineName:%s", fn, groupId, machineName);
        var machineId = Integer.parseInt(getNextMachineId());
        return createMachineWithId(groupId, machineId, machineName);
    }

    /**
     * deleteMessageDigest()
     * Category: MessageDigest
     * Deletes a previously-created message digest.
     * Hard-coded here to allow us to avoid deletion of special labels
     *
     * @param label: Returns the label associated with the deleted message digest
     * @return Returns the label associated with the deleted message digest
     * @throws LiqidException if anything goes wrong
     */
    public String deleteMessageDigest(String label) throws LiqidException {
        var fn = "deleteMessageDigest";
        _logger.trace("Entering %s label:%s", fn, label);

        if (label != null && label.equalsIgnoreCase("slurm")) {
            _logger.error("Attempted to delete system label");
            throw new LiqidException("Cannot delete this label");
        }

        String result = null;
        try {
            var path = "digest";
            path += "/" + (label == null ? "" : label.toString());
            var httpResponse = send("DELETE", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            var wrapper = mapper.readValue((String) httpResponse.body(), StringWrapper.class);
            wrapper.check();
            result = wrapper.getResponse().getData().getFirst();
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.catching(ex);
            throw new LiqidException("Caught exception:", ex);
        }
        _logger.trace("%s returning %s", fn, result);
        return result;
    }

    public static DeviceQueryType deviceTypeToQueryDeviceType(
        final DeviceType deviceType
    ) {
        return switch (deviceType) {
            case INFINIBAND_LINK, ETHERNET_LINK, FIBER_CHANNEL_LINK -> DeviceQueryType.LINK;
            case GPU -> DeviceQueryType.GPU;
            case SSD -> DeviceQueryType.TARGET;
            case FPGA -> DeviceQueryType.FPGA;
            case COMPUTE -> DeviceQueryType.COMPUTE;
            case MEMORY -> DeviceQueryType.MEMORY;
        };
    }

    public Machine enableP2PForMachine(Integer machineId, Boolean enabledFlag) throws LiqidException {
        var fn = "enableP2PForMachine";
        _logger.trace("Entering %s machineId:%s enabledFlag:%s", fn, machineId, enabledFlag);

        Machine.MachineWrapper wrapper;
        String path = "machine";
        try {
            var builder = HttpRequest.newBuilder();
            HttpRequest request = builder.uri(URI.create(getFullPath(path)))
                                         .GET()
                                         .timeout(Duration.ofSeconds(_timeoutInSeconds))
                                         .build();
            HttpResponse<String> response = _httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            var mapper = new ObjectMapper();
            wrapper = mapper.readValue(response.body(), Machine.MachineWrapper.class);
        } catch (Exception cex) {
            var ex = new LiqidException("Caught exception", cex);
            _logger.catching(ex);
            throw ex;
        }

        wrapper.check();
        var machine = wrapper.getResponse().getData().getFirst();
        machine.setP2PEnabled(enabledFlag ? P2PType.ON : P2PType.OFF);

        try {
            var newWrapper = new Machine.MachineWrapper();
            newWrapper.setResponse(new Wrapper.Response<>(Collections.singletonList(machine)));

            var reqMapper = new ObjectMapper();
            var body = reqMapper.writeValueAsString(newWrapper);
            var builder = HttpRequest.newBuilder();
            HttpRequest request = builder.uri(URI.create(getFullPath(path)))
                                         .timeout(Duration.ofSeconds(_timeoutInSeconds))
                                         .POST(HttpRequest.BodyPublishers.ofString(body))
                                         .header("Content-type", "application/json")
                                         .build();
            HttpResponse<String> response = _httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            var respMapper = new ObjectMapper();
            var respWrapper = respMapper.readValue(response.body(), Machine.MachineWrapper.class);
            respWrapper.check();

            var result = respWrapper.getResponse().getData().getFirst();
            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (Exception cex) {
            var ex = new LiqidException("Caught exception", cex);
            _logger.catching(ex);
            throw ex;
        }
    }

    public DeviceStatus getDeviceStatus(Integer deviceId) throws LiqidException {
        var fn = "getDeviceStatus";
        _logger.trace("Entering %s deviceId:%s", fn, deviceId);

        try {
            var devices = getAllDevicesStatus();
            for (var devStat : devices) {
                if (devStat.getDeviceId().equals(deviceId)) {
                    _logger.trace("%s returning %s", fn, devStat);
                    return devStat;
                }
            }
        } catch (Exception cex) {
            var ex = new LiqidException("Caught exception", cex);
            _logger.catching(ex);
            throw ex;
        }

        var ex = new LiqidException(String.format("Device 0x%08x not found", deviceId));
        _logger.throwing(ex);
        throw ex;
    }

    public void addDeviceToGroup(Integer deviceId, Integer groupId) throws LiqidException {
        var fn = "addDeviceToGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);

        var devStat = getDeviceStatus(deviceId);
        switch (devStat.getDeviceType()) {
            case COMPUTE -> addComputeDeviceToGroup(deviceId, groupId);
            case FPGA -> addFPGADeviceToGroup(deviceId, groupId);
            case GPU -> addGPUDeviceToGroup(deviceId, groupId);
            case MEMORY -> addMemoryDeviceToGroup(deviceId, groupId);
            case ETHERNET_LINK, FIBER_CHANNEL_LINK, INFINIBAND_LINK -> addNetworkDeviceToGroup(deviceId, groupId);
            case SSD -> addStorageDeviceToGroup(deviceId, groupId);
            default -> throw new LiqidException("Invalid device type");
        }

        _logger.trace("%s returning", fn);
    }

    public void addDeviceToMachine(Integer deviceId, Integer groupId, Integer machineId) throws LiqidException {
        var fn = "addDeviceToMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);

        var devStat = getDeviceStatus(deviceId);
        switch (devStat.getDeviceType()) {
            case COMPUTE -> addComputeDeviceToMachine(deviceId, groupId, machineId);
            case FPGA -> addFPGADeviceToMachine(deviceId, groupId, machineId);
            case GPU -> addGPUDeviceToMachine(deviceId, groupId, machineId);
            case MEMORY -> addMemoryDeviceToMachine(deviceId, groupId, machineId);
            case ETHERNET_LINK, FIBER_CHANNEL_LINK, INFINIBAND_LINK -> addNetworkDeviceToMachine(deviceId, groupId, machineId);
            case SSD -> addStorageDeviceToMachine(deviceId, groupId, machineId);
            default -> throw new LiqidException("Invalid device type");
        }

        _logger.trace("%s returning", fn);
    }

    public LinkedList<DeviceInfo> getAllDeviceInfos() throws LiqidException {
        var infos = new LinkedList<DeviceInfo>();
        infos.addAll(getComputeDeviceInfo());
        infos.addAll(getFPGADeviceInfo());
        infos.addAll(getGPUDeviceInfo());
        infos.addAll(getMemoryDeviceInfo());
        infos.addAll(getNetworkDeviceInfo());
        infos.addAll(getStorageDeviceInfo());
        return infos;
    }

    public LinkedList<PreDevice> getUnattachedDevicesForGroup(
        final DeviceQueryType deviceType,
        final Integer groupId
    ) throws LiqidException {
        var devs = getPreDevices(deviceType, groupId, null);
        return devs.stream()
                   .filter(preDev -> preDev.getMachineId() == 0)
                   .collect(Collectors.toCollection(LinkedList::new));
    }

    public void removeDeviceFromGroup(Integer deviceId, Integer groupId) throws LiqidException {
        var fn = "removeDeviceFromGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);

        var devStat = getDeviceStatus(deviceId);
        switch (devStat.getDeviceType()) {
            case COMPUTE -> removeComputeDeviceFromGroup(deviceId, groupId);
            case FPGA -> removeFPGADeviceFromGroup(deviceId, groupId);
            case GPU -> removeGPUDeviceFromGroup(deviceId, groupId);
            case MEMORY -> removeMemoryDeviceFromGroup(deviceId, groupId);
            case ETHERNET_LINK, FIBER_CHANNEL_LINK, INFINIBAND_LINK -> removeNetworkDeviceFromGroup(deviceId, groupId);
            case SSD -> removeStorageDeviceFromGroup(deviceId, groupId);
            default -> throw new LiqidException("Invalid device type");
        }

        _logger.trace("%s returning", fn);
    }

    public void removeDeviceFromMachine(Integer deviceId, Integer groupId, Integer machineId) throws LiqidException {
        var fn = "removeDeviceFromMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);

        var devStat = getDeviceStatus(deviceId);
        switch (devStat.getDeviceType()) {
            case COMPUTE -> removeComputeDeviceFromMachine(deviceId, groupId, machineId);
            case FPGA -> removeFPGADeviceFromMachine(deviceId, groupId, machineId);
            case GPU -> removeGPUDeviceFromMachine(deviceId, groupId, machineId);
            case MEMORY -> removeMemoryDeviceFromMachine(deviceId, groupId, machineId);
            case ETHERNET_LINK, FIBER_CHANNEL_LINK, INFINIBAND_LINK -> removeNetworkDeviceFromMachine(deviceId, groupId, machineId);
            case SSD -> removeStorageDeviceFromMachine(deviceId, groupId, machineId);
            default -> throw new LiqidException("Invalid device type");
        }

        _logger.trace("%s returning", fn);
    }
}
