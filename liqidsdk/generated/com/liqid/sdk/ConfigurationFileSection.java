// File: ConfigurationFileSection.java
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
 * ConfigurationFileSection
 * Describes a type of configuration file section
 */
public enum ConfigurationFileSection {
    @JsonProperty("enclosure") ENCLOSURE("enclosure"),
    @JsonProperty("cpu") CPU("cpu");

    private final String _value;

    ConfigurationFileSection(String value) {
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
