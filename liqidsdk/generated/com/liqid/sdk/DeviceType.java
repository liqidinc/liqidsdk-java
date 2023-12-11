// File: DeviceType.java
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
 * DeviceType
 * Represents a particular device type as a short string.
 * These values apply only to DeviceStatus and DeviceInfo
 */
public enum DeviceType {
    @JsonProperty("infiniband_link") INFINIBAND_LINK("infiniband_link"),
    @JsonProperty("ethernet_link") ETHERNET_LINK("ethernet_link"),
    @JsonProperty("fibrechannel_link") FIBER_CHANNEL_LINK("fibrechannel_link"),
    @JsonProperty("gpu") GPU("gpu"),
    @JsonProperty("target/SSD") SSD("target/SSD"),
    @JsonProperty("fpga") FPGA("fpga"),
    @JsonProperty("compute") COMPUTE("compute"),
    @JsonProperty("mem") MEMORY("mem");

    private final String _value;

    DeviceType(String value) {
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
