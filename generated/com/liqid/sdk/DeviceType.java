// File: DeviceType.java
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
 * DeviceType
 * Represents a particular device type as a short string.
 * Note that redundant but differing values are not represented here.
 * This includes iba, fch, ramd, scm, vic, enc, nof, gof, pcpu, ncpu, gcpu
 */
public enum DeviceType {
    @JsonProperty("encl") ENCLOSURE("encl"),
    @JsonProperty("eth") ETHERNET("eth"),
    @JsonProperty("link") LINK("link"),
    @JsonProperty("mem") MEMORY("mem"),
    @JsonProperty("nic") NETWORK("nic"),
    @JsonProperty("infi") INFINIBAND1("infi"),
    @JsonProperty("iba") INFINIBAND2("iba"),
    @JsonProperty("ssd") SSD("ssd"),
    @JsonProperty("targ") TARGET("targ"),
    @JsonProperty("plx") PLX("plx"),
    @JsonProperty("compute") COMPUTE("compute"),
    @JsonProperty("cpu") CPU("cpu"),
    @JsonProperty("gpu") GPU("gpu"),
    @JsonProperty("gcpu") GCPU("gcpu"),
    @JsonProperty("fibr") FIBER_CHANNEL1("fibr"),
    @JsonProperty("fch") FIBER_CHANNEL2("fch"),
    @JsonProperty("fpga") FPGA("fpga");

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
