// Copyright (c) 2024 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.mock;

import com.liqid.sdk.LiqidException;

public abstract class MockException extends LiqidException {

    protected MockException(
        final String message
    ) {
        super("Mock Exception:" + message);
    }
}
