// File: LiqidClientMock.java
//
// Copyright (c) 2024 Liqid, Inc. All rights reserved.
// 
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.mock;

import com.liqid.sdk.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * This is not an exact mock, but it should be sufficient for casual use.
 * WARNING: NONE OF THIS IS THREAD-SAFE.
 * Neither is the SDK, so we don't worry too much about that, bue client beware!
 */
public class MockLiqidClient extends LiqidClient {

    public static final Integer DEFAULT_FABRIC_IDENTIFIER = 21;
    public static final Integer DEFAULT_RACK_COORDINATE = 0;
    public static final Integer DEFAULT_SHELF_COORDINATE = 1;
    public static final Integer DEFAULT_NODE_COORDINATE = 2;
    public static final Integer DEFAULT_POD_IDENTIFIER = -1;

    // inventory
    private final Map<Integer, MockDevice> _devices = new HashMap<>();
    private final Map<Integer, MockGroup> _groups = new HashMap<>();
    private final Map<Integer, MockMachine> _machines = new HashMap<>();
    private final Set<Integer> _systemFreePool = new HashSet<>();
    private final Set<Integer> _devicesToBeAdded = new HashSet<>();
    private final Set<Integer> _devicesToBeRemoved = new HashSet<>();
    private final Map<String, MockDevice> _devicesByName = new HashMap<>();

    private final Set<Integer> _computeDeviceIds = new TreeSet<>();
    private final Set<Integer> _fpgaDeviceIds = new TreeSet<>();
    private final Set<Integer> _gpuDeviceIds = new TreeSet<>();
    private final Set<Integer> _memoryDeviceIds = new TreeSet<>();
    private final Set<Integer> _networkDeviceIds = new TreeSet<>();
    private final Set<Integer> _storageDeviceIds = new TreeSet<>();

    // unchanging
    private final MockCoordinates _coordinates;
    private final Integer _fabricIdentifier;
    private final Integer _podIdentifier;
    private final Random _random = new Random(System.currentTimeMillis());

    // can change over time
    private MockCoordinates _defaultCoordinates;
    private Integer _groupBeingEdited = null;
    private Integer _machineBeingEdited = null;
    private SSHStatus _sshStatus;
    private final Map<String, DigestInfo> _digestTable = new HashMap<>();

    private MockLiqidClient(
        final Integer fabricIdentifier,
        final MockCoordinates coordinates,
        final Integer podIdentifier,
        final SSHStatus sshStatus
    ) {
        super(false,
              "mock",
              0,
              true,
              60,
              false,
              0,
              0,
              false,
              0);
        _fabricIdentifier = fabricIdentifier;
        _coordinates = coordinates;
        _defaultCoordinates = coordinates;
        _podIdentifier = podIdentifier;
        _sshStatus = sshStatus;
    }

    @Override
    public String toString() {
        return "LiqidClientMock";
    }

    public static class Builder {

        private MockCoordinates _coordinates = new MockCoordinates(DEFAULT_RACK_COORDINATE,
                                                                   DEFAULT_SHELF_COORDINATE,
                                                                   DEFAULT_NODE_COORDINATE);
        private int _fabricIdentifier = DEFAULT_FABRIC_IDENTIFIER;
        private int _podIdentifier = DEFAULT_POD_IDENTIFIER;
        private SSHStatus _sshStatus = new MockSSHStatus(false, false);

        public Builder setCoordinates(
            final int rack,
            final int shelf,
            final int node
        ) {
            _coordinates = new MockCoordinates(rack, shelf, node);
            return this;
        }

        public Builder setFabricIdentifier(final int value) { _fabricIdentifier = value; return this; }
        public Builder setPodIdentifier(final int value) { _podIdentifier = value; return this; }

        public Builder setSSHStatus(
            final boolean enabled,
            final boolean active
        ) {
            _sshStatus = new MockSSHStatus(enabled, active);
            return this;
        }

        public MockLiqidClient build() {
            return new MockLiqidClient(_fabricIdentifier,
                                       _coordinates,
                                       _podIdentifier,
                                       _sshStatus);
        }
    }

    /**
     * Create a MockLiqidClient which contains the general inventory of an existing LiqidClient
     * (which may be another mock, or it may be real).
     * @param client source client
     * @return new MockLiqidClient
     * @throws LiqidException if something goes wrong while communicating with the Liqid Cluster
     * (unless source client is another mock)
     */
    public static MockLiqidClient createFrom(
        final LiqidClient client
    ) throws LiqidException {
        // get everything we need from the client before populating anything
        var devStatusList = client.getAllDevicesStatus();
        var devInfoList = client.getAllDeviceInfos();
        var groups = client.getGroups();
        var machines = client.getMachines();
        var fabricIdentifier = client.getCurrentFabricId();
        var coordinates = client.getDefaultCoordinates();
        var sshStatus = client.getSSHStatus();

        var devInfoMap =
            devInfoList.stream()
                       .collect(Collectors.toMap(DeviceInfo::getDeviceIdentifier, di -> di, (a, b) -> b, HashMap::new));

        var mock = new MockLiqidClient(fabricIdentifier, new MockCoordinates(coordinates), -1, sshStatus);

        // populate devices
        for (var devStat : devStatusList) {
            var devId = devStat.getDeviceId();
            var mockDev = new MockDevice(devStat, devInfoMap.get(devId));
            mock._devices.put(devId, mockDev);
            mock._devicesByName.put(devStat.getName(), mockDev);
            mock._systemFreePool.add(devId);
            switch (mockDev.getDeviceType()) {
                case COMPUTE -> mock._computeDeviceIds.add(devId);
                case FPGA -> mock._fpgaDeviceIds.add(devId);
                case GPU -> mock._gpuDeviceIds.add(devId);
                case MEMORY -> mock._memoryDeviceIds.add(devId);
                case ETHERNET_LINK, FIBER_CHANNEL_LINK, INFINIBAND_LINK -> mock._networkDeviceIds.add(devId);
                case SSD -> mock._storageDeviceIds.add(devId);
            }
        }

        // populate groups
        for (var group : groups) {
            var mockGroup = new MockGroup(group);
            mock._groups.put(mockGroup.getGroupId(), mockGroup);

            var preDevs = client.getPreDevices(null, group.getGroupId(), null);
            for (var preDev : preDevs) {
                var mockDev = mock._devicesByName.get(preDev.getDeviceName());
                mock._systemFreePool.remove(mockDev.getDeviceId());
                mockGroup._freePool.put(mockDev.getDeviceId(), mockDev);
            }
        }

        // populate machines
        for (var machine : machines) {
            var mockMachine = new MockMachine(machine);
            mock._machines.put(mockMachine.getMachineId(), mockMachine);

            var mockGroup = mock._groups.get(mockMachine.getGroupId());
            mockGroup._machines.put(mockMachine.getMachineId(), mockMachine);

            var preDevs = client.getPreDevices(null, mockGroup.getGroupId(), machine.getMachineId());
            for (var preDev : preDevs) {
                var mockDev = mock._devicesByName.get(preDev.getDeviceName());
                mockGroup._freePool.remove(mockDev.getDeviceId()); // should not be there, but just in case...
                mockMachine._attachedDevices.put(mockDev.getDeviceId(), mockDev);
            }
        }

        return mock;
    }

    // mock-specific methods

    /**
     * Creates a mock device for the mock client, and injects it into the inventory of this mock client
     * placing it into the system free pool.
     * @param deviceType type of the devies. for network devices, we really only want ETHERNET
     * @param pciVendorId value for PCI vendor id
     * @param vendor name of the vendor
     * @param model name of the model
     * @return device id of the created mock device.
     */
    public Integer createDevice(
        final DeviceType deviceType,
        final short pciVendorId,
        final String vendor,
        final String model
    ) {
        var fn = "createDevice";
        _logger.trace("Entering %s with devType=%s pciVendorId=0x%04x vendor=%s model=%s",
                      fn, deviceType, pciVendorId, vendor, model);

        MockDevice mockDevice = switch (deviceType) {
            case COMPUTE -> createComputeMockDevice();
            case FPGA -> createFPGAMockDevice();
            case GPU -> createGPUMockDevice();
            case MEMORY -> createMemoryMockDevice();
            case ETHERNET_LINK, FIBER_CHANNEL_LINK, INFINIBAND_LINK -> createNetworkMockDevice();
            case SSD -> createStorageMockDevice();
        };

        var deviceStatus = mockDevice.getDeviceStatus();
        deviceStatus.setDeviceType(deviceType);
        deviceStatus.setFabricType(FabricType.GEN4.getValue());
        deviceStatus.setFabricId(_fabricIdentifier);
        deviceStatus.setPodId(_podIdentifier);

        var deviceInfo = mockDevice.getDeviceInfo();
        deviceInfo.setDeviceInfoType(deviceType);
        deviceInfo.setFabricType(FabricType.GEN4);
        deviceInfo.setPodId(_podIdentifier);
        deviceInfo.setPCIVendorId(String.format("0x%04x", pciVendorId));
        deviceInfo.setVendor(vendor);
        deviceInfo.setModel(model);
        deviceInfo.setUserDescription("n/a");

        var devId = mockDevice.getDeviceId();
        _logger.trace("%s returning 0x%08x", fn, devId);
        return devId;
    }

    /**
     * A convenient wrapper around createDevice() - for creating a number of devices in one call.
     * @param deviceType type of the devies. for network devices, we really only want ETHERNET
     * @param pciVendorId value for PCI vendor id
     * @param vendor name of the vendor
     * @param model name of the model
     * @param count number of devices to be created
     * @return collection of the device identifiers of the newly-created mock devices
     */
    public Collection<Integer> createDevices(
        final DeviceType deviceType,
        final short pciVendorId,
        final String vendor,
        final String model,
        final int count
    ) {
        var fn = "createDevices";
        _logger.trace("Entering %s with devType=%s pciVendorId=0x%04x vendor=%s model=%s count=%d",
                      fn, deviceType, pciVendorId, vendor, model, count);

        var deviceIds = new LinkedList<Integer>();
        for (var dx = 0; dx < count; dx++) {
            deviceIds.add(createDevice(deviceType, pciVendorId, vendor, model));
        }

        _logger.trace("%s returning %s", fn, deviceIds);
        return deviceIds;
    }

    // private helpful functions

    private void checkCoordinates() throws WrongCoordinatesException {
        var ex = new WrongCoordinatesException(_defaultCoordinates, _coordinates);
        _logger.throwing(ex);
        throw ex;
    }

    private void checkDeviceExists(
        final int deviceId
    ) throws LiqidException {
        if (!_devices.containsKey(deviceId)) {
            var ex = new LiqidException(String.format("Device 0x%08x does not exist", deviceId));
            _logger.throwing(ex);
            throw ex;
        }
    }

    private void checkDeviceType(
        final MockDevice device,
        final DeviceType type
    ) throws LiqidException {
        assert(device != null);
        assert(type != null);

        if (!device.getDeviceType().equals(type)) {
            var ex = new LiqidException(String.format("Device 0x%08x is not of type %s", device.getDeviceId(), type));
            _logger.throwing(ex);
            throw ex;
        }
    }

    private void checkGroupExists(
        final int groupId
    ) throws LiqidException {
        if (!_groups.containsKey(groupId)) {
            var ex = new LiqidException(String.format("Group 0x%08x does not exist", groupId));
            _logger.throwing(ex);
            throw ex;
        }
    }

    private void checkGroupIdentifier(
        final int groupId
    ) throws LiqidException {
        if (!isValidGroupIdentifier(groupId)) {
            var ex = new LiqidException("Group ID must be greater than 0x0");
            _logger.throwing(ex);
            throw ex;
        }
    }

    private void checkGroupInEditMode(
        final int groupId
    ) throws LiqidException {
        if (!Objects.equals(_groupBeingEdited, groupId)) {
            var msg = String.format("Group 0x%08x is not in edit mode", groupId);
            var ex = new LiqidException(msg);
            _logger.throwing(ex);
            throw ex;
        }
    }

    private void checkMachineExists(
        final int machineId
    ) throws LiqidException {
        if (!_machines.containsKey(machineId)) {
            var ex = new LiqidException(String.format("Machine 0x%08x does not exist", machineId));
            _logger.throwing(ex);
            throw ex;
        }
    }

    private void checkMachineIdentifier(
        final int machineId
    ) throws LiqidException {
        if (!isValidMachineIdentifier(machineId)) {
            var ex = new LiqidException("Machine ID must be greater than 0x0 and less than 0x4000 (16384)");
            _logger.throwing(ex);
            throw ex;
        }
    }

    private void checkMachineInEditMode(
        final int machineId
    ) throws LiqidException {
        if (!Objects.equals(_machineBeingEdited, machineId)) {
            var msg = String.format("Machine 0x%08x is not in edit mode", machineId);
            var ex = new LiqidException(msg);
            _logger.throwing(ex);
            throw ex;
        }
    }

    private MockDevice createComputeMockDevice() {
        var fn = "createComputeMockDevice";
        _logger.trace("Entering %s", fn);

        var index = _computeDeviceIds.size();
        var deviceId = index;
        var deviceIdStr = String.format("0x%08x", deviceId);
        _computeDeviceIds.add(deviceId);
        var deviceName = String.format("cpu%d", deviceId);

        var deviceStatus = new ComputeDeviceStatus.Builder().setDeviceId(deviceIdStr)
                                                            .setName(deviceName)
                                                            .setIndex(index)
                                                            .build();

        var deviceInfo = new ComputeDeviceInfo.Builder().setDeviceIdentifier(deviceIdStr)
                                                        .setName(deviceName)
                                                        .setIndex(index)
                                                        .build();

        var mockDevice = new MockDevice(deviceStatus, deviceInfo);
        _devices.put(deviceId, mockDevice);
        _devicesByName.put(deviceName, mockDevice);
        _logger.trace("%s returning %s", fn, mockDevice);
        return mockDevice;
    }

    private MockDevice createFPGAMockDevice() {
        var fn = "createFPGAMockDevice";
        _logger.trace("Entering %s", fn);

        var index = _fpgaDeviceIds.size();
        var deviceId = 0x1000 + index;
        var deviceIdStr = String.format("0x%08x", deviceId);
        _fpgaDeviceIds.add(deviceId);
        var deviceName = String.format("fpga%d", deviceId);

        var deviceStatus = new FPGADeviceStatus.Builder().setDeviceId(deviceIdStr)
                                                         .setName(deviceName)
                                                         .setIndex(index)
                                                         .build();

        var deviceInfo = new FPGADeviceInfo.Builder().setDeviceIdentifier(deviceIdStr)
                                                     .setName(deviceName)
                                                     .setIndex(index)
                                                     .build();

        var mockDevice = new MockDevice(deviceStatus, deviceInfo);
        _devices.put(deviceId, mockDevice);
        _devicesByName.put(deviceName, mockDevice);

        _logger.trace("%s returning %s", fn, mockDevice);
        return mockDevice;
    }

    private MockDevice createGPUMockDevice() {
        var fn = "createGPUMockDevice";
        _logger.trace("Entering %s", fn);

        var index = _gpuDeviceIds.size();
        var deviceId = 0x2000 + index;
        var deviceIdStr = String.format("0x%08x", deviceId);
        _gpuDeviceIds.add(deviceId);
        var deviceName = String.format("gpu%d", deviceId);

        var deviceStatus = new GPUDeviceStatus.Builder().setDeviceId(deviceIdStr)
                                                        .setName(deviceName)
                                                        .setIndex(index)
                                                        .build();

        var deviceInfo = new GPUDeviceInfo.Builder().setDeviceIdentifier(deviceIdStr)
                                                    .setName(deviceName)
                                                    .setIndex(index)
                                                    .build();

        var mockDevice = new MockDevice(deviceStatus, deviceInfo);
        _devices.put(deviceId, mockDevice);
        _devicesByName.put(deviceName, mockDevice);

        _logger.trace("%s returning %s", fn, mockDevice);
        return mockDevice;
    }

    private MockDevice createMemoryMockDevice() {
        var fn = "createMemoryMockDevice";
        _logger.trace("Entering %s", fn);

        var index = _memoryDeviceIds.size();
        var deviceId = 0x3000 + index;
        var deviceIdStr = String.format("0x%08x", deviceId);
        _memoryDeviceIds.add(deviceId);
        var deviceName = String.format("mem%d", deviceId);

        var deviceStatus = new MemoryDeviceStatus.Builder().setDeviceId(deviceIdStr)
                                                           .setName(deviceName)
                                                           .setIndex(index)
                                                           .build();

        var deviceInfo = new MemoryDeviceInfo.Builder().setDeviceIdentifier(deviceIdStr)
                                                       .setName(deviceName)
                                                       .setIndex(index)
                                                       .build();

        var mockDevice = new MockDevice(deviceStatus, deviceInfo);
        _devices.put(deviceId, mockDevice);
        _devicesByName.put(deviceName, mockDevice);

        _logger.trace("%s returning %s", fn, mockDevice);
        return mockDevice;
    }

    private MockDevice createNetworkMockDevice() {
        var fn = "createNetworkMockDevice";
        _logger.trace("Entering %s", fn);

        var index = _networkDeviceIds.size();
        var deviceId = 0x4000 + index;
        var deviceIdStr = String.format("0x%08x", deviceId);
        _networkDeviceIds.add(deviceId);
        var deviceName = String.format("nic%d", deviceId);

        var deviceStatus = new NetworkDeviceStatus.Builder().setDeviceId(deviceIdStr)
                                                            .setName(deviceName)
                                                            .setIndex(index)
                                                            .build();

        var deviceInfo = new MemoryDeviceInfo.Builder().setDeviceIdentifier(deviceIdStr)
                                                       .setName(deviceName)
                                                       .setIndex(index)
                                                       .build();

        var mockDevice = new MockDevice(deviceStatus, deviceInfo);
        _devices.put(deviceId, mockDevice);
        _devicesByName.put(deviceName, mockDevice);

        _logger.trace("%s returning %s", fn, mockDevice);
        return mockDevice;
    }

    private MockDevice createStorageMockDevice() {
        var fn = "createStorageMockDevice";
        _logger.trace("Entering %s", fn);

        var index = _storageDeviceIds.size();
        var deviceId = 0x5000 + index;
        var deviceIdStr = String.format("0x%08x", deviceId);
        _storageDeviceIds.add(deviceId);
        var deviceName = String.format("targ%d", deviceId);

        var deviceStatus = new StorageDeviceStatus.Builder().setDeviceId(deviceIdStr)
                                                            .setName(deviceName)
                                                            .setIndex(index)
                                                            .build();

        var deviceInfo = new StorageDeviceInfo.Builder().setDeviceIdentifier(deviceIdStr)
                                                        .setName(deviceName)
                                                        .setIndex(index)
                                                        .build();

        var mockDevice = new MockDevice(deviceStatus, deviceInfo);
        _devices.put(deviceId, mockDevice);
        _devicesByName.put(deviceName, mockDevice);

        _logger.trace("%s returning %s", fn, mockDevice);
        return mockDevice;
    }

    private PreDevice createPreDevice(
        final MockDevice device
    ) {
        var preDevType =
            switch (device.getDeviceType()) {
                case INFINIBAND_LINK -> PreDeviceType.INFINIBAND;
                case ETHERNET_LINK -> PreDeviceType.LINK;
                case FIBER_CHANNEL_LINK -> PreDeviceType.FIBER_CHANNEL;
                case GPU -> PreDeviceType.GPU;
                case SSD -> PreDeviceType.TARGET;
                case FPGA -> PreDeviceType.FPGA;
                case COMPUTE -> PreDeviceType.COMPUTE;
                case MEMORY -> PreDeviceType.MEMORY;
            };


        var groupId = 0;
        var machineId = 0;
        for (var group : _groups.values()) {
            if (group._freePool.containsKey(device.getDeviceId())) {
                groupId = group.getGroupId();
                break;
            }

            for (var machine : group._machines.values()) {
                if (machine._attachedDevices.containsKey(device.getDeviceId())) {
                    groupId = group.getGroupId();
                    machineId = machine.getMachineId();
                    break;
                }
            }
        }
        var machineStr = machineId > 0 ? String.format("0x%08x", machineId) : "n/a";

        return new PreDevice.Builder().setDeviceName(device.getDeviceStatus().getName())
                                      .setFabricGlobalId("0x0")
                                      .setFabricId(_fabricIdentifier)
                                      .setGroupId(groupId)
                                      .setMachineId(machineStr)
                                      .setPreDeviceType(preDevType)
                                      .setPodId(_podIdentifier)
                                      .build();
    }

    private Integer[] getDeviceRelation(
        final int deviceId
    ) {
        // result is a two=value array as such:
        //  [0] is null if the device is in the system free pool, or
        //      contains the group id which contains the device, while
        //  [1] is null if the device is in the system or a group free pool, or
        //      contains the machine id to which the device is attached.
        var result = new Integer[]{ null, null };
        if (!_systemFreePool.contains(deviceId)) {
            for (var group : _groups.values()) {
                if (group._freePool.containsKey(deviceId)) {
                    result[0] = group.getGroupId();
                    break;
                } else {
                    for (var machine : group._machines.values()) {
                        if (machine._attachedDevices.containsKey(deviceId)) {
                            result[0] = group.getGroupId();
                            result[1] = machine.getMachineId();
                            break;
                        }
                    }
                    if (result[0] != null) {
                        break;
                    }
                }
            }
        }
        return result;
    }

    private MockDevice getMockDeviceByName(
        final String deviceName
    ) throws LiqidException {
        if (!_devicesByName.containsKey(deviceName)) {
            var ex = new LiqidException(String.format("Device '%s' does not exist", deviceName));
            _logger.throwing(ex);
            throw ex;
        }

        return _devicesByName.get(deviceName);
    }

    private void injectDevice(
        final MockDevice device
    ) {
        int devId = 0;
        int index = 0;
        String name = null;
        switch (device.getDeviceType()) {
            case INFINIBAND_LINK, ETHERNET_LINK, FIBER_CHANNEL_LINK -> {
                index = _networkDeviceIds.size();
                devId = index;
                name = String.format("net%d", index);
                _networkDeviceIds.add(index);
            }
            case GPU -> {
                index = _gpuDeviceIds.size();
                devId = 0x1000 + index;
                name = String.format("gpu%d", index);
                _gpuDeviceIds.add(index);
            }
            case SSD -> {
                index = _storageDeviceIds.size();
                devId = 0x2000 + index;
                name = String.format("targ%d", index);
                _storageDeviceIds.add(index);
            }
            case FPGA -> {
                index = _fpgaDeviceIds.size();
                devId = 0x3000 + index;
                name = String.format("fpga%d", index);
                _fpgaDeviceIds.add(index);
            }
            case COMPUTE -> {
                index = _computeDeviceIds.size();
                devId = 0x4000 + index;
                name = String.format("cpu%d", index);
                _computeDeviceIds.add(index);
            }
            case MEMORY -> {
                index = _memoryDeviceIds.size();
                devId = 0x5000 + index;
                name = String.format("mem%d", index);
                _memoryDeviceIds.add(index);
            }
        };

        var stat = device.getDeviceStatus();
        stat.setDeviceId(devId);
        stat.setIndex(index);
        stat.setName(name);
        stat.setFabricId(_fabricIdentifier);
        stat.setFabricType(FabricType.GEN4.getValue());
        stat.setPodId(_podIdentifier);

        var info = device.getDeviceInfo();
        info.setDeviceIdentifier(devId);
        info.setIndex(index);
        info.setName(name);
        info.setPodId(_podIdentifier);
        info.setFabricType(FabricType.GEN4);

        _devices.put(device.getDeviceId(), device);
    }

    private boolean isValidGroupIdentifier(
        final int groupId
    ) {
        return groupId > 0;
    }

    private boolean isValidMachineIdentifier(
        final int machineId
    ) {
        return (machineId > 0) && (machineId < 16384);
    }

    private void queueAddDeviceToGroup(
        final int deviceId,
        final int groupId
    ) throws LiqidException {
        var fn = "queueAddDeviceToGroup";
        _logger.trace("Entering %s with devId=0x%08x grpId=0x%08x", fn, deviceId, groupId);

        checkDeviceExists(deviceId);
        checkGroupExists(groupId);
        checkGroupInEditMode(groupId);

        if (!_systemFreePool.contains(deviceId)) {
            var msg = String.format("Device 0x%08x is not in the system free pool", deviceId);
            var ex = new LiqidException(msg);
            _logger.throwing(ex);
            throw ex;
        }

        _logger.info(String.format("Queueing adding device 0x%08x to group 0x%08x", deviceId, groupId));
        _devicesToBeAdded.add(deviceId);
        _logger.trace("%s returning", fn);
    }

    private void queueAddDeviceToMachine(
        final int deviceId,
        final int groupId,
        final int machineId
    ) throws LiqidException {
        var fn = "queueAddDeviceToMachine";
        _logger.trace("Entering %s with devId=0x%08x grpId=0x%08x machId=0x%08x",
                      fn, deviceId, groupId, machineId);

        checkDeviceExists(deviceId);
        checkGroupExists(groupId);
        checkMachineExists(machineId);
        checkMachineInEditMode(machineId);

        var group = _groups.get(groupId);
        if (!group._freePool.containsKey(deviceId)) {
            var msg = String.format("Device 0x%08x is not in group 0x%08x free pool", deviceId, groupId);
            var ex = new LiqidException(msg);
            _logger.throwing(ex);
            throw ex;
        }

        _logger.info(String.format("Queueing adding device 0x%08x to machine 0x%08x", deviceId, machineId));
        _devicesToBeAdded.add(deviceId);
        _logger.trace("%s returning", fn);
    }

    private void queueRemoveDeviceFromGroup(
        final int deviceId,
        final int groupId
    ) throws LiqidException {
        var fn = "queueRemoveDeviceFromGroup";
        _logger.trace("Entering %s with devId=0x%08x grpId=0x%08x", fn, deviceId, groupId);

        checkGroupExists(groupId);
        checkGroupInEditMode(groupId);

        var group = _groups.get(groupId);
        if (!group._freePool.containsKey(groupId)) {
            var msg = String.format("Device 0x%08x is not in group 0x%08x free pool", deviceId, groupId);
            var ex = new LiqidException(msg);
            _logger.throwing(ex);
            throw ex;
        }

        _devicesToBeRemoved.add(deviceId);
        _logger.info(String.format("Queueing removing device 0x%08x from group 0x%08x", deviceId, groupId));
        _logger.trace("%s returning", fn);
    }

    private void queueRemoveDeviceFromMachine(
        final int deviceId,
        final int machineId
    ) throws LiqidException {
        var fn = "queueRemoveDeviceFromMachine";
        _logger.trace("Entering %s with dev=0x%08x mach=0x%08x", fn, deviceId, machineId);

        checkDeviceExists(deviceId);
        checkMachineExists(machineId);
        checkMachineInEditMode(machineId);

        var machine = _machines.get(machineId);
        if (!machine._attachedDevices.containsKey(deviceId)) {
            var msg = String.format("Machine 0x%08x does not contain device 0x%08x", machineId, deviceId);
            var ex = new LiqidException(msg);
            _logger.throwing(ex);
            throw ex;
        }

        _devicesToBeRemoved.add(deviceId);
        _logger.info(String.format("Queueing removing device 0x%08x from machine 0x%08x", deviceId, machineId));
        _logger.trace("%s returning", fn);
    }

    // mocked API methods

    @Override
    public GroupComputeDeviceRelator addComputeDeviceToGroup(
        Integer deviceId,
        Integer groupId
    ) throws LiqidException {
        var fn = "addComputeDeviceToGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkCoordinates();

        var device = _devices.get(deviceId);
        var group = _groups.get(groupId);
        checkDeviceType(device, DeviceType.COMPUTE);

        queueAddDeviceToGroup(deviceId, groupId);

        var groupRel = new GroupComputeDeviceRelator.Builder().setGroup(group)
                                                              .setDeviceStatus(device.getComputeDeviceStatus())
                                                              .build();
        _logger.trace("%s returning %s", groupRel);
        return groupRel;
    }

    @Override
    public MachineComputeDeviceRelator addComputeDeviceToMachine(
        Integer deviceId,
        Integer groupId,
        Integer machineId
    ) throws LiqidException {
        var fn = "addComputeDeviceToMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);
        checkCoordinates();

        var device = _devices.get(deviceId);
        var group = _groups.get(groupId);
        var machine = _machines.get(machineId);
        checkDeviceType(device, DeviceType.COMPUTE);

        queueAddDeviceToMachine(deviceId, groupId, machineId);

        var groupRel = new GroupComputeDeviceRelator.Builder().setGroup(group)
                                                              .setDeviceStatus(device.getComputeDeviceStatus())
                                                              .build();
        var machineRel = new MachineComputeDeviceRelator.Builder().setMachine(machine)
                                                                  .setGroupDeviceRelator(groupRel)
                                                                  .build();

        _logger.trace("%s returning %s", machineRel);
        return machineRel;
    }

    @Override
    public GroupFPGADeviceRelator addFPGADeviceToGroup(
        Integer deviceId,
        Integer groupId
    ) throws LiqidException {
        var fn = "addFPGADeviceToGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkCoordinates();

        var device = _devices.get(deviceId);
        var group = _groups.get(groupId);
        checkDeviceType(device, DeviceType.FPGA);
        queueAddDeviceToGroup(deviceId, groupId);

        var groupRel = new GroupFPGADeviceRelator.Builder().setGroup(group)
                                                           .setDeviceStatus(device.getFPGADeviceStatus())
                                                           .build();
        _logger.trace("%s returning %s", groupRel);
        return groupRel;
    }

    @Override
    public MachineFPGADeviceRelator addFPGADeviceToMachine(
        Integer deviceId,
        Integer groupId,
        Integer machineId
    ) throws LiqidException {
        var fn = "addFPGADeviceToMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);
        checkCoordinates();

        var device = _devices.get(deviceId);
        var group = _groups.get(groupId);
        var machine = _machines.get(machineId);
        checkDeviceType(device, DeviceType.FPGA);

        queueAddDeviceToMachine(deviceId, groupId, machineId);

        var groupRel = new GroupFPGADeviceRelator.Builder().setGroup(group)
                                                           .setDeviceStatus(device.getFPGADeviceStatus())
                                                           .build();
        var machineRel = new MachineFPGADeviceRelator.Builder().setMachine(machine)
                                                               .setGroupDeviceRelator(groupRel)
                                                               .build();

        _logger.trace("%s returning %s", machineRel);
        return machineRel;
    }

    @Override
    public GroupGPUDeviceRelator addGPUDeviceToGroup(
        Integer deviceId,
        Integer groupId
    ) throws LiqidException {
        var fn = "addGPUDeviceToGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkCoordinates();

        var device = _devices.get(deviceId);
        var group = _groups.get(groupId);
        checkDeviceType(device, DeviceType.GPU);
        queueAddDeviceToGroup(deviceId, groupId);

        var groupRel = new GroupGPUDeviceRelator.Builder().setGroup(group)
                                                          .setDeviceStatus(device.getGPUDeviceStatus())
                                                          .build();
        _logger.trace("%s returning %s", groupRel);
        return groupRel;
    }

    @Override
    public MachineGPUDeviceRelator addGPUDeviceToMachine(
        Integer deviceId,
        Integer groupId,
        Integer machineId
    ) throws LiqidException {
        var fn = "addGPUDeviceToMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);
        checkCoordinates();

        var device = _devices.get(deviceId);
        var group = _groups.get(groupId);
        var machine = _machines.get(machineId);
        checkDeviceType(device, DeviceType.GPU);

        queueAddDeviceToMachine(deviceId, groupId, machineId);

        var groupRel = new GroupGPUDeviceRelator.Builder().setGroup(group)
                                                          .setDeviceStatus(device.getGPUDeviceStatus())
                                                          .build();
        var machineRel = new MachineGPUDeviceRelator.Builder().setMachine(machine)
                                                              .setGroupDeviceRelator(groupRel)
                                                              .build();

        _logger.trace("%s returning %s", machineRel);
        return machineRel;
    }

    @Override
    public GroupMemoryDeviceRelator addMemoryDeviceToGroup(
        Integer deviceId,
        Integer groupId
    ) throws LiqidException {
        var fn = "addMemoryDeviceToGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkCoordinates();

        var device = _devices.get(deviceId);
        var group = _groups.get(groupId);
        checkDeviceType(device, DeviceType.GPU);
        queueAddDeviceToGroup(deviceId, groupId);

        var groupRel = new GroupMemoryDeviceRelator.Builder().setGroup(group)
                                                             .setDeviceStatus(device.getMemoryDeviceStatus())
                                                             .build();
        _logger.trace("%s returning %s", groupRel);
        return groupRel;
    }

    @Override
    public MachineMemoryDeviceRelator addMemoryDeviceToMachine(
        Integer deviceId,
        Integer groupId,
        Integer machineId
    ) throws LiqidException {
        var fn = "addMemoryDeviceToMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);
        checkCoordinates();

        var device = _devices.get(deviceId);
        var group = _groups.get(groupId);
        var machine = _machines.get(machineId);
        checkDeviceType(device, DeviceType.MEMORY);

        queueAddDeviceToMachine(deviceId, groupId, machineId);

        var groupRel = new GroupMemoryDeviceRelator.Builder().setGroup(group)
                                                             .setDeviceStatus(device.getMemoryDeviceStatus())
                                                             .build();
        var machineRel = new MachineMemoryDeviceRelator.Builder().setMachine(machine)
                                                                 .setGroupDeviceRelator(groupRel)
                                                                 .build();

        _logger.trace("%s returning %s", machineRel);
        return machineRel;
    }

    @Override
    public GroupNetworkDeviceRelator addNetworkDeviceToGroup(
        Integer deviceId,
        Integer groupId
    ) throws LiqidException {
        var fn = "addNetworkDeviceToGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkCoordinates();

        var device = _devices.get(deviceId);
        var group = _groups.get(groupId);
        checkDeviceType(device, DeviceType.ETHERNET_LINK);
        queueAddDeviceToGroup(deviceId, groupId);

        var groupRel = new GroupNetworkDeviceRelator.Builder().setGroup(group)
                                                              .setDeviceStatus(device.getNetworkDeviceStatus())
                                                              .build();
        _logger.trace("%s returning %s", groupRel);
        return groupRel;
    }

    @Override
    public MachineNetworkDeviceRelator addNetworkDeviceToMachine(
        Integer deviceId,
        Integer groupId,
        Integer machineId
    ) throws LiqidException {
        var fn = "addNetworkDeviceToMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);
        checkCoordinates();

        var device = _devices.get(deviceId);
        var group = _groups.get(groupId);
        var machine = _machines.get(machineId);
        checkDeviceType(device, DeviceType.ETHERNET_LINK);

        queueAddDeviceToMachine(deviceId, groupId, machineId);

        var groupRel = new GroupNetworkDeviceRelator.Builder().setGroup(group)
                                                              .setDeviceStatus(device.getNetworkDeviceStatus())
                                                              .build();
        var machineRel = new MachineNetworkDeviceRelator.Builder().setMachine(machine)
                                                                  .setGroupDeviceRelator(groupRel)
                                                                  .build();

        _logger.trace("%s returning %s", machineRel);
        return machineRel;
    }

    @Override
    public GroupStorageDeviceRelator addStorageDeviceToGroup(
        Integer deviceId,
        Integer groupId
    ) throws LiqidException {
        var fn = "addStorageDeviceToGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkCoordinates();

        var device = _devices.get(deviceId);
        var group = _groups.get(groupId);
        checkDeviceType(device, DeviceType.GPU);
        queueAddDeviceToGroup(deviceId, groupId);

        var groupRel = new GroupStorageDeviceRelator.Builder().setGroup(group)
                                                              .setDeviceStatus(device.getStorageDeviceStatus())
                                                              .build();
        _logger.trace("%s returning %s", groupRel);
        return groupRel;
    }

    @Override
    public MachineStorageDeviceRelator addStorageDeviceToMachine(
        Integer deviceId,
        Integer groupId,
        Integer machineId
    ) throws LiqidException {
        var fn = "addStorageDeviceToMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);
        checkCoordinates();

        var device = _devices.get(deviceId);
        var group = _groups.get(groupId);
        var machine = _machines.get(machineId);
        checkDeviceType(device, DeviceType.SSD);

        queueAddDeviceToMachine(deviceId, groupId, machineId);

        var groupRel = new GroupStorageDeviceRelator.Builder().setGroup(group)
                                                              .setDeviceStatus(device.getStorageDeviceStatus())
                                                              .build();
        var machineRel = new MachineStorageDeviceRelator.Builder().setMachine(machine)
                                                                  .setGroupDeviceRelator(groupRel)
                                                                  .build();

        _logger.trace("%s returning %s", machineRel);
        return machineRel;
    }

    @Override
    public FabricToggleComposite applyFabricChanges(
        String flagName,
        String flagValue,
        FabricToggleCompositeOption operation
    ) throws LiqidException {
        var fn = "applyFabricChanges";
        _logger.trace("Entering %s flagName:%s flagValue:%s operation:%s", fn, flagName, flagValue, operation);
        checkParameterNotNull(flagName, "flagName", fn);
        checkParameterNotNull(flagValue, "flagValue", fn);
        checkParameterNotNull(operation, "operation", fn);
        checkCoordinates();

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public BackupResult backupDirector(
        BackupDestination destination
    ) throws LiqidException {
        var fn = "backupDirector";
        _logger.trace("Entering %s destination:%s", fn, destination);
        checkParameterNotNull(destination, "destination", fn);
        checkCoordinates();

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public Machine cancelEditFabric(
        Integer machineId
    ) throws LiqidException {
        var fn = "cancelEditFabric";
        _logger.trace("Entering %s machineId:%s", fn, machineId);
        checkParameterNotNull(machineId, "machineId", fn);
        checkCoordinates();
        checkMachineExists(machineId);
        checkMachineInEditMode(machineId);

        _devicesToBeRemoved.clear();
        _devicesToBeAdded.clear();

        _machineBeingEdited = null;
        _logger.info("Machine 0x%08a exiting edit mode", machineId);

        var machine = _machines.get(machineId);
        _logger.trace("%s returning %s", fn, machine);
        return machine;
    }

    @Override
    public GroupPool cancelGroupPoolEdit(
        Integer groupId
    ) throws LiqidException {
        var fn = "cancelGroupPoolEdit";
        _logger.trace("Entering %s groupId:%s", fn, groupId);
        checkParameterNotNull(groupId, "groupId", fn);
        checkCoordinates();
        checkGroupExists(groupId);

        if (!Objects.equals(_groupBeingEdited, groupId)) {
            var msg = String.format("Group 0x%08x is not being edited", _groupBeingEdited);
            var ex = new LiqidException(msg);
            _logger.throwing(ex);
            throw ex;
        }

        _devicesToBeRemoved.clear();
        _devicesToBeAdded.clear();

        _groupBeingEdited = null;
        _logger.info("Group 0x%08a exiting edit mode", groupId);

        var groupPool = new GroupPool.Builder().setGroupId(groupId)
                                               .setFabricId(_fabricIdentifier)
                                               .setCoordinates(_coordinates)
                                               .build();
        _logger.trace("%s returning %s", fn, groupPool);
        return groupPool;
    }

    @Override
    public Machine cancelReprogramFabric(
        Integer machineId
    ) throws LiqidException {
        var fn = "cancelReprogramFabric";
        _logger.trace("Entering %s machineId:%s", fn, machineId);
        checkParameterNotNull(machineId, "machineId", fn);
        checkCoordinates();

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public Boolean clearGroups() throws LiqidException {
        var fn = "clearGroups";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        _logger.info("Deleting all groups and machines");
        _groups.clear();
        _systemFreePool.clear();
        _groupBeingEdited = null;
        _machineBeingEdited = null;
        _devicesToBeAdded.clear();
        _devicesToBeRemoved.clear();

        _systemFreePool.addAll(_devices.keySet());
        return true;
    }

    @Override
    public DeviceUserDescription createDeviceDescription(
        DeviceQueryType queryDeviceType,
        Integer deviceId,
        String description
    ) throws LiqidException {
        var fn = "createDeviceDescription";
        _logger.trace("Entering %s queryDeviceType:%s deviceId:%s description:%s",
                      fn, queryDeviceType, deviceId, description);
        checkParameterNotNull(queryDeviceType, "queryDeviceType", fn);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(description, "description", fn);
        checkCoordinates();
        checkDeviceExists(deviceId);

        _devices.get(deviceId).getDeviceInfo().setUserDescription(description);

        var devDesc = new DeviceUserDescription.Builder().setUserDescription(description).build();
        _logger.trace("%s returning %s", fn, devDesc);
        return devDesc;
    }

    @Override
    @Deprecated
    public Group createGroupWithId(
        Integer groupId,
        String groupName
    ) throws LiqidException {
        var fn = "createGroupWithId";
        _logger.trace("Entering %s groupId:%s groupName:%s", fn, groupId, groupName);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(groupName, "groupName", fn);
        checkCoordinates();
        checkGroupIdentifier(groupId);

        if (_groups.containsKey(groupId)) {
            var ex = new LiqidException(String.format("Group identifier %d is already in use", groupId));
            _logger.throwing(ex);
            throw ex;
        }

        var group = new MockGroup(groupId, groupName, _fabricIdentifier, _podIdentifier);
        _groups.put(groupId, group);

        _logger.trace("%s returning %s", fn, group);
        return group;
    }

    @Override
    @Deprecated
    public Machine createMachineWithId(
        Integer groupId,
        Integer machineId,
        String machineName
    ) throws LiqidException {
        var fn = "createMachineWithId";
        _logger.trace("Entering %s groupId:%s machineId:%s machineName:%s",
                      fn, groupId, machineId, machineName);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);
        checkParameterNotNull(machineName, "machineName", fn);
        checkCoordinates();
        checkGroupIdentifier(groupId);
        checkGroupExists(groupId);
        checkMachineIdentifier(machineId);

        if (_machines.containsKey(machineId)) {
            var ex = new LiqidException(String.format("Machine identifier 0x%08x is already in use", machineId));
            _logger.throwing(ex);
            throw ex;
        }

        var machine = new MockMachine(0, machineId, groupId, _fabricIdentifier, machineName);
        _machines.put(machineId, machine);
        _groups.get(groupId)._machines.put(machineId, machine);

        _logger.trace("%s returning %s", fn, machine);
        return machine;
    }

    @Override
    public DigestInfo createMessageDigest(
        String label
    ) throws LiqidException {
        var fn = "createMessageDigest";
        _logger.trace("Entering %s label:%s", fn, label);
        checkParameterNotNull(label, "label", fn);
        checkCoordinates();

        var digestInfo = new DigestInfo.Builder().setDigestLabel(label)
                                                 .setDigestId(_random.nextInt())
                                                 .setDigestKey(UUID.randomUUID().toString())
                                                 .build();
        _digestTable.put(label, digestInfo);

        _logger.trace("%s returning %s", fn, digestInfo);
        return digestInfo;
    }

    @Override
    public String deleteMessageDigest(
        String label
    ) throws LiqidException {
        var fn = "deleteMessageDigest";
        _logger.trace("Entering %s label:%s", fn, label);

        if (label != null && label.equalsIgnoreCase("slurm")) {
            var ex = new LiqidException("Cannot delete this label");
            _logger.throwing(ex);
            throw ex;
        }

        if (!_digestTable.containsKey(label)) {
            var ex = new LiqidException("Digest label does not exist");
            _logger.throwing(ex);
            throw ex;
        }

        _digestTable.remove(label);

        _logger.trace("%s returning %s", fn, label);
        return label;
    }

    @Override
    public DeviceUserDescription deleteDeviceDescription(
        DeviceQueryType queryDeviceType,
        Integer deviceId
    ) throws LiqidException {
        var fn = "deleteDeviceDescription";
        _logger.trace("Entering %s queryDeviceType:%s deviceId:%s", fn, queryDeviceType, deviceId);
        checkParameterNotNull(queryDeviceType, "queryDeviceType", fn);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkCoordinates();
        checkDeviceExists(deviceId);

        _devices.get(deviceId).getDeviceInfo().setUserDescription("n/a");

        var devDesc = new DeviceUserDescription.Builder().setUserDescription("n/a").build();
        _logger.trace("%s returning %s", fn, devDesc);
        return devDesc;
    }

    @Override
    public Group deleteGroup(
        Integer groupId
    ) throws LiqidException {
        var fn = "deleteGroup";
        _logger.trace("Entering %s groupId:%s", fn, groupId);
        checkParameterNotNull(groupId, "groupId", fn);
        checkCoordinates();
        checkGroupIdentifier(groupId);
        checkGroupExists(groupId);

        if (Objects.equals(_groupBeingEdited, groupId)) {
            var msg = String.format("Group 0x%08x is currently in edit mode and cannot be deleted", groupId);
            var ex = new LiqidException(msg);
            _logger.throwing(ex);
            throw ex;
        }

        if (_machineBeingEdited != null) {
            var machine = _machines.get(_machineBeingEdited);
            if (machine.getGroupId().equals(groupId)) {
                var msg = String.format("Machine 0x%08x owned by Group 0x%08x is currently in edit mode",
                                        _machineBeingEdited, groupId);
                var ex = new LiqidException(msg);
                _logger.throwing(ex);
                throw ex;
            }
        }

        var group = _groups.get(groupId);
        var mIter = group._machines.entrySet().iterator();
        while (mIter.hasNext()) {
            var machine = mIter.next().getValue();
            if (!machine._attachedDevices.isEmpty()) {
                _logger.info("Moving devices from machine 0x%08x to group free pool", machine.getMachineId());
                group._freePool.putAll(machine._attachedDevices);
                machine._attachedDevices.clear();
            }

            _logger.info("Deleting machine 0x%08x", machine.getMachineId());
            mIter.remove();
        }

        if (!group._freePool.isEmpty()) {
            _logger.info("Moving devices from group 0x%08x to system free pool", group.getGroupId());
            _systemFreePool.addAll(group._freePool.keySet());
            group._freePool.clear();
        }

        _logger.info("Deleting group 0x%08x", groupId);
        _groups.remove(groupId);

        _logger.trace("%s returning %s", fn, group);
        return group;
    }

    @Override
    public Machine deleteMachine(
        Integer machineId
    ) throws LiqidException {
        var fn = "deleteMachine";
        _logger.trace("Entering %s machineId:%s", fn, machineId);
        checkParameterNotNull(machineId, "machineId", fn);
        checkCoordinates();
        checkMachineIdentifier(machineId);
        checkMachineExists(machineId);

        if (Objects.equals(_machineBeingEdited, machineId)) {
            _logger.info("Clearing machine-being-edited");
            _machineBeingEdited = null;
        }

        var machine = _machines.get(machineId);
        var groupId = machine.getGroupId();
        var group = _groups.get(groupId);

        for (var entry : machine._attachedDevices.entrySet()) {
            var deviceId = entry.getKey();
            var device = entry.getValue();
            _logger.info("Moving device 0x%08x from machine 0x%08x to group 0x%08x free pool",
                         deviceId, machineId, groupId);
            group._freePool.put(deviceId, device);
        }

        group._machines.remove(machineId);
        _logger.info("Deleted machine 0x%08x", machineId);

        _logger.trace("%s returning %s", fn, machine);
        return machine;
    }

    @Override
    public Machine editFabric(
        Integer machineId
    ) throws LiqidException {
        var fn = "editFabric";
        _logger.trace("Entering %s machineId:%s", fn, machineId);
        checkParameterNotNull(machineId, "machineId", fn);
        checkCoordinates();
        checkMachineIdentifier(machineId);
        checkMachineExists(machineId);

        if (_machineBeingEdited != null) {
            var msg = String.format("Machine 0x%08x is already being edited", _machineBeingEdited);
            var ex = new LiqidException(msg);
            _logger.throwing(ex);
            throw ex;
        }

        _machineBeingEdited = machineId;
        _devicesToBeAdded.clear();
        _devicesToBeRemoved.clear();
        _logger.info("Machine 0x%08x entering edit mode", machineId);

        var machine = _machines.get(machineId);
        _logger.trace("%s returning %s", fn, machine);
        return machine;
    }

    @Override
    public LinkedList<DeviceStatus> getAllDevicesStatus() throws LiqidException {
        var fn = "getAllDevicesStatus";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var result = _devices.values()
                             .stream()
                             .map(MockDevice::getDeviceStatus)
                             .collect(Collectors.toCollection(LinkedList::new));

        _logger.trace("%s returning %s", fn, result);
        return result;
    }

    @Override
    public LinkedList<AvailableCoordinates> getAvailableCoordinates() throws LiqidException {
        var fn = "getAvailableCoordinates";
        _logger.trace("Entering %s", fn);
        var result = new LinkedList<AvailableCoordinates>();
        var coordinates = new AvailableCoordinates.Builder().setCoordinates(_coordinates)
                                                            .setIPAddress(getHostAddress())
                                                            .setPortNumber(getPortNumber())
                                                            .build();
        result.add(coordinates);
        _logger.trace("%s returning %s", fn, result);
        return result;
    }

    @Override
    public ComputeDeviceStatus getComputeDeviceStatus(
        Integer deviceId
    ) throws LiqidException {
        var fn = "getComputeDeviceStatus";
        _logger.trace("Entering %s deviceId:%s", fn, deviceId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkCoordinates();
        checkDeviceExists(deviceId);
        var device = _devices.get(deviceId);
        checkDeviceType(device, DeviceType.COMPUTE);

        var devStat = device.getComputeDeviceStatus();
        _logger.trace("%s returning %s", fn, devStat);
        return devStat;
    }

    @Override
    public LinkedList<ComputeDeviceStatus> getComputeDevicesStatus() throws LiqidException {
        var fn = "getComputeDevicesStatus";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var result = _devices.values()
                             .stream()
                             .filter(device -> device.getDeviceType() == DeviceType.COMPUTE)
                             .map(MockDevice::getComputeDeviceStatus)
                             .collect(Collectors.toCollection(LinkedList::new));

        _logger.trace("%s returning %s", fn, result);
        return result;
    }

    @Override
    public LinkedList<ComputeDeviceStatus> getComputeDevicesStatusWithMultiplePortsStatus() throws LiqidException {
        var fn = "getComputeDevicesStatusWithMultiplePortsStatus";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public LinkedList<ComputeDeviceInfo> getComputeDeviceInfo() throws LiqidException {
        var fn = "getComputeDeviceInfo";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var result = _devices.values()
                             .stream()
                             .filter(device -> device.getDeviceType().equals(DeviceType.COMPUTE))
                             .map(MockDevice::getComputeDeviceInfo)
                             .collect(Collectors.toCollection(LinkedList::new));

        _logger.trace("%s returning %s", fn, result);
        return result;
    }

    @Override
    public ComputeDeviceInfo getComputeDeviceInfoByName(
        String deviceName
    ) throws LiqidException {
        var fn = "getComputeDeviceInfoByName";
        _logger.trace("Entering %s deviceName:%s", fn, deviceName);
        checkParameterNotNull(deviceName, "deviceName", fn);
        checkCoordinates();

        var device = getMockDeviceByName(deviceName);
        checkDeviceType(device, DeviceType.COMPUTE);

        var devInfo = device.getComputeDeviceInfo();
        _logger.trace("%s returning %s", fn, devInfo);
        return devInfo;
    }

    @Override
    public Integer getCurrentFabricId() throws LiqidException {
        var fn = "getCurrentFabricId";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        _logger.trace("%s returning %d", fn, _fabricIdentifier);
        return _fabricIdentifier;
    }

    @Override
    public Coordinates getDefaultCoordinates() throws LiqidException {
        var fn = "getDefaultCoordinates";
        _logger.trace("Entering %s", fn);

        _logger.trace("%s returning %s", fn, _defaultCoordinates);
        return _defaultCoordinates;
    }

    @Override
    public LinkedList<PreDevice> getPreDevices(
        DeviceQueryType queryDeviceType,
        Integer groupId,
        Integer machineId
    ) throws LiqidException {
        var fn = "getPreDevices";
        _logger.trace("Entering %s queryDeviceType:%s groupId:%s machineId:%s",
                      fn, queryDeviceType, groupId, machineId);
        checkParameterNotNull(groupId, "groupId", fn);
        checkCoordinates();
        checkGroupIdentifier(groupId);
        if (machineId != null) {
            checkMachineIdentifier(machineId);
        }

        //  with no machineId, we return devices in the given group's free pool.
        var group = _groups.get(groupId);
        var result = new LinkedList<PreDevice>();
        if (group != null) {
            for (var device : _devices.values()) {
                if (queryDeviceType != null) {
                    var devQueryDeviceType = deviceTypeToQueryDeviceType(device.getDeviceType());
                    if (!Objects.equals(queryDeviceType, devQueryDeviceType)) {
                        continue;
                    }
                }

                var ids = getDeviceRelation(device.getDeviceId());
                if (Objects.equals(groupId, ids[0]) && Objects.equals(machineId, ids[1])) {
                    result.add(createPreDevice(device));
                }
            }
        }

        _logger.trace("%s returning %s", fn, result);
        return result;
    }

    @Override
    public LinkedList<DeviceTypeAndAttributes> getDeviceAttributes() throws LiqidException {
        var fn = "getDeviceAttributes";
        checkCoordinates();

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public DeviceCounters getDeviceCounters() throws LiqidException {
        var fn = "getDeviceCounters";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public LinkedList<DiscoveryToken> getDiscoveryTokens() throws LiqidException {
        var fn = "getDiscoveryTokens";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public LinkedList<DiscoveryToken> getDiscoveryTokensByType(
        TokenType tokenType
    ) throws LiqidException {
        var fn = "getDiscoveryTokensByType";
        _logger.trace("Entering %s tokenType:%s", fn, tokenType);
        checkParameterNotNull(tokenType, "tokenType", fn);
        checkCoordinates();

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public FabricTopology getFabricTopology() throws LiqidException {
        var fn = "getFabricTopology";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public LinkedList<NameValuePair> getFabricTypes() throws LiqidException {
        var fn = "getFabricTypes";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public FPGADeviceStatus getFPGADeviceStatus(
        Integer deviceId
    ) throws LiqidException {
        var fn = "getFPGADeviceStatus";
        _logger.trace("Entering %s deviceId:%s", fn, deviceId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkCoordinates();
        checkDeviceExists(deviceId);
        var device = _devices.get(deviceId);
        checkDeviceType(device, DeviceType.FPGA);

        var devStat = device.getFPGADeviceStatus();
        _logger.trace("%s returning %s", fn, devStat);
        return devStat;
    }

    @Override
    public LinkedList<FPGADeviceStatus> getFPGADevicesStatus() throws LiqidException {
        var fn = "getFPGADevicesStatus";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var result = _devices.values()
                             .stream()
                             .filter(device -> device.getDeviceType() == DeviceType.FPGA)
                             .map(MockDevice::getFPGADeviceStatus)
                             .collect(Collectors.toCollection(LinkedList::new));

        _logger.trace("%s returning %s", fn, result);
        return result;
    }

    @Override
    public LinkedList<FPGADeviceInfo> getFPGADeviceInfo() throws LiqidException {
        var fn = "getFPGADeviceInfo";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var result = _devices.values()
                             .stream()
                             .filter(device -> device.getDeviceType().equals(DeviceType.FPGA))
                             .map(MockDevice::getFPGADeviceInfo)
                             .collect(Collectors.toCollection(LinkedList::new));

        _logger.trace("%s returning %s", fn, result);
        return result;
    }

    @Override
    public FPGADeviceInfo getFPGADeviceInfoByName(
        String deviceName
    ) throws LiqidException {
        var fn = "getFPGADeviceInfoByName";
        _logger.trace("Entering %s deviceName:%s", fn, deviceName);
        checkParameterNotNull(deviceName, "deviceName", fn);
        checkCoordinates();

        var device = getMockDeviceByName(deviceName);
        checkDeviceType(device, DeviceType.FPGA);

        var devInfo = device.getFPGADeviceInfo();
        _logger.trace("%s returning %s", fn, devInfo);
        return devInfo;
    }

    @Override
    public LinkedList<ComputeDeviceStatus> getFreeComputeDevicesStatus() throws LiqidException {
        var fn = "getFreeComputeDevicesStatus";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var result = new LinkedList<ComputeDeviceStatus>();
        for (var device : _devices.values()) {
            if (_systemFreePool.contains(device.getDeviceId()) && (device.getDeviceType() == DeviceType.COMPUTE)) {
                result.add(device.getComputeDeviceStatus());
            }
        }

        _logger.trace("%s returning %s", fn, result);
        return result;
    }

    @Override
    public LinkedList<FPGADeviceStatus> getFreeFPGADevicesStatus() throws LiqidException {
        var fn = "getFreeFPGADevicesStatus";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var result = new LinkedList<FPGADeviceStatus>();
        for (var device : _devices.values()) {
            if (_systemFreePool.contains(device.getDeviceId()) && (device.getDeviceType() == DeviceType.FPGA)) {
                result.add(device.getFPGADeviceStatus());
            }
        }

        _logger.trace("%s returning %s", fn, result);
        return result;
    }

    @Override
    public LinkedList<GPUDeviceStatus> getFreeGPUDevicesStatus() throws LiqidException {
        var fn = "getFreeGPUDevicesStatus";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var result = new LinkedList<GPUDeviceStatus>();
        for (var device : _devices.values()) {
            if (_systemFreePool.contains(device.getDeviceId()) && (device.getDeviceType() == DeviceType.GPU)) {
                result.add(device.getGPUDeviceStatus());
            }
        }

        _logger.trace("%s returning %s", fn, result);
        return result;
    }

    @Override
    public LinkedList<MemoryDeviceStatus> getFreeMemoryDevicesStatus() throws LiqidException {
        var fn = "getFreeMemoryDevicesStatus";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var result = new LinkedList<MemoryDeviceStatus>();
        for (var device : _devices.values()) {
            if (_systemFreePool.contains(device.getDeviceId()) && (device.getDeviceType() == DeviceType.MEMORY)) {
                result.add(device.getMemoryDeviceStatus());
            }
        }

        _logger.trace("%s returning %s", fn, result);
        return result;
    }

    @Override
    public LinkedList<NetworkDeviceStatus> getFreeNetworkDevicesStatus() throws LiqidException {
        var fn = "getFreeNetworkDevicesStatus";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var result = new LinkedList<NetworkDeviceStatus>();
        for (var device : _devices.values()) {
            if (_systemFreePool.contains(device.getDeviceId()) && (device.getDeviceType() == DeviceType.ETHERNET_LINK)) {
                result.add(device.getNetworkDeviceStatus());
            }
        }

        _logger.trace("%s returning %s", fn, result);
        return result;
    }

    @Override
    public LinkedList<StorageDeviceStatus> getFreeStorageDevicesStatus() throws LiqidException {
        var fn = "getFreeStorageDevicesStatus";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var result = new LinkedList<StorageDeviceStatus>();
        for (var device : _devices.values()) {
            if (_systemFreePool.contains(device.getDeviceId()) && (device.getDeviceType() == DeviceType.SSD)) {
                result.add(device.getStorageDeviceStatus());
            }
        }

        _logger.trace("%s returning %s", fn, result);
        return result;
    }

    @Override
    public GPUDeviceStatus getGPUDeviceStatus(
        Integer deviceId
    ) throws LiqidException {
        var fn = "getGPUDeviceStatus";
        _logger.trace("Entering %s deviceId:%s", fn, deviceId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkCoordinates();
        checkDeviceExists(deviceId);
        var device = _devices.get(deviceId);
        checkDeviceType(device, DeviceType.GPU);

        var devStat = device.getGPUDeviceStatus();
        _logger.trace("%s returning %s", fn, devStat);
        return devStat;
    }

    @Override
    public LinkedList<GPUDeviceStatus> getGPUDevicesStatus() throws LiqidException {
        var fn = "getGPUDevicesStatus";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var result = _devices.values()
                             .stream()
                             .filter(device -> device.getDeviceType() == DeviceType.GPU)
                             .map(MockDevice::getGPUDeviceStatus)
                             .collect(Collectors.toCollection(LinkedList::new));

        _logger.trace("%s returning %s", fn, result);
        return result;
    }

    @Override
    public LinkedList<GPUDeviceInfo> getGPUDeviceInfo() throws LiqidException {
        var fn = "getGPUDeviceInfo";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var result = _devices.values()
                             .stream()
                             .filter(device -> device.getDeviceType().equals(DeviceType.GPU))
                             .map(MockDevice::getGPUDeviceInfo)
                             .collect(Collectors.toCollection(LinkedList::new));

        _logger.trace("%s returning %s", fn, result);
        return result;
    }

    @Override
    public GPUDeviceInfo getGPUDeviceInfoByName(
        String deviceName
    ) throws LiqidException {
        var fn = "getGPUDeviceInfoByName";
        _logger.trace("Entering %s deviceName:%s", fn, deviceName);
        checkParameterNotNull(deviceName, "deviceName", fn);
        checkCoordinates();

        var device = getMockDeviceByName(deviceName);
        checkDeviceType(device, DeviceType.GPU);

        var devInfo = device.getGPUDeviceInfo();
        _logger.trace("%s returning %s", fn, devInfo);
        return devInfo;
    }

    @Override
    public Group getGroup(
        Integer groupId
    ) throws LiqidException {
        var fn = "getGroup";
        _logger.trace("Entering %s groupId:%s", fn, groupId);
        checkParameterNotNull(groupId, "groupId", fn);
        checkCoordinates();

        var group = _groups.get(groupId);
        if (group == null) {
            var ex = new LiqidException("Entity not found");
            _logger.throwing(ex);
            throw ex;
        }

        _logger.trace("%s returning %s", fn, group);
        return group;
    }

    @Override
    public LinkedList<Group> getGroups() throws LiqidException {
        var fn = "getGroups";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var result = new LinkedList<Group>(_groups.values());
        _logger.trace("%s returning %s", fn, result);
        return result;
    }

    @Override
    public GroupComputeDeviceRelator getGroupComputeDeviceRelator(
        Integer groupId,
        Integer deviceId
    ) throws LiqidException {
        return super.getGroupComputeDeviceRelator(groupId, deviceId);
    }

    @Override
    public GroupFPGADeviceRelator getGroupFPGADeviceRelator(
        Integer groupId,
        Integer deviceId
    ) throws LiqidException {
        return super.getGroupFPGADeviceRelator(groupId, deviceId);
    }

    @Override
    public GroupGPUDeviceRelator getGroupGPUDeviceRelator(
        Integer groupId,
        Integer deviceId
    ) throws LiqidException {
        return super.getGroupGPUDeviceRelator(groupId, deviceId);
    }

    @Override
    public GroupMemoryDeviceRelator getGroupMemoryDeviceRelator(
        Integer groupId,
        Integer deviceId
    ) throws LiqidException {
        return super.getGroupMemoryDeviceRelator(groupId, deviceId);
    }

    @Override
    public GroupNetworkDeviceRelator getGroupNetworkDeviceRelator(
        Integer groupId,
        Integer deviceId
    ) throws LiqidException {
        return super.getGroupNetworkDeviceRelator(groupId, deviceId);
    }

    @Override
    public GroupStorageDeviceRelator getGroupStorageDeviceRelator(
        Integer groupId,
        Integer deviceId
    ) throws LiqidException {
        return super.getGroupStorageDeviceRelator(groupId, deviceId);
    }

    @Override
    public GroupDetails getGroupDetails(
        Integer groupId
    ) throws LiqidException {
        var fn = "getGroupDetails";
        _logger.trace("Entering %s groupId:%s", fn, groupId);
        checkParameterNotNull(groupId, "groupId", fn);
        checkCoordinates();

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public Integer getGroupIdByName(
        String groupName
    ) throws LiqidException {
        var fn = "getGroupIdByName";
        _logger.trace("Entering %s groupName:%s", fn, groupName);
        checkParameterNotNull(groupName, "groupName", fn);
        checkCoordinates();

        for (var group : _groups.values()) {
            if (group.getGroupName().equals(groupName)) {
                _logger.trace("%s returning %s", fn, group);
                return group.getGroupId();
            }
        }

        var ex = new LiqidException("Entity not found");
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public Machine getMachine(
        Integer machineId
    ) throws LiqidException {
        var fn = "getMachine";
        _logger.trace("Entering %s machineId:%s", fn, machineId);
        checkParameterNotNull(machineId, "machineId", fn);
        checkCoordinates();

        var machine = _machines.get(machineId);
        if (machine == null) {
            var ex = new LiqidException("Entity not found");
            _logger.throwing(ex);
            throw ex;
        }

        _logger.trace("%s returning %s", fn, machine);
        return machine;
    }

    @Override
    public LinkedList<Machine> getMachines() throws LiqidException {
        var fn = "getMachines";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var result = new LinkedList<Machine>(_machines.values());
        _logger.trace("%s returning %s", fn, result);
        return result;
    }

    @Override
    public LinkedList<Machine> getMachinesAtCoordinates(
        Integer rackId,
        Integer shelfId,
        Integer nodeId
    ) throws LiqidException {
        var fn = "getMachinesAtCoordinates";
        _logger.trace("Entering %s rackId:%s shelfId:%s nodeId:%s", fn, rackId, shelfId, nodeId);
        checkParameterNotNull(rackId, "rackId", fn);
        checkParameterNotNull(shelfId, "shelfId", fn);
        checkParameterNotNull(nodeId, "nodeId", fn);

        var result = new LinkedList<Machine>();
        var coords = new MockCoordinates(rackId, shelfId, nodeId);
        if (coords.equals(_coordinates)) {
            result.addAll(_machines.values());
        }

        _logger.trace("%s returning %s", fn, result);
        return result;
    }

    @Override
    public LinkedList<Machine> getMachinesByGroupId(
        Integer groupId
    ) throws LiqidException {
        var fn = "getMachinesByGroupId";
        _logger.trace("Entering %s groupId:%s", fn, groupId);
        checkParameterNotNull(groupId, "groupId", fn);
        checkCoordinates();

        var result = _machines.values()
                              .stream()
                              .filter(machine -> Objects.equals(machine.getGroupId(), groupId))
                              .collect(Collectors.toCollection(() -> new LinkedList<Machine>()));

        _logger.trace("%s returning %s", fn, result);
        return result;
    }

    @Override
    public Machine getMachineByName(
        String machineName
    ) throws LiqidException {
        var fn = "getMachineByName";
        _logger.trace("Entering %s machineName:%s", fn, machineName);
        checkParameterNotNull(machineName, "machineName", fn);
        checkCoordinates();

        for (var machine : _machines.values()) {
            if (Objects.equals(machine.getMachineName(), machineName)) {
                return machine;
            }
        }

        var ex = new LiqidException("Entity not found");
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public MachineDetails getMachineDetails(
        Integer machineId
    ) throws LiqidException {
        var fn = "getMachineDetails";
        _logger.trace("Entering %s machineId:%s", fn, machineId);
        checkParameterNotNull(machineId, "machineId", fn);
        checkCoordinates();

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public LinkedList<ManagedEntity> getManagedEntities() throws LiqidException {
        var fn = "getManagedEntities";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public ManagedEntity getManagedEntity(
        String pciVendorId,
        String pciDeviceId
    ) throws LiqidException {
        var fn = "getManagedEntity";
        _logger.trace("Entering %s pciVendorId:%s pciDeviceId:%s", fn, pciVendorId, pciDeviceId);
        checkParameterNotNull(pciVendorId, "pciVendorId", fn);
        checkParameterNotNull(pciDeviceId, "pciDeviceId", fn);
        checkCoordinates();

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public LinkedList<MemoryDeviceInfo> getMemoryDeviceInfo() throws LiqidException {
        var fn = "getMemoryDeviceInfo";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var result = _devices.values()
                             .stream()
                             .filter(device -> device.getDeviceType().equals(DeviceType.MEMORY))
                             .map(MockDevice::getMemoryDeviceInfo)
                             .collect(Collectors.toCollection(LinkedList::new));

        _logger.trace("%s returning %s", fn, result);
        return result;
    }

    @Override
    public MemoryDeviceInfo getMemoryDeviceInfoByName(
        String deviceName
    ) throws LiqidException {
        var fn = "getMemoryDeviceInfoByName";
        _logger.trace("Entering %s deviceName:%s", fn, deviceName);
        checkParameterNotNull(deviceName, "deviceName", fn);
        checkCoordinates();

        var device = getMockDeviceByName(deviceName);
        checkDeviceType(device, DeviceType.MEMORY);

        var devInfo = device.getMemoryDeviceInfo();
        _logger.trace("%s returning %s", fn, devInfo);
        return devInfo;
    }

    @Override
    public MemoryDeviceStatus getMemoryDeviceStatus(
        Integer deviceId
    ) throws LiqidException {
        var fn = "getMemoryDeviceStatus";
        _logger.trace("Entering %s deviceId:%s", fn, deviceId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkCoordinates();
        checkDeviceExists(deviceId);
        var device = _devices.get(deviceId);
        checkDeviceType(device, DeviceType.MEMORY);

        var devStat = device.getMemoryDeviceStatus();
        _logger.trace("%s returning %s", fn, devStat);
        return devStat;
    }

    @Override
    public LinkedList<MemoryDeviceStatus> getMemoryDevicesStatus() throws LiqidException {
        var fn = "getMemoryDevicesStatus";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var result = _devices.values()
                             .stream()
                             .filter(device -> device.getDeviceType() == DeviceType.MEMORY)
                             .map(MockDevice::getMemoryDeviceStatus)
                             .collect(Collectors.toCollection(LinkedList::new));

        _logger.trace("%s returning %s", fn, result);
        return result;
    }

    @Override
    public LinkedList<String> getMessageDigestLabels() throws LiqidException {
        var fn = "getMessageDigestLabels";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public LinkedList<NetworkDeviceInfo> getNetworkDeviceInfo() throws LiqidException {
        var fn = "getNetworkDeviceInfo";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var result = _devices.values()
                             .stream()
                             .filter(device -> device.getDeviceType().equals(DeviceType.ETHERNET_LINK))
                             .map(MockDevice::getNetworkDeviceInfo)
                             .collect(Collectors.toCollection(LinkedList::new));

        _logger.trace("%s returning %s", fn, result);
        return result;
    }

    @Override
    public NetworkDeviceInfo getNetworkDeviceInfoByName(
        String deviceName
    ) throws LiqidException {
        var fn = "getNetworkDeviceInfoByName";
        _logger.trace("Entering %s deviceName:%s", fn, deviceName);
        checkParameterNotNull(deviceName, "deviceName", fn);
        checkCoordinates();

        var device = getMockDeviceByName(deviceName);
        checkDeviceType(device, DeviceType.ETHERNET_LINK);

        var devInfo = device.getNetworkDeviceInfo();
        _logger.trace("%s returning %s", fn, devInfo);
        return devInfo;
    }

    @Override
    public NetworkDeviceStatus getNetworkDeviceStatus(
        Integer deviceId
    ) throws LiqidException {
        var fn = "getNetworkDeviceStatus";
        _logger.trace("Entering %s deviceId:%s", fn, deviceId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkCoordinates();
        checkDeviceExists(deviceId);
        var device = _devices.get(deviceId);
        checkDeviceType(device, DeviceType.ETHERNET_LINK);

        var devStat = device.getNetworkDeviceStatus();
        _logger.trace("%s returning %s", fn, devStat);
        return devStat;
    }

    @Override
    public LinkedList<NetworkDeviceStatus> getNetworkDevicesStatus() throws LiqidException {
        var fn = "getNetworkDevicesStatus";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var result = _devices.values()
                             .stream()
                             .filter(device -> device.getDeviceType() == DeviceType.ETHERNET_LINK)
                             .map(MockDevice::getNetworkDeviceStatus)
                             .collect(Collectors.toCollection(LinkedList::new));

        _logger.trace("%s returning %s", fn, result);
        return result;
    }

    @Override
    public NetworkManagedCPU getNetworkIPMIManagedCPU(
        String cpuName
    ) throws LiqidException {
        var fn = "getNetworkIPMIManagedCPU";
        _logger.trace("Entering %s cpuName:%s", fn, cpuName);
        checkParameterNotNull(cpuName, "cpuName", fn);
        checkCoordinates();

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public LinkedList<NetworkManagedCPU> getNetworkIPMIManagedCPUS() throws LiqidException {
        var fn = "getNetworkIPMIManagedCPUS";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public NetworkManagedEnclosure getNetworkIPMIManagedEnclosure(
        Integer enclosureName
    ) throws LiqidException {
        var fn = "getNetworkIPMIManagedEnclosure";
        _logger.trace("Entering %s enclosureName:%s", fn, enclosureName);
        checkParameterNotNull(enclosureName, "enclosureName", fn);
        checkCoordinates();

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public LinkedList<NetworkManagedEnclosure> getNetworkIPMIManagedEnclosures() throws LiqidException {
        var fn = "getNetworkIPMIManagedEnclosures";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    @Deprecated
    public Integer getNextGroupId() throws LiqidException {
        var fn = "getNextGroupId";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        Integer id = 1;
        while (_groups.containsKey(id)) {
            id++;
        }

        _logger.trace("%s returning %d", fn, id);
        return id;
    }

    @Override
    @Deprecated
    public String getNextMachineId() throws LiqidException {
        var fn = "getNextMachineId";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        Integer id = 1;
        while (_machines.containsKey(id)) {
            id++;
        }

        _logger.trace("%s returning %d", fn, id);
        return String.format("%d", id);
    }

    @Override
    public LinkedList<NodeStatus> getNodesStatus() throws LiqidException {
        var fn = "getNodesStatus";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public AsynchronousStatus getSecureEraseStatus(
        Integer deviceId
    ) throws LiqidException {
        var fn = "getSecureEraseStatus";
        _logger.trace("Entering %s deviceId:%s", fn, deviceId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkCoordinates();

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public SSHStatus getSSHStatus() throws LiqidException {
        var fn = "getSSHStatus";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        _logger.trace("%s returning %s", fn, _sshStatus);
        return _sshStatus;
    }

    @Override
    public StorageDeviceStatus getStorageDeviceStatus(
        Integer deviceId
    ) throws LiqidException {
        var fn = "getStorageDeviceStatus";
        _logger.trace("Entering %s deviceId:%s", fn, deviceId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkCoordinates();
        checkDeviceExists(deviceId);
        var device = _devices.get(deviceId);
        checkDeviceType(device, DeviceType.SSD);

        var devStat = device.getStorageDeviceStatus();
        _logger.trace("%s returning %s", fn, devStat);
        return devStat;
    }

    @Override
    public LinkedList<StorageDeviceStatus> getStorageDevicesStatus() throws LiqidException {
        var fn = "getStorageDevicesStatus";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var result = _devices.values()
                             .stream()
                             .filter(device -> device.getDeviceType() == DeviceType.SSD)
                             .map(MockDevice::getStorageDeviceStatus)
                             .collect(Collectors.toCollection(LinkedList::new));

        _logger.trace("%s returning %s", fn, result);
        return result;
    }

    @Override
    public LinkedList<StorageDeviceInfo> getStorageDeviceInfo() throws LiqidException {
        var fn = "getStorageDeviceInfo";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var result = _devices.values()
                             .stream()
                             .filter(device -> device.getDeviceType().equals(DeviceType.SSD))
                             .map(MockDevice::getStorageDeviceInfo)
                             .collect(Collectors.toCollection(LinkedList::new));

        _logger.trace("%s returning %s", fn, result);
        return result;
    }

    @Override
    public StorageDeviceInfo getStorageDeviceInfoByName(
        String deviceName
    ) throws LiqidException {
        var fn = "getStorageDeviceInfoByName";
        _logger.trace("Entering %s deviceName:%s", fn, deviceName);
        checkParameterNotNull(deviceName, "deviceName", fn);
        checkCoordinates();

        var device = getMockDeviceByName(deviceName);
        checkDeviceType(device, DeviceType.SSD);

        var devInfo = device.getStorageDeviceInfo();
        _logger.trace("%s returning %s", fn, devInfo);
        return devInfo;
    }

    @Override
    public LinkedList<VersionInfo> getVersions() throws LiqidException {
        var fn = "getVersions";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public GroupPool groupPoolDone(
        Integer groupId
    ) throws LiqidException {
        var fn = "groupPoolDone";
        _logger.trace("Entering %s groupId:%s", fn, groupId);
        checkParameterNotNull(groupId, "groupId", fn);
        checkCoordinates();

        if (!Objects.equals(_groupBeingEdited, groupId)) {
            var msg = String.format("Group 0x%08x is not being edited", _groupBeingEdited);
            var ex = new LiqidException(msg);
            _logger.throwing(ex);
            throw ex;
        }

        var group = _groups.get(groupId);

        for (var deviceId : _devicesToBeRemoved) {
            group._freePool.remove(deviceId);
            _systemFreePool.add(deviceId);
            _logger.info("Moved device 0x%08x from group 0x%08x to system free pool", deviceId, groupId);
        }
        _devicesToBeRemoved.clear();

        for (var deviceId : _devicesToBeAdded) {
            var device = _devices.get(deviceId);
            _systemFreePool.remove(deviceId);
            group._freePool.put(deviceId, device);
            _logger.info("Moved device 0x%08x from system free pool to group 0x%08x", deviceId, groupId);
        }
        _devicesToBeAdded.clear();

        _groupBeingEdited = null;
        _logger.info("Group 0x%08a exiting edit mode", groupId);

        var groupPool = new GroupPool.Builder().setGroupId(groupId)
                                               .setFabricId(_fabricIdentifier)
                                               .setCoordinates(_coordinates)
                                               .build();
        _logger.trace("%s returning %s", fn, groupPool);
        return groupPool;
    }

    @Override
    public GroupPool groupPoolEdit(
        Integer groupId
    ) throws LiqidException {
        var fn = "groupPoolEdit";
        _logger.trace("Entering %s groupId:%s", fn, groupId);
        checkParameterNotNull(groupId, "groupId", fn);
        checkCoordinates();
        checkGroupExists(groupId);

        if (_groupBeingEdited != null) {
            var msg = String.format("Group 0x%08x is already being edited", _groupBeingEdited);
            var ex = new LiqidException(msg);
            _logger.throwing(ex);
            throw ex;
        }

        if (_machineBeingEdited != null) {
            var msg = String.format("Machine 0x%08x is currently being edited for reprogramming", _machineBeingEdited);
            var ex = new LiqidException(msg);
            _logger.throwing(ex);
            throw ex;
        }

        _groupBeingEdited = groupId;
        _devicesToBeAdded.clear();
        _devicesToBeRemoved.clear();
        _logger.info("Group 0x%08a entering edit mode", groupId);

        var groupPool = new GroupPool.Builder().setGroupId(groupId)
                                               .setFabricId(_fabricIdentifier)
                                               .setCoordinates(_coordinates)
                                               .build();
        _logger.trace("%s returning %s", fn, groupPool);
        return groupPool;
    }

    @Override
    public Machine rebootNode(
        Integer groupId,
        Integer machineId
    ) throws LiqidException {
        var fn = "rebootNode";
        _logger.trace("Entering %s groupId:%s machineId:%s", fn, groupId, machineId);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);
        checkCoordinates();

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public GroupComputeDeviceRelator removeComputeDeviceFromGroup(
        Integer deviceId,
        Integer groupId
    ) throws LiqidException {
        var fn = "removeComputeDeviceFromGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkCoordinates();

        var device = _devices.get(deviceId);
        var group = _groups.get(groupId);
        checkDeviceType(device, DeviceType.COMPUTE);
        queueRemoveDeviceFromGroup(deviceId, groupId);

        var groupRel = new GroupComputeDeviceRelator.Builder().setGroup(group)
                                                              .setDeviceStatus(device.getComputeDeviceStatus())
                                                              .build();

        _logger.trace("%s returning %s", groupRel);
        return groupRel;
    }

    @Override
    public MachineComputeDeviceRelator removeComputeDeviceFromMachine(
        Integer deviceId,
        Integer groupId,
        Integer machineId
    ) throws LiqidException {
        var fn = "removeComputeDeviceFromMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);
        checkCoordinates();
        checkMachineExists(machineId);

        var device = _devices.get(deviceId);
        var group = _groups.get(groupId);
        var machine = _machines.get(machineId);
        checkDeviceType(device, DeviceType.COMPUTE);
        queueRemoveDeviceFromMachine(deviceId, machineId);

        var groupRel = new GroupComputeDeviceRelator.Builder().setGroup(group)
                                                              .setDeviceStatus(device.getComputeDeviceStatus())
                                                              .build();
        var machineRel = new MachineComputeDeviceRelator.Builder().setMachine(machine)
                                                                  .setGroupDeviceRelator(groupRel)
                                                                  .build();

        _logger.trace("%s returning %s", machineRel);
        return machineRel;
    }

    @Override
    public GroupFPGADeviceRelator removeFPGADeviceFromGroup(
        Integer deviceId,
        Integer groupId
    ) throws LiqidException {
        var fn = "removeFPGADeviceFromGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkCoordinates();

        var device = _devices.get(deviceId);
        var group = _groups.get(groupId);
        checkDeviceType(device, DeviceType.FPGA);
        queueRemoveDeviceFromGroup(deviceId, groupId);

        var groupRel = new GroupFPGADeviceRelator.Builder().setGroup(group)
                                                           .setDeviceStatus(device.getFPGADeviceStatus())
                                                           .build();

        _logger.trace("%s returning %s", groupRel);
        return groupRel;
    }

    @Override
    public MachineFPGADeviceRelator removeFPGADeviceFromMachine(
        Integer deviceId,
        Integer groupId,
        Integer machineId
    ) throws LiqidException {
        var fn = "removeFPGADeviceFromMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);
        checkCoordinates();
        checkMachineExists(machineId);

        var device = _devices.get(deviceId);
        var group = _groups.get(groupId);
        var machine = _machines.get(machineId);
        checkDeviceType(device, DeviceType.FPGA);
        queueRemoveDeviceFromMachine(deviceId, machineId);

        var groupRel = new GroupFPGADeviceRelator.Builder().setGroup(group)
                                                           .setDeviceStatus(device.getFPGADeviceStatus())
                                                           .build();
        var machineRel = new MachineFPGADeviceRelator.Builder().setMachine(machine)
                                                               .setGroupDeviceRelator(groupRel)
                                                               .build();

        _logger.trace("%s returning %s", machineRel);
        return machineRel;
    }

    @Override
    public GroupGPUDeviceRelator removeGPUDeviceFromGroup(
        Integer deviceId,
        Integer groupId
    ) throws LiqidException {
        var fn = "removeGPUDeviceFromGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkCoordinates();

        var device = _devices.get(deviceId);
        var group = _groups.get(groupId);
        checkDeviceType(device, DeviceType.GPU);
        queueRemoveDeviceFromGroup(deviceId, groupId);

        var groupRel = new GroupGPUDeviceRelator.Builder().setGroup(group)
                                                          .setDeviceStatus(device.getGPUDeviceStatus())
                                                          .build();

        _logger.trace("%s returning %s", groupRel);
        return groupRel;
    }

    @Override
    public MachineGPUDeviceRelator removeGPUDeviceFromMachine(
        Integer deviceId,
        Integer groupId,
        Integer machineId
    ) throws LiqidException {
        var fn = "removeGPUDeviceFromMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);
        checkCoordinates();
        checkMachineExists(machineId);

        var device = _devices.get(deviceId);
        var group = _groups.get(groupId);
        var machine = _machines.get(machineId);
        checkDeviceType(device, DeviceType.GPU);
        queueRemoveDeviceFromMachine(deviceId, machineId);

        var groupRel = new GroupGPUDeviceRelator.Builder().setGroup(group)
                                                          .setDeviceStatus(device.getGPUDeviceStatus())
                                                          .build();
        var machineRel = new MachineGPUDeviceRelator.Builder().setMachine(machine)
                                                              .setGroupDeviceRelator(groupRel)
                                                              .build();

        _logger.trace("%s returning %s", machineRel);
        return machineRel;
    }

    @Override
    public GroupMemoryDeviceRelator removeMemoryDeviceFromGroup(
        Integer deviceId,
        Integer groupId
    ) throws LiqidException {
        var fn = "removeMemoryDeviceFromGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkCoordinates();

        var device = _devices.get(deviceId);
        var group = _groups.get(groupId);
        checkDeviceType(device, DeviceType.MEMORY);
        queueRemoveDeviceFromGroup(deviceId, groupId);

        var groupRel = new GroupMemoryDeviceRelator.Builder().setGroup(group)
                                                             .setDeviceStatus(device.getMemoryDeviceStatus())
                                                             .build();

        _logger.trace("%s returning %s", groupRel);
        return groupRel;
    }

    @Override
    public MachineMemoryDeviceRelator removeMemoryDeviceFromMachine(
        Integer deviceId,
        Integer groupId,
        Integer machineId
    ) throws LiqidException {
        var fn = "removeMemoryDeviceFromMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);
        checkCoordinates();
        checkMachineExists(machineId);

        var device = _devices.get(deviceId);
        var group = _groups.get(groupId);
        var machine = _machines.get(machineId);
        checkDeviceType(device, DeviceType.MEMORY);
        queueRemoveDeviceFromMachine(deviceId, machineId);

        var groupRel = new GroupMemoryDeviceRelator.Builder().setGroup(group)
                                                             .setDeviceStatus(device.getMemoryDeviceStatus())
                                                             .build();
        var machineRel = new MachineMemoryDeviceRelator.Builder().setMachine(machine)
                                                                 .setGroupDeviceRelator(groupRel)
                                                                 .build();

        _logger.trace("%s returning %s", machineRel);
        return machineRel;
    }

    @Override
    public GroupNetworkDeviceRelator removeNetworkDeviceFromGroup(
        Integer deviceId,
        Integer groupId
    ) throws LiqidException {
        var fn = "removeNetworkDeviceFromGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkCoordinates();

        var device = _devices.get(deviceId);
        var group = _groups.get(groupId);
        checkDeviceType(device, DeviceType.ETHERNET_LINK);
        queueRemoveDeviceFromGroup(deviceId, groupId);

        var groupRel = new GroupNetworkDeviceRelator.Builder().setGroup(group)
                                                              .setDeviceStatus(device.getNetworkDeviceStatus())
                                                              .build();

        _logger.trace("%s returning %s", groupRel);
        return groupRel;
    }

    @Override
    public MachineNetworkDeviceRelator removeNetworkDeviceFromMachine(
        Integer deviceId,
        Integer groupId,
        Integer machineId
    ) throws LiqidException {
        var fn = "removeNetworkDeviceFromMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);
        checkCoordinates();
        checkMachineExists(machineId);

        var device = _devices.get(deviceId);
        var group = _groups.get(groupId);
        var machine = _machines.get(machineId);
        checkDeviceType(device, DeviceType.ETHERNET_LINK); // we only know about ethernet here in the mock
        queueRemoveDeviceFromMachine(deviceId, machineId);

        var groupRel = new GroupNetworkDeviceRelator.Builder().setGroup(group)
                                                              .setDeviceStatus(device.getNetworkDeviceStatus())
                                                              .build();
        var machineRel = new MachineNetworkDeviceRelator.Builder().setMachine(machine)
                                                                  .setGroupDeviceRelator(groupRel)
                                                                  .build();

        _logger.trace("%s returning %s", machineRel);
        return machineRel;
    }

    @Override
    public GroupStorageDeviceRelator removeStorageDeviceFromGroup(
        Integer deviceId,
        Integer groupId
    ) throws LiqidException {
        var fn = "removeStorageDeviceFromGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkCoordinates();

        var device = _devices.get(deviceId);
        var group = _groups.get(groupId);
        checkDeviceType(device, DeviceType.SSD);
        queueRemoveDeviceFromGroup(deviceId, groupId);

        var groupRel = new GroupStorageDeviceRelator.Builder().setGroup(group)
                                                              .setDeviceStatus(device.getStorageDeviceStatus())
                                                              .build();

        _logger.trace("%s returning %s", groupRel);
        return groupRel;
    }

    @Override
    public MachineStorageDeviceRelator removeStorageDeviceFromMachine(
        Integer deviceId,
        Integer groupId,
        Integer machineId
    ) throws LiqidException {
        var fn = "removeStorageDeviceFromMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);
        checkCoordinates();
        checkMachineExists(machineId);

        var device = _devices.get(deviceId);
        var group = _groups.get(groupId);
        var machine = _machines.get(machineId);
        checkDeviceType(device, DeviceType.SSD);
        queueRemoveDeviceFromMachine(deviceId, machineId);

        var groupRel = new GroupStorageDeviceRelator.Builder().setGroup(group)
                                                              .setDeviceStatus(device.getStorageDeviceStatus())
                                                              .build();
        var machineRel = new MachineStorageDeviceRelator.Builder().setMachine(machine)
                                                                  .setGroupDeviceRelator(groupRel)
                                                                  .build();

        _logger.trace("%s returning %s", machineRel);
        return machineRel;
    }

    @Override
    public Machine reprogramFabric(
        Integer machineId
    ) throws LiqidException {
        var fn = "reprogramFabric";
        _logger.trace("Entering %s machineId:%s", fn, machineId);
        checkParameterNotNull(machineId, "machineId", fn);
        checkCoordinates();
        checkMachineExists(machineId);
        checkMachineInEditMode(machineId);

        var machine = _machines.get(machineId);
        var groupId = machine.getGroupId();
        var group = _groups.get(groupId);

        for (var deviceId : _devicesToBeRemoved) {
            var device = _devices.get(deviceId);
            machine._attachedDevices.remove(deviceId);
            group._freePool.put(deviceId, device);
            _logger.info("Device 0x%08x moved from machine 0x%08x to group 0x%08x free pool",
                         deviceId, machineId, groupId);
        }
        _devicesToBeRemoved.clear();

        for (var deviceId : _devicesToBeAdded) {
            var device = _devices.get(deviceId);
            group._freePool.remove(deviceId);
            machine._attachedDevices.put(deviceId, device);
            _logger.info("Device 0x%08x moved from group 0x%08x free pool to machine 0x%08x",
                         deviceId, groupId, machineId);
        }
        _devicesToBeAdded.clear();

        _machineBeingEdited = null;
        _logger.info("Machine 0x%08a exiting edit mode", machineId);

        _logger.trace("%s returning %s", fn, machine);
        return machine;
    }

    @Override
    public Timestamp resetState() throws LiqidException {
        var fn = "resetState";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public Timestamp restartFabric() throws LiqidException {
        var fn = "restartFabric";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public Timestamp restartHierarchy() throws LiqidException {
        var fn = "restartHierarchy";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public Machine restartNode(
        Integer groupId,
        Integer machineId
    ) throws LiqidException {
        var fn = "restartNode";
        _logger.trace("Entering %s groupId:%s machineId:%s", fn, groupId, machineId);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);
        checkCoordinates();

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public Timestamp restartSwitch() throws LiqidException {
        var fn = "restartSwitch";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public AsynchronousInfo secureErase(
        Integer deviceId
    ) throws LiqidException {
        var fn = "secureErase";
        _logger.trace("Entering %s deviceId:%s", fn, deviceId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkCoordinates();

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public Coordinates setDefaultCoordinates(
        Integer rack,
        Integer shelf,
        Integer node
    ) throws LiqidException {
        var fn = "setDefaultCoordinates";
        _logger.trace("Entering %s rack:%s shelf:%s node:%s", fn, rack, shelf, node);
        checkParameterNotNull(node, "node", fn);

        _defaultCoordinates = new MockCoordinates(rack, shelf, node);
        _logger.trace("%s returning %s", fn, _defaultCoordinates);
        return _defaultCoordinates;
    }

    @Override
    public SSHStatus setSSHStatus(
        Boolean active,
        Boolean enabled
    ) throws LiqidException {
        var fn = "setSSHStatus";
        _logger.trace("Entering %s active:%s enabled:%s", fn, active, enabled);
        checkParameterNotNull(active, "active", fn);
        checkParameterNotNull(enabled, "enabled", fn);
        checkCoordinates();

        _sshStatus = new MockSSHStatus(enabled, active);
        _logger.trace("%s returning %s", fn, _sshStatus);
        return _sshStatus;
    }

    @Override
    public Coordinates shutdown() throws LiqidException {
        var fn = "shutdown";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        _logger.trace("%s returning %s", fn, _coordinates);
        return _coordinates;
    }

    @Override
    public Coordinates shutdownAt(
        Integer rackId,
        Integer shelfId,
        Integer nodeId
    ) throws LiqidException {
        var fn = "shutdownAt";
        _logger.trace("Entering %s rackId:%s shelfId:%s nodeId:%s", fn, rackId, shelfId, nodeId);
        checkParameterNotNull(rackId, "rackId", fn);
        checkParameterNotNull(shelfId, "shelfId", fn);
        checkParameterNotNull(nodeId, "nodeId", fn);

        var coordinates = new MockCoordinates(rackId, shelfId, nodeId);
        _logger.trace("%s returning %s", fn, coordinates);
        return coordinates;
    }

    @Override
    public Machine shutdownNode(
        Integer groupId,
        Integer machineId
    ) throws LiqidException {
        var fn = "shutdownNode";
        _logger.trace("Entering %s groupId:%s machineId:%s", fn, groupId, machineId);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);
        checkCoordinates();

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public Machine startNode(
        Integer groupId,
        Integer machineId
    ) throws LiqidException {
        var fn = "startNode";
        _logger.trace("Entering %s groupId:%s machineId:%s", fn, groupId, machineId);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);
        checkCoordinates();

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public Machine setP2PEnabled(
        Integer machineId,
        P2PType p2pEnabled
    ) throws LiqidException {
        var fn = "setP2PEnabled";
        _logger.trace("Entering %s machineId:%s p2pEnabled:%s", fn, machineId, p2pEnabled);
        checkParameterNotNull(machineId, "machineId", fn);
        checkParameterNotNull(p2pEnabled, "p2pEnabled", fn);
        checkCoordinates();
        checkMachineIdentifier(machineId);
        checkMachineExists(machineId);

        var machine = _machines.get(machineId);
        if (p2pEnabled.equals(machine.getP2PEnabled())) {
            var msg = String.format("P2P is already %s for machine with identifier %d", p2pEnabled, machineId);
            var ex = new LiqidException(msg);
            _logger.throwing(ex);
            throw ex;
        }

        machine.setP2PEnabled(p2pEnabled);

        _logger.trace("%s returning %s", machine);
        return machine;
    }
}
