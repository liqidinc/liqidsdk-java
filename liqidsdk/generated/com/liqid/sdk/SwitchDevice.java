// File: SwitchDevice.java
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
 * SwitchDevice
 * Additional details specific to the switch
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SwitchDevice {

    public static class SwitchDeviceWrapper extends Wrapper<SwitchDevice>{}

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
     * Unique identifier of the group expressed as a hex value prefixed with '0x'
     */
    @JsonProperty("gid")
    private String _groupId = null;

    public String getGroupId() {
        return _groupId;
    }

    public void setGroupId(String value) {
        _groupId = value;
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
     * TODO
     */
    @JsonProperty("type")
    private String _switchDeviceType = null;

    public String getSwitchDeviceType() {
        return _switchDeviceType;
    }

    public void setSwitchDeviceType(String value) {
        _switchDeviceType = value;
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
    public SwitchDevice() {
    }

    /**
     * Parameterized Constructor
     */
    protected SwitchDevice(String name,
                           String groupId,
                           String vendorId,
                           String deviceId,
                           String busWidth,
                           String direction,
                           String switchDeviceType) {
        _name = name;
        _groupId = groupId;
        _vendorId = vendorId;
        _deviceId = deviceId;
        _busWidth = busWidth;
        _direction = direction;
        _switchDeviceType = switchDeviceType;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_name:").append(getName());
        sb.append(", ").append("_groupId:").append(getGroupId());
        sb.append(", ").append("_vendorId:").append(getVendorId());
        sb.append(", ").append("_deviceId:").append(getDeviceId());
        sb.append(", ").append("_busWidth:").append(getBusWidth());
        sb.append(", ").append("_direction:").append(getDirection());
        sb.append(", ").append("_switchDeviceType:").append(getSwitchDeviceType());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private String _name = null;
        private String _groupId = null;
        private String _vendorId = null;
        private String _deviceId = null;
        private String _busWidth = null;
        private String _direction = null;
        private String _switchDeviceType = null;

        public Builder setName(String value) { _name = value; return this; }
        public Builder setGroupId(String value) { _groupId = value; return this; }
        public Builder setVendorId(String value) { _vendorId = value; return this; }
        public Builder setDeviceId(String value) { _deviceId = value; return this; }
        public Builder setBusWidth(String value) { _busWidth = value; return this; }
        public Builder setDirection(String value) { _direction = value; return this; }
        public Builder setSwitchDeviceType(String value) { _switchDeviceType = value; return this; }

        public SwitchDevice build() {
            if (_name == null) {
                throw new RuntimeException("setName() was not invoked in Builder for class SwitchDevice");
            }
            if (_groupId == null) {
                throw new RuntimeException("setGroupId() was not invoked in Builder for class SwitchDevice");
            }
            if (_vendorId == null) {
                throw new RuntimeException("setVendorId() was not invoked in Builder for class SwitchDevice");
            }
            if (_deviceId == null) {
                throw new RuntimeException("setDeviceId() was not invoked in Builder for class SwitchDevice");
            }
            if (_busWidth == null) {
                throw new RuntimeException("setBusWidth() was not invoked in Builder for class SwitchDevice");
            }
            if (_direction == null) {
                throw new RuntimeException("setDirection() was not invoked in Builder for class SwitchDevice");
            }
            if (_switchDeviceType == null) {
                throw new RuntimeException("setSwitchDeviceType() was not invoked in Builder for class SwitchDevice");
            }
            return new SwitchDevice(_name,
                                    _groupId,
                                    _vendorId,
                                    _deviceId,
                                    _busWidth,
                                    _direction,
                                    _switchDeviceType);
        }
    }
}
