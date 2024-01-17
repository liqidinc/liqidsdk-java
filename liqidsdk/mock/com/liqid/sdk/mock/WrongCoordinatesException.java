// Copyright (c) 2024 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.mock;

/**
 * Indicates that the invoked method has not (yet) been implemented for the mock
 */
public class WrongCoordinatesException extends MockException {

    protected WrongCoordinatesException(
        final MockCoordinates coordinates,
        final MockCoordinates defaultCoordinates
    ) {
        super(String.format("Default coordinates %s do not match mock coordinates %s", defaultCoordinates, coordinates));
    }
}
