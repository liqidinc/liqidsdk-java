// File: ManagedEntityDeviceType.java
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
 * ManagedEntityDeviceType
 * Represents a particular device type as a short string.
 * These values apply only to ManagedEntity
 */
public enum ManagedEntityDeviceType {
    @JsonProperty("link") LINK("link"),
    @JsonProperty("gpu") GPU("gpu"),
    @JsonProperty("target/SSD") SSD("target/SSD"),
    @JsonProperty("fpga") FPGA("fpga"),
    @JsonProperty("compute") COMPUTE("compute"),
    @JsonProperty("mem") MEMORY("mem");

    private final String _value;

    ManagedEntityDeviceType(String value) {
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
