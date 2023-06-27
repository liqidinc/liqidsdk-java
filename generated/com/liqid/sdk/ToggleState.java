// File: ToggleState.java
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
 * ToggleState
 * Describes a binary state of enablement/disablement
 */
public enum ToggleState {
    @JsonProperty("enabled") ENABLED("enabled"),
    @JsonProperty("disabled") DISABLED("disabled");

    private final String _value;

    ToggleState(String value) {
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
