// File: Switch.java
//
// Copyright (c) 2022-2023 Liqid, Inc. All rights reserved.
// 
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//
// Liqid SDK - Version 3.3.0
// This file was automatically generated - do not modify it directly.
//

package com.liqid.sdk;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.PropertyAccessor;

/**
 * Switch
 * Details related to the PCI switch
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Switch {

    public static class SwitchWrapper extends Wrapper<Switch>{}

    /**
     * Bus-width for the switch
     */
    @JsonProperty("buswidth")
    private String _busWidth = null;

    public String getBusWidth() {
        return _busWidth;
    }

    public void setBusWidth(String value) {
        _busWidth = value;
    }

    /**
     * Vendor-unique identifier of the device expressed as a hex value prefixed with '0x'
     */
    @JsonProperty("deviceid")
    private String _deviceId = null;

    public String getDeviceId() {
        return _deviceId;
    }

    public void setDeviceId(String value) {
        _deviceId = value;
    }

    /**
     * TODO
     */
    @JsonProperty("direction")
    private String _direction = null;

    public String getDirection() {
        return _direction;
    }

    public void setDirection(String value) {
        _direction = value;
    }

    /**
     * Global identifier expressed as a hex value prefixed with '0x'
     */
    @JsonProperty("gid")
    private String _globalId = null;

    public String getGlobalId() {
        return _globalId;
    }

    public void setGlobalId(String value) {
        _globalId = value;
    }

    /**
     * Switch name
     */
    @JsonProperty("name")
    private String _name = null;

    public String getName() {
        return _name;
    }

    public void setName(String value) {
        _name = value;
    }

    /**
     * Additional details regarding the switch
     */
    @JsonProperty("device")
    private SwitchDevice _switchDevice = null;

    public SwitchDevice getSwitchDevice() {
        return _switchDevice;
    }

    public void setSwitchDevice(SwitchDevice value) {
        _switchDevice = value;
    }

    /**
     * TODO
     */
    @JsonProperty("type")
    private String _switchType = null;

    public String getSwitchType() {
        return _switchType;
    }

    public void setSwitchType(String value) {
        _switchType = value;
    }

    /**
     * Unique identifier of the hardware vendor expressed as a hex value prefixed with '0x'
     */
    @JsonProperty("vendorid")
    private String _vendorId = null;

    public String getVendorId() {
        return _vendorId;
    }

    public void setVendorId(String value) {
        _vendorId = value;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public Switch() {
        _switchDevice = new SwitchDevice();
    }

    /**
     * Parameterized Constructor
     */
    protected Switch(String name,
                     String globalId,
                     String vendorId,
                     String deviceId,
                     String busWidth,
                     String direction,
                     String switchType,
                     SwitchDevice switchDevice) {
        _name = name;
        _globalId = globalId;
        _vendorId = vendorId;
        _deviceId = deviceId;
        _busWidth = busWidth;
        _direction = direction;
        _switchType = switchType;
        _switchDevice = switchDevice;
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
        sb.append(", ").append("_switchType:").append(getSwitchType());
        sb.append(", ").append("_switchDevice:").append(getSwitchDevice());
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
        private String _switchType = null;
        private SwitchDevice _switchDevice = null;

        public Builder setName(String value) { _name = value; return this; }
        public Builder setGlobalId(String value) { _globalId = value; return this; }
        public Builder setVendorId(String value) { _vendorId = value; return this; }
        public Builder setDeviceId(String value) { _deviceId = value; return this; }
        public Builder setBusWidth(String value) { _busWidth = value; return this; }
        public Builder setDirection(String value) { _direction = value; return this; }
        public Builder setSwitchType(String value) { _switchType = value; return this; }
        public Builder setSwitchDevice(SwitchDevice value) { _switchDevice = value; return this; }

        public Switch build() {
            if (_name == null) {
                throw new RuntimeException("setName() was not invoked in Builder for class Switch");
            }
            if (_globalId == null) {
                throw new RuntimeException("setGlobalId() was not invoked in Builder for class Switch");
            }
            if (_vendorId == null) {
                throw new RuntimeException("setVendorId() was not invoked in Builder for class Switch");
            }
            if (_deviceId == null) {
                throw new RuntimeException("setDeviceId() was not invoked in Builder for class Switch");
            }
            if (_busWidth == null) {
                throw new RuntimeException("setBusWidth() was not invoked in Builder for class Switch");
            }
            if (_direction == null) {
                throw new RuntimeException("setDirection() was not invoked in Builder for class Switch");
            }
            if (_switchType == null) {
                throw new RuntimeException("setSwitchType() was not invoked in Builder for class Switch");
            }
            if (_switchDevice == null) {
                throw new RuntimeException("setSwitchDevice() was not invoked in Builder for class Switch");
            }
            return new Switch(_name,
                              _globalId,
                              _vendorId,
                              _deviceId,
                              _busWidth,
                              _direction,
                              _switchType,
                              _switchDevice);
        }
    }
}
