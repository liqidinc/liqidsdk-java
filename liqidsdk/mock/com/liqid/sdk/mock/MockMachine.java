// Copyright (c) 2024 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.mock;

import com.liqid.sdk.Machine;
import com.liqid.sdk.P2PType;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

public class MockMachine extends Machine {

    final Map<Integer, MockDevice> _attachedDevices = new HashMap<>();

    MockMachine(
        final Integer index,
        final Integer machineId,
        final Integer groupId,
        final Integer fabricId,
        final String machineName
    ) {
        super(index,
              machineId,
              groupId,
              fabricId,
              "n/a",
              "0x0",
              "0x0",
              "", // compute name,
              machineName,
              P2PType.OFF, // p2p enabled
              System.currentTimeMillis(),
              new LinkedList<>());
    }

    @Override
    public boolean equals(
        final Object obj
    ) {
        if (obj instanceof Machine m) {
            return Objects.equals(getMachineId(), m.getMachineId());
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
        return String.format("{id=%d, name=%s}", getMachineId(), getMachineName());
    }
}
