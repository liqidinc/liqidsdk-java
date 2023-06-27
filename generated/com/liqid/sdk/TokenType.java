// File: TokenType.java
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
 * TokenType
 * A subset of DeviceType values used exclusively with tokens
 */
public enum TokenType {
    @JsonProperty("targ") TARGET("targ"),
    @JsonProperty("link") LINK("link"),
    @JsonProperty("gpu") GPU("gpu"),
    @JsonProperty("fpga") FPGA("fpga");

    private final String _value;

    TokenType(String value) {
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
