// File: AsynchronousInfo.java
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
 * AsynchronousInfo
 * Reports an identifier of an asynchronous operation
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AsynchronousInfo {

    public static class AsynchronousInfoWrapper extends Wrapper<AsynchronousInfo>{}

    /**
     * Identifier to be used for polling the state of an asynchronous task
     */
    @JsonProperty("async_id")
    private String _asynchronousId = null;

    public String getAsynchronousId() {
        return _asynchronousId;
    }

    public void setAsynchronousId(String value) {
        _asynchronousId = value;
    }

    /**
     * Device identifier which is associated with the asynchronous task
     */
    @JsonProperty("device_id")
    private String _deviceId = null;

    public String getDeviceId() {
        return _deviceId;
    }

    public void setDeviceId(String value) {
        _deviceId = value;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public AsynchronousInfo() {
    }

    /**
     * Parameterized Constructor
     */
    protected AsynchronousInfo(String asynchronousId,
                               String deviceId) {
        _asynchronousId = asynchronousId;
        _deviceId = deviceId;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_asynchronousId:").append(getAsynchronousId());
        sb.append(", ").append("_deviceId:").append(getDeviceId());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private String _asynchronousId = null;
        private String _deviceId = null;

        public Builder setAsynchronousId(String value) { _asynchronousId = value; return this; }
        public Builder setDeviceId(String value) { _deviceId = value; return this; }

        public AsynchronousInfo build() {
            if (_asynchronousId == null) {
                throw new RuntimeException("setAsynchronousId() was not invoked in Builder for class AsynchronousInfo");
            }
            if (_deviceId == null) {
                throw new RuntimeException("setDeviceId() was not invoked in Builder for class AsynchronousInfo");
            }
            return new AsynchronousInfo(_asynchronousId,
                                        _deviceId);
        }
    }
}
