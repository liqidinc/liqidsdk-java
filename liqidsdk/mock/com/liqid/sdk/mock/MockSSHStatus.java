// Copyright (c) 2024 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.mock;

import com.liqid.sdk.SSHStatus;

import java.util.Objects;

public class MockSSHStatus extends SSHStatus {

    MockSSHStatus(
        final boolean enabled,
        final boolean active
    ) {
        super(active, enabled);
    }

    @Override
    public boolean equals(
        final Object obj
    ) {
        if (obj instanceof SSHStatus s) {
            return (Objects.equals(getActive(), s.getActive()))
                && Objects.equals(getEnabled(), s.getEnabled());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (getEnabled() ? 1 : 0) | (getActive() ? 2 : 0);
    }

    @Override
    public String toString() {
        return String.format("{enabled=%s, active=%s", getEnabled(), getActive());
    }
}
