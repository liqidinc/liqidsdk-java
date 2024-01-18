// File: Timestamp.java
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
 * Timestamp
 * Contains a timestamp value
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Timestamp {

    public static class TimestampWrapper extends Wrapper<Timestamp>{}

    /**
     * Old value with wrong size int and incorrectly-named JSON tag of 'epoch'
     */
    @JsonProperty("epoch")
    private Integer _oldTimestamp = null;

    public Integer getOldTimestamp() {
        return _oldTimestamp;
    }

    public Timestamp setOldTimestamp(Integer value) {
        _oldTimestamp = value;
        return this;
    }

    /**
     * A time and date based on the Unix epoch
     */
    @JsonProperty("timestamp")
    private Long _timestamp = null;

    public Long getTimestamp() {
        return _timestamp;
    }

    public Timestamp setTimestamp(Long value) {
        _timestamp = value;
        return this;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public Timestamp() {
    }

    /**
     * Parameterized Constructor
     */
    protected Timestamp(Integer oldTimestamp,
                        Long timestamp) {
        _oldTimestamp = oldTimestamp;
        _timestamp = timestamp;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_oldTimestamp:").append(getOldTimestamp());
        sb.append(", ").append("_timestamp:").append(getTimestamp());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private Integer _oldTimestamp = null;
        private Long _timestamp = null;

        public Builder setOldTimestamp(Integer value) { _oldTimestamp = value; return this; }
        public Builder setTimestamp(Long value) { _timestamp = value; return this; }

        public Timestamp build() {
            if (_oldTimestamp == null) {
                throw new RuntimeException("setOldTimestamp() was not invoked in Builder for class Timestamp");
            }
            if (_timestamp == null) {
                throw new RuntimeException("setTimestamp() was not invoked in Builder for class Timestamp");
            }
            return new Timestamp(_oldTimestamp,
                                 _timestamp);
        }
    }
}
