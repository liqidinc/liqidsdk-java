// File: DeviceStatus.java
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
 * DeviceStatus
 * Status of a manageable device
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceStatus {

    public static class DeviceStatusWrapper extends Wrapper<DeviceStatus>{}

    /**
     * Connection type for the device
     */
    @JsonProperty("conn_type")
    private String _connectionType = null;

    public String getConnectionType() {
        return _connectionType;
    }

    public DeviceStatus setConnectionType(String value) {
        _connectionType = value;
        return this;
    }

    /**
     * Unique identifier for the device
     */
    @JsonProperty("dev_id")
    private String _deviceId = null;

    public Integer getDeviceId() {
        return LiqidClientBase.hexStringToInteger(_deviceId);
    }

    public DeviceStatus setDeviceId(Integer value) {
        _deviceId = String.format("0x%08x", value);
        return this;
    }

    /**
     * State of this device
     */
    @JsonProperty("device_state")
    private String _deviceState = null;

    public String getDeviceState() {
        return _deviceState;
    }

    public DeviceStatus setDeviceState(String value) {
        _deviceState = value;
        return this;
    }

    /**
     * Type of DeviceStatus entity
     */
    @JsonProperty("type")
    private String _deviceStatusType = null;

    public String getDeviceStatusType() {
        return _deviceStatusType;
    }

    public DeviceStatus setDeviceStatusType(String value) {
        _deviceStatusType = value;
        return this;
    }

    /**
     * Device type for this device
     */
    @JsonProperty("device_type")
    private DeviceType _deviceType = null;

    public DeviceType getDeviceType() {
        return _deviceType;
    }

    public DeviceStatus setDeviceType(DeviceType value) {
        _deviceType = value;
        return this;
    }

    /**
     * Fabric identifier
     */
    @JsonProperty("fabr_id")
    private Integer _fabricId = null;

    public Integer getFabricId() {
        return _fabricId;
    }

    public DeviceStatus setFabricId(Integer value) {
        _fabricId = value;
        return this;
    }

    /**
     * Fabric type
     */
    @JsonProperty("fabric_type")
    private String _fabricType = null;

    public String getFabricType() {
        return _fabricType;
    }

    public DeviceStatus setFabricType(String value) {
        _fabricType = value;
        return this;
    }

    /**
     * Hardware flags as a 64-bit bitmask
     */
    @JsonProperty("flags")
    private String _flags = null;

    public Long getFlags() {
        return LiqidClientBase.hexStringToLong(_flags);
    }

    public DeviceStatus setFlags(Long value) {
        _flags = String.format("0x%016x", value);
        return this;
    }

    /**
     * Additional hardware flags displayed as a hex string prefixed by 'ox'
     */
    @JsonProperty("flags2")
    private String _flags2 = null;

    public String getFlags2() {
        return _flags2;
    }

    public DeviceStatus setFlags2(String value) {
        _flags2 = value;
        return this;
    }

    /**
     * Fabric global identifier
     */
    @JsonProperty("fabr_gid")
    private String _globalId = null;

    public Integer getGlobalId() {
        return LiqidClientBase.hexStringToInteger(_globalId);
    }

    public DeviceStatus setGlobalId(Integer value) {
        _globalId = String.format("0x%08x", value);
        return this;
    }

    /**
     * Internal index of this device
     */
    @JsonProperty("index")
    private Integer _index = null;

    public Integer getIndex() {
        return _index;
    }

    public DeviceStatus setIndex(Integer value) {
        _index = value;
        return this;
    }

    /**
     * Liqid coordinates for this component
     */
    @JsonProperty("location")
    private Coordinates _location = null;

    public Coordinates getLocation() {
        return _location;
    }

    public DeviceStatus setLocation(Coordinates value) {
        _location = value;
        return this;
    }

    /**
     * Name of this component
     */
    @JsonProperty("name")
    private String _name = null;

    public String getName() {
        return _name;
    }

    public DeviceStatus setName(String value) {
        _name = value;
        return this;
    }

    /**
     * Liqid coordinates for the component directly above this in the containing fabric topology
     */
    @JsonProperty("owner")
    private Coordinates _owner = null;

    public Coordinates getOwner() {
        return _owner;
    }

    public DeviceStatus setOwner(Coordinates value) {
        _owner = value;
        return this;
    }

    /**
     * PCI Vendor-Unique Device identifier
     */
    @JsonProperty("did")
    private String _pciDeviceId = null;

    public String getPCIDeviceId() {
        return _pciDeviceId;
    }

    public DeviceStatus setPCIDeviceId(String value) {
        _pciDeviceId = value;
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

    public DeviceStatus setPCILaneCount(Integer value) {
        _pciLaneCount = value;
        return this;
    }

    /**
     * Unique PCI Vendor Identifier
     */
    @JsonProperty("vid")
    private String _pciVendorId = null;

    public String getPCIVendorId() {
        return _pciVendorId;
    }

    public DeviceStatus setPCIVendorId(String value) {
        _pciVendorId = value;
        return this;
    }

    /**
     * Pod identifier - obsolete value which is generally always -1
     */
    @JsonProperty("pod_id")
    private Integer _podId = -1;

    public Integer getPodId() {
        return _podId;
    }

    public DeviceStatus setPodId(Integer value) {
        _podId = value;
        return this;
    }

    /**
     * Port Global Identifier
     */
    @JsonProperty("port_gid")
    private String _portGlobalId = null;

    public Integer getPortGlobalId() {
        return LiqidClientBase.hexStringToInteger(_portGlobalId);
    }

    public DeviceStatus setPortGlobalId(Integer value) {
        _portGlobalId = String.format("0x%06x", value);
        return this;
    }

    /**
     * Obsolete value
     */
    @JsonProperty("sled_id")
    private String _sledId = null;

    public Integer getSledId() {
        return LiqidClientBase.hexStringToInteger(_sledId);
    }

    public DeviceStatus setSledId(Integer value) {
        _sledId = String.format("0x%08x", value);
        return this;
    }

    /**
     * Switch Global Identifier
     */
    @JsonProperty("swit_gid")
    private String _switchGlobalId = null;

    public Integer getSwitchGlobalId() {
        return LiqidClientBase.hexStringToInteger(_switchGlobalId);
    }

    public DeviceStatus setSwitchGlobalId(Integer value) {
        _switchGlobalId = String.format("0x%06x", value);
        return this;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public DeviceStatus() {
        _location = new Coordinates();
        _owner = new Coordinates();
    }

    /**
     * Parameterized Constructor
     */
    protected DeviceStatus(String connectionType,
                           String deviceId,
                           String deviceState,
                           DeviceType deviceType,
                           String pciDeviceId,
                           String globalId,
                           Integer fabricId,
                           String fabricType,
                           String flags,
                           String flags2,
                           Integer index,
                           Integer pciLaneCount,
                           Coordinates location,
                           String name,
                           Coordinates owner,
                           Integer podId,
                           String portGlobalId,
                           String sledId,
                           String switchGlobalId,
                           String deviceStatusType,
                           String pciVendorId) {
        _connectionType = connectionType;
        _deviceId = deviceId;
        _deviceState = deviceState;
        _deviceType = deviceType;
        _pciDeviceId = pciDeviceId;
        _globalId = globalId;
        _fabricId = fabricId;
        _fabricType = fabricType;
        _flags = flags;
        _flags2 = flags2;
        _index = index;
        _pciLaneCount = pciLaneCount;
        _location = location;
        _name = name;
        _owner = owner;
        _podId = podId;
        _portGlobalId = portGlobalId;
        _sledId = sledId;
        _switchGlobalId = switchGlobalId;
        _deviceStatusType = deviceStatusType;
        _pciVendorId = pciVendorId;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_connectionType:").append(getConnectionType());
        sb.append(", ").append("_deviceId:").append(getDeviceId());
        sb.append(", ").append("_deviceState:").append(getDeviceState());
        sb.append(", ").append("_deviceType:").append(getDeviceType());
        sb.append(", ").append("_pciDeviceId:").append(getPCIDeviceId());
        sb.append(", ").append("_globalId:").append(getGlobalId());
        sb.append(", ").append("_fabricId:").append(getFabricId());
        sb.append(", ").append("_fabricType:").append(getFabricType());
        sb.append(", ").append("_flags:").append(getFlags());
        sb.append(", ").append("_flags2:").append(getFlags2());
        sb.append(", ").append("_index:").append(getIndex());
        sb.append(", ").append("_pciLaneCount:").append(getPCILaneCount());
        sb.append(", ").append("_location:").append(getLocation());
        sb.append(", ").append("_name:").append(getName());
        sb.append(", ").append("_owner:").append(getOwner());
        sb.append(", ").append("_podId:").append(getPodId());
        sb.append(", ").append("_portGlobalId:").append(getPortGlobalId());
        sb.append(", ").append("_sledId:").append(getSledId());
        sb.append(", ").append("_switchGlobalId:").append(getSwitchGlobalId());
        sb.append(", ").append("_deviceStatusType:").append(getDeviceStatusType());
        sb.append(", ").append("_pciVendorId:").append(getPCIVendorId());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private String _connectionType = null;
        private String _deviceId = null;
        private String _deviceState = null;
        private DeviceType _deviceType = null;
        private String _pciDeviceId = null;
        private String _globalId = null;
        private Integer _fabricId = null;
        private String _fabricType = null;
        private String _flags = null;
        private String _flags2 = null;
        private Integer _index = null;
        private Integer _pciLaneCount = null;
        private Coordinates _location = null;
        private String _name = null;
        private Coordinates _owner = null;
        private Integer _podId = -1;
        private String _portGlobalId = null;
        private String _sledId = null;
        private String _switchGlobalId = null;
        private String _deviceStatusType = null;
        private String _pciVendorId = null;

        public Builder setConnectionType(String value) { _connectionType = value; return this; }
        public Builder setDeviceId(String value) { _deviceId = value; return this; }
        public Builder setDeviceState(String value) { _deviceState = value; return this; }
        public Builder setDeviceType(DeviceType value) { _deviceType = value; return this; }
        public Builder setPCIDeviceId(String value) { _pciDeviceId = value; return this; }
        public Builder setGlobalId(String value) { _globalId = value; return this; }
        public Builder setFabricId(Integer value) { _fabricId = value; return this; }
        public Builder setFabricType(String value) { _fabricType = value; return this; }
        public Builder setFlags(String value) { _flags = value; return this; }
        public Builder setFlags2(String value) { _flags2 = value; return this; }
        public Builder setIndex(Integer value) { _index = value; return this; }
        public Builder setPCILaneCount(Integer value) { _pciLaneCount = value; return this; }
        public Builder setLocation(Coordinates value) { _location = value; return this; }
        public Builder setName(String value) { _name = value; return this; }
        public Builder setOwner(Coordinates value) { _owner = value; return this; }
        public Builder setPodId(Integer value) { _podId = value; return this; }
        public Builder setPortGlobalId(String value) { _portGlobalId = value; return this; }
        public Builder setSledId(String value) { _sledId = value; return this; }
        public Builder setSwitchGlobalId(String value) { _switchGlobalId = value; return this; }
        public Builder setDeviceStatusType(String value) { _deviceStatusType = value; return this; }
        public Builder setPCIVendorId(String value) { _pciVendorId = value; return this; }

        public DeviceStatus build() {
            if (_connectionType == null) {
                throw new RuntimeException("setConnectionType() was not invoked in Builder for class DeviceStatus");
            }
            if (_deviceId == null) {
                throw new RuntimeException("setDeviceId() was not invoked in Builder for class DeviceStatus");
            }
            if (_deviceState == null) {
                throw new RuntimeException("setDeviceState() was not invoked in Builder for class DeviceStatus");
            }
            if (_deviceType == null) {
                throw new RuntimeException("setDeviceType() was not invoked in Builder for class DeviceStatus");
            }
            if (_pciDeviceId == null) {
                throw new RuntimeException("setPCIDeviceId() was not invoked in Builder for class DeviceStatus");
            }
            if (_globalId == null) {
                throw new RuntimeException("setGlobalId() was not invoked in Builder for class DeviceStatus");
            }
            if (_fabricId == null) {
                throw new RuntimeException("setFabricId() was not invoked in Builder for class DeviceStatus");
            }
            if (_fabricType == null) {
                throw new RuntimeException("setFabricType() was not invoked in Builder for class DeviceStatus");
            }
            if (_flags == null) {
                throw new RuntimeException("setFlags() was not invoked in Builder for class DeviceStatus");
            }
            if (_flags2 == null) {
                throw new RuntimeException("setFlags2() was not invoked in Builder for class DeviceStatus");
            }
            if (_index == null) {
                throw new RuntimeException("setIndex() was not invoked in Builder for class DeviceStatus");
            }
            if (_pciLaneCount == null) {
                throw new RuntimeException("setPCILaneCount() was not invoked in Builder for class DeviceStatus");
            }
            if (_location == null) {
                throw new RuntimeException("setLocation() was not invoked in Builder for class DeviceStatus");
            }
            if (_name == null) {
                throw new RuntimeException("setName() was not invoked in Builder for class DeviceStatus");
            }
            if (_owner == null) {
                throw new RuntimeException("setOwner() was not invoked in Builder for class DeviceStatus");
            }
            if (_portGlobalId == null) {
                throw new RuntimeException("setPortGlobalId() was not invoked in Builder for class DeviceStatus");
            }
            if (_switchGlobalId == null) {
                throw new RuntimeException("setSwitchGlobalId() was not invoked in Builder for class DeviceStatus");
            }
            if (_deviceStatusType == null) {
                throw new RuntimeException("setDeviceStatusType() was not invoked in Builder for class DeviceStatus");
            }
            if (_pciVendorId == null) {
                throw new RuntimeException("setPCIVendorId() was not invoked in Builder for class DeviceStatus");
            }
            return new DeviceStatus(_connectionType,
                                    _deviceId,
                                    _deviceState,
                                    _deviceType,
                                    _pciDeviceId,
                                    _globalId,
                                    _fabricId,
                                    _fabricType,
                                    _flags,
                                    _flags2,
                                    _index,
                                    _pciLaneCount,
                                    _location,
                                    _name,
                                    _owner,
                                    _podId,
                                    _portGlobalId,
                                    _sledId,
                                    _switchGlobalId,
                                    _deviceStatusType,
                                    _pciVendorId);
        }
    }
}
