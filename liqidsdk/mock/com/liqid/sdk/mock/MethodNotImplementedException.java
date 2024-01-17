// Copyright (c) 2024 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.mock;

/**
 * Indicates that the invoked method has not (yet) been implemented for the mock
 */
public class MethodNotImplementedException extends MockException {

    protected MethodNotImplementedException(
        final String methodName
    ) {
        super(String.format("method %s has not (yet) been implemented", methodName));
    }
}
