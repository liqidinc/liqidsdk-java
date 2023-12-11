// File: DiscoveryToken.java
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
 * DiscoveryToken
 * Describes a single discovery token
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DiscoveryToken {

    public static class DiscoveryTokenWrapper extends Wrapper<DiscoveryToken>{}

    /**
     * Internal index value for the token
     */
    @JsonProperty("index")
    private Integer _index = null;

    public Integer getIndex() {
        return _index;
    }

    public void setIndex(Integer value) {
        _index = value;
    }

    /**
     * The actual token
     */
    @JsonProperty("discovery_token")
    private String _token = null;

    public String getToken() {
        return _token;
    }

    public void setToken(String value) {
        _token = value;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public DiscoveryToken() {
    }

    /**
     * Parameterized Constructor
     */
    protected DiscoveryToken(String token,
                             Integer index) {
        _token = token;
        _index = index;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_token:").append(getToken());
        sb.append(", ").append("_index:").append(getIndex());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private String _token = null;
        private Integer _index = null;

        public Builder setToken(String value) { _token = value; return this; }
        public Builder setIndex(Integer value) { _index = value; return this; }

        public DiscoveryToken build() {
            if (_token == null) {
                throw new RuntimeException("setToken() was not invoked in Builder for class DiscoveryToken");
            }
            if (_index == null) {
                throw new RuntimeException("setIndex() was not invoked in Builder for class DiscoveryToken");
            }
            return new DiscoveryToken(_token,
                                      _index);
        }
    }
}
