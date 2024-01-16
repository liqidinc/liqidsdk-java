// File: DeviceUserDescription.java
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
 * DeviceUserDescription
 * Wraps a user-provided description for a particular device
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceUserDescription {

    public static class DeviceUserDescriptionWrapper extends Wrapper<DeviceUserDescription>{}

    /**
     * The actual description
     */
    @JsonProperty("udesc")
    private String _userDescription = null;

    public String getUserDescription() {
        return _userDescription;
    }

    public void setUserDescription(String value) {
        _userDescription = value;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public DeviceUserDescription() {
    }

    /**
     * Parameterized Constructor
     */
    protected DeviceUserDescription(String userDescription) {
        _userDescription = userDescription;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_userDescription:").append(getUserDescription());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private String _userDescription = null;

        public Builder setUserDescription(String value) { _userDescription = value; return this; }

        public DeviceUserDescription build() {
            if (_userDescription == null) {
                throw new RuntimeException("setUserDescription() was not invoked in Builder for class DeviceUserDescription");
            }
            return new DeviceUserDescription(_userDescription);
        }
    }
}
