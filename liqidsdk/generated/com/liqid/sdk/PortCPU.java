// File: PortCPU.java
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
 * PortCPU
 * Describes a CPU switch port
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PortCPU {

    public static class PortCPUWrapper extends Wrapper<PortCPU>{}

    /**
     * Bus-width for the switch
     */
    @JsonProperty("buswidth")
    private String _busWidth = null;

    public String getBusWidth() {
        return _busWidth;
    }

    public PortCPU setBusWidth(String value) {
        _busWidth = value;
        return this;
    }

    /**
     * Vendor-unique identifier of the device expressed as a hex value prefixed with '0x'
     */
    @JsonProperty("deviceid")
    private String _deviceId = null;

    public String getDeviceId() {
        return _deviceId;
    }

    public PortCPU setDeviceId(String value) {
        _deviceId = value;
        return this;
    }

    /**
     * TODO
     */
    @JsonProperty("direction")
    private String _direction = null;

    public String getDirection() {
        return _direction;
    }

    public PortCPU setDirection(String value) {
        _direction = value;
        return this;
    }

    /**
     * Global identifier for this entity
     */
    @JsonProperty("gid")
    private String _globalId = null;

    public String getGlobalId() {
        return _globalId;
    }

    public PortCPU setGlobalId(String value) {
        _globalId = value;
        return this;
    }

    /**
     * Name of this entity
     */
    @JsonProperty("name")
    private String _name = null;

    public String getName() {
        return _name;
    }

    public PortCPU setName(String value) {
        _name = value;
        return this;
    }

    /**
     * TODO
     */
    @JsonProperty("type")
    private String _portType = null;

    public String getPortType() {
        return _portType;
    }

    public PortCPU setPortType(String value) {
        _portType = value;
        return this;
    }

    /**
     * Unique identifier of the hardware vendor expressed as a hex value prefixed with '0x'
     */
    @JsonProperty("vendorid")
    private String _vendorId = null;

    public String getVendorId() {
        return _vendorId;
    }

    public PortCPU setVendorId(String value) {
        _vendorId = value;
        return this;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public PortCPU() {
    }

    /**
     * Parameterized Constructor
     */
    protected PortCPU(String name,
                      String globalId,
                      String vendorId,
                      String deviceId,
                      String busWidth,
                      String direction,
                      String portType) {
        _name = name;
        _globalId = globalId;
        _vendorId = vendorId;
        _deviceId = deviceId;
        _busWidth = busWidth;
        _direction = direction;
        _portType = portType;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_name:").append(getName());
        sb.append(", ").append("_globalId:").append(getGlobalId());
        sb.append(", ").append("_vendorId:").append(getVendorId());
        sb.append(", ").append("_deviceId:").append(getDeviceId());
        sb.append(", ").append("_busWidth:").append(getBusWidth());
        sb.append(", ").append("_direction:").append(getDirection());
        sb.append(", ").append("_portType:").append(getPortType());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private String _name = null;
        private String _globalId = null;
        private String _vendorId = null;
        private String _deviceId = null;
        private String _busWidth = null;
        private String _direction = null;
        private String _portType = null;

        public Builder setName(String value) { _name = value; return this; }
        public Builder setGlobalId(String value) { _globalId = value; return this; }
        public Builder setVendorId(String value) { _vendorId = value; return this; }
        public Builder setDeviceId(String value) { _deviceId = value; return this; }
        public Builder setBusWidth(String value) { _busWidth = value; return this; }
        public Builder setDirection(String value) { _direction = value; return this; }
        public Builder setPortType(String value) { _portType = value; return this; }

        public PortCPU build() {
            if (_name == null) {
                throw new RuntimeException("setName() was not invoked in Builder for class PortCPU");
            }
            if (_globalId == null) {
                throw new RuntimeException("setGlobalId() was not invoked in Builder for class PortCPU");
            }
            if (_vendorId == null) {
                throw new RuntimeException("setVendorId() was not invoked in Builder for class PortCPU");
            }
            if (_deviceId == null) {
                throw new RuntimeException("setDeviceId() was not invoked in Builder for class PortCPU");
            }
            if (_busWidth == null) {
                throw new RuntimeException("setBusWidth() was not invoked in Builder for class PortCPU");
            }
            if (_direction == null) {
                throw new RuntimeException("setDirection() was not invoked in Builder for class PortCPU");
            }
            if (_portType == null) {
                throw new RuntimeException("setPortType() was not invoked in Builder for class PortCPU");
            }
            return new PortCPU(_name,
                               _globalId,
                               _vendorId,
                               _deviceId,
                               _busWidth,
                               _direction,
                               _portType);
        }
    }
}
