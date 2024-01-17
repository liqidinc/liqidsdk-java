// Copyright (c) 2024 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.mock;

import com.liqid.sdk.Group;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MockGroup extends Group {

    private boolean _editMode = false;
    private final Set<Integer> _machineIds = new HashSet<>();

    MockGroup(
        final Integer groupId,
        final String groupName,
        final Integer fabricId,
        final Integer podIdentifier
    ) {
        super(fabricId, groupId, groupName, podIdentifier);
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

    MockGroup addMachineId(final Integer machineId) { _machineIds.add(machineId); return this; }
    boolean getEditMode() { return _editMode; }
    Collection<Integer> getMachineIds() { return _machineIds; }
    boolean hasMachines() { return !_machineIds.isEmpty(); }
    MockGroup removeMachine(final Integer machineId) { _machineIds.remove(machineId); return this; }
    MockGroup setEditMode(final boolean flag) { _editMode = flag; return this; }
}
