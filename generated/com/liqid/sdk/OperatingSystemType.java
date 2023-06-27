// File: OperatingSystemType.java
//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
// 
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//
// Liqid SDK - Version 3.2.0
// This file was automatically generated - do not modify it directly.
//

package com.liqid.sdk;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * OperatingSystemType
 * Describes an operating system
 */
public enum OperatingSystemType {
    @JsonProperty("linux") LINUX("linux"),
    @JsonProperty("FreeBSD") FREE_BSD("FreeBSD"),
    @JsonProperty("windows") WINDOWS("windows"),
    @JsonProperty("liqidos") LIQID_OS("liqidos"),
    @JsonProperty("Apple Inc. Mac(TM) OSX operating system") MAX_OSX("Apple Inc. Mac(TM) OSX operating system"),
    @JsonProperty("unknown") UNKNOWN("unknown");

    private final String _value;

    OperatingSystemType(String value) {
        _value = value;
    }

    public String getValue() {
        return _value;
    }

    @Override
    public String toString() {
        return _value;
    }
}
