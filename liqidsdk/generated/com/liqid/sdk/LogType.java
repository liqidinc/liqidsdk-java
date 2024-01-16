// File: LogType.java
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
 * LogType
 * Describes a log file type
 */
public enum LogType {
    @JsonProperty("system") SYSTEM("system"),
    @JsonProperty("liqid") LIQID("liqid"),
    @JsonProperty("combined") COMBINED("combined");

    private final String _value;

    LogType(String value) {
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
