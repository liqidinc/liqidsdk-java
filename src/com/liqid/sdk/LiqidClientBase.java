//
// Copyright (c) 2022 by Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Base64;
import java.util.Collections;

public abstract class LiqidClientBase {

    public static final String API_BASE_PATH = "/liqid/api/v2";

    protected final boolean _secureHTTP;            //  true for HTTPS, false for HTTP
    protected final String _hostAddress;            //  DNS name or IP address (not including scheme/protocol)
    protected final int _portNumber;                //  API port number
    protected final boolean _ignoreCertificates;
    protected final int _timeoutInSeconds;

    protected final String _scheme;                 //  auto-set (by us) to "http" or "https"
    protected final HttpClient _httpClient;

    protected Logger _logger;
    protected String _base64Credentials = null;
    protected String _authLabel = null;
    protected String _authToken = null;

    protected boolean _presetsAreSet = false;
    protected Coordinates _presetCoordinates;
    protected int _presetFabricId;

    protected LiqidClientBase(final boolean secureHttp,
                              final String hostAddress,
                              final int portNumber,
                              final boolean ignoreCertificates,
                              final int timeoutInSeconds) {
        _secureHTTP = secureHttp;
        _hostAddress = hostAddress;
        _portNumber = portNumber;
        _ignoreCertificates = ignoreCertificates;
        _timeoutInSeconds = timeoutInSeconds;

        _scheme = secureHttp ? "https" : "http";
        _logger = LogManager.getLogger("LiqidSdk");

        _httpClient = HttpClient.newBuilder()
                                .version(HttpClient.Version.HTTP_1_1)
                                .followRedirects(HttpClient.Redirect.NORMAL)
                                .build();
    }

    protected String getFullPath(final String partialPath) {
        return _scheme + "://" + _hostAddress + ":" + _portNumber + API_BASE_PATH + "/" + partialPath;
    }

    protected Boolean getSecureHTTP() {
        return _secureHTTP;
    }

    protected String getHostAddress() {
        return _hostAddress;
    }

    protected Integer getPortNumber() {
        return _portNumber;
    }

    protected Boolean getIgnoreCertificates() {
        return _ignoreCertificates;
    }

    protected Integer getTimeoutInSeconds() {
        return _timeoutInSeconds;
    }

    protected enum BodyType {
        None,
        OctetStream,
        Json,
    }

    protected HttpResponse<?> send(String method,
                                   String partialPath,
                                   BodyType requestBodyType,
                                   Object body,
                                   BodyType responseBodyType)
        throws LiqidException {
        var fullPath = getFullPath(partialPath);
        var builder = HttpRequest.newBuilder()
                                 .uri(URI.create(fullPath))
                                 .timeout(Duration.ofSeconds(_timeoutInSeconds));

        if (method.equalsIgnoreCase("DELETE")) {
            builder.DELETE();
        } else if (method.equalsIgnoreCase("GET")) {
            builder.GET();
        } else {
            try {
                HttpRequest.BodyPublisher publisher = null;
                switch (requestBodyType) {
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
                throw new LiqidException("JSON exception", ex);
            }
        }

        applyAuthentication(builder);
        var httpRequest = builder.build();

        _logger.atTrace().log("===>{}:{}", method, fullPath);
        if (body != null) {
            _logger.atTrace().log("===>{}", body);
        }

        var responseHandler =
            switch (responseBodyType) {
                case None -> HttpResponse.BodyHandlers.discarding();
                case OctetStream -> HttpResponse.BodyHandlers.ofByteArray();
                case Json -> HttpResponse.BodyHandlers.ofString();
            };

        try {
            var response = _httpClient.send(httpRequest, responseHandler);
            _logger.atTrace().log("<==={}", response.toString());
            if (response.body() != null) {
                _logger.atTrace().log("<==={}", response.body().toString());
            }
            return response;
        } catch (InterruptedException | IOException ex) {
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
        _logger.atTrace().log("Entering {}", fn);

        if (!hasCredentials()) {
            var ex = new LiqidException("Credentials are not established");
            _logger.atTrace().withThrowable(ex).log("{} throwing Exception", fn);
            throw ex;
        }

        _base64Credentials = null;
        _logger.atTrace().log("{} returning", fn);
    }

    public void setCredentials(String username, String password) throws LiqidException {
        var fn = "setCredentials";
        _logger.atTrace().log("Entering {} username:{} password:{}", fn, username, "********");

        if (hasCredentials()) {
            var ex = new LiqidException("Credentials already established");
            _logger.atTrace().withThrowable(ex).log("{} throwing Exception", fn);
            throw ex;
        } else if (isLoggedIn()) {
            var ex = new LiqidException("Session is logged in");
            _logger.atTrace().withThrowable(ex).log("{} throwing Exception", fn);
            throw ex;
        }

        _base64Credentials = generateBasicAuthString(username, password);
        _logger.atTrace().log("{} returning", fn);
    }

    public boolean hasCredentials() {
        var fn = "hasCredentials";
        _logger.atTrace().log("Entering {}", fn);

        var result = _base64Credentials != null;
        _logger.atTrace().log("{} returning {}", fn, result);
        return result;
    }

    public void login(String label, String username, String password) throws LiqidException {
        var fn = "login";
        _logger.atTrace().log("Entering {} label:{} username:{} password:{}", fn, label, username, "********");

        if (hasCredentials()) {
            var ex = new LiqidException("Credentials already established");
            _logger.atTrace().withThrowable(ex).log("{} throwing Exception", fn);
            throw ex;
        } else if (isLoggedIn()) {
            var ex = new LiqidException("Session is logged in");
            _logger.atTrace().withThrowable(ex).log("{} throwing Exception", fn);
            throw ex;
        } else if (label.equalsIgnoreCase("slurm")) {
            var ex = new LiqidException("Permission denied");
            _logger.atTrace().withThrowable(ex).log("{} throwing Exception", fn);
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
        _logger.atTrace().log("{} returning", fn);
    }

    public void logout() throws LiqidException {
        var fn = "logout";
        _logger.atTrace().log("Entering {}", fn);

        if (!isLoggedIn()) {
            var ex = new LiqidException("Not logged in");
            _logger.atTrace().withThrowable(ex).log("{} throwing Exception", fn);
            throw ex;
        }

        _authToken = null;
        _authLabel = null;
        _logger.atTrace().log("{} returning", fn);
    }

    public boolean isLoggedIn() {
        var fn = "isLoggedIn";
        _logger.atTrace().log("Entering {}", fn);

        var result = _authToken != null;
        _logger.atTrace().log("{} returning {}", fn, result);
        return result;
    }

    protected void applyAuthentication(HttpRequest.Builder builder) {
        if (hasCredentials()) {
            builder.setHeader("Authorization", "Basic " + _base64Credentials);
        } else if (isLoggedIn()) {
            builder.setHeader("X-API-Key", _authToken);
        }
    }

    //  Hard-coded special cases -----------------------------------------------

    //  methods we need to invoke from this class, which must be implemented by the base class
    public abstract Integer getCurrentFabricId() throws LiqidException;

    public abstract Coordinates getDefaultCoordinates() throws LiqidException;

    public abstract Group createGroupWithId(Integer groupId, String groupName) throws LiqidException;

    public abstract DigestInfo createMessageDigest(String label) throws LiqidException;

    public abstract Integer getNextGroupId() throws LiqidException;

    public void checkParameterNotNull(Object value, String parameterName, String functionName) throws LiqidException {
        if (value == null) {
            throw new LiqidException(String.format("Parameter %s in %s() cannot be null", parameterName, functionName));
        }
    }

    public Group createGroup(String groupName) throws LiqidException {
        var fn = "createGroup";
        _logger.atTrace().log("Entering {} groupName:{}", fn, groupName);
        var groupId = getNextGroupId();
        return createGroupWithId(groupId, groupName);
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
    public final String deleteMessageDigest(String label) throws LiqidException {
        var fn = "deleteMessageDigest";
        _logger.atTrace().log("Entering {} label:{}", fn, label);

        if (label != null && label.equalsIgnoreCase("slurm")) {
            _logger.atError().log("Attempted to delete system label");
            throw new LiqidException("Cannot delete this label");
        }

        String result = null;
        try {
            var path = "digest";
            path += "/" + (label == null ? "" : label.toString());
            var httpResponse = send("DELETE", path, BodyType.None, null, BodyType.Json);
            var mapper = new ObjectMapper();
            var wrapper = mapper.readValue((String) httpResponse.body(), StringWrapper.class);
            wrapper.check();
            result = wrapper.getResponse().getData().getFirst();
        } catch (LiqidException ex) {
            _logger.atTrace().withThrowable(ex).log("{} throwing Exception", fn);
            throw ex;
        } catch (Exception ex) {
            _logger.atTrace().withThrowable(ex).log("{} rethrowing Exception", fn);
            throw new LiqidException("Caught exception:", ex);
        }
        _logger.atTrace().log("{} returning {}", fn, result);
        return result;
    }

    protected Machine enableP2PForMachine(Integer machineId, Boolean enabledFlag) throws LiqidException {
        var fn = "enableP2PForMachine";
        _logger.atTrace().log("Entering {} machineId:{} enabledFlag:{}", fn, machineId, enabledFlag);

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
            _logger.atTrace().withThrowable(ex).log("{} throwing Exception", fn);
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
            _logger.atTrace().log("{} returning {}", fn, result);
            return result;
        } catch (Exception cex) {
            var ex = new LiqidException("Caught exception", cex);
            _logger.atTrace().withThrowable(ex).log("{} throwing Exception", fn);
            throw ex;
        }
    }
}
