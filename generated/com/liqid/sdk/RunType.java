// File: RunType.java
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
 * RunType
 * Describes the various run states for liqid
 */
public enum RunType {
    @JsonProperty("up") UP("up"),
    @JsonProperty("on") ON("on"),
    @JsonProperty("off") OFF("off"),
    @JsonProperty("unknown") UNKNOWN("unknown");

    private final String _value;

    RunType(String value) {
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
