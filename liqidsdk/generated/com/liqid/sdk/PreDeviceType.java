// File: PreDeviceType.java
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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * PreDeviceType
 * Represents a particular device type as a short string.
 * These are values which apply to the PreDevice device type field.
 */
public enum PreDeviceType {
    @JsonProperty("comp") COMPUTE("comp"),
    @JsonProperty("fibr") FIBER_CHANNEL("fibr"),
    @JsonProperty("fpga") FPGA("fpga"),
    @JsonProperty("gpu") GPU("gpu"),
    @JsonProperty("infi") INFINIBAND("infi"),
    @JsonProperty("link") LINK("link"),
    @JsonProperty("mem") MEMORY("mem"),
    @JsonProperty("plx") FABRIC_CHIP("plx"),
    @JsonProperty("targ") TARGET("targ");

    private final String _value;

    PreDeviceType(String value) {
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
