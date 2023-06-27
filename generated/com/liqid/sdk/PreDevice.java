// File: PreDevice.java
//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
// 
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//
// Liqid SDK - Version 3.2.0
// This file was automatically generated - do not modify it directly.
//

package com.liqid.sdk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    public String getDeviceName() { return _deviceName; }
    public void setDeviceName(String value) { _deviceName = value; }

    /**
     * Device type
     */
    @JsonProperty("dev_type")
    private DeviceType _deviceType = null;
    public DeviceType getDeviceType() { return _deviceType; }
    public void setDeviceType(DeviceType value) { _deviceType = value; }

    /**
     * Fabric global id
     */
    @JsonProperty("fabr_gid")
    private String _fabricGlobalId = null;
    public String getFabricGlobalId() { return _fabricGlobalId; }
    public void setFabricGlobalId(String value) { _fabricGlobalId = value; }

    /**
     * Fabric identifier to which the device belongs
     */
    @JsonProperty("fabr_id")
    private Integer _fabricId = null;
    public Integer getFabricId() { return _fabricId; }
    public void setFabricId(Integer value) { _fabricId = value; }

    /**
     * Flags for the device - a hex string prefixed by '0x'
     */
    @JsonProperty("flags")
    private String _flags = null;
    public String getFlags() { return _flags; }
    public void setFlags(String value) { _flags = value; }

    /**
     * TODO
     */
    @JsonProperty("grp_id")
    private Integer _groupId = null;
    public Integer getGroupId() { return _groupId; }
    public void setGroupId(Integer value) { _groupId = value; }

    /**
     * TODO
     */
    @JsonProperty("gname")
    private String _groupName = null;
    public String getGroupName() { return _groupName; }
    public void setGroupName(String value) { _groupName = value; }

    /**
     * Internal index for this pre-device
     */
    @JsonProperty("index")
    private String _index = null;
    public String getIndex() { return _index; }
    public void setIndex(String value) { _index = value; }

    /**
     * Machine identifier
     */
    @JsonProperty("mach_id")
    private String _machineId = null;
    public String getMachineId() { return _machineId; }
    public void setMachineId(String value) { _machineId = value; }

    /**
     * Machine name
     */
    @JsonProperty("mname")
    private String _machineName = null;
    public String getMachineName() { return _machineName; }
    public void setMachineName(String value) { _machineName = value; }

    /**
     * Owner identifier
     */
    @JsonProperty("owner_id")
    private String _ownerId = null;
    public String getOwnerId() { return _ownerId; }
    public void setOwnerId(String value) { _ownerId = value; }

    /**
     * Number of PCI lanes for this device
     */
    @JsonProperty("lanes")
    private Integer _pciLaneCount = null;
    public Integer getPCILaneCount() { return _pciLaneCount; }
    public void setPCILaneCount(Integer value) { _pciLaneCount = value; }

    /**
     * Obsolete value - should always be -1
     */
    @JsonProperty("pod_id")
    private Integer _podId = -1;
    public Integer getPodId() { return _podId; }
    public void setPodId(Integer value) { _podId = value; }

    /**
     * Device port global identifier
     */
    @JsonProperty("port_gid")
    private Integer _portGlobalId = null;
    public Integer getPortGlobalId() { return _portGlobalId; }
    public void setPortGlobalId(Integer value) { _portGlobalId = value; }

    /**
     * Device switch global identifier
     */
    @JsonProperty("swit_gid")
    private Integer _switchGlobalId = null;
    public Integer getSwitchGlobalId() { return _switchGlobalId; }
    public void setSwitchGlobalId(Integer value) { _switchGlobalId = value; }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public PreDevice() {
    }

    /**
     * Parameterized Constructor
     */
    protected PreDevice(DeviceType deviceType,
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
                        Integer portGlobalId,
                        Integer switchGlobalId) {
        _deviceType = deviceType;
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
        sb.append("_deviceType:").append(getDeviceType());
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

        private DeviceType _deviceType = null;
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
        private Integer _portGlobalId = null;
        private Integer _switchGlobalId = null;

        public Builder setDeviceType(DeviceType value) { _deviceType = value; return this; }
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
        public Builder setPortGlobalId(Integer value) { _portGlobalId = value; return this; }
        public Builder setSwitchGlobalId(Integer value) { _switchGlobalId = value; return this; }

        public PreDevice build() {
            if (_deviceType == null) {
                throw new RuntimeException("setDeviceType() was not invoked in Builder for class PreDevice");
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
            return new PreDevice(_deviceType,
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
