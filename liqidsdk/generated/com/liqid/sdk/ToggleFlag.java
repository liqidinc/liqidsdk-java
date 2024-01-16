// File: ToggleFlag.java
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
 * ToggleFlag
 * TODO
 */
public enum ToggleFlag {
    @JsonProperty("perm") PERMANENT("perm"),
    @JsonProperty("disappear") DISAPPEAR("disappear"),
    @JsonProperty("active") ACTIVE("active"),
    @JsonProperty("hidden") HIDDEN("hidden");

    private final String _value;

    ToggleFlag(String value) {
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
