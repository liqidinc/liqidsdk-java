// File: LiqidClientMock.java
//
// Copyright (c) 2024 Liqid, Inc. All rights reserved.
// 
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.mock;

import com.liqid.sdk.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This is not an exact mock, but it should be sufficient for casual use
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

    // unchanging
    private final MockCoordinates _coordinates;
    private final Integer _fabricIdentifier;
    private final Integer _podIdentifier;

    // can change over time
    private MockCoordinates _defaultCoordinates;
    private SSHStatus _sshStatus;

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

    // mock-specific methods

    private void checkCoordinates() throws WrongCoordinatesException {
        var ex = new WrongCoordinatesException(_defaultCoordinates, _coordinates);
        _logger.throwing(ex);
        throw ex;
    }

    private void checkDeviceExists(
        final Integer deviceId
    ) throws LiqidException {
        if (!_devices.containsKey(deviceId)) {
            var ex = new LiqidException(String.format("Device with identifier 0x%08x does not exist", deviceId));
            _logger.throwing(ex);
            throw ex;
        }
    }

    private void checkDeviceType(
        final Integer deviceId,
        final DeviceType type
    ) throws LiqidException {
        var d = _devices.get(deviceId);
        if (d == null) {
            var ex = new LiqidException(String.format("Device with identifier 0x%08x does not exist", deviceId));
            _logger.throwing(ex);
            throw ex;
        } else {
            if (!d.getDeviceType().equals(type)) {
                var ex = new LiqidException(String.format("Device with identifier 0x%08x is not of type %s",
                                                          deviceId, type));
                _logger.throwing(ex);
                throw ex;
            }
        }
    }

    private void checkGroupExists(
        final Integer groupId
    ) throws LiqidException {
        if (!_groups.containsKey(groupId)) {
            var ex = new LiqidException(String.format("Group with identifier %d does not exist", groupId));
            _logger.throwing(ex);
            throw ex;
        }
    }

    private void checkGroupIdentifier(
        final Integer machineId
    ) throws LiqidException {
        if (!isValidGroupIdentifier(machineId)) {
            var ex = new LiqidException("Group ID must be greater than 0");
            _logger.throwing(ex);
            throw ex;
        }
    }

    private void checkMachineExists(
        final Integer machineId
    ) throws LiqidException {
        if (!_machines.containsKey(machineId)) {
            var ex = new LiqidException(String.format("Machine with identifier %d does not exist", machineId));
            _logger.throwing(ex);
            throw ex;
        }
    }

    private void checkMachineIdentifier(
        final Integer machineId
    ) throws LiqidException {
        if (!isValidMachineIdentifier(machineId)) {
            var ex = new LiqidException("Machine ID must be greater than 0 and less than 16384");
            _logger.throwing(ex);
            throw ex;
        }
    }

    private boolean isValidGroupIdentifier(
        final Integer groupId
    ) {
        return groupId > 0;
    }

    private boolean isValidMachineIdentifier(
        final Integer machineId
    ) {
        return (machineId > 0) && (machineId < 16384);
    }

    private void mockRemoveDeviceFromGroup(
        final DeviceStatus deviceStatus,
        final Group group
    ) {
        // TODO
    }

    private void mockRemoveDeviceFromMachine(
        final DeviceStatus deviceStatus,
        final Group group,
        final Machine machine
    ) {
        // TODO
    }

    // mocked API methods

    @Override
    public GroupComputeDeviceRelator addComputeDeviceToGroup(
        Integer deviceId,
        Integer groupId
    ) throws LiqidException {
        var fn = "addComputeDeviceToGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", "addComputeDeviceToGroup");
        checkParameterNotNull(groupId, "groupId", "addComputeDeviceToGroup");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public GroupComputeDeviceRelator addComputeDeviceToMachine(
        Integer deviceId,
        Integer groupId,
        Integer machineId
    ) throws LiqidException {
        var fn = "addComputeDeviceToMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", "addComputeDeviceToMachine");
        checkParameterNotNull(groupId, "groupId", "addComputeDeviceToMachine");
        checkParameterNotNull(machineId, "machineId", "addComputeDeviceToMachine");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public GroupFPGADeviceRelator addFPGADeviceToGroup(
        Integer deviceId,
        Integer groupId
    ) throws LiqidException {
        var fn = "addFPGADeviceToGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", "addFPGADeviceToGroup");
        checkParameterNotNull(groupId, "groupId", "addFPGADeviceToGroup");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public GroupFPGADeviceRelator addFPGADeviceToMachine(
        Integer deviceId,
        Integer groupId,
        Integer machineId
    ) throws LiqidException {
        var fn = "addFPGADeviceToMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", "addFPGADeviceToMachine");
        checkParameterNotNull(groupId, "groupId", "addFPGADeviceToMachine");
        checkParameterNotNull(machineId, "machineId", "addFPGADeviceToMachine");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public GroupGPUDeviceRelator addGPUDeviceToGroup(
        Integer deviceId,
        Integer groupId
    ) throws LiqidException {
        var fn = "addGPUDeviceToGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", "addGPUDeviceToGroup");
        checkParameterNotNull(groupId, "groupId", "addGPUDeviceToGroup");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public GroupGPUDeviceRelator addGPUDeviceToMachine(
        Integer deviceId,
        Integer groupId,
        Integer machineId
    ) throws LiqidException {
        var fn = "addGPUDeviceToMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", "addGPUDeviceToMachine");
        checkParameterNotNull(groupId, "groupId", "addGPUDeviceToMachine");
        checkParameterNotNull(machineId, "machineId", "addGPUDeviceToMachine");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public GroupMemoryDeviceRelator addMemoryDeviceToGroup(
        Integer deviceId,
        Integer groupId
    ) throws LiqidException {
        var fn = "addMemoryDeviceToGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", "addMemoryDeviceToGroup");
        checkParameterNotNull(groupId, "groupId", "addMemoryDeviceToGroup");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public GroupMemoryDeviceRelator addMemoryDeviceToMachine(
        Integer deviceId,
        Integer groupId,
        Integer machineId
    ) throws LiqidException {
        var fn = "addMemoryDeviceToMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", "addMemoryDeviceToMachine");
        checkParameterNotNull(groupId, "groupId", "addMemoryDeviceToMachine");
        checkParameterNotNull(machineId, "machineId", "addMemoryDeviceToMachine");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public GroupNetworkDeviceRelator addNetworkDeviceToGroup(
        Integer deviceId,
        Integer groupId
    ) throws LiqidException {
        var fn = "addNetworkDeviceToGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", "addNetworkDeviceToGroup");
        checkParameterNotNull(groupId, "groupId", "addNetworkDeviceToGroup");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public GroupNetworkDeviceRelator addNetworkDeviceToMachine(
        Integer deviceId,
        Integer groupId,
        Integer machineId
    ) throws LiqidException {
        var fn = "addNetworkDeviceToMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", "addNetworkDeviceToMachine");
        checkParameterNotNull(groupId, "groupId", "addNetworkDeviceToMachine");
        checkParameterNotNull(machineId, "machineId", "addNetworkDeviceToMachine");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public GroupStorageDeviceRelator addStorageDeviceToGroup(
        Integer deviceId,
        Integer groupId
    ) throws LiqidException {
        var fn = "addStorageDeviceToGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", "addStorageDeviceToGroup");
        checkParameterNotNull(groupId, "groupId", "addStorageDeviceToGroup");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public GroupStorageDeviceRelator addStorageDeviceToMachine(
        Integer deviceId,
        Integer groupId,
        Integer machineId
    ) throws LiqidException {
        var fn = "addStorageDeviceToMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", "addStorageDeviceToMachine");
        checkParameterNotNull(groupId, "groupId", "addStorageDeviceToMachine");
        checkParameterNotNull(machineId, "machineId", "addStorageDeviceToMachine");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public FabricToggleComposite applyFabricChanges(
        String flagName,
        String flagValue,
        FabricToggleCompositeOption operation
    ) throws LiqidException {
        var fn = "applyFabricChanges";
        _logger.trace("Entering %s flagName:%s flagValue:%s operation:%s", fn, flagName, flagValue, operation);
        checkParameterNotNull(flagName, "flagName", "applyFabricChanges");
        checkParameterNotNull(flagValue, "flagValue", "applyFabricChanges");
        checkParameterNotNull(operation, "operation", "applyFabricChanges");
        checkCoordinates();

        // TODO

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
        checkParameterNotNull(destination, "destination", "backupDirector");
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
        checkParameterNotNull(machineId, "machineId", "cancelEditFabric");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public GroupPool cancelGroupPoolEdit(
        Integer groupId
    ) throws LiqidException {
        var fn = "cancelGroupPoolEdit";
        _logger.trace("Entering %s groupId:%s", fn, groupId);
        checkParameterNotNull(groupId, "groupId", "cancelGroupPoolEdit");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public Machine cancelReprogramFabric(
        Integer machineId
    ) throws LiqidException {
        var fn = "cancelReprogramFabric";
        _logger.trace("Entering %s machineId:%s", fn, machineId);
        checkParameterNotNull(machineId, "machineId", "cancelReprogramFabric");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public Boolean clearGroups() throws LiqidException {
        var fn = "clearGroups";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public DeviceUserDescription createDeviceDescription(
        DeviceQueryType queryDeviceType,
        Integer deviceId,
        String description
    ) throws LiqidException {
        var fn = "createDeviceDescription";
        _logger.trace("Entering %s queryDeviceType:%s deviceId:%s description:%s", fn, queryDeviceType, deviceId, description);
        checkParameterNotNull(queryDeviceType, "queryDeviceType", "createDeviceDescription");
        checkParameterNotNull(deviceId, "deviceId", "createDeviceDescription");
        checkParameterNotNull(description, "description", "createDeviceDescription");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    @Deprecated
    public Group createGroupWithId(
        Integer groupId,
        String groupName
    ) throws LiqidException {
        var fn = "createGroupWithId";
        _logger.trace("Entering %s groupId:%s groupName:%s", fn, groupId, groupName);
        checkParameterNotNull(groupId, "groupId", "createGroupWithId");
        checkParameterNotNull(groupName, "groupName", "createGroupWithId");
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
        _logger.trace("Entering %s groupId:%s machineId:%s machineName:%s", fn, groupId, machineId, machineName);
        checkParameterNotNull(groupId, "groupId", "createMachineWithId");
        checkParameterNotNull(machineId, "machineId", "createMachineWithId");
        checkParameterNotNull(machineName, "machineName", "createMachineWithId");
        checkCoordinates();
        checkGroupIdentifier(groupId);
        checkGroupExists(groupId);
        checkMachineIdentifier(machineId);

        if (_machines.containsKey(machineId)) {
            var ex = new LiqidException(String.format("Machine identifier %d is already in use", machineId));
            _logger.throwing(ex);
            throw ex;
        }

        var machine = new MockMachine(0, machineId, groupId, _fabricIdentifier, machineName);
        _machines.put(machineId, machine);
        _groups.get(groupId).addMachineId(machineId);

        _logger.trace("%s returning %s", fn, machine);
        return machine;
    }

    @Override
    public DigestInfo createMessageDigest(
        String label
    ) throws LiqidException {
        var fn = "createMessageDigest";
        _logger.trace("Entering %s label:%s", fn, label);
        checkParameterNotNull(label, "label", "createMessageDigest");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public DeviceUserDescription deleteDeviceDescription(
        DeviceQueryType queryDeviceType,
        Integer deviceId
    ) throws LiqidException {
        var fn = "deleteDeviceDescription";
        _logger.trace("Entering %s queryDeviceType:%s deviceId:%s", fn, queryDeviceType, deviceId);
        checkParameterNotNull(queryDeviceType, "queryDeviceType", "deleteDeviceDescription");
        checkParameterNotNull(deviceId, "deviceId", "deleteDeviceDescription");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public Group deleteGroup(
        Integer groupId
    ) throws LiqidException {
        var fn = "deleteGroup";
        _logger.trace("Entering %s groupId:%s", fn, groupId);
        checkParameterNotNull(groupId, "groupId", "deleteGroup");
        checkCoordinates();
        checkGroupIdentifier(groupId);
        checkGroupExists(groupId);

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public Machine deleteMachine(
        Integer machineId
    ) throws LiqidException {
        var fn = "deleteMachine";
        _logger.trace("Entering %s machineId:%s", fn, machineId);
        checkParameterNotNull(machineId, "machineId", "deleteMachine");
        checkCoordinates();
        checkMachineIdentifier(machineId);
        checkMachineExists(machineId);

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public Machine editFabric(
        Integer machineId
    ) throws LiqidException {
        var fn = "editFabric";
        _logger.trace("Entering %s machineId:%s", fn, machineId);
        checkParameterNotNull(machineId, "machineId", "editFabric");
        checkCoordinates();
        checkMachineIdentifier(machineId);
        checkMachineExists(machineId);

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public LinkedList<DeviceStatus> getAllDevicesStatus() throws LiqidException {
        var fn = "getAllDevicesStatus";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
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
        checkParameterNotNull(deviceId, "deviceId", "getComputeDeviceStatus");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public LinkedList<ComputeDeviceStatus> getComputeDevicesStatus() throws LiqidException {
        var fn = "getComputeDevicesStatus";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public LinkedList<ComputeDeviceStatus> getComputeDevicesStatusWithMultiplePortsStatus() throws LiqidException {
        var fn = "getComputeDevicesStatusWithMultiplePortsStatus";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public LinkedList<ComputeDeviceInfo> getComputeDeviceInfo() throws LiqidException {
        var fn = "getComputeDeviceInfo";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public ComputeDeviceInfo getComputeDeviceInfoByName(
        String deviceName
    ) throws LiqidException {
        var fn = "getComputeDeviceInfoByName";
        _logger.trace("Entering %s deviceName:%s", fn, deviceName);
        checkParameterNotNull(deviceName, "deviceName", "getComputeDeviceInfoByName");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
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
    public LinkedList<PreDevice> getDevices(
        DeviceQueryType queryDeviceType,
        Integer groupId,
        Integer machineId
    ) throws LiqidException {
        var fn = "getDevices";
        _logger.trace("Entering %s queryDeviceType:%s groupId:%s machineId:%s", fn, queryDeviceType, groupId, machineId);
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
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
        checkParameterNotNull(tokenType, "tokenType", "getDiscoveryTokensByType");
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
        checkParameterNotNull(deviceId, "deviceId", "getFPGADeviceStatus");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public LinkedList<FPGADeviceStatus> getFPGADevicesStatus() throws LiqidException {
        var fn = "getFPGADevicesStatus";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public LinkedList<FPGADeviceInfo> getFPGADeviceInfo() throws LiqidException {
        var fn = "getFPGADeviceInfo";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public FPGADeviceInfo getFPGADeviceInfoByName(
        String deviceName
    ) throws LiqidException {
        var fn = "getFPGADeviceInfoByName";
        _logger.trace("Entering %s deviceName:%s", fn, deviceName);
        checkParameterNotNull(deviceName, "deviceName", "getFPGADeviceInfoByName");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public LinkedList<ComputeDeviceStatus> getFreeComputeDevicesStatus() throws LiqidException {
        var fn = "getFreeComputeDevicesStatus";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public LinkedList<FPGADeviceStatus> getFreeFPGADevicesStatus() throws LiqidException {
        var fn = "getFreeFPGADevicesStatus";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public LinkedList<GPUDeviceStatus> getFreeGPUDevicesStatus() throws LiqidException {
        var fn = "getFreeGPUDevicesStatus";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public LinkedList<MemoryDeviceStatus> getFreeMemoryDevicesStatus() throws LiqidException {
        var fn = "getFreeMemoryDevicesStatus";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public LinkedList<NetworkDeviceStatus> getFreeNetworkDevicesStatus() throws LiqidException {
        var fn = "getFreeNetworkDevicesStatus";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public LinkedList<StorageDeviceStatus> getFreeStorageDevicesStatus() throws LiqidException {
        var fn = "getFreeStorageDevicesStatus";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public GPUDeviceStatus getGPUDeviceStatus(
        Integer deviceId
    ) throws LiqidException {
        var fn = "getGPUDeviceStatus";
        _logger.trace("Entering %s deviceId:%s", fn, deviceId);
        checkParameterNotNull(deviceId, "deviceId", "getGPUDeviceStatus");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public LinkedList<GPUDeviceStatus> getGPUDevicesStatus() throws LiqidException {
        var fn = "getGPUDevicesStatus";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public LinkedList<GPUDeviceInfo> getGPUDeviceInfo() throws LiqidException {
        var fn = "getGPUDeviceInfo";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public GPUDeviceInfo getGPUDeviceInfoByName(
        String deviceName
    ) throws LiqidException {
        var fn = "getGPUDeviceInfoByName";
        _logger.trace("Entering %s deviceName:%s", fn, deviceName);
        checkParameterNotNull(deviceName, "deviceName", "getGPUDeviceInfoByName");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public Group getGroup(
        Integer groupId
    ) throws LiqidException {
        var fn = "getGroup";
        _logger.trace("Entering %s groupId:%s", fn, groupId);
        checkParameterNotNull(groupId, "groupId", "getGroup");
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
        var fn = "getGroupComputeDeviceRelator";
        _logger.trace("Entering %s groupId:%s deviceId:%s", fn, groupId, deviceId);
        checkParameterNotNull(groupId, "groupId", "getGroupComputeDeviceRelator");
        checkParameterNotNull(deviceId, "deviceId", "getGroupComputeDeviceRelator");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public GroupFPGADeviceRelator getGroupFPGADeviceRelator(
        Integer groupId,
        Integer deviceId
    ) throws LiqidException {
        var fn = "getGroupFPGADeviceRelator";
        _logger.trace("Entering %s groupId:%s deviceId:%s", fn, groupId, deviceId);
        checkParameterNotNull(groupId, "groupId", "getGroupFPGADeviceRelator");
        checkParameterNotNull(deviceId, "deviceId", "getGroupFPGADeviceRelator");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public GroupGPUDeviceRelator getGroupGPUDeviceRelator(
        Integer groupId,
        Integer deviceId
    ) throws LiqidException {
        var fn = "getGroupGPUDeviceRelator";
        _logger.trace("Entering %s groupId:%s deviceId:%s", fn, groupId, deviceId);
        checkParameterNotNull(groupId, "groupId", "getGroupGPUDeviceRelator");
        checkParameterNotNull(deviceId, "deviceId", "getGroupGPUDeviceRelator");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public GroupMemoryDeviceRelator getGroupMemoryDeviceRelator(
        Integer groupId,
        Integer deviceId
    ) throws LiqidException {
        var fn = "getGroupMemoryDeviceRelator";
        _logger.trace("Entering %s groupId:%s deviceId:%s", fn, groupId, deviceId);
        checkParameterNotNull(groupId, "groupId", "getGroupMemoryDeviceRelator");
        checkParameterNotNull(deviceId, "deviceId", "getGroupMemoryDeviceRelator");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public GroupNetworkDeviceRelator getGroupNetworkDeviceRelator(
        Integer groupId,
        Integer deviceId
    ) throws LiqidException {
        var fn = "getGroupNetworkDeviceRelator";
        _logger.trace("Entering %s groupId:%s deviceId:%s", fn, groupId, deviceId);
        checkParameterNotNull(groupId, "groupId", "getGroupNetworkDeviceRelator");
        checkParameterNotNull(deviceId, "deviceId", "getGroupNetworkDeviceRelator");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public GroupStorageDeviceRelator getGroupStorageDeviceRelator(
        Integer groupId,
        Integer deviceId
    ) throws LiqidException {
        var fn = "getGroupStorageDeviceRelator";
        _logger.trace("Entering %s groupId:%s deviceId:%s", fn, groupId, deviceId);
        checkParameterNotNull(groupId, "groupId", "getGroupStorageDeviceRelator");
        checkParameterNotNull(deviceId, "deviceId", "getGroupStorageDeviceRelator");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public GroupDetails getGroupDetails(
        Integer groupId
    ) throws LiqidException {
        var fn = "getGroupDetails";
        _logger.trace("Entering %s groupId:%s", fn, groupId);
        checkParameterNotNull(groupId, "groupId", "getGroupDetails");
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
        checkParameterNotNull(groupName, "groupName", "getGroupIdByName");
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
        checkParameterNotNull(machineId, "machineId", "getMachine");
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
        checkParameterNotNull(rackId, "rackId", "getMachinesAtCoordinates");
        checkParameterNotNull(shelfId, "shelfId", "getMachinesAtCoordinates");
        checkParameterNotNull(nodeId, "nodeId", "getMachinesAtCoordinates");

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
        checkParameterNotNull(groupId, "groupId", "getMachinesByGroupId");
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
        checkParameterNotNull(machineName, "machineName", "getMachineByName");
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
        checkParameterNotNull(machineId, "machineId", "getMachineDetails");
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
        checkParameterNotNull(pciVendorId, "pciVendorId", "getManagedEntity");
        checkParameterNotNull(pciDeviceId, "pciDeviceId", "getManagedEntity");
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

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public MemoryDeviceInfo getMemoryDeviceInfoByName(
        String deviceName
    ) throws LiqidException {
        var fn = "getMemoryDeviceInfoByName";
        _logger.trace("Entering %s deviceName:%s", fn, deviceName);
        checkParameterNotNull(deviceName, "deviceName", "getMemoryDeviceInfoByName");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public MemoryDeviceStatus getMemoryDeviceStatus(
        Integer deviceId
    ) throws LiqidException {
        var fn = "getMemoryDeviceStatus";
        _logger.trace("Entering %s deviceId:%s", fn, deviceId);
        checkParameterNotNull(deviceId, "deviceId", "getMemoryDeviceStatus");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public LinkedList<MemoryDeviceStatus> getMemoryDevicesStatus() throws LiqidException {
        var fn = "getMemoryDevicesStatus";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
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

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public NetworkDeviceInfo getNetworkDeviceInfoByName(
        String deviceName
    ) throws LiqidException {
        var fn = "getNetworkDeviceInfoByName";
        _logger.trace("Entering %s deviceName:%s", fn, deviceName);
        checkParameterNotNull(deviceName, "deviceName", "getNetworkDeviceInfoByName");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public NetworkDeviceStatus getNetworkDeviceStatus(
        Integer deviceId
    ) throws LiqidException {
        var fn = "getNetworkDeviceStatus";
        _logger.trace("Entering %s deviceId:%s", fn, deviceId);
        checkParameterNotNull(deviceId, "deviceId", "getNetworkDeviceStatus");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public LinkedList<NetworkDeviceStatus> getNetworkDevicesStatus() throws LiqidException {
        var fn = "getNetworkDevicesStatus";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public NetworkManagedCPU getNetworkIPMIManagedCPU(
        String cpuName
    ) throws LiqidException {
        var fn = "getNetworkIPMIManagedCPU";
        _logger.trace("Entering %s cpuName:%s", fn, cpuName);
        checkParameterNotNull(cpuName, "cpuName", "getNetworkIPMIManagedCPU");
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
        checkParameterNotNull(enclosureName, "enclosureName", "getNetworkIPMIManagedEnclosure");
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
        checkParameterNotNull(deviceId, "deviceId", "getSecureEraseStatus");
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
        checkParameterNotNull(deviceId, "deviceId", "getStorageDeviceStatus");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public LinkedList<StorageDeviceStatus> getStorageDevicesStatus() throws LiqidException {
        var fn = "getStorageDevicesStatus";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public LinkedList<StorageDeviceInfo> getStorageDeviceInfo() throws LiqidException {
        var fn = "getStorageDeviceInfo";
        _logger.trace("Entering %s", fn);
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public StorageDeviceInfo getStorageDeviceInfoByName(
        String deviceName
    ) throws LiqidException {
        var fn = "getStorageDeviceInfoByName";
        _logger.trace("Entering %s deviceName:%s", fn, deviceName);
        checkParameterNotNull(deviceName, "deviceName", "getStorageDeviceInfoByName");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
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
        checkParameterNotNull(groupId, "groupId", "groupPoolDone");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public GroupPool groupPoolEdit(
        Integer groupId
    ) throws LiqidException {
        var fn = "groupPoolEdit";
        _logger.trace("Entering %s groupId:%s", fn, groupId);
        checkParameterNotNull(groupId, "groupId", "groupPoolEdit");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public Machine rebootNode(
        Integer groupId,
        Integer machineId
    ) throws LiqidException {
        var fn = "rebootNode";
        _logger.trace("Entering %s groupId:%s machineId:%s", fn, groupId, machineId);
        checkParameterNotNull(groupId, "groupId", "rebootNode");
        checkParameterNotNull(machineId, "machineId", "rebootNode");
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
        checkParameterNotNull(deviceId, "deviceId", "removeComputeDeviceFromGroup");
        checkParameterNotNull(groupId, "groupId", "removeComputeDeviceFromGroup");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public MachineComputeDeviceRelator removeComputeDeviceFromMachine(
        Integer deviceId,
        Integer groupId,
        Integer machineId
    ) throws LiqidException {
        var fn = "removeComputeDeviceFromMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", "removeComputeDeviceFromMachine");
        checkParameterNotNull(groupId, "groupId", "removeComputeDeviceFromMachine");
        checkParameterNotNull(machineId, "machineId", "removeComputeDeviceFromMachine");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public GroupFPGADeviceRelator removeFPGADeviceFromGroup(
        Integer deviceId,
        Integer groupId
    ) throws LiqidException {
        var fn = "removeFPGADeviceFromGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", "removeFPGADeviceFromGroup");
        checkParameterNotNull(groupId, "groupId", "removeFPGADeviceFromGroup");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public MachineFPGADeviceRelator removeFPGADeviceFromMachine(
        Integer deviceId,
        Integer groupId,
        Integer machineId
    ) throws LiqidException {
        var fn = "removeFPGADeviceFromMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", "removeFPGADeviceFromMachine");
        checkParameterNotNull(groupId, "groupId", "removeFPGADeviceFromMachine");
        checkParameterNotNull(machineId, "machineId", "removeFPGADeviceFromMachine");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public GroupGPUDeviceRelator removeGPUDeviceFromGroup(
        Integer deviceId,
        Integer groupId
    ) throws LiqidException {
        var fn = "removeGPUDeviceFromGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", "removeGPUDeviceFromGroup");
        checkParameterNotNull(groupId, "groupId", "removeGPUDeviceFromGroup");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public MachineGPUDeviceRelator removeGPUDeviceFromMachine(
        Integer deviceId,
        Integer groupId,
        Integer machineId
    ) throws LiqidException {
        var fn = "removeGPUDeviceFromMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", "removeGPUDeviceFromMachine");
        checkParameterNotNull(groupId, "groupId", "removeGPUDeviceFromMachine");
        checkParameterNotNull(machineId, "machineId", "removeGPUDeviceFromMachine");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public GroupMemoryDeviceRelator removeMemoryDeviceFromGroup(
        Integer deviceId,
        Integer groupId
    ) throws LiqidException {
        var fn = "removeMemoryDeviceFromGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", "removeMemoryDeviceFromGroup");
        checkParameterNotNull(groupId, "groupId", "removeMemoryDeviceFromGroup");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public MachineMemoryDeviceRelator removeMemoryDeviceFromMachine(
        Integer deviceId,
        Integer groupId,
        Integer machineId
    ) throws LiqidException {
        var fn = "removeMemoryDeviceFromMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", "removeMemoryDeviceFromMachine");
        checkParameterNotNull(groupId, "groupId", "removeMemoryDeviceFromMachine");
        checkParameterNotNull(machineId, "machineId", "removeMemoryDeviceFromMachine");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public GroupNetworkDeviceRelator removeNetworkDeviceFromGroup(
        Integer deviceId,
        Integer groupId
    ) throws LiqidException {
        var fn = "removeNetworkDeviceFromGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", "removeNetworkDeviceFromGroup");
        checkParameterNotNull(groupId, "groupId", "removeNetworkDeviceFromGroup");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public MachineNetworkDeviceRelator removeNetworkDeviceFromMachine(
        Integer deviceId,
        Integer groupId,
        Integer machineId
    ) throws LiqidException {
        var fn = "removeNetworkDeviceFromMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", "removeNetworkDeviceFromMachine");
        checkParameterNotNull(groupId, "groupId", "removeNetworkDeviceFromMachine");
        checkParameterNotNull(machineId, "machineId", "removeNetworkDeviceFromMachine");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public GroupStorageDeviceRelator removeStorageDeviceFromGroup(
        Integer deviceId,
        Integer groupId
    ) throws LiqidException {
        var fn = "removeStorageDeviceFromGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", "removeStorageDeviceFromGroup");
        checkParameterNotNull(groupId, "groupId", "removeStorageDeviceFromGroup");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
    }

    @Override
    public MachineStorageDeviceRelator removeStorageDeviceFromMachine(
        Integer deviceId,
        Integer groupId,
        Integer machineId
    ) throws LiqidException {
        var fn = "removeStorageDeviceFromMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", "removeStorageDeviceFromMachine");
        checkParameterNotNull(groupId, "groupId", "removeStorageDeviceFromMachine");
        checkParameterNotNull(machineId, "machineId", "removeStorageDeviceFromMachine");
        checkCoordinates();
        checkMachineExists(machineId);
        checkDeviceType(deviceId, DeviceType.SSD);

        var device = _devices.get(deviceId);
        var group = _groups.get(groupId);
        var machine = _machines.get(machineId);
        mockRemoveDeviceFromMachine(device.getDeviceStatus(), group, machine);

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
        checkParameterNotNull(machineId, "machineId", "reprogramFabric");
        checkCoordinates();

        // TODO

        var ex = new MethodNotImplementedException(fn);
        _logger.throwing(ex);
        throw ex;
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
        checkParameterNotNull(groupId, "groupId", "restartNode");
        checkParameterNotNull(machineId, "machineId", "restartNode");
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
        checkParameterNotNull(deviceId, "deviceId", "secureErase");
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
        checkParameterNotNull(node, "node", "setDefaultCoordinates");

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
        checkParameterNotNull(active, "active", "setSSHStatus");
        checkParameterNotNull(enabled, "enabled", "setSSHStatus");
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
        checkParameterNotNull(rackId, "rackId", "shutdownAt");
        checkParameterNotNull(shelfId, "shelfId", "shutdownAt");
        checkParameterNotNull(nodeId, "nodeId", "shutdownAt");

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
        checkParameterNotNull(groupId, "groupId", "shutdownNode");
        checkParameterNotNull(machineId, "machineId", "shutdownNode");
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
        checkParameterNotNull(groupId, "groupId", "startNode");
        checkParameterNotNull(machineId, "machineId", "startNode");
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
        checkParameterNotNull(machineId, "machineId", "setP2PEnabled");
        checkParameterNotNull(p2pEnabled, "p2pEnabled", "setP2PEnabled");
        checkCoordinates();
        checkMachineIdentifier(machineId);
        checkMachineExists(machineId);

        var machine = _machines.get(machineId);
        if (p2pEnabled.equals(machine.getP2PEnabled())) {
            var ex = new LiqidException(String.format("P2P is already %s for machine with identifier %d", p2pEnabled, machineId));
            _logger.throwing(ex);
            throw ex;
        }

        machine.setP2PEnabled(p2pEnabled);

        _logger.trace("%s returning %s", machine);
        return machine;
    }
}
