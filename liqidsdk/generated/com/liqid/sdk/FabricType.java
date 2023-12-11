// File: FabricType.java
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
 * FabricType
 * Describes a particular fabric topology type
 */
public enum FabricType {
    @JsonProperty("gen3") GEN3("gen3"),
    @JsonProperty("gen4") GEN4("gen4"),
    @JsonProperty("nvof") NV_OVER_FABRIC("nvof"),
    @JsonProperty("gpuof") GPU_OVER_FABRIC("gpuof"),
    @JsonProperty("genz") GEN_Z("genz"),
    @JsonProperty("nvmeof") NVME_OVER_FABRIC("nvmeof"),
    @JsonProperty("ip_based") IP_BASED("ip_based");

    private final String _value;

    FabricType(String value) {
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
