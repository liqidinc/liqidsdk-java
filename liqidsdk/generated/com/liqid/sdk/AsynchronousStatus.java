// File: AsynchronousStatus.java
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
 * AsynchronousStatus
 * Reports the status of an asynchronous operation
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AsynchronousStatus {

    public static class AsynchronousStatusWrapper extends Wrapper<AsynchronousStatus>{}

    /**
     * Identifier of the device to which this operation applies
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
     * Timestamp of when the process was initiated
     */
    @JsonProperty("execution_date")
    private String _executionDateTime = null;

    public String getExecutionDateTime() {
        return _executionDateTime;
    }

    public void setExecutionDateTime(String value) {
        _executionDateTime = value;
    }

    /**
     * execution state of the asynchronous operation
     */
    @JsonProperty("command_execution_state")
    private String _executionState = null;

    public String getExecutionState() {
        return _executionState;
    }

    public void setExecutionState(String value) {
        _executionState = value;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public AsynchronousStatus() {
    }

    /**
     * Parameterized Constructor
     */
    protected AsynchronousStatus(String executionState,
                                 String deviceId,
                                 String executionDateTime) {
        _executionState = executionState;
        _deviceId = deviceId;
        _executionDateTime = executionDateTime;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_executionState:").append(getExecutionState());
        sb.append(", ").append("_deviceId:").append(getDeviceId());
        sb.append(", ").append("_executionDateTime:").append(getExecutionDateTime());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private String _executionState = null;
        private String _deviceId = null;
        private String _executionDateTime = null;

        public Builder setExecutionState(String value) { _executionState = value; return this; }
        public Builder setDeviceId(String value) { _deviceId = value; return this; }
        public Builder setExecutionDateTime(String value) { _executionDateTime = value; return this; }

        public AsynchronousStatus build() {
            if (_executionState == null) {
                throw new RuntimeException("setExecutionState() was not invoked in Builder for class AsynchronousStatus");
            }
            if (_deviceId == null) {
                throw new RuntimeException("setDeviceId() was not invoked in Builder for class AsynchronousStatus");
            }
            if (_executionDateTime == null) {
                throw new RuntimeException("setExecutionDateTime() was not invoked in Builder for class AsynchronousStatus");
            }
            return new AsynchronousStatus(_executionState,
                                          _deviceId,
                                          _executionDateTime);
        }
    }
}
