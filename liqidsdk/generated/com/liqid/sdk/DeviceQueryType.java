// File: DeviceQueryType.java
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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DeviceQueryType
 * Represents a particular device type as a short string.
 * These values apply to function parameters accepting device types.
 */
public enum DeviceQueryType {
    @JsonProperty("targ") TARGET("targ"),
    @JsonProperty("link") LINK("link"),
    @JsonProperty("comp") COMPUTE("comp"),
    @JsonProperty("fpga") FPGA("fpga"),
    @JsonProperty("gpu") GPU("gpu"),
    @JsonProperty("mem") MEMORY("mem");

    private final String _value;

    DeviceQueryType(String value) {
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
