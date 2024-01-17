// File: LiqidClient.java
//
// Copyright (c) 2022-2023 Liqid, Inc. All rights reserved.
// 
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//
// Liqid SDK - Version 3.4
// This file was automatically generated - do not modify it directly.
//

package com.liqid.sdk;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.LinkedList;

/**
 * LiqidClient
 * This struct is necessary for managing all communication with the Liqid Director.
 * For OO languages, it serves as the class on which all methods are defined.
 * For non-OO languages, it is a required parameter for all function invocations.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LiqidClient extends LiqidClientBase {

    /**
     * addComputeDeviceToGroup()
     * Category: GroupDeviceRelator
     * Moves a device from the system free pool to the group free pool for the indicated group
     * @param deviceId: Identifier of the device to be added to a group free pool.
     * @param groupId: Identifier of the group to which the device is to be added.
     * @return A description of the relation being created
     * @throws LiqidException if anything goes wrong
     */
    public GroupComputeDeviceRelator addComputeDeviceToGroup(Integer deviceId,
                                                             Integer groupId) throws LiqidException {
        var fn = "addComputeDeviceToGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        try {
            var composite = new GroupComputeDeviceRelator();
            composite.setDeviceStatus(getComputeDeviceStatus(deviceId));
            composite.setGroup(getGroup(groupId));

            var path = "predevice/compute";
            var httpResponse = send("POST", path, HttpBodyType.Json, composite, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), GroupComputeDeviceRelator.GroupComputeDeviceRelatorWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * addComputeDeviceToMachine()
     * Category: MachineDeviceRelator
     * Attaches a particular device to a machine
     * @param deviceId: Unique identifier for the device to be added
     * @param groupId: Unique identifier for the group to which the machine belongs
     * @param machineId: Unique identifier for the machine to which the device is to be added
     * @return A description of the relation being created
     * @throws LiqidException if anything goes wrong
     */
    public MachineComputeDeviceRelator addComputeDeviceToMachine(Integer deviceId,
                                                                 Integer groupId,
                                                                 Integer machineId) throws LiqidException {
        var fn = "addComputeDeviceToMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);
        try {
            var composite = new MachineComputeDeviceRelator();
            composite.setGroupDeviceRelator(getGroupComputeDeviceRelator(groupId, deviceId));
            composite.setMachine(getMachine(machineId));

            var path = "relate/compute";
            var httpResponse = send("POST", path, HttpBodyType.Json, composite, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), MachineComputeDeviceRelator.MachineComputeDeviceRelatorWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * addFPGADeviceToGroup()
     * Category: GroupDeviceRelator
     * Moves a device from the system free pool to the group free pool for the indicated group
     * @param deviceId: Identifier of the device to be added to a group free pool.
     * @param groupId: Identifier of the group to which the device is to be added.
     * @return A description of the relation being created
     * @throws LiqidException if anything goes wrong
     */
    public GroupFPGADeviceRelator addFPGADeviceToGroup(Integer deviceId,
                                                       Integer groupId) throws LiqidException {
        var fn = "addFPGADeviceToGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        try {
            var composite = new GroupFPGADeviceRelator();
            composite.setDeviceStatus(getFPGADeviceStatus(deviceId));
            composite.setGroup(getGroup(groupId));

            var path = "predevice/fpga";
            var httpResponse = send("POST", path, HttpBodyType.Json, composite, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), GroupFPGADeviceRelator.GroupFPGADeviceRelatorWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * addFPGADeviceToMachine()
     * Category: MachineDeviceRelator
     * Attaches a particular device to a machine
     * @param deviceId: Unique identifier for the device to be added
     * @param groupId: Unique identifier for the group to which the machine belongs
     * @param machineId: Unique identifier for the machine to which the device is to be added
     * @return A description of the relation being created
     * @throws LiqidException if anything goes wrong
     */
    public MachineFPGADeviceRelator addFPGADeviceToMachine(Integer deviceId,
                                                           Integer groupId,
                                                           Integer machineId) throws LiqidException {
        var fn = "addFPGADeviceToMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);
        try {
            var composite = new MachineFPGADeviceRelator();
            composite.setGroupDeviceRelator(getGroupFPGADeviceRelator(groupId, deviceId));
            composite.setMachine(getMachine(machineId));

            var path = "relate/fpga";
            var httpResponse = send("POST", path, HttpBodyType.Json, composite, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), MachineFPGADeviceRelator.MachineFPGADeviceRelatorWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * addGPUDeviceToGroup()
     * Category: GroupDeviceRelator
     * Moves a device from the system free pool to the group free pool for the indicated group
     * @param deviceId: Identifier of the device to be added to a group free pool.
     * @param groupId: Identifier of the group to which the device is to be added.
     * @return A description of the relation being created
     * @throws LiqidException if anything goes wrong
     */
    public GroupGPUDeviceRelator addGPUDeviceToGroup(Integer deviceId,
                                                     Integer groupId) throws LiqidException {
        var fn = "addGPUDeviceToGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        try {
            var composite = new GroupGPUDeviceRelator();
            composite.setDeviceStatus(getGPUDeviceStatus(deviceId));
            composite.setGroup(getGroup(groupId));

            var path = "predevice/gpu";
            var httpResponse = send("POST", path, HttpBodyType.Json, composite, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), GroupGPUDeviceRelator.GroupGPUDeviceRelatorWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * addGPUDeviceToMachine()
     * Category: MachineDeviceRelator
     * Attaches a particular device to a machine
     * @param deviceId: Unique identifier for the device to be added
     * @param groupId: Unique identifier for the group to which the machine belongs
     * @param machineId: Unique identifier for the machine to which the device is to be added
     * @return A description of the relation being created
     * @throws LiqidException if anything goes wrong
     */
    public MachineGPUDeviceRelator addGPUDeviceToMachine(Integer deviceId,
                                                         Integer groupId,
                                                         Integer machineId) throws LiqidException {
        var fn = "addGPUDeviceToMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);
        try {
            var composite = new MachineGPUDeviceRelator();
            composite.setGroupDeviceRelator(getGroupGPUDeviceRelator(groupId, deviceId));
            composite.setMachine(getMachine(machineId));

            var path = "relate/gpu";
            var httpResponse = send("POST", path, HttpBodyType.Json, composite, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), MachineGPUDeviceRelator.MachineGPUDeviceRelatorWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * addMemoryDeviceToGroup()
     * Category: GroupDeviceRelator
     * Moves a device from the system free pool to the group free pool for the indicated group
     * @param deviceId: Identifier of the device to be added to a group free pool.
     * @param groupId: Identifier of the group to which the device is to be added.
     * @return A description of the relation being created
     * @throws LiqidException if anything goes wrong
     */
    public GroupMemoryDeviceRelator addMemoryDeviceToGroup(Integer deviceId,
                                                           Integer groupId) throws LiqidException {
        var fn = "addMemoryDeviceToGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        try {
            var composite = new GroupMemoryDeviceRelator();
            composite.setGroup(getGroup(groupId));
            composite.setDeviceStatus(getMemoryDeviceStatus(deviceId));

            var path = "predevice/mem";
            var httpResponse = send("POST", path, HttpBodyType.Json, composite, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), GroupMemoryDeviceRelator.GroupMemoryDeviceRelatorWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * addMemoryDeviceToMachine()
     * Category: MachineDeviceRelator
     * Attaches a particular device to a machine
     * @param deviceId: Unique identifier for the device to be added
     * @param groupId: Unique identifier for the group to which the machine belongs
     * @param machineId: Unique identifier for the machine to which the device is to be added
     * @return A description of the relation being created
     * @throws LiqidException if anything goes wrong
     */
    public MachineMemoryDeviceRelator addMemoryDeviceToMachine(Integer deviceId,
                                                               Integer groupId,
                                                               Integer machineId) throws LiqidException {
        var fn = "addMemoryDeviceToMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);
        try {
            var composite = new MachineMemoryDeviceRelator();
            composite.setGroupDeviceRelator(getGroupMemoryDeviceRelator(groupId, deviceId));
            composite.setMachine(getMachine(machineId));

            var path = "relate/memory";
            var httpResponse = send("POST", path, HttpBodyType.Json, composite, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), MachineMemoryDeviceRelator.MachineMemoryDeviceRelatorWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * addNetworkDeviceToGroup()
     * Category: GroupDeviceRelator
     * Moves a device from the system free pool to the group free pool for the indicated group
     * @param deviceId: Identifier of the device to be added to a group free pool.
     * @param groupId: Identifier of the group to which the device is to be added.
     * @return A description of the relation being created
     * @throws LiqidException if anything goes wrong
     */
    public GroupNetworkDeviceRelator addNetworkDeviceToGroup(Integer deviceId,
                                                             Integer groupId) throws LiqidException {
        var fn = "addNetworkDeviceToGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        try {
            var composite = new GroupNetworkDeviceRelator();
            composite.setGroup(getGroup(groupId));
            composite.setDeviceStatus(getNetworkDeviceStatus(deviceId));

            var path = "predevice/network";
            var httpResponse = send("POST", path, HttpBodyType.Json, composite, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), GroupNetworkDeviceRelator.GroupNetworkDeviceRelatorWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * addNetworkDeviceToMachine()
     * Category: MachineDeviceRelator
     * Attaches a particular device to a machine
     * @param deviceId: Unique identifier for the device to be added
     * @param groupId: Unique identifier for the group to which the machine belongs
     * @param machineId: Unique identifier for the machine to which the device is to be added
     * @return A description of the relation being created
     * @throws LiqidException if anything goes wrong
     */
    public MachineNetworkDeviceRelator addNetworkDeviceToMachine(Integer deviceId,
                                                                 Integer groupId,
                                                                 Integer machineId) throws LiqidException {
        var fn = "addNetworkDeviceToMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);
        try {
            var composite = new MachineNetworkDeviceRelator();
            composite.setGroupDeviceRelator(getGroupNetworkDeviceRelator(groupId, deviceId));
            composite.setMachine(getMachine(machineId));

            var path = "relate/network";
            var httpResponse = send("POST", path, HttpBodyType.Json, composite, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), MachineNetworkDeviceRelator.MachineNetworkDeviceRelatorWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * addStorageDeviceToGroup()
     * Category: GroupDeviceRelator
     * Moves a device from the system free pool to the group free pool for the indicated group
     * @param deviceId: Identifier of the device to be added to a group free pool.
     * @param groupId: Identifier of the group to which the device is to be added.
     * @return A description of the relation being created
     * @throws LiqidException if anything goes wrong
     */
    public GroupStorageDeviceRelator addStorageDeviceToGroup(Integer deviceId,
                                                             Integer groupId) throws LiqidException {
        var fn = "addStorageDeviceToGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        try {
            var composite = new GroupStorageDeviceRelator();
            composite.setGroup(getGroup(groupId));
            composite.setDeviceStatus(getStorageDeviceStatus(deviceId));

            var path = "predevice/storage";
            var httpResponse = send("POST", path, HttpBodyType.Json, composite, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), GroupStorageDeviceRelator.GroupStorageDeviceRelatorWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * addStorageDeviceToMachine()
     * Category: MachineDeviceRelator
     * Attaches a particular device to a machine
     * @param deviceId: Unique identifier for the device to be added
     * @param groupId: Unique identifier for the group to which the machine belongs
     * @param machineId: Unique identifier for the machine to which the device is to be added
     * @return A description of the relation being created
     * @throws LiqidException if anything goes wrong
     */
    public MachineStorageDeviceRelator addStorageDeviceToMachine(Integer deviceId,
                                                                 Integer groupId,
                                                                 Integer machineId) throws LiqidException {
        var fn = "addStorageDeviceToMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);
        try {
            var composite = new MachineStorageDeviceRelator();
            composite.setGroupDeviceRelator(getGroupStorageDeviceRelator(groupId, deviceId));
            composite.setMachine(getMachine(machineId));

            var path = "relate/storage";
            var httpResponse = send("POST", path, HttpBodyType.Json, composite, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), MachineStorageDeviceRelator.MachineStorageDeviceRelatorWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * applyFabricChanges()
     * Category: Fabric
     * applies changes to the fabric - currently one may only add a particular flag with a value
     * @param flagName: The name of the flag to be added
     * @param flagValue: The value for the flag to be added
     * @param operation: Currently, only ADD is supported
     * @return Describes the new toggle state
     * @throws LiqidException if anything goes wrong
     */
    public FabricToggleComposite applyFabricChanges(String flagName,
                                                    String flagValue,
                                                    FabricToggleCompositeOption operation) throws LiqidException {
        var fn = "applyFabricChanges";
        _logger.trace("Entering %s flagName:%s flagValue:%s operation:%s", fn, flagName, flagValue, operation);
        checkParameterNotNull(flagName, "flagName", fn);
        checkParameterNotNull(flagValue, "flagValue", fn);
        checkParameterNotNull(operation, "operation", fn);

        try {
            var path = "fabric";
            var obj = new FabricToggleComposite();
            obj.getControlFlag().setName(flagName);
            obj.getControlFlag().setValueString(flagValue);
            obj.setOptions(operation);
            obj.getCoordinates().setRack(getPresetRack());
            obj.getCoordinates().setShelf(getPresetShelf());
            obj.getCoordinates().setNode(getPresetNode());
            var httpResponse = send("PUT", path, HttpBodyType.Json, obj, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), FabricToggleComposite.FabricToggleCompositeWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * backupDirector()
     * Category: Backup
     * Retrieves the result of the backup process
     * @param destination: 
     * @return Result of the backup process
     * @throws LiqidException if anything goes wrong
     */
    public BackupResult backupDirector(BackupDestination destination) throws LiqidException {
        var fn = "backupDirector";
        _logger.trace("Entering %s destination:%s", fn, destination);
        checkParameterNotNull(destination, "destination", fn);

        try {
            var path = "backup";
            path += "/" + destination.toString();
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), BackupResult.BackupResultWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * cancelEditFabric()
     * Category: Fabric
     * Cancels fabric edit mode - reverts all pending changes made to device connections
     * @param machineId: Unique id of the machine for which edit mode is to be canceled.
     * @return A Machine entity describing the machine of interest
     * @throws LiqidException if anything goes wrong
     */
    public Machine cancelEditFabric(Integer machineId) throws LiqidException {
        var fn = "cancelEditFabric";
        _logger.trace("Entering %s machineId:%s", fn, machineId);
        checkParameterNotNull(machineId, "machineId", fn);
        try {
            var object = getMachine(machineId);
            var path = "fabric/edit/cancel";
            var httpResponse = send("POST", path, HttpBodyType.Json, object, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), Machine.MachineWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * cancelGroupPoolEdit()
     * Category: GroupPool
     * Cancels a pending group pool edit operation.
     * All pending device attachments or detachments will be canceled.
     * @param groupId: Identifier of the group for which edit mode is to be canceled
     * @return A Group entity describing the group pool
     * @throws LiqidException if anything goes wrong
     */
    public GroupPool cancelGroupPoolEdit(Integer groupId) throws LiqidException {
        var fn = "cancelGroupPoolEdit";
        _logger.trace("Entering %s groupId:%s", fn, groupId);
        checkParameterNotNull(groupId, "groupId", fn);

        try {
            var path = "pool/cancel";
            var obj = new GroupPool();
            obj.setGroupId(groupId);
            obj.getCoordinates().setRack(getPresetRack());
            obj.getCoordinates().setShelf(getPresetShelf());
            obj.getCoordinates().setNode(getPresetNode());
            obj.setFabricId(getPresetFabricId());
            var httpResponse = send("POST", path, HttpBodyType.Json, obj, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), GroupPool.GroupPoolWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * cancelReprogramFabric()
     * Category: Fabric
     * Cancels fabric reprogram mode
     * @param machineId: Unique id of the machine for which fabric reprogramming is to be canceled.
     * @return A Machine entity describing the machine of interest
     * @throws LiqidException if anything goes wrong
     */
    public Machine cancelReprogramFabric(Integer machineId) throws LiqidException {
        var fn = "cancelReprogramFabric";
        _logger.trace("Entering %s machineId:%s", fn, machineId);
        checkParameterNotNull(machineId, "machineId", fn);
        try {
            var object = getMachine(machineId);
            var path = "fabric/reprogram/cancel";
            var httpResponse = send("POST", path, HttpBodyType.Json, object, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), Machine.MachineWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * clearGroups()
     * Category: Group
     * Deletes all groups and all machines within those groups.
     * Restores all devices to the system free pool.
     * This is effectively a soft configuration reset which does not rediscover devices.
     * @return Returns true if successful, throws an error otherwise.
     * @throws LiqidException if anything goes wrong
     */
    public Boolean clearGroups() throws LiqidException {
        var fn = "clearGroups";
        _logger.trace("Entering %s", fn);

        try {
            var path = "group/clear";
            var httpResponse = send("POST", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), BooleanWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * createDeviceDescription()
     * Category: Device
     * Creates a user-supplied device description for a particular device
     * @param queryDeviceType: Device type of the device to which a description should be applied
     * @param deviceId: Device ID of the device
     * @param description: Description to be applied to the device
     * @return User-supplied device description
     * @throws LiqidException if anything goes wrong
     */
    public DeviceUserDescription createDeviceDescription(DeviceQueryType queryDeviceType,
                                                         Integer deviceId,
                                                         String description) throws LiqidException {
        var fn = "createDeviceDescription";
        _logger.trace("Entering %s queryDeviceType:%s deviceId:%s description:%s", fn, queryDeviceType, deviceId, description);
        checkParameterNotNull(queryDeviceType, "queryDeviceType", fn);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(description, "description", fn);
        var deviceIdTranslated = String.format("0x%08x", deviceId);

        try {
            var path = "device/udesc";
            path += "/" + queryDeviceType.toString();
            path += "/" + deviceIdTranslated.toString();
            var obj = new DeviceUserDescription();
            obj.setUserDescription(description);
            var httpResponse = send("PUT", path, HttpBodyType.Json, obj, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), DeviceUserDescription.DeviceUserDescriptionWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * createGroupWithId()
     * Category: Group
     * Creates a new group within the current fabric.
     * @param groupId: Unique identifier to be associated with the group
     * @param groupName: Unique name of the group
     * @return A Group entity which describes the newly-created group
     * @throws LiqidException if anything goes wrong
     * Deprecated:
     *   SDK Clients should use createGroup instead, which does not require that the client specify a group id.
     */
    @Deprecated
    public Group createGroupWithId(Integer groupId,
                                   String groupName) throws LiqidException {
        var fn = "createGroupWithId";
        _logger.trace("Entering %s groupId:%s groupName:%s", fn, groupId, groupName);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(groupName, "groupName", fn);

        try {
            var path = "group";
            var obj = new Group();
            obj.setGroupId(groupId);
            obj.setGroupName(groupName);
            obj.setFabricId(getPresetFabricId());
            var httpResponse = send("POST", path, HttpBodyType.Json, obj, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), Group.GroupWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * createMachineWithId()
     * Category: Machine
     * Creates a new machine for a particular group
     * @param groupId: Identifier of the group to which the machine should be attached
     * @param machineId: Unique identifier to be applied to the machine
     * @param machineName: Name for the newly-created machine
     * @return A Machine entity describing the created machine
     * @throws LiqidException if anything goes wrong
     * Deprecated:
     *   SDK Clients should use createMachine instead, which does not require that the client specify a machine id.
     */
    @Deprecated
    public Machine createMachineWithId(Integer groupId,
                                       Integer machineId,
                                       String machineName) throws LiqidException {
        var fn = "createMachineWithId";
        _logger.trace("Entering %s groupId:%s machineId:%s machineName:%s", fn, groupId, machineId, machineName);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);
        checkParameterNotNull(machineName, "machineName", fn);

        try {
            var path = "machine";
            var obj = new Machine();
            obj.setGroupId(groupId);
            obj.setMachineId(machineId);
            obj.setMachineName(machineName);
            obj.setComputeName(machineName);
            obj.setFabricId(getPresetFabricId());
            var httpResponse = send("POST", path, HttpBodyType.Json, obj, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), Machine.MachineWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * createMessageDigest()
     * Category: MessageDigest
     * Creates a new digest associated with a given label.
     * The digest will be returned to the client, and will not be otherwise exposed by the Director.
     * This digest should be used for authenticating subsequent REST API invocations.
     * At the end of the client session, this digest should be deleted by invoking deleteMessageDigest.
     * @param label: The label to be associated with a newly-created digest
     * @return Contains information regarding the created digest.
     * @throws LiqidException if anything goes wrong
     */
    public DigestInfo createMessageDigest(String label) throws LiqidException {
        var fn = "createMessageDigest";
        _logger.trace("Entering %s label:%s", fn, label);
        checkParameterNotNull(label, "label", fn);

        try {
            var path = "digest";
            path += "/" + label.toString();
            var httpResponse = send("POST", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), DigestInfo.DigestInfoWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * deleteDeviceDescription()
     * Category: Device
     * Deletes a user-supplied device description for a particular device
     * @param queryDeviceType: Device type of the device to which a description should be applied
     * @param deviceId: Device ID
     * @return Deleted user-supplied device description
     * @throws LiqidException if anything goes wrong
     */
    public DeviceUserDescription deleteDeviceDescription(DeviceQueryType queryDeviceType,
                                                         Integer deviceId) throws LiqidException {
        var fn = "deleteDeviceDescription";
        _logger.trace("Entering %s queryDeviceType:%s deviceId:%s", fn, queryDeviceType, deviceId);
        checkParameterNotNull(queryDeviceType, "queryDeviceType", fn);
        checkParameterNotNull(deviceId, "deviceId", fn);
        var deviceIdTranslated = String.format("0x%08x", deviceId);

        try {
            var path = "device/udesc";
            path += "/" + queryDeviceType.toString();
            path += "/" + deviceIdTranslated.toString();
            var httpResponse = send("DELETE", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), DeviceUserDescription.DeviceUserDescriptionWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * deleteGroup()
     * Category: Group
     * Deletes a configured group along with all the machines in the group.
     * Returns all devices related to the group or to the machines in the group to the system free pool.
     * @param groupId: Unique identifier of the group to be deleted
     * @return Returns a Group item describing the deleted group
     * @throws LiqidException if anything goes wrong
     */
    public Group deleteGroup(Integer groupId) throws LiqidException {
        var fn = "deleteGroup";
        _logger.trace("Entering %s groupId:%s", fn, groupId);
        checkParameterNotNull(groupId, "groupId", fn);

        try {
            var path = "group";
            path += "/" + groupId.toString();
            var httpResponse = send("DELETE", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), Group.GroupWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * deleteMachine()
     * Category: Machine
     * Deletes a configured machine, returning its attached devices to the containing group free pool
     * @param machineId: Unique identifier for the machine to be deleted
     * @return a Machine entity describing the deleted machine
     * @throws LiqidException if anything goes wrong
     */
    public Machine deleteMachine(Integer machineId) throws LiqidException {
        var fn = "deleteMachine";
        _logger.trace("Entering %s machineId:%s", fn, machineId);
        checkParameterNotNull(machineId, "machineId", fn);

        try {
            var path = "machine";
            path += "/" + machineId.toString();
            var httpResponse = send("DELETE", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), Machine.MachineWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * editFabric()
     * Category: Fabric
     * enters fabric edit mode which allows the fabric to be electrically connected
     *  - the system must be put into edit mode before a device is added to a machine
     * @param machineId: Unique id of the machine for which edit mode is to be established.
     * @return A Machine entity describing the machine of interest
     * @throws LiqidException if anything goes wrong
     */
    public Machine editFabric(Integer machineId) throws LiqidException {
        var fn = "editFabric";
        _logger.trace("Entering %s machineId:%s", fn, machineId);
        checkParameterNotNull(machineId, "machineId", fn);
        try {
            var object = getMachine(machineId);
            var path = "fabric/edit";
            var httpResponse = send("POST", path, HttpBodyType.Json, object, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), Machine.MachineWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getAllDevicesStatus()
     * Category: DeviceStatus
     * Retrieves status for all devices
     * @return An array of DeviceStatus entities describing the status of all devices in the system
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<DeviceStatus> getAllDevicesStatus() throws LiqidException {
        var fn = "getAllDevicesStatus";
        _logger.trace("Entering %s", fn);

        try {
            var path = "status";
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), DeviceStatus.DeviceStatusWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getAvailableCoordinates()
     * Category: Coordinates
     * Retrieves a list of entities indicating the known values which may be employed for identifying particular Liqid entities
     * within a configuration. As a general rule, there will be only one such entry.
     * @return Array of all known available Liqid coordinates to which REST API invocations may be directed
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<AvailableCoordinates> getAvailableCoordinates() throws LiqidException {
        var fn = "getAvailableCoordinates";
        _logger.trace("Entering %s", fn);

        try {
            var path = "coordinates/available";
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), AvailableCoordinates.AvailableCoordinatesWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getComputeDeviceStatus()
     * Category: DeviceStatus
     * Retrieves status for a particular compute device
     * @param deviceId: Identifier of the device in question
     * @return A ComputeDeviceStatus entity describing the status of the requested device
     * @throws LiqidException if anything goes wrong
     */
    public ComputeDeviceStatus getComputeDeviceStatus(Integer deviceId) throws LiqidException {
        var fn = "getComputeDeviceStatus";
        _logger.trace("Entering %s deviceId:%s", fn, deviceId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        for (var item : getComputeDevicesStatus()) {
            if (deviceId.equals(item.getDeviceId())) {
                _logger.trace("%%s returning %%s", fn, item);
                return item;
            }
        }

        var ex = new LiqidException("Entity not found");
        _logger.throwing(ex);
        throw ex;
    }

    /**
     * getComputeDevicesStatus()
     * Category: DeviceStatus
     * Retrieves status for compute devices
     * @return An array of ComputeDeviceStatus entities describing the status of compute devices in the system
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<ComputeDeviceStatus> getComputeDevicesStatus() throws LiqidException {
        var fn = "getComputeDevicesStatus";
        _logger.trace("Entering %s", fn);

        try {
            var path = "status/compute";
            var qpList = new LinkedList<String>();
            qpList.add("filter=" + false);
            path += "?" + String.join("&", qpList);
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), ComputeDeviceStatus.ComputeDeviceStatusWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getComputeDevicesStatusWithMultiplePortsStatus()
     * Category: DeviceStatus
     * Retrieves status for compute devices
     * @return An array of ComputeDeviceStatus entities describing the status of compute devices in the system
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<ComputeDeviceStatus> getComputeDevicesStatusWithMultiplePortsStatus() throws LiqidException {
        var fn = "getComputeDevicesStatusWithMultiplePortsStatus";
        _logger.trace("Entering %s", fn);

        try {
            var path = "status/compute/parents";
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), ComputeDeviceStatus.ComputeDeviceStatusWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getComputeDeviceInfo()
     * Category: DeviceInfo
     * Retrieves additional information for all known compute devices
     * @return An array of ComputeDeviceInfo entities describing all known compute devices
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<ComputeDeviceInfo> getComputeDeviceInfo() throws LiqidException {
        var fn = "getComputeDeviceInfo";
        _logger.trace("Entering %s", fn);

        try {
            var path = "device/info/cpu";
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), ComputeDeviceInfo.ComputeDeviceInfoWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getComputeDeviceInfoByName()
     * Category: DeviceInfo
     * Retrieves additional information for a particular compute device
     * @param deviceName: Name of the device in question
     * @return ComputeDeviceInfo entity for the requested compute device
     * @throws LiqidException if anything goes wrong
     */
    public ComputeDeviceInfo getComputeDeviceInfoByName(String deviceName) throws LiqidException {
        var fn = "getComputeDeviceInfoByName";
        _logger.trace("Entering %s deviceName:%s", fn, deviceName);
        checkParameterNotNull(deviceName, "deviceName", fn);

        try {
            var path = "device/info/cpu";
            path += "/" + deviceName.toString();
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), ComputeDeviceInfo.ComputeDeviceInfoWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getCurrentFabricId()
     * Category: Fabric
     * Retrieves the fabric id associated with the current default coordinates.
     * This is not generally needed for SDK interactions, but may be useful for troubleshooting.
     * @return The fabric id which is managed by the connected Director.
     * @throws LiqidException if anything goes wrong
     */
    public Integer getCurrentFabricId() throws LiqidException {
        var fn = "getCurrentFabricId";
        _logger.trace("Entering %s", fn);

        try {
            var path = "fabric/id";
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), IntegerWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getDefaultCoordinates()
     * Category: Coordinates
     * Retrieves the current default coordinates used for the various operations which are initiated by the REST API for the SDK.
     * @return Liqid coordinates to which all REST API invocations are directed
     * @throws LiqidException if anything goes wrong
     */
    public Coordinates getDefaultCoordinates() throws LiqidException {
        var fn = "getDefaultCoordinates";
        _logger.trace("Entering %s", fn);

        try {
            var path = "coordinates";
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), Coordinates.CoordinatesWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getPreDevices()
     * Category: GroupDeviceRelator
     * Returns information regarding devices which are attached to a particular group.
     * @param queryDeviceType: Limits the device type of the devices to be queried.
                               If not specified, all device types will be returned.
                               This parameter is optional, and should be set to null if it is to remain unspecified.
     * @param groupId: Indicates the group for which devices are queried.
                       If MachineId is specified, only those devices which are in the
                       group free pool will be returned.
     * @param machineId: Only return devices associated with the indicated machine.
                         This parameter is optional, and should be set to null if it is to remain unspecified.
     * @return An array of PreDevice entities describing the various devices in the configuration
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<PreDevice> getPreDevices(DeviceQueryType queryDeviceType,
                                               Integer groupId,
                                               Integer machineId) throws LiqidException {
        var fn = "getPreDevices";
        _logger.trace("Entering %s queryDeviceType:%s groupId:%s machineId:%s", fn, queryDeviceType, groupId, machineId);
        checkParameterNotNull(groupId, "groupId", fn);

        try {
            var path = "predevice";
            var qpList = new LinkedList<String>();
            if (queryDeviceType != null) {
                qpList.add("dev_type=" + queryDeviceType.toString());
            }
            qpList.add("grp_id=" + groupId.toString());
            if (machineId != null) {
                qpList.add("mach_id=" + machineId.toString());
            }
            path += "?" + String.join("&", qpList);
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), PreDevice.PreDeviceWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getDeviceAttributes()
     * Category: Device
     * Retrieves available device attribute names for all device types
     * @return An array of DeviceTypeAndAttributes entities
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<DeviceTypeAndAttributes> getDeviceAttributes() throws LiqidException {
        var fn = "getDeviceAttributes";
        _logger.trace("Entering %s", fn);

        try {
            var path = "device/attributes";
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), DeviceTypeAndAttributes.DeviceTypeAndAttributesWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getDeviceCounters()
     * Category: Coordinates
     * Retrieves counters of devices for each type of device
     * @return contains counters per device type
     * @throws LiqidException if anything goes wrong
     */
    public DeviceCounters getDeviceCounters() throws LiqidException {
        var fn = "getDeviceCounters";
        _logger.trace("Entering %s", fn);

        try {
            var path = "devices/count";
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), DeviceCounters.DeviceCountersWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getDiscoveryTokens()
     * Category: Manager
     * Returns all of the configured discovery tokens
     * @return An array of DiscoveryToken entities
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<DiscoveryToken> getDiscoveryTokens() throws LiqidException {
        var fn = "getDiscoveryTokens";
        _logger.trace("Entering %s", fn);

        try {
            var path = "manager/discovery";
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), DiscoveryToken.DiscoveryTokenWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getDiscoveryTokensByType()
     * Category: Manager
     * Returns a subset of the configured discovery tokens, specified by the TokenType parameter
     * @param tokenType: The type of tokens requested
     * @return An array of DiscoveryToken entities
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<DiscoveryToken> getDiscoveryTokensByType(TokenType tokenType) throws LiqidException {
        var fn = "getDiscoveryTokensByType";
        _logger.trace("Entering %s tokenType:%s", fn, tokenType);
        checkParameterNotNull(tokenType, "tokenType", fn);

        try {
            var path = "manager/discovery";
            path += "/" + tokenType.toString();
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), DiscoveryToken.DiscoveryTokenWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getFabricTopology()
     * Category: Fabric
     * Returns the current fabric topology
     * @return A FabricTopology entity
     * @throws LiqidException if anything goes wrong
     */
    public FabricTopology getFabricTopology() throws LiqidException {
        var fn = "getFabricTopology";
        _logger.trace("Entering %s", fn);

        try {
            var path = "fabric/topology";
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), FabricTopology.FabricTopologyWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getFabricTypes()
     * Category: Fabric
     * Returns all known fabric types and associated id values
     * @return An array of fabric types and identifiers
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<NameValuePair> getFabricTypes() throws LiqidException {
        var fn = "getFabricTypes";
        _logger.trace("Entering %s", fn);

        try {
            var path = "fabric/types";
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), NameValuePair.NameValuePairWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getFPGADeviceStatus()
     * Category: DeviceStatus
     * Retrieves status for a particular FPGA device
     * @param deviceId: Identifier of the device in question
     * @return A FPGADeviceStatus entity describing the status of the requested device
     * @throws LiqidException if anything goes wrong
     */
    public FPGADeviceStatus getFPGADeviceStatus(Integer deviceId) throws LiqidException {
        var fn = "getFPGADeviceStatus";
        _logger.trace("Entering %s deviceId:%s", fn, deviceId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        for (var item : getFPGADevicesStatus()) {
            if (deviceId.equals(item.getDeviceId())) {
                _logger.trace("%%s returning %%s", fn, item);
                return item;
            }
        }

        var ex = new LiqidException("Entity not found");
        _logger.throwing(ex);
        throw ex;
    }

    /**
     * getFPGADevicesStatus()
     * Category: DeviceStatus
     * Retrieves status for FPGA devices
     * @return An array of FPGADeviceStatus entities describing the status of FPGA devices in the system
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<FPGADeviceStatus> getFPGADevicesStatus() throws LiqidException {
        var fn = "getFPGADevicesStatus";
        _logger.trace("Entering %s", fn);

        try {
            var path = "status/fpga";
            var qpList = new LinkedList<String>();
            qpList.add("filter=" + false);
            path += "?" + String.join("&", qpList);
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), FPGADeviceStatus.FPGADeviceStatusWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getFPGADeviceInfo()
     * Category: DeviceInfo
     * Retrieves additional information for all known FPGA devices
     * @return An array of FPGADeviceInfo entities describing all known FPGA devices
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<FPGADeviceInfo> getFPGADeviceInfo() throws LiqidException {
        var fn = "getFPGADeviceInfo";
        _logger.trace("Entering %s", fn);

        try {
            var path = "device/info/fpga";
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), FPGADeviceInfo.FPGADeviceInfoWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getFPGADeviceInfoByName()
     * Category: DeviceInfo
     * Retrieves additional information for a particular FPGA device
     * @param deviceName: Name of the device in question
     * @return FPGADeviceInfo entity for the requested device
     * @throws LiqidException if anything goes wrong
     */
    public FPGADeviceInfo getFPGADeviceInfoByName(String deviceName) throws LiqidException {
        var fn = "getFPGADeviceInfoByName";
        _logger.trace("Entering %s deviceName:%s", fn, deviceName);
        checkParameterNotNull(deviceName, "deviceName", fn);

        try {
            var path = "device/info/fpga";
            path += "/" + deviceName.toString();
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), FPGADeviceInfo.FPGADeviceInfoWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getFreeComputeDevicesStatus()
     * Category: DeviceStatus
     * Retrieves status for compute devices which are not assigned to a group or machine
     * @return An array of ComputeDeviceStatus entities describing the status of compute devices in the system
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<ComputeDeviceStatus> getFreeComputeDevicesStatus() throws LiqidException {
        var fn = "getFreeComputeDevicesStatus";
        _logger.trace("Entering %s", fn);

        try {
            var path = "status/compute";
            var qpList = new LinkedList<String>();
            qpList.add("filter=" + true);
            path += "?" + String.join("&", qpList);
            var obj = new ComputeDeviceStatus();
            var httpResponse = send("GET", path, HttpBodyType.Json, obj, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), ComputeDeviceStatus.ComputeDeviceStatusWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getFreeFPGADevicesStatus()
     * Category: DeviceStatus
     * Retrieves status for compute FPGA which are not assigned to a group or machine
     * @return An array of FPGADeviceStatus entities describing the status of FPGA devices in the system
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<FPGADeviceStatus> getFreeFPGADevicesStatus() throws LiqidException {
        var fn = "getFreeFPGADevicesStatus";
        _logger.trace("Entering %s", fn);

        try {
            var path = "status/fpga";
            var qpList = new LinkedList<String>();
            qpList.add("filter=" + true);
            path += "?" + String.join("&", qpList);
            var obj = new FPGADeviceStatus();
            var httpResponse = send("GET", path, HttpBodyType.Json, obj, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), FPGADeviceStatus.FPGADeviceStatusWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getFreeGPUDevicesStatus()
     * Category: DeviceStatus
     * Retrieves status for compute GPU which are not assigned to a group or machine
     * @return An array of GPUDeviceStatus entities describing the status of GPU devices in the system
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<GPUDeviceStatus> getFreeGPUDevicesStatus() throws LiqidException {
        var fn = "getFreeGPUDevicesStatus";
        _logger.trace("Entering %s", fn);

        try {
            var path = "status/gpu";
            var qpList = new LinkedList<String>();
            qpList.add("filter=" + true);
            path += "?" + String.join("&", qpList);
            var obj = new GPUDeviceStatus();
            var httpResponse = send("GET", path, HttpBodyType.Json, obj, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), GPUDeviceStatus.GPUDeviceStatusWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getFreeMemoryDevicesStatus()
     * Category: DeviceStatus
     * Retrieves status for memory devices which are not assigned to a group or machine
     * @return An array of MemoryDeviceStatus entities describing the status of memory devices in the system
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<MemoryDeviceStatus> getFreeMemoryDevicesStatus() throws LiqidException {
        var fn = "getFreeMemoryDevicesStatus";
        _logger.trace("Entering %s", fn);

        try {
            var path = "status/mem";
            var qpList = new LinkedList<String>();
            qpList.add("filter=" + true);
            path += "?" + String.join("&", qpList);
            var obj = new MemoryDeviceStatus();
            var httpResponse = send("GET", path, HttpBodyType.Json, obj, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), MemoryDeviceStatus.MemoryDeviceStatusWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getFreeNetworkDevicesStatus()
     * Category: DeviceStatus
     * Retrieves status for network devices which are not assigned to a group or machine
     * @return An array of NetworkDeviceStatus entities describing the status of network devices in the system
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<NetworkDeviceStatus> getFreeNetworkDevicesStatus() throws LiqidException {
        var fn = "getFreeNetworkDevicesStatus";
        _logger.trace("Entering %s", fn);

        try {
            var path = "status/network";
            var qpList = new LinkedList<String>();
            qpList.add("filter=" + true);
            path += "?" + String.join("&", qpList);
            var obj = new NetworkDeviceStatus();
            var httpResponse = send("GET", path, HttpBodyType.Json, obj, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), NetworkDeviceStatus.NetworkDeviceStatusWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getFreeStorageDevicesStatus()
     * Category: DeviceStatus
     * Retrieves status for storage devices which are not assigned to a group or machine
     * @return An array of StorageDeviceStatus entities describing the status of storage devices in the system
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<StorageDeviceStatus> getFreeStorageDevicesStatus() throws LiqidException {
        var fn = "getFreeStorageDevicesStatus";
        _logger.trace("Entering %s", fn);

        try {
            var path = "status/storage";
            var qpList = new LinkedList<String>();
            qpList.add("filter=" + true);
            path += "?" + String.join("&", qpList);
            var obj = new StorageDeviceStatus();
            var httpResponse = send("GET", path, HttpBodyType.Json, obj, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), StorageDeviceStatus.StorageDeviceStatusWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getGPUDeviceStatus()
     * Category: DeviceStatus
     * Retrieves status for a particular GPU device
     * @param deviceId: Identifier of the device in question
     * @return A GPUDeviceStatus entity describing the status of the requested device
     * @throws LiqidException if anything goes wrong
     */
    public GPUDeviceStatus getGPUDeviceStatus(Integer deviceId) throws LiqidException {
        var fn = "getGPUDeviceStatus";
        _logger.trace("Entering %s deviceId:%s", fn, deviceId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        for (var item : getGPUDevicesStatus()) {
            if (deviceId.equals(item.getDeviceId())) {
                _logger.trace("%%s returning %%s", fn, item);
                return item;
            }
        }

        var ex = new LiqidException("Entity not found");
        _logger.throwing(ex);
        throw ex;
    }

    /**
     * getGPUDevicesStatus()
     * Category: DeviceStatus
     * Retrieves status for GPU devices
     * @return An array of GPUDeviceStatus entities describing the status of GPU devices in the system
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<GPUDeviceStatus> getGPUDevicesStatus() throws LiqidException {
        var fn = "getGPUDevicesStatus";
        _logger.trace("Entering %s", fn);

        try {
            var path = "status/gpu";
            var qpList = new LinkedList<String>();
            qpList.add("filter=" + false);
            path += "?" + String.join("&", qpList);
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), GPUDeviceStatus.GPUDeviceStatusWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getGPUDeviceInfo()
     * Category: DeviceInfo
     * Retrieves additional information for all known GPU devices
     * @return An array of GPUDeviceInfo entities describing all known GPU devices
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<GPUDeviceInfo> getGPUDeviceInfo() throws LiqidException {
        var fn = "getGPUDeviceInfo";
        _logger.trace("Entering %s", fn);

        try {
            var path = "device/info/gpu";
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), GPUDeviceInfo.GPUDeviceInfoWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getGPUDeviceInfoByName()
     * Category: DeviceInfo
     * Retrieves additional information for a particular GPU device
     * @param deviceName: Name of the device in question
     * @return GPUDeviceInfo entity for the requested device
     * @throws LiqidException if anything goes wrong
     */
    public GPUDeviceInfo getGPUDeviceInfoByName(String deviceName) throws LiqidException {
        var fn = "getGPUDeviceInfoByName";
        _logger.trace("Entering %s deviceName:%s", fn, deviceName);
        checkParameterNotNull(deviceName, "deviceName", fn);

        try {
            var path = "device/info/gpu";
            path += "/" + deviceName.toString();
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), GPUDeviceInfo.GPUDeviceInfoWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getGroup()
     * Category: Group
     * Retrieves a particular group given its identifier
     * @param groupId: Identifier of the group in question
     * @return A Group entity describing the indicated group
     * @throws LiqidException if anything goes wrong
     */
    public Group getGroup(Integer groupId) throws LiqidException {
        var fn = "getGroup";
        _logger.trace("Entering %s groupId:%s", fn, groupId);
        checkParameterNotNull(groupId, "groupId", fn);
        for (var item : getGroups()) {
            if (groupId.equals(item.getGroupId())) {
                _logger.trace("%%s returning %%s", fn, item);
                return item;
            }
        }

        var ex = new LiqidException("Entity not found");
        _logger.throwing(ex);
        throw ex;
    }

    /**
     * getGroups()
     * Category: Group
     * Retrieves all configured groups
     * @return An array of Group entities describing the configured groups
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<Group> getGroups() throws LiqidException {
        var fn = "getGroups";
        _logger.trace("Entering %s", fn);

        try {
            var path = "group";
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), Group.GroupWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getGroupComputeDeviceRelator()
     * Category: GroupDeviceRelator
     * This function is required by the SDK architecture.
     * It is not intended for use by the SDK client.
     * @param groupId: Identifier of the related group
     * @param deviceId: Identifier of the related device
     * @return A GroupComputeDeviceRelator entity
     * @throws LiqidException if anything goes wrong
     */
    public GroupComputeDeviceRelator getGroupComputeDeviceRelator(Integer groupId,
                                                                  Integer deviceId) throws LiqidException {
        var fn = "getGroupComputeDeviceRelator";
        _logger.trace("Entering %s groupId:%s deviceId:%s", fn, groupId, deviceId);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(deviceId, "deviceId", fn);
        try {
            var composite = new GroupComputeDeviceRelator();
            composite.setDeviceStatus(getComputeDeviceStatus(deviceId));
            composite.setGroup(getGroup(groupId));

            _logger.trace("%s returning %s", fn, composite);
            return composite;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getGroupFPGADeviceRelator()
     * Category: GroupDeviceRelator
     * This function is required by the SDK architecture.
     * It is not intended for use by the SDK client.
     * @param groupId: Identifier of the related group
     * @param deviceId: Identifier of the related device
     * @return A GroupFPGADeviceRelator entity
     * @throws LiqidException if anything goes wrong
     */
    public GroupFPGADeviceRelator getGroupFPGADeviceRelator(Integer groupId,
                                                            Integer deviceId) throws LiqidException {
        var fn = "getGroupFPGADeviceRelator";
        _logger.trace("Entering %s groupId:%s deviceId:%s", fn, groupId, deviceId);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(deviceId, "deviceId", fn);
        try {
            var composite = new GroupFPGADeviceRelator();
            composite.setDeviceStatus(getFPGADeviceStatus(deviceId));
            composite.setGroup(getGroup(groupId));

            _logger.trace("%s returning %s", fn, composite);
            return composite;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getGroupGPUDeviceRelator()
     * Category: GroupDeviceRelator
     * This function is required by the SDK architecture.
     * It is not intended for use by the SDK client.
     * @param groupId: Identifier of the related group
     * @param deviceId: Identifier of the related device
     * @return A GroupGPUDeviceRelator entity
     * @throws LiqidException if anything goes wrong
     */
    public GroupGPUDeviceRelator getGroupGPUDeviceRelator(Integer groupId,
                                                          Integer deviceId) throws LiqidException {
        var fn = "getGroupGPUDeviceRelator";
        _logger.trace("Entering %s groupId:%s deviceId:%s", fn, groupId, deviceId);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(deviceId, "deviceId", fn);
        try {
            var composite = new GroupGPUDeviceRelator();
            composite.setDeviceStatus(getGPUDeviceStatus(deviceId));
            composite.setGroup(getGroup(groupId));

            _logger.trace("%s returning %s", fn, composite);
            return composite;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getGroupMemoryDeviceRelator()
     * Category: GroupDeviceRelator
     * This function is required by the SDK architecture.
     * It is not intended for use by the SDK client.
     * @param groupId: Identifier of the related group
     * @param deviceId: Identifier of the related device
     * @return A GroupMemoryDeviceRelator entity
     * @throws LiqidException if anything goes wrong
     */
    public GroupMemoryDeviceRelator getGroupMemoryDeviceRelator(Integer groupId,
                                                                Integer deviceId) throws LiqidException {
        var fn = "getGroupMemoryDeviceRelator";
        _logger.trace("Entering %s groupId:%s deviceId:%s", fn, groupId, deviceId);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(deviceId, "deviceId", fn);
        try {
            var composite = new GroupMemoryDeviceRelator();
            composite.setGroup(getGroup(groupId));
            composite.setDeviceStatus(getMemoryDeviceStatus(deviceId));

            _logger.trace("%s returning %s", fn, composite);
            return composite;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getGroupNetworkDeviceRelator()
     * Category: GroupDeviceRelator
     * This function is required by the SDK architecture.
     * It is not intended for use by the SDK client.
     * @param groupId: Identifier of the related group
     * @param deviceId: Identifier of the related device
     * @return A GroupNetworkDeviceRelator entity
     * @throws LiqidException if anything goes wrong
     */
    public GroupNetworkDeviceRelator getGroupNetworkDeviceRelator(Integer groupId,
                                                                  Integer deviceId) throws LiqidException {
        var fn = "getGroupNetworkDeviceRelator";
        _logger.trace("Entering %s groupId:%s deviceId:%s", fn, groupId, deviceId);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(deviceId, "deviceId", fn);
        try {
            var composite = new GroupNetworkDeviceRelator();
            composite.setGroup(getGroup(groupId));
            composite.setDeviceStatus(getNetworkDeviceStatus(deviceId));

            _logger.trace("%s returning %s", fn, composite);
            return composite;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getGroupStorageDeviceRelator()
     * Category: GroupDeviceRelator
     * This function is required by the SDK architecture.
     * It is not intended for use by the SDK client.
     * @param groupId: Identifier of the related group
     * @param deviceId: Identifier of the related device
     * @return A GroupStorageDeviceRelator entity
     * @throws LiqidException if anything goes wrong
     */
    public GroupStorageDeviceRelator getGroupStorageDeviceRelator(Integer groupId,
                                                                  Integer deviceId) throws LiqidException {
        var fn = "getGroupStorageDeviceRelator";
        _logger.trace("Entering %s groupId:%s deviceId:%s", fn, groupId, deviceId);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(deviceId, "deviceId", fn);
        try {
            var composite = new GroupStorageDeviceRelator();
            composite.setGroup(getGroup(groupId));
            composite.setDeviceStatus(getStorageDeviceStatus(deviceId));

            _logger.trace("%s returning %s", fn, composite);
            return composite;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getGroupDetails()
     * Category: Group
     * Retrieves details regarding a particular configured group
     * @param groupId: Unique identifier of the group of interest
     * @return A GroupDetails entity containing details for the indicated group
     * @throws LiqidException if anything goes wrong
     */
    public GroupDetails getGroupDetails(Integer groupId) throws LiqidException {
        var fn = "getGroupDetails";
        _logger.trace("Entering %s groupId:%s", fn, groupId);
        checkParameterNotNull(groupId, "groupId", fn);

        try {
            var path = "group/details";
            path += "/" + groupId.toString();
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), GroupDetails.GroupDetailsWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getGroupIdByName()
     * Category: Group
     * Retrieves the unique identifier of a configured group given its name
     * @param groupName: Name of the group of interest
     * @return Returns the unique identifier of the indicated group
     * @throws LiqidException if anything goes wrong
     */
    public Integer getGroupIdByName(String groupName) throws LiqidException {
        var fn = "getGroupIdByName";
        _logger.trace("Entering %s groupName:%s", fn, groupName);
        checkParameterNotNull(groupName, "groupName", fn);

        try {
            var path = "group/mapper";
            var qpList = new LinkedList<String>();
            qpList.add("group-name=" + groupName.toString());
            path += "?" + String.join("&", qpList);
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), IntegerWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getMachine()
     * Category: Machine
     * Retrieves information about a configured machine given the machine identifier
     * @param machineId: Unique machine identifier
     * @return A Machine entity
     * @throws LiqidException if anything goes wrong
     */
    public Machine getMachine(Integer machineId) throws LiqidException {
        var fn = "getMachine";
        _logger.trace("Entering %s machineId:%s", fn, machineId);
        checkParameterNotNull(machineId, "machineId", fn);

        try {
            var path = "machine";
            var qpList = new LinkedList<String>();
            qpList.add("mach_id=" + machineId.toString());
            path += "?" + String.join("&", qpList);
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), Machine.MachineWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getMachines()
     * Category: Machine
     * Retrieves information about all the configured machines
     * @return An array of Machine entities
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<Machine> getMachines() throws LiqidException {
        var fn = "getMachines";
        _logger.trace("Entering %s", fn);

        try {
            var path = "machine";
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), Machine.MachineWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getMachinesAtCoordinates()
     * Category: Machine
     * Retrieves information about all the configured machines
     * @param rackId: Rack component of Liqid Coordinates of interest
     * @param shelfId: Shelf component of Liqid Coordinates of interest
     * @param nodeId: Node component of Liqid Coordinates of interest
     * @return An array of Machine entities
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<Machine> getMachinesAtCoordinates(Integer rackId,
                                                        Integer shelfId,
                                                        Integer nodeId) throws LiqidException {
        var fn = "getMachinesAtCoordinates";
        _logger.trace("Entering %s rackId:%s shelfId:%s nodeId:%s", fn, rackId, shelfId, nodeId);
        checkParameterNotNull(rackId, "rackId", fn);
        checkParameterNotNull(shelfId, "shelfId", fn);
        checkParameterNotNull(nodeId, "nodeId", fn);

        try {
            var path = "machine";
            path += "/" + rackId.toString();
            path += "/" + shelfId.toString();
            path += "/" + nodeId.toString();
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), Machine.MachineWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getMachinesByGroupId()
     * Category: Machine
     * Retrieves information about all the configured machines in a particular group
     * @param groupId: Unique group identifier
     * @return An array of Machine entities
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<Machine> getMachinesByGroupId(Integer groupId) throws LiqidException {
        var fn = "getMachinesByGroupId";
        _logger.trace("Entering %s groupId:%s", fn, groupId);
        checkParameterNotNull(groupId, "groupId", fn);

        try {
            var path = "machine";
            var qpList = new LinkedList<String>();
            qpList.add("grp_id=" + groupId.toString());
            path += "?" + String.join("&", qpList);
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), Machine.MachineWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getMachineByName()
     * Category: Machine
     * Retrieves information about a configured machine given the machine name
     * @param machineName: Unique machine name
     * @return A Machine entity
     * @throws LiqidException if anything goes wrong
     */
    public Machine getMachineByName(String machineName) throws LiqidException {
        var fn = "getMachineByName";
        _logger.trace("Entering %s machineName:%s", fn, machineName);
        checkParameterNotNull(machineName, "machineName", fn);

        try {
            var path = "machine";
            var qpList = new LinkedList<String>();
            qpList.add("mach_name=" + machineName.toString());
            path += "?" + String.join("&", qpList);
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), Machine.MachineWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getMachineDetails()
     * Category: Machine
     * Retrieves details for a particular machine
     * @param machineId: Identifier for the machine
     * @return A MachineDetails entity describing details for the machine
     * @throws LiqidException if anything goes wrong
     */
    public MachineDetails getMachineDetails(Integer machineId) throws LiqidException {
        var fn = "getMachineDetails";
        _logger.trace("Entering %s machineId:%s", fn, machineId);
        checkParameterNotNull(machineId, "machineId", fn);

        try {
            var path = "machine/details";
            path += "/" + machineId.toString();
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), MachineDetails.MachineDetailsWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getManagedEntities()
     * Category: Manager
     * Reports all the managed entity entries used for discovering PCI devices
     * @return Returns an array of ManagedEntity items describing the entries which are used for device discovery
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<ManagedEntity> getManagedEntities() throws LiqidException {
        var fn = "getManagedEntities";
        _logger.trace("Entering %s", fn);

        try {
            var path = "manager/device";
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), ManagedEntity.ManagedEntityWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getManagedEntity()
     * Category: Manager
     * Retrieves a particular managed entity
     * @param pciVendorId: PCI vendor id for the managed entity
     * @param pciDeviceId: PCI device id for the managed entity
     * @return A ManagedEntity entity describing the requested entity
     * @throws LiqidException if anything goes wrong
     */
    public ManagedEntity getManagedEntity(String pciVendorId,
                                          String pciDeviceId) throws LiqidException {
        var fn = "getManagedEntity";
        _logger.trace("Entering %s pciVendorId:%s pciDeviceId:%s", fn, pciVendorId, pciDeviceId);
        checkParameterNotNull(pciVendorId, "pciVendorId", fn);
        checkParameterNotNull(pciDeviceId, "pciDeviceId", fn);
        for (var item : getManagedEntities()) {
            if (pciVendorId.equals(item.getPCIVendorId()) && pciDeviceId.equals(item.getPCIDeviceId())) {
                _logger.trace("%%s returning %%s", fn, item);
                return item;
            }
        }

        var ex = new LiqidException("Entity not found");
        _logger.throwing(ex);
        throw ex;
    }

    /**
     * getMemoryDeviceInfo()
     * Category: DeviceInfo
     * Retrieves additional information for all known Memory devices
     * @return An array of MemoryDeviceInfo entities describing all known Memory devices
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<MemoryDeviceInfo> getMemoryDeviceInfo() throws LiqidException {
        var fn = "getMemoryDeviceInfo";
        _logger.trace("Entering %s", fn);

        try {
            var path = "device/info/mem";
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), MemoryDeviceInfo.MemoryDeviceInfoWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getMemoryDeviceInfoByName()
     * Category: DeviceInfo
     * Retrieves additional information for a particular Memory device
     * @param deviceName: Name of the device in question
     * @return MemoryDeviceInfo entity for the requested device
     * @throws LiqidException if anything goes wrong
     */
    public MemoryDeviceInfo getMemoryDeviceInfoByName(String deviceName) throws LiqidException {
        var fn = "getMemoryDeviceInfoByName";
        _logger.trace("Entering %s deviceName:%s", fn, deviceName);
        checkParameterNotNull(deviceName, "deviceName", fn);

        try {
            var path = "device/info/mem";
            path += "/" + deviceName.toString();
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), MemoryDeviceInfo.MemoryDeviceInfoWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getMemoryDeviceStatus()
     * Category: DeviceStatus
     * Retrieves status for a particular memory device
     * @param deviceId: Identifier of the device in question
     * @return A MemoryDeviceStatus entity describing the status of the requested device
     * @throws LiqidException if anything goes wrong
     */
    public MemoryDeviceStatus getMemoryDeviceStatus(Integer deviceId) throws LiqidException {
        var fn = "getMemoryDeviceStatus";
        _logger.trace("Entering %s deviceId:%s", fn, deviceId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        for (var item : getMemoryDevicesStatus()) {
            if (deviceId.equals(item.getDeviceId())) {
                _logger.trace("%%s returning %%s", fn, item);
                return item;
            }
        }

        var ex = new LiqidException("Entity not found");
        _logger.throwing(ex);
        throw ex;
    }

    /**
     * getMemoryDevicesStatus()
     * Category: DeviceStatus
     * Retrieves status for memory devices
     * @return An array of MemoryDeviceStatus entities describing the status of memory devices in the system
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<MemoryDeviceStatus> getMemoryDevicesStatus() throws LiqidException {
        var fn = "getMemoryDevicesStatus";
        _logger.trace("Entering %s", fn);

        try {
            var path = "status/mem";
            var qpList = new LinkedList<String>();
            qpList.add("filter=" + false);
            path += "?" + String.join("&", qpList);
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), MemoryDeviceStatus.MemoryDeviceStatusWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getMessageDigestLabels()
     * Category: MessageDigest
     * Retrieves all existing message digest labels.
     * @return These are labels which can be used for login/logout authentication.
               The labels are NOT authentication tokens.
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<String> getMessageDigestLabels() throws LiqidException {
        var fn = "getMessageDigestLabels";
        _logger.trace("Entering %s", fn);

        try {
            var path = "digest";
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), StringWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getNetworkDeviceInfo()
     * Category: DeviceInfo
     * Retrieves additional information for all known Network devices
     * @return An array of NetworkDeviceInfo entities describing all known Network devices
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<NetworkDeviceInfo> getNetworkDeviceInfo() throws LiqidException {
        var fn = "getNetworkDeviceInfo";
        _logger.trace("Entering %s", fn);

        try {
            var path = "device/info/link";
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), NetworkDeviceInfo.NetworkDeviceInfoWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getNetworkDeviceInfoByName()
     * Category: DeviceInfo
     * Retrieves additional information for a particular Network device
     * @param deviceName: Name of the device in question
     * @return NetworkDeviceInfo entity for the requested device
     * @throws LiqidException if anything goes wrong
     */
    public NetworkDeviceInfo getNetworkDeviceInfoByName(String deviceName) throws LiqidException {
        var fn = "getNetworkDeviceInfoByName";
        _logger.trace("Entering %s deviceName:%s", fn, deviceName);
        checkParameterNotNull(deviceName, "deviceName", fn);

        try {
            var path = "device/info/link";
            path += "/" + deviceName.toString();
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), NetworkDeviceInfo.NetworkDeviceInfoWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getNetworkDeviceStatus()
     * Category: DeviceStatus
     * Retrieves status for a particular network device
     * @param deviceId: Identifier of the device in question
     * @return A NetworkDeviceStatus entity describing the status of the requested device
     * @throws LiqidException if anything goes wrong
     */
    public NetworkDeviceStatus getNetworkDeviceStatus(Integer deviceId) throws LiqidException {
        var fn = "getNetworkDeviceStatus";
        _logger.trace("Entering %s deviceId:%s", fn, deviceId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        for (var item : getNetworkDevicesStatus()) {
            if (deviceId.equals(item.getDeviceId())) {
                _logger.trace("%%s returning %%s", fn, item);
                return item;
            }
        }

        var ex = new LiqidException("Entity not found");
        _logger.throwing(ex);
        throw ex;
    }

    /**
     * getNetworkDevicesStatus()
     * Category: DeviceStatus
     * Retrieves status for network devices
     * @return An array of NetworkDeviceStatus entities describing the status of network devices in the system
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<NetworkDeviceStatus> getNetworkDevicesStatus() throws LiqidException {
        var fn = "getNetworkDevicesStatus";
        _logger.trace("Entering %s", fn);

        try {
            var path = "status/network";
            var qpList = new LinkedList<String>();
            qpList.add("filter=" + false);
            path += "?" + String.join("&", qpList);
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), NetworkDeviceStatus.NetworkDeviceStatusWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getNetworkIPMIManagedCPU()
     * Category: Manager
     * Retrieves a particular network-IPMI-managed CPU given its name
     * @param cpuName: Name of the CPU in question
     * @return A NetworkManagedCPU entity
     * @throws LiqidException if anything goes wrong
     */
    public NetworkManagedCPU getNetworkIPMIManagedCPU(String cpuName) throws LiqidException {
        var fn = "getNetworkIPMIManagedCPU";
        _logger.trace("Entering %s cpuName:%s", fn, cpuName);
        checkParameterNotNull(cpuName, "cpuName", fn);
        for (var item : getNetworkIPMIManagedCPUS()) {
            if (cpuName.equals(item.getCPUName())) {
                _logger.trace("%%s returning %%s", fn, item);
                return item;
            }
        }

        var ex = new LiqidException("Entity not found");
        _logger.throwing(ex);
        throw ex;
    }

    /**
     * getNetworkIPMIManagedCPUS()
     * Category: Manager
     * Retrieves a list of management entries for IPMI-via-network CPUs
     * @return An array of NetworkManagedCPU entities
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<NetworkManagedCPU> getNetworkIPMIManagedCPUS() throws LiqidException {
        var fn = "getNetworkIPMIManagedCPUS";
        _logger.trace("Entering %s", fn);

        try {
            var path = "manager/network/ipmi/cpu";
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), NetworkManagedCPU.NetworkManagedCPUWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getNetworkIPMIManagedEnclosure()
     * Category: Manager
     * Retrieves a particular network-IPMI-managed enclosure given its name
     * @param enclosureName: Name of the enclosure in question
     * @return A NetworkManagedEnclosure entity
     * @throws LiqidException if anything goes wrong
     */
    public NetworkManagedEnclosure getNetworkIPMIManagedEnclosure(Integer enclosureName) throws LiqidException {
        var fn = "getNetworkIPMIManagedEnclosure";
        _logger.trace("Entering %s enclosureName:%s", fn, enclosureName);
        checkParameterNotNull(enclosureName, "enclosureName", fn);
        for (var item : getNetworkIPMIManagedEnclosures()) {
            if (enclosureName.equals(item.getEnclosureName())) {
                _logger.trace("%%s returning %%s", fn, item);
                return item;
            }
        }

        var ex = new LiqidException("Entity not found");
        _logger.throwing(ex);
        throw ex;
    }

    /**
     * getNetworkIPMIManagedEnclosures()
     * Category: Manager
     * Retrieves a list of management entries for IPMI-via-network enclosures
     * @return An array of NetworkManagedEnclosure entities
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<NetworkManagedEnclosure> getNetworkIPMIManagedEnclosures() throws LiqidException {
        var fn = "getNetworkIPMIManagedEnclosures";
        _logger.trace("Entering %s", fn);

        try {
            var path = "manager/network/ipmi/enclosure";
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), NetworkManagedEnclosure.NetworkManagedEnclosureWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getNextGroupId()
     * Category: Group
     * Retrieves the next sequential unused group identifier
     * @return The next sequential unused group identifier
     * @throws LiqidException if anything goes wrong
     * Deprecated:
     *   Use of this function in conjunction with createGroupWithId opens a potential race condition.
     *   This problem exists internally in the Director, and is reflected both in the REST API and in this SDK.
     *   A future version of the Director will provide a means of creating a group whereby the group id is internally generated.
     *   For this reason, SDK clients are encouraged to use the createGroup function which wraps the Get/Create mechanism in one SDK function call.
     *   For the time being, this does not correct the race condition; however, it protects SDK clients from the eventual removal of createGroupWithId and getNextGroupId.
     */
    @Deprecated
    public Integer getNextGroupId() throws LiqidException {
        var fn = "getNextGroupId";
        _logger.trace("Entering %s", fn);

        try {
            var path = "group/nextid";
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), IntegerWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getNextMachineId()
     * Category: Machine
     * Retrieves the next sequential unused machine identifier
     * @return The next sequential unused machine identifier
     * @throws LiqidException if anything goes wrong
     * Deprecated:
     *   Use of this function in conjunction with createMachineWithId opens a potential race condition.
     *   This problem exists internally in the Director, and is reflected both in the REST API and in this SDK.
     *   A future version of the Director will provide a means of creating a machine whereby the machine id is internally generated.
     *   For this reason, SDK clients are encouraged to use the createMachine function which wraps the Get/Create mechanism in one SDK function call.
     *   For the time being, this does not correct the race condition; however, it protects SDK clients from the eventual removal of createMachineWithId and getNextMachineId.
     */
    @Deprecated
    public String getNextMachineId() throws LiqidException {
        var fn = "getNextMachineId";
        _logger.trace("Entering %s", fn);

        try {
            var path = "machine/nextid";
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), StringWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getNodesStatus()
     * Category: NodeStatus
     * Retrieves status for the nodes in the configuration
     * @return An array of NodeStatus entities describing the status of the nodes in the configuraiton
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<NodeStatus> getNodesStatus() throws LiqidException {
        var fn = "getNodesStatus";
        _logger.trace("Entering %s", fn);

        try {
            var path = "node/status";
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), NodeStatus.NodeStatusWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getSecureEraseStatus()
     * Category: Device
     * Retrieves the status of an asynchronous secure erase operation
     * @param deviceId: Device identifier returned by secureErase
     * @return A AsynchronousStatus entity describing the state of the indicated operation
     * @throws LiqidException if anything goes wrong
     */
    public AsynchronousStatus getSecureEraseStatus(Integer deviceId) throws LiqidException {
        var fn = "getSecureEraseStatus";
        _logger.trace("Entering %s deviceId:%s", fn, deviceId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        var deviceIdTranslated = String.format("0x%08x", deviceId);

        try {
            var path = "device/erase/";
            path += "/" + deviceIdTranslated.toString();
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), AsynchronousStatus.AsynchronousStatusWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getSSHStatus()
     * Category: Ssh
     * Retrieves the current state of SSH
     * @return A SSHStatus entity describing the state of SSH
     * @throws LiqidException if anything goes wrong
     */
    public SSHStatus getSSHStatus() throws LiqidException {
        var fn = "getSSHStatus";
        _logger.trace("Entering %s", fn);

        try {
            var path = "ssh/enable";
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), SSHStatus.SSHStatusWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getStorageDeviceStatus()
     * Category: DeviceStatus
     * Retrieves status for a particular storage device
     * @param deviceId: Identifier of the device in question
     * @return A StorageDeviceStatus entity describing the status of the requested device
     * @throws LiqidException if anything goes wrong
     */
    public StorageDeviceStatus getStorageDeviceStatus(Integer deviceId) throws LiqidException {
        var fn = "getStorageDeviceStatus";
        _logger.trace("Entering %s deviceId:%s", fn, deviceId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        for (var item : getStorageDevicesStatus()) {
            if (deviceId.equals(item.getDeviceId())) {
                _logger.trace("%%s returning %%s", fn, item);
                return item;
            }
        }

        var ex = new LiqidException("Entity not found");
        _logger.throwing(ex);
        throw ex;
    }

    /**
     * getStorageDevicesStatus()
     * Category: DeviceStatus
     * Retrieves status for all storage devices
     * @return An array of StorageDeviceStatus entities describing the status of storage devices in the system
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<StorageDeviceStatus> getStorageDevicesStatus() throws LiqidException {
        var fn = "getStorageDevicesStatus";
        _logger.trace("Entering %s", fn);

        try {
            var path = "status/storage";
            var qpList = new LinkedList<String>();
            qpList.add("filter=" + false);
            path += "?" + String.join("&", qpList);
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), StorageDeviceStatus.StorageDeviceStatusWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getStorageDeviceInfo()
     * Category: DeviceInfo
     * Retrieves additional information for all known Storage devices
     * @return An array of StorageDeviceInfo entities describing all known Storage devices
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<StorageDeviceInfo> getStorageDeviceInfo() throws LiqidException {
        var fn = "getStorageDeviceInfo";
        _logger.trace("Entering %s", fn);

        try {
            var path = "device/info/ssd";
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), StorageDeviceInfo.StorageDeviceInfoWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getStorageDeviceInfoByName()
     * Category: DeviceInfo
     * Retrieves additional information for a particular Storage device
     * @param deviceName: Name of the device in question
     * @return StorageDeviceInfo entity for the requested device
     * @throws LiqidException if anything goes wrong
     */
    public StorageDeviceInfo getStorageDeviceInfoByName(String deviceName) throws LiqidException {
        var fn = "getStorageDeviceInfoByName";
        _logger.trace("Entering %s deviceName:%s", fn, deviceName);
        checkParameterNotNull(deviceName, "deviceName", fn);

        try {
            var path = "device/info/ssd";
            path += "/" + deviceName.toString();
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), StorageDeviceInfo.StorageDeviceInfoWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * getVersions()
     * Category: Version
     * Retrieves information describing the various software components which comprise the Liqid Director.
     * @return An array of VersionInfo entities describing the various software components
     * @throws LiqidException if anything goes wrong
     */
    public LinkedList<VersionInfo> getVersions() throws LiqidException {
        var fn = "getVersions";
        _logger.trace("Entering %s", fn);

        try {
            var path = "version";
            var httpResponse = send("GET", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), VersionInfo.VersionInfoWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * groupPoolDone()
     * Category: GroupPool
     * Completes a pending group pool edit operation.
     * All pending device attachments or detachments are finalized.
     * @param groupId: Identifier of the group for which edit mode is to be completed
     * @return A Group entity describing the group pool
     * @throws LiqidException if anything goes wrong
     */
    public GroupPool groupPoolDone(Integer groupId) throws LiqidException {
        var fn = "groupPoolDone";
        _logger.trace("Entering %s groupId:%s", fn, groupId);
        checkParameterNotNull(groupId, "groupId", fn);

        try {
            var path = "pool/done";
            var obj = new GroupPool();
            obj.setGroupId(groupId);
            obj.getCoordinates().setRack(getPresetRack());
            obj.getCoordinates().setShelf(getPresetShelf());
            obj.getCoordinates().setNode(getPresetNode());
            obj.setFabricId(getPresetFabricId());
            var httpResponse = send("POST", path, HttpBodyType.Json, obj, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), GroupPool.GroupPoolWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * groupPoolEdit()
     * Category: GroupPool
     * Establishes edit mode for a particular group pool.
     * Must be invoked before adding devices to or removing devices from a group free pool.
     * @param groupId: Identifier of the group for which edit mode is to be established
     * @return A Group entity describing the group pool
     * @throws LiqidException if anything goes wrong
     */
    public GroupPool groupPoolEdit(Integer groupId) throws LiqidException {
        var fn = "groupPoolEdit";
        _logger.trace("Entering %s groupId:%s", fn, groupId);
        checkParameterNotNull(groupId, "groupId", fn);

        try {
            var path = "pool/edit";
            var obj = new GroupPool();
            obj.setGroupId(groupId);
            obj.getCoordinates().setRack(getPresetRack());
            obj.getCoordinates().setShelf(getPresetShelf());
            obj.getCoordinates().setNode(getPresetNode());
            obj.setFabricId(getPresetFabricId());
            var httpResponse = send("POST", path, HttpBodyType.Json, obj, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), GroupPool.GroupPoolWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * rebootNode()
     * Category: PowerManagement
     * Reboots a particular node
     * @param groupId: Unique identifier of the group which contains the node of interest
     * @param machineId: Unique machine identifier containing the node of interest
     * @return A Machine entity describing the node
     * @throws LiqidException if anything goes wrong
     */
    public Machine rebootNode(Integer groupId,
                              Integer machineId) throws LiqidException {
        var fn = "rebootNode";
        _logger.trace("Entering %s groupId:%s machineId:%s", fn, groupId, machineId);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);

        try {
            var path = "power/reboot";
            path += "/" + groupId.toString();
            path += "/" + machineId.toString();
            var httpResponse = send("PUT", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), Machine.MachineWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * removeComputeDeviceFromGroup()
     * Category: GroupDeviceRelator
     * Moves a device from the group free pool to the system free pool
     * @param deviceId: Identifier of the device to be removed from the group.
     * @param groupId: Identifier of the group from which the device is to be removed.
     * @return A description of the deleted relation
     * @throws LiqidException if anything goes wrong
     */
    public GroupComputeDeviceRelator removeComputeDeviceFromGroup(Integer deviceId,
                                                                  Integer groupId) throws LiqidException {
        var fn = "removeComputeDeviceFromGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        try {
            var composite = new GroupComputeDeviceRelator();
            composite.setDeviceStatus(getComputeDeviceStatus(deviceId));
            composite.setGroup(getGroup(groupId));

            var path = "predevice/compute";
            var httpResponse = send("DELETE", path, HttpBodyType.Json, composite, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), GroupComputeDeviceRelator.GroupComputeDeviceRelatorWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * removeComputeDeviceFromMachine()
     * Category: MachineDeviceRelator
     * Detaches a particular device from a machine, returning it to the group free pool
     * @param deviceId: Unique identifier of the device to be removed
     * @param groupId: Unique identifier of the group to which the containing machine belongs
     * @param machineId: Unique identifier of the machine from which the device is to be removed
     * @return A description of the relation being removed
     * @throws LiqidException if anything goes wrong
     */
    public MachineComputeDeviceRelator removeComputeDeviceFromMachine(Integer deviceId,
                                                                      Integer groupId,
                                                                      Integer machineId) throws LiqidException {
        var fn = "removeComputeDeviceFromMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);
        try {
            var composite = new MachineComputeDeviceRelator();
            composite.setGroupDeviceRelator(getGroupComputeDeviceRelator(groupId, deviceId));
            composite.setMachine(getMachine(machineId));

            var path = "relate/compute";
            var httpResponse = send("DELETE", path, HttpBodyType.Json, composite, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), MachineComputeDeviceRelator.MachineComputeDeviceRelatorWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * removeFPGADeviceFromGroup()
     * Category: GroupDeviceRelator
     * Moves a device from the group free pool to the system free pool
     * @param deviceId: Identifier of the device to be removed from the group.
     * @param groupId: Identifier of the group from which the device is to be removed.
     * @return A description of the deleted relation
     * @throws LiqidException if anything goes wrong
     */
    public GroupFPGADeviceRelator removeFPGADeviceFromGroup(Integer deviceId,
                                                            Integer groupId) throws LiqidException {
        var fn = "removeFPGADeviceFromGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        try {
            var composite = new GroupFPGADeviceRelator();
            composite.setDeviceStatus(getFPGADeviceStatus(deviceId));
            composite.setGroup(getGroup(groupId));

            var path = "predevice/fpga";
            var httpResponse = send("DELETE", path, HttpBodyType.Json, composite, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), GroupFPGADeviceRelator.GroupFPGADeviceRelatorWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * removeFPGADeviceFromMachine()
     * Category: MachineDeviceRelator
     * Detaches a particular device from a machine, returning it to the group free pool
     * @param deviceId: Unique identifier of the device to be removed
     * @param groupId: Unique identifier of the group to which the containing machine belongs
     * @param machineId: Unique identifier of the machine from which the device is to be removed
     * @return A description of the relation being removed
     * @throws LiqidException if anything goes wrong
     */
    public MachineFPGADeviceRelator removeFPGADeviceFromMachine(Integer deviceId,
                                                                Integer groupId,
                                                                Integer machineId) throws LiqidException {
        var fn = "removeFPGADeviceFromMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);
        try {
            var composite = new MachineFPGADeviceRelator();
            composite.setGroupDeviceRelator(getGroupFPGADeviceRelator(groupId, deviceId));
            composite.setMachine(getMachine(machineId));

            var path = "relate/fpga";
            var httpResponse = send("DELETE", path, HttpBodyType.Json, composite, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), MachineFPGADeviceRelator.MachineFPGADeviceRelatorWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * removeGPUDeviceFromGroup()
     * Category: GroupDeviceRelator
     * Moves a device from the group free pool to the system free pool
     * @param deviceId: Identifier of the device to be removed from the group.
     * @param groupId: Identifier of the group from which the device is to be removed.
     * @return A description of the deleted relation
     * @throws LiqidException if anything goes wrong
     */
    public GroupGPUDeviceRelator removeGPUDeviceFromGroup(Integer deviceId,
                                                          Integer groupId) throws LiqidException {
        var fn = "removeGPUDeviceFromGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        try {
            var composite = new GroupGPUDeviceRelator();
            composite.setDeviceStatus(getGPUDeviceStatus(deviceId));
            composite.setGroup(getGroup(groupId));

            var path = "predevice/gpu";
            var httpResponse = send("DELETE", path, HttpBodyType.Json, composite, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), GroupGPUDeviceRelator.GroupGPUDeviceRelatorWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * removeGPUDeviceFromMachine()
     * Category: MachineDeviceRelator
     * Detaches a particular device from a machine, returning it to the group free pool
     * @param deviceId: Unique identifier of the device to be removed
     * @param groupId: Unique identifier of the group to which the containing machine belongs
     * @param machineId: Unique identifier of the machine from which the device is to be removed
     * @return A description of the relation being removed
     * @throws LiqidException if anything goes wrong
     */
    public MachineGPUDeviceRelator removeGPUDeviceFromMachine(Integer deviceId,
                                                              Integer groupId,
                                                              Integer machineId) throws LiqidException {
        var fn = "removeGPUDeviceFromMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);
        try {
            var composite = new MachineGPUDeviceRelator();
            composite.setGroupDeviceRelator(getGroupGPUDeviceRelator(groupId, deviceId));
            composite.setMachine(getMachine(machineId));

            var path = "relate/gpu";
            var httpResponse = send("DELETE", path, HttpBodyType.Json, composite, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), MachineGPUDeviceRelator.MachineGPUDeviceRelatorWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * removeMemoryDeviceFromGroup()
     * Category: GroupDeviceRelator
     * Moves a device from the group free pool to the system free pool
     * @param deviceId: Identifier of the device to be removed from the group.
     * @param groupId: Identifier of the group from which the device is to be removed.
     * @return A description of the deleted relation
     * @throws LiqidException if anything goes wrong
     */
    public GroupMemoryDeviceRelator removeMemoryDeviceFromGroup(Integer deviceId,
                                                                Integer groupId) throws LiqidException {
        var fn = "removeMemoryDeviceFromGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        try {
            var composite = new GroupMemoryDeviceRelator();
            composite.setGroup(getGroup(groupId));
            composite.setDeviceStatus(getMemoryDeviceStatus(deviceId));

            var path = "predevice/mem";
            var httpResponse = send("DELETE", path, HttpBodyType.Json, composite, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), GroupMemoryDeviceRelator.GroupMemoryDeviceRelatorWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * removeMemoryDeviceFromMachine()
     * Category: MachineDeviceRelator
     * Detaches a particular device from a machine, returning it to the group free pool
     * @param deviceId: Unique identifier of the device to be removed
     * @param groupId: Unique identifier of the group to which the containing machine belongs
     * @param machineId: Unique identifier of the machine from which the device is to be removed
     * @return A description of the relation being removed
     * @throws LiqidException if anything goes wrong
     */
    public MachineMemoryDeviceRelator removeMemoryDeviceFromMachine(Integer deviceId,
                                                                    Integer groupId,
                                                                    Integer machineId) throws LiqidException {
        var fn = "removeMemoryDeviceFromMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);
        try {
            var composite = new MachineMemoryDeviceRelator();
            composite.setGroupDeviceRelator(getGroupMemoryDeviceRelator(groupId, deviceId));
            composite.setMachine(getMachine(machineId));

            var path = "relate/memory";
            var httpResponse = send("DELETE", path, HttpBodyType.Json, composite, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), MachineMemoryDeviceRelator.MachineMemoryDeviceRelatorWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * removeNetworkDeviceFromGroup()
     * Category: GroupDeviceRelator
     * Moves a device from the group free pool to the system free pool
     * @param deviceId: Identifier of the device to be removed from the group.
     * @param groupId: Identifier of the group from which the device is to be removed.
     * @return A description of the deleted relation
     * @throws LiqidException if anything goes wrong
     */
    public GroupNetworkDeviceRelator removeNetworkDeviceFromGroup(Integer deviceId,
                                                                  Integer groupId) throws LiqidException {
        var fn = "removeNetworkDeviceFromGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        try {
            var composite = new GroupNetworkDeviceRelator();
            composite.setGroup(getGroup(groupId));
            composite.setDeviceStatus(getNetworkDeviceStatus(deviceId));

            var path = "predevice/network";
            var httpResponse = send("DELETE", path, HttpBodyType.Json, composite, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), GroupNetworkDeviceRelator.GroupNetworkDeviceRelatorWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * removeNetworkDeviceFromMachine()
     * Category: MachineDeviceRelator
     * Detaches a particular device from a machine, returning it to the group free pool
     * @param deviceId: Unique identifier of the device to be removed
     * @param groupId: Unique identifier of the group to which the containing machine belongs
     * @param machineId: Unique identifier of the machine from which the device is to be removed
     * @return A description of the relation being removed
     * @throws LiqidException if anything goes wrong
     */
    public MachineNetworkDeviceRelator removeNetworkDeviceFromMachine(Integer deviceId,
                                                                      Integer groupId,
                                                                      Integer machineId) throws LiqidException {
        var fn = "removeNetworkDeviceFromMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);
        try {
            var composite = new MachineNetworkDeviceRelator();
            composite.setGroupDeviceRelator(getGroupNetworkDeviceRelator(groupId, deviceId));
            composite.setMachine(getMachine(machineId));

            var path = "relate/network";
            var httpResponse = send("DELETE", path, HttpBodyType.Json, composite, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), MachineNetworkDeviceRelator.MachineNetworkDeviceRelatorWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * removeStorageDeviceFromGroup()
     * Category: GroupDeviceRelator
     * Moves a device from the group free pool to the system free pool
     * @param deviceId: Identifier of the device to be removed from the group.
     * @param groupId: Identifier of the group from which the device is to be removed.
     * @return A description of the deleted relation
     * @throws LiqidException if anything goes wrong
     */
    public GroupStorageDeviceRelator removeStorageDeviceFromGroup(Integer deviceId,
                                                                  Integer groupId) throws LiqidException {
        var fn = "removeStorageDeviceFromGroup";
        _logger.trace("Entering %s deviceId:%s groupId:%s", fn, deviceId, groupId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        try {
            var composite = new GroupStorageDeviceRelator();
            composite.setGroup(getGroup(groupId));
            composite.setDeviceStatus(getStorageDeviceStatus(deviceId));

            var path = "predevice/storage";
            var httpResponse = send("DELETE", path, HttpBodyType.Json, composite, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), GroupStorageDeviceRelator.GroupStorageDeviceRelatorWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * removeStorageDeviceFromMachine()
     * Category: MachineDeviceRelator
     * Detaches a particular device from a machine, returning it to the group free pool
     * @param deviceId: Unique identifier of the device to be removed
     * @param groupId: Unique identifier of the group to which the containing machine belongs
     * @param machineId: Unique identifier of the machine from which the device is to be removed
     * @return A description of the relation being removed
     * @throws LiqidException if anything goes wrong
     */
    public MachineStorageDeviceRelator removeStorageDeviceFromMachine(Integer deviceId,
                                                                      Integer groupId,
                                                                      Integer machineId) throws LiqidException {
        var fn = "removeStorageDeviceFromMachine";
        _logger.trace("Entering %s deviceId:%s groupId:%s machineId:%s", fn, deviceId, groupId, machineId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);
        try {
            var composite = new MachineStorageDeviceRelator();
            composite.setGroupDeviceRelator(getGroupStorageDeviceRelator(groupId, deviceId));
            composite.setMachine(getMachine(machineId));

            var path = "relate/storage";
            var httpResponse = send("DELETE", path, HttpBodyType.Json, composite, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), MachineStorageDeviceRelator.MachineStorageDeviceRelatorWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * reprogramFabric()
     * Category: Fabric
     * Reprogram the fabric. This will result in devices associated with a machine being electrically connected to the machine.
     * This step MUST be done in order for a device to be added to a machine.
     * @param machineId: Unique id of the machine for which fabric reprogramming is to be accomplished.
     * @return A Machine entity
     * @throws LiqidException if anything goes wrong
     */
    public Machine reprogramFabric(Integer machineId) throws LiqidException {
        var fn = "reprogramFabric";
        _logger.trace("Entering %s machineId:%s", fn, machineId);
        checkParameterNotNull(machineId, "machineId", fn);
        try {
            var object = getMachine(machineId);
            var path = "fabric/reprogram";
            var httpResponse = send("POST", path, HttpBodyType.Json, object, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), Machine.MachineWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * resetState()
     * Category: System
     * Disconnects the device connections to a CPU.
     * Removes LiqOS state information related to machines, groups, and devices.
     * Forces a Liqid rediscovery of the fabric.
     * @return The date/time at which the operation was invoked
     * @throws LiqidException if anything goes wrong
     */
    public Timestamp resetState() throws LiqidException {
        var fn = "resetState";
        _logger.trace("Entering %s", fn);

        try {
            var path = "system/state/reset";
            var httpResponse = send("POST", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), Timestamp.TimestampWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * restartFabric()
     * Category: System
     * Restarts the entire fabric
     * @return The date/time at which the operation was invoked
     * @throws LiqidException if anything goes wrong
     */
    public Timestamp restartFabric() throws LiqidException {
        var fn = "restartFabric";
        _logger.trace("Entering %s", fn);

        try {
            var path = "system/restart/fabric";
            var httpResponse = send("POST", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), Timestamp.TimestampWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * restartHierarchy()
     * Category: System
     * Initiates a discovery of the fabric hierarchy
     * @return The date/time at which the operation was invoked
     * @throws LiqidException if anything goes wrong
     */
    public Timestamp restartHierarchy() throws LiqidException {
        var fn = "restartHierarchy";
        _logger.trace("Entering %s", fn);

        try {
            var path = "system/restart/hierarchy";
            var httpResponse = send("POST", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), Timestamp.TimestampWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * restartNode()
     * Category: PowerManagement
     * Restarts a particular node
     * @param groupId: Unique identifier of the group which contains the node of interest
     * @param machineId: Unique machine identifier containing the node of interest
     * @return A Machine entity describing the node
     * @throws LiqidException if anything goes wrong
     */
    public Machine restartNode(Integer groupId,
                               Integer machineId) throws LiqidException {
        var fn = "restartNode";
        _logger.trace("Entering %s groupId:%s machineId:%s", fn, groupId, machineId);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);

        try {
            var path = "power/restart";
            path += "/" + groupId.toString();
            path += "/" + machineId.toString();
            var httpResponse = send("PUT", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), Machine.MachineWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * restartSwitch()
     * Category: System
     * Restarts the switch at the default coordinates
     * @return The date/time at which the operation was invoked
     * @throws LiqidException if anything goes wrong
     */
    public Timestamp restartSwitch() throws LiqidException {
        var fn = "restartSwitch";
        _logger.trace("Entering %s", fn);

        try {
            var path = "system/restart/switch";
            var httpResponse = send("POST", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), Timestamp.TimestampWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * secureErase()
     * Category: Device
     * Securely erases a particular storage device.
     * This is an asynchronous operation - the function returns before the process is complete.
     * @param deviceId: The identifier of the drive to be securely erased.
                        This is the hexadecimal identifier prefixed with '0x'
     * @return Describes the asynchronous identifier associated with this operation
     * @throws LiqidException if anything goes wrong
     */
    public AsynchronousInfo secureErase(Integer deviceId) throws LiqidException {
        var fn = "secureErase";
        _logger.trace("Entering %s deviceId:%s", fn, deviceId);
        checkParameterNotNull(deviceId, "deviceId", fn);
        var deviceIdTranslated = String.format("0x%08x", deviceId);

        try {
            var path = "device";
            path += "/" + deviceIdTranslated.toString();
            var httpResponse = send("PUT", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), AsynchronousInfo.AsynchronousInfoWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * setDefaultCoordinates()
     * Category: Coordinates
     * Sets the default coordinates used for subsequent operations initiated via the SDK.
     * Specifying any set of values apart from what is available from getAvailableCoordinates produces undefined behavior.
     * @param rack: Rack component of the liqid coordinates for the REST API endpoint
                    Should always be zero.
                    This parameter is optional, and should be set to null if it is to remain unspecified.
     * @param shelf: Shelf component of the liqid coordinates for the REST API endpoint
                     Should always be zero.
                     This parameter is optional, and should be set to null if it is to remain unspecified.
     * @param node: Node component of the liqid coordinates for the REST API endpoint
     * @return Liqid coordinates to which all future REST API invocations are directed
     * @throws LiqidException if anything goes wrong
     */
    public Coordinates setDefaultCoordinates(Integer rack,
                                             Integer shelf,
                                             Integer node) throws LiqidException {
        var fn = "setDefaultCoordinates";
        _logger.trace("Entering %s rack:%s shelf:%s node:%s", fn, rack, shelf, node);
        checkParameterNotNull(node, "node", fn);

        try {
            var path = "coordinates";
            var obj = new Coordinates();
            if (rack != null) obj.setRack(rack);
            if (shelf != null) obj.setShelf(shelf);
            obj.setNode(node);
            var httpResponse = send("POST", path, HttpBodyType.Json, obj, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), Coordinates.CoordinatesWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * setSSHStatus()
     * Category: Ssh
     * Sets the state of SSH
     * @param active: Indicates whether SSH should be active
     * @param enabled: Indicates whether SSH should be enabled
     * @return A SSHStatus entity describing the state of SSH
     * @throws LiqidException if anything goes wrong
     */
    public SSHStatus setSSHStatus(Boolean active,
                                  Boolean enabled) throws LiqidException {
        var fn = "setSSHStatus";
        _logger.trace("Entering %s active:%s enabled:%s", fn, active, enabled);
        checkParameterNotNull(active, "active", fn);
        checkParameterNotNull(enabled, "enabled", fn);

        try {
            var path = "ssh/enable";
            var obj = new SSHStatus();
            obj.setActive(active);
            obj.setEnabled(enabled);
            var httpResponse = send("POST", path, HttpBodyType.Json, obj, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), SSHStatus.SSHStatusWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * shutdown()
     * Category: System
     * Gracefully powers down the director at the default coordinates
     * @return The coordinates for the shutdown operation
     * @throws LiqidException if anything goes wrong
     */
    public Coordinates shutdown() throws LiqidException {
        var fn = "shutdown";
        _logger.trace("Entering %s", fn);

        try {
            var path = "system/shutdown";
            var httpResponse = send("PUT", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), Coordinates.CoordinatesWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * shutdownAt()
     * Category: System
     * Shuts down the director at the given coordinates
     * @param rackId: The rack id component of the Liqid coordinates
     * @param shelfId: The shelf id component of the Liqid coordinates
     * @param nodeId: The node id component of the Liqid coordinates
     * @return The coordinates for the shutdown operation
     * @throws LiqidException if anything goes wrong
     */
    public Coordinates shutdownAt(Integer rackId,
                                  Integer shelfId,
                                  Integer nodeId) throws LiqidException {
        var fn = "shutdownAt";
        _logger.trace("Entering %s rackId:%s shelfId:%s nodeId:%s", fn, rackId, shelfId, nodeId);
        checkParameterNotNull(rackId, "rackId", fn);
        checkParameterNotNull(shelfId, "shelfId", fn);
        checkParameterNotNull(nodeId, "nodeId", fn);

        try {
            var path = "system/shutdown";
            path += "/" + rackId.toString();
            path += "/" + shelfId.toString();
            path += "/" + nodeId.toString();
            var httpResponse = send("PUT", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), Coordinates.CoordinatesWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * shutdownNode()
     * Category: PowerManagement
     * Shuts down a particular node
     * @param groupId: Unique identifier of the group which contains the node of interest
     * @param machineId: Unique machine identifier containing the node of interest
     * @return A Machine entity describing the node
     * @throws LiqidException if anything goes wrong
     */
    public Machine shutdownNode(Integer groupId,
                                Integer machineId) throws LiqidException {
        var fn = "shutdownNode";
        _logger.trace("Entering %s groupId:%s machineId:%s", fn, groupId, machineId);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);

        try {
            var path = "power/shutdown";
            path += "/" + groupId.toString();
            path += "/" + machineId.toString();
            var httpResponse = send("PUT", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), Machine.MachineWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * startNode()
     * Category: PowerManagement
     * Starts a particular node
     * @param groupId: Unique identifier of the group which contains the node of interest
     * @param machineId: Unique machine identifier containing the node of interest
     * @return A Machine entity describing the node
     * @throws LiqidException if anything goes wrong
     */
    public Machine startNode(Integer groupId,
                             Integer machineId) throws LiqidException {
        var fn = "startNode";
        _logger.trace("Entering %s groupId:%s machineId:%s", fn, groupId, machineId);
        checkParameterNotNull(groupId, "groupId", fn);
        checkParameterNotNull(machineId, "machineId", fn);

        try {
            var path = "power/start";
            path += "/" + groupId.toString();
            path += "/" + machineId.toString();
            var httpResponse = send("PUT", path, HttpBodyType.None, null, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), Machine.MachineWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }

    /**
     * setP2PEnabled()
     * Category: Machine
     * Sets or clears the P2P flag for the indicated machine.
     * Only effective for powered-on machines having at least two GPUs.
     * @param machineId: Unique id of the machine for which P2P is to be enabled or disabled.
     * @param p2pEnabled: Value to be set for the P2P flag.
     * @return a Machine entity describing the affected machine
     * @throws LiqidException if anything goes wrong
     */
    public Machine setP2PEnabled(Integer machineId,
                                 P2PType p2pEnabled) throws LiqidException {
        var fn = "setP2PEnabled";
        _logger.trace("Entering %s machineId:%s p2pEnabled:%s", fn, machineId, p2pEnabled);
        checkParameterNotNull(machineId, "machineId", fn);
        checkParameterNotNull(p2pEnabled, "p2pEnabled", fn);
        try {
            var object = getMachine(machineId);
            object.setP2PEnabled(p2pEnabled);
            var path = "machine/p2p";
            var httpResponse = send("POST", path, HttpBodyType.Json, object, HttpBodyType.Json);
            var mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            var wrapper = mapper.readValue((String) httpResponse.body(), Machine.MachineWrapper.class);
            wrapper.check();
            var result = wrapper.getResponse().getData().getFirst();

            _logger.trace("%s returning %s", fn, result);
            return result;
        } catch (LiqidException ex) {
            _logger.throwing(ex);
            throw ex;
        } catch (Exception ex) {
            _logger.throwing(ex);
            throw new LiqidException("Caught exception:", ex);
        }
    }


    /**
     * Parameterized Constructor
     */
    protected LiqidClient(Boolean secureHTTP,
                          String hostAddress,
                          Integer portNumber,
                          Boolean ignoreCertificates,
                          Integer timeoutInSeconds,
                          Boolean retryOnServerError,
                          Integer retryLimit,
                          Integer retryDelayInSeconds,
                          Boolean waitForAsyncCompletion,
                          Integer maxAsyncWaitTimeInSeconds) {
        super(secureHTTP, hostAddress, portNumber, ignoreCertificates, timeoutInSeconds, retryOnServerError, retryLimit, retryDelayInSeconds, waitForAsyncCompletion, maxAsyncWaitTimeInSeconds);
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_secureHTTP:").append(getSecureHTTP());
        sb.append(", ").append("_hostAddress:").append(getHostAddress());
        sb.append(", ").append("_portNumber:").append(getPortNumber());
        sb.append(", ").append("_ignoreCertificates:").append(getIgnoreCertificates());
        sb.append(", ").append("_timeoutInSeconds:").append(getTimeoutInSeconds());
        sb.append(", ").append("_retryOnServerError:").append(getRetryOnServerError());
        sb.append(", ").append("_retryLimit:").append(getRetryLimit());
        sb.append(", ").append("_retryDelayInSeconds:").append(getRetryDelayInSeconds());
        sb.append(", ").append("_waitForAsyncCompletion:").append(getWaitForAsyncCompletion());
        sb.append(", ").append("_maxAsyncWaitTimeInSeconds:").append(getMaxAsyncWaitTimeInSeconds());
        sb.append("}");
        return sb.toString();
    }
}
