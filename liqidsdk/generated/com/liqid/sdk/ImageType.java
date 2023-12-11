// File: ImageType.java
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
 * ImageType
 * Describes a type of software image file
 */
public enum ImageType {
    @JsonProperty("boot") BOOT("boot"),
    @JsonProperty("fpga") FPGA("fpga"),
    @JsonProperty("vnic") VNIC("vnic"),
    @JsonProperty("upgrade") UPGRADE("upgrade"),
    @JsonProperty("application") APPLICATION("application");

    private final String _value;

    ImageType(String value) {
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
