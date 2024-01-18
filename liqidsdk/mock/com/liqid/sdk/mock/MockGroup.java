// Copyright (c) 2024 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.mock;

import com.liqid.sdk.Group;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MockGroup extends Group {

    final Map<Integer, MockDevice> _freePool = new HashMap<>();
    final Map<Integer, MockMachine> _machines = new HashMap<>();

    MockGroup(
        final Integer groupId,
        final String groupName,
        final Integer fabricId,
        final Integer podIdentifier
    ) {
        super(fabricId, groupId, groupName, podIdentifier);
    }

    MockGroup(
        final Group source
    ) {
        this(source.getGroupId(), source.getGroupName(), source.getFabricId(), source.getPodId());
    }

    @Override
    public boolean equals(
        final Object obj
    ) {
        if (obj instanceof Group g) {
            return Objects.equals(getGroupId(), g.getGroupId());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getGroupId().hashCode();
    }

    @Override
    public String toString() {
        return String.format("{id=%d, name=%s}", getGroupId(), getGroupName());
    }
}
