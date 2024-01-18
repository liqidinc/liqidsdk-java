// File: NameValuePair.java
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
 * NameValuePair
 * A simple tuple tying a value key or name to a value
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NameValuePair {

    public static class NameValuePairWrapper extends Wrapper<NameValuePair>{}

    /**
     * The key or name associated with a value
     */
    @JsonProperty("name")
    private String _name = null;

    public String getName() {
        return _name;
    }

    public NameValuePair setName(String value) {
        _name = value;
        return this;
    }

    /**
     * The value associated with the given key or name
     */
    @JsonProperty("valueString")
    private String _valueString = null;

    public String getValueString() {
        return _valueString;
    }

    public NameValuePair setValueString(String value) {
        _valueString = value;
        return this;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public NameValuePair() {
    }

    /**
     * Parameterized Constructor
     */
    protected NameValuePair(String name,
                            String valueString) {
        _name = name;
        _valueString = valueString;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_name:").append(getName());
        sb.append(", ").append("_valueString:").append(getValueString());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private String _name = null;
        private String _valueString = null;

        public Builder setName(String value) { _name = value; return this; }
        public Builder setValueString(String value) { _valueString = value; return this; }

        public NameValuePair build() {
            if (_name == null) {
                throw new RuntimeException("setName() was not invoked in Builder for class NameValuePair");
            }
            if (_valueString == null) {
                throw new RuntimeException("setValueString() was not invoked in Builder for class NameValuePair");
            }
            return new NameValuePair(_name,
                                     _valueString);
        }
    }
}
