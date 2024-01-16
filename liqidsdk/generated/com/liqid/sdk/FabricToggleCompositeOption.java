// File: FabricToggleCompositeOption.java
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
 * FabricToggleCompositeOption
 * TODO
 */
public enum FabricToggleCompositeOption {
    @JsonProperty("add") ADD("add");

    private final String _value;

    FabricToggleCompositeOption(String value) {
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
