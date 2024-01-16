// File: GPUDeviceStatus.java
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
 * GPUDeviceStatus
 * GPU Device Status Information
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GPUDeviceStatus extends DeviceStatus {

    public static class GPUDeviceStatusWrapper extends Wrapper<GPUDeviceStatus>{}

    /**
     * Internal value
     */
    @JsonProperty("unique")
    private String _unique = null;

    public String getUnique() {
        return _unique;
    }

    public void setUnique(String value) {
        _unique = value;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public GPUDeviceStatus() {
    }

    /**
     * Parameterized Constructor
     */
    protected GPUDeviceStatus(String connectionType,
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
                              String pciVendorId,
                              String unique) {
        super(connectionType, deviceId, deviceState, deviceType, pciDeviceId, globalId, fabricId, fabricType, flags, flags2, index, pciLaneCount, location, name, owner, podId, portGlobalId, sledId, switchGlobalId, deviceStatusType, pciVendorId);
        _unique = unique;
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
        sb.append(", ").append("_unique:").append(getUnique());
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
        private String _unique = null;

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
        public Builder setUnique(String value) { _unique = value; return this; }

        public GPUDeviceStatus build() {
            if (_connectionType == null) {
                throw new RuntimeException("setConnectionType() was not invoked in Builder for class GPUDeviceStatus");
            }
            if (_deviceId == null) {
                throw new RuntimeException("setDeviceId() was not invoked in Builder for class GPUDeviceStatus");
            }
            if (_deviceState == null) {
                throw new RuntimeException("setDeviceState() was not invoked in Builder for class GPUDeviceStatus");
            }
            if (_deviceType == null) {
                throw new RuntimeException("setDeviceType() was not invoked in Builder for class GPUDeviceStatus");
            }
            if (_pciDeviceId == null) {
                throw new RuntimeException("setPCIDeviceId() was not invoked in Builder for class GPUDeviceStatus");
            }
            if (_globalId == null) {
                throw new RuntimeException("setGlobalId() was not invoked in Builder for class GPUDeviceStatus");
            }
            if (_fabricId == null) {
                throw new RuntimeException("setFabricId() was not invoked in Builder for class GPUDeviceStatus");
            }
            if (_fabricType == null) {
                throw new RuntimeException("setFabricType() was not invoked in Builder for class GPUDeviceStatus");
            }
            if (_flags == null) {
                throw new RuntimeException("setFlags() was not invoked in Builder for class GPUDeviceStatus");
            }
            if (_flags2 == null) {
                throw new RuntimeException("setFlags2() was not invoked in Builder for class GPUDeviceStatus");
            }
            if (_index == null) {
                throw new RuntimeException("setIndex() was not invoked in Builder for class GPUDeviceStatus");
            }
            if (_pciLaneCount == null) {
                throw new RuntimeException("setPCILaneCount() was not invoked in Builder for class GPUDeviceStatus");
            }
            if (_location == null) {
                throw new RuntimeException("setLocation() was not invoked in Builder for class GPUDeviceStatus");
            }
            if (_name == null) {
                throw new RuntimeException("setName() was not invoked in Builder for class GPUDeviceStatus");
            }
            if (_owner == null) {
                throw new RuntimeException("setOwner() was not invoked in Builder for class GPUDeviceStatus");
            }
            if (_portGlobalId == null) {
                throw new RuntimeException("setPortGlobalId() was not invoked in Builder for class GPUDeviceStatus");
            }
            if (_switchGlobalId == null) {
                throw new RuntimeException("setSwitchGlobalId() was not invoked in Builder for class GPUDeviceStatus");
            }
            if (_deviceStatusType == null) {
                throw new RuntimeException("setDeviceStatusType() was not invoked in Builder for class GPUDeviceStatus");
            }
            if (_pciVendorId == null) {
                throw new RuntimeException("setPCIVendorId() was not invoked in Builder for class GPUDeviceStatus");
            }
            if (_unique == null) {
                throw new RuntimeException("setUnique() was not invoked in Builder for class GPUDeviceStatus");
            }
            return new GPUDeviceStatus(_connectionType,
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
                                       _pciVendorId,
                                       _unique);
        }
    }
}
