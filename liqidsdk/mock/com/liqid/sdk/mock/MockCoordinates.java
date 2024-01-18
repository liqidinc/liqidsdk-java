// Copyright (c) 2024 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.mock;

import com.liqid.sdk.Coordinates;

import java.util.Objects;

public class MockCoordinates extends Coordinates {

    MockCoordinates(
        final int rack,
        final int shelf,
        final int node
    ) {
        super(rack, shelf, node);
    }

    MockCoordinates(
        final Coordinates source
    ) {
        super(source.getRack(), source.getShelf(), source.getNode());
    }

    @Override
    public boolean equals(
        final Object obj
    ) {
        if (obj instanceof Coordinates c) {
            return (Objects.equals(getRack(), c.getNode()))
                && (Objects.equals(getShelf(), c.getShelf()))
                && Objects.equals(getNode(), c.getNode());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (getRack() << 20) | (getShelf() << 10) | getNode();
    }

    @Override
    public String toString() {
        return String.format("{rack=%d, shelf=%d, node=%d}", getRack(), getShelf(), getNode());
    }
}
