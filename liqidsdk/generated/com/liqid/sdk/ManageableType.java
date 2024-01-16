// File: ManageableType.java
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
 * ManageableType
 * Describes a type of manageable entity
 */
public enum ManageableType {
    @JsonProperty("ManageableDevice") DEVICE("ManageableDevice"),
    @JsonProperty("ManageableCpuIpmiNetworkConfig") CPU_IPMI_NETWORK("ManageableCpuIpmiNetworkConfig"),
    @JsonProperty("ManageableDeviceIpmiNetworkConfig") ENCLOSURE_IPMI_NETWORK("ManageableDeviceIpmiNetworkConfig");

    private final String _value;

    ManageableType(String value) {
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
