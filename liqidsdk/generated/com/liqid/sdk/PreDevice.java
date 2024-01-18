// File: PreDevice.java
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

/**
 * PreDevice
 * Describes a device before it is added to a group
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PreDevice {

    public static class PreDeviceWrapper extends Wrapper<PreDevice>{}

    /**
     * Device name
     */
    @JsonProperty("name")
    private String _deviceName = null;

    public String getDeviceName() {
        return _deviceName;
    }

    public PreDevice setDeviceName(String value) {
        _deviceName = value;
        return this;
    }

    /**
     * Fabric global id
     */
    @JsonProperty("fabr_gid")
    private String _fabricGlobalId = null;

    public Integer getFabricGlobalId() {
        return LiqidClientBase.hexStringToInteger(_fabricGlobalId);
    }

    public PreDevice setFabricGlobalId(Integer value) {
        _fabricGlobalId = String.format("0x%08x", value);
        return this;
    }

    /**
     * Fabric identifier to which the device belongs
     */
    @JsonProperty("fabr_id")
    private Integer _fabricId = null;

    public Integer getFabricId() {
        return _fabricId;
    }

    public PreDevice setFabricId(Integer value) {
        _fabricId = value;
        return this;
    }

    /**
     * Flags for the device - a hex string prefixed by '0x'
     */
    @JsonProperty("flags")
    private String _flags = null;

    public Long getFlags() {
        return LiqidClientBase.hexStringToLong(_flags);
    }

    public PreDevice setFlags(Long value) {
        _flags = String.format("0x%016x", value);
        return this;
    }

    /**
     * TODO
     */
    @JsonProperty("grp_id")
    private Integer _groupId = null;

    public Integer getGroupId() {
        return _groupId;
    }

    public PreDevice setGroupId(Integer value) {
        _groupId = value;
        return this;
    }

    /**
     * TODO
     */
    @JsonProperty("gname")
    private String _groupName = null;

    public String getGroupName() {
        return _groupName;
    }

    public PreDevice setGroupName(String value) {
        _groupName = value;
        return this;
    }

    /**
     * Internal index for this pre-device
     */
    @JsonProperty("index")
    private String _index = null;

    public String getIndex() {
        return _index;
    }

    public PreDevice setIndex(String value) {
        _index = value;
        return this;
    }

    /**
     * Machine identifier
     */
    @JsonProperty("mach_id")
    private String _machineId = null;

    public Integer getMachineId() {
        // check for null - jackson likes to use beans, and beans like to use
        // getters and setters, and sometimes the field is null.
        if (_machineId == null) return null;
        if (_machineId.equals("n/a")) {
            return 0;
        }
        return Integer.parseInt(_machineId);
    }

    public PreDevice setMachineId(Integer value) {
        if (value.equals(0)) {
            _machineId = "n/a";
            return this;
        }
        _machineId = String.format("%d", value);
        return this;
    }

    /**
     * Machine name
     */
    @JsonProperty("mname")
    private String _machineName = null;

    public String getMachineName() {
        return _machineName;
    }

    public PreDevice setMachineName(String value) {
        _machineName = value;
        return this;
    }

    /**
     * Owner identifier
     */
    @JsonProperty("owner_id")
    private String _ownerId = null;

    public Integer getOwnerId() {
        return LiqidClientBase.hexStringToInteger(_ownerId);
    }

    public PreDevice setOwnerId(Integer value) {
        _ownerId = String.format("0x%08x", value);
        return this;
    }

    /**
     * Number of PCI lanes for this device
     */
    @JsonProperty("lanes")
    private Integer _pciLaneCount = null;

    public Integer getPCILaneCount() {
        return _pciLaneCount;
    }

    public PreDevice setPCILaneCount(Integer value) {
        _pciLaneCount = value;
        return this;
    }

    /**
     * Obsolete value - should always be -1
     */
    @JsonProperty("pod_id")
    private Integer _podId = -1;

    public Integer getPodId() {
        return _podId;
    }

    public PreDevice setPodId(Integer value) {
        _podId = value;
        return this;
    }

    /**
     * Device port global identifier
     */
    @JsonProperty("port_gid")
    private String _portGlobalId = null;

    public Integer getPortGlobalId() {
        return LiqidClientBase.hexStringToInteger(_portGlobalId);
    }

    public PreDevice setPortGlobalId(Integer value) {
        _portGlobalId = String.format("0x%06x", value);
        return this;
    }

    /**
     * Pre Device type (not exactly the same as the Device Status device type)
     */
    @JsonProperty("dev_type")
    private PreDeviceType _preDeviceType = null;

    public PreDeviceType getPreDeviceType() {
        return _preDeviceType;
    }

    public PreDevice setPreDeviceType(PreDeviceType value) {
        _preDeviceType = value;
        return this;
    }

    /**
     * Device switch global identifier
     */
    @JsonProperty("swit_gid")
    private String _switchGlobalId = null;

    public Integer getSwitchGlobalId() {
        return LiqidClientBase.hexStringToInteger(_switchGlobalId);
    }

    public PreDevice setSwitchGlobalId(Integer value) {
        _switchGlobalId = String.format("0x%06x", value);
        return this;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public PreDevice() {
    }

    /**
     * Parameterized Constructor
     */
    protected PreDevice(PreDeviceType preDeviceType,
                        String fabricGlobalId,
                        Integer fabricId,
                        String flags,
                        String groupName,
                        Integer groupId,
                        String index,
                        Integer pciLaneCount,
                        String machineId,
                        String machineName,
                        String deviceName,
                        String ownerId,
                        Integer podId,
                        String portGlobalId,
                        String switchGlobalId) {
        _preDeviceType = preDeviceType;
        _fabricGlobalId = fabricGlobalId;
        _fabricId = fabricId;
        _flags = flags;
        _groupName = groupName;
        _groupId = groupId;
        _index = index;
        _pciLaneCount = pciLaneCount;
        _machineId = machineId;
        _machineName = machineName;
        _deviceName = deviceName;
        _ownerId = ownerId;
        _podId = podId;
        _portGlobalId = portGlobalId;
        _switchGlobalId = switchGlobalId;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_preDeviceType:").append(getPreDeviceType());
        sb.append(", ").append("_fabricGlobalId:").append(getFabricGlobalId());
        sb.append(", ").append("_fabricId:").append(getFabricId());
        sb.append(", ").append("_flags:").append(getFlags());
        sb.append(", ").append("_groupName:").append(getGroupName());
        sb.append(", ").append("_groupId:").append(getGroupId());
        sb.append(", ").append("_index:").append(getIndex());
        sb.append(", ").append("_pciLaneCount:").append(getPCILaneCount());
        sb.append(", ").append("_machineId:").append(getMachineId());
        sb.append(", ").append("_machineName:").append(getMachineName());
        sb.append(", ").append("_deviceName:").append(getDeviceName());
        sb.append(", ").append("_ownerId:").append(getOwnerId());
        sb.append(", ").append("_podId:").append(getPodId());
        sb.append(", ").append("_portGlobalId:").append(getPortGlobalId());
        sb.append(", ").append("_switchGlobalId:").append(getSwitchGlobalId());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private PreDeviceType _preDeviceType = null;
        private String _fabricGlobalId = null;
        private Integer _fabricId = null;
        private String _flags = null;
        private String _groupName = null;
        private Integer _groupId = null;
        private String _index = null;
        private Integer _pciLaneCount = null;
        private String _machineId = null;
        private String _machineName = null;
        private String _deviceName = null;
        private String _ownerId = null;
        private Integer _podId = -1;
        private String _portGlobalId = null;
        private String _switchGlobalId = null;

        public Builder setPreDeviceType(PreDeviceType value) { _preDeviceType = value; return this; }
        public Builder setFabricGlobalId(String value) { _fabricGlobalId = value; return this; }
        public Builder setFabricId(Integer value) { _fabricId = value; return this; }
        public Builder setFlags(String value) { _flags = value; return this; }
        public Builder setGroupName(String value) { _groupName = value; return this; }
        public Builder setGroupId(Integer value) { _groupId = value; return this; }
        public Builder setIndex(String value) { _index = value; return this; }
        public Builder setPCILaneCount(Integer value) { _pciLaneCount = value; return this; }
        public Builder setMachineId(String value) { _machineId = value; return this; }
        public Builder setMachineName(String value) { _machineName = value; return this; }
        public Builder setDeviceName(String value) { _deviceName = value; return this; }
        public Builder setOwnerId(String value) { _ownerId = value; return this; }
        public Builder setPodId(Integer value) { _podId = value; return this; }
        public Builder setPortGlobalId(String value) { _portGlobalId = value; return this; }
        public Builder setSwitchGlobalId(String value) { _switchGlobalId = value; return this; }

        public PreDevice build() {
            if (_preDeviceType == null) {
                throw new RuntimeException("setPreDeviceType() was not invoked in Builder for class PreDevice");
            }
            if (_fabricGlobalId == null) {
                throw new RuntimeException("setFabricGlobalId() was not invoked in Builder for class PreDevice");
            }
            if (_fabricId == null) {
                throw new RuntimeException("setFabricId() was not invoked in Builder for class PreDevice");
            }
            if (_flags == null) {
                throw new RuntimeException("setFlags() was not invoked in Builder for class PreDevice");
            }
            if (_groupName == null) {
                throw new RuntimeException("setGroupName() was not invoked in Builder for class PreDevice");
            }
            if (_groupId == null) {
                throw new RuntimeException("setGroupId() was not invoked in Builder for class PreDevice");
            }
            if (_index == null) {
                throw new RuntimeException("setIndex() was not invoked in Builder for class PreDevice");
            }
            if (_pciLaneCount == null) {
                throw new RuntimeException("setPCILaneCount() was not invoked in Builder for class PreDevice");
            }
            if (_machineId == null) {
                throw new RuntimeException("setMachineId() was not invoked in Builder for class PreDevice");
            }
            if (_machineName == null) {
                throw new RuntimeException("setMachineName() was not invoked in Builder for class PreDevice");
            }
            if (_deviceName == null) {
                throw new RuntimeException("setDeviceName() was not invoked in Builder for class PreDevice");
            }
            if (_ownerId == null) {
                throw new RuntimeException("setOwnerId() was not invoked in Builder for class PreDevice");
            }
            if (_portGlobalId == null) {
                throw new RuntimeException("setPortGlobalId() was not invoked in Builder for class PreDevice");
            }
            if (_switchGlobalId == null) {
                throw new RuntimeException("setSwitchGlobalId() was not invoked in Builder for class PreDevice");
            }
            return new PreDevice(_preDeviceType,
                                 _fabricGlobalId,
                                 _fabricId,
                                 _flags,
                                 _groupName,
                                 _groupId,
                                 _index,
                                 _pciLaneCount,
                                 _machineId,
                                 _machineName,
                                 _deviceName,
                                 _ownerId,
                                 _podId,
                                 _portGlobalId,
                                 _switchGlobalId);
        }
    }
}
