// File: BackupDestination.java
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
 * BackupDestination
 * Indicates the backup destination
 */
public enum BackupDestination {
    LOCAL(0),
    REMOTE(1);

    private final Integer _value;

    BackupDestination(Integer value) {
        _value = value;
    }

    public Integer getValue() {
        return _value;
    }

    @Override
    public String toString() {
        return _value.toString();
    }
}
