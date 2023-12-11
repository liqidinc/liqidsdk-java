// File: SSHStatus.java
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
 * SSHStatus
 * Describes the current state of SSH
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SSHStatus {

    public static class SSHStatusWrapper extends Wrapper<SSHStatus>{}

    /**
     * Indicates whether SSH is active
     */
    @JsonProperty("is-active")
    private Boolean _active = null;

    public Boolean getActive() {
        return _active;
    }

    public void setActive(Boolean value) {
        _active = value;
    }

    /**
     * Indicates whether SSH is enabled
     */
    @JsonProperty("is-enabled")
    private Boolean _enabled = null;

    public Boolean getEnabled() {
        return _enabled;
    }

    public void setEnabled(Boolean value) {
        _enabled = value;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public SSHStatus() {
    }

    /**
     * Parameterized Constructor
     */
    protected SSHStatus(Boolean active,
                        Boolean enabled) {
        _active = active;
        _enabled = enabled;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_active:").append(getActive());
        sb.append(", ").append("_enabled:").append(getEnabled());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private Boolean _active = null;
        private Boolean _enabled = null;

        public Builder setActive(Boolean value) { _active = value; return this; }
        public Builder setEnabled(Boolean value) { _enabled = value; return this; }

        public SSHStatus build() {
            if (_active == null) {
                throw new RuntimeException("setActive() was not invoked in Builder for class SSHStatus");
            }
            if (_enabled == null) {
                throw new RuntimeException("setEnabled() was not invoked in Builder for class SSHStatus");
            }
            return new SSHStatus(_active,
                                 _enabled);
        }
    }
}
