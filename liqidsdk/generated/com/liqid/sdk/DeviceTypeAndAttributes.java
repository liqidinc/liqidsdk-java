// File: DeviceTypeAndAttributes.java
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
import java.util.HashMap;

/**
 * DeviceTypeAndAttributes
 * Describes the various attributes for a particular device type
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceTypeAndAttributes {

    public static class DeviceTypeAndAttributesWrapper extends Wrapper<DeviceTypeAndAttributes>{}

    /**
     * Array of string descriptors
     */
    @JsonProperty("attributes")
    private HashMap<String, String[]> _attributes = null;

    public HashMap<String, String[]> getAttributes() {
        return _attributes;
    }

    public DeviceTypeAndAttributes setAttributes(HashMap<String, String[]> value) {
        _attributes = value;
        return this;
    }

    /**
     * A particular data type
     */
    @JsonProperty("deviceType")
    private DeviceType _deviceType = null;

    public DeviceType getDeviceType() {
        return _deviceType;
    }

    public DeviceTypeAndAttributes setDeviceType(DeviceType value) {
        _deviceType = value;
        return this;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public DeviceTypeAndAttributes() {
    }

    /**
     * Parameterized Constructor
     */
    protected DeviceTypeAndAttributes(DeviceType deviceType,
                                      HashMap<String, String[]> attributes) {
        _deviceType = deviceType;
        _attributes = attributes;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_deviceType:").append(getDeviceType());
        sb.append(", ").append("_attributes:").append(getAttributes());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private DeviceType _deviceType = null;
        private HashMap<String, String[]> _attributes = null;

        public Builder setDeviceType(DeviceType value) { _deviceType = value; return this; }
        public Builder setAttributes(HashMap<String, String[]> value) { _attributes = value; return this; }

        public DeviceTypeAndAttributes build() {
            if (_deviceType == null) {
                throw new RuntimeException("setDeviceType() was not invoked in Builder for class DeviceTypeAndAttributes");
            }
            if (_attributes == null) {
                throw new RuntimeException("setAttributes() was not invoked in Builder for class DeviceTypeAndAttributes");
            }
            return new DeviceTypeAndAttributes(_deviceType,
                                               _attributes);
        }
    }
}
