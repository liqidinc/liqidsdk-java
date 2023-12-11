//
// Copyright (c) 2022-2023 by Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk;

import static com.liqid.sdk.LiqidClientBuilder.*;

/**
 * Creates a LiqidClient object (the class for which is auto-generated),
 * given a few parameters (or just one parameter).
 * This is deprecated in favor of the LiqidClientBuilder class.
 */
@Deprecated
public class LiqidClientFactory {

    public LiqidClient createClient(final boolean secure,
                                    final String hostAddress,
                                    final int portNumber,
                                    final int timeoutInSeconds) {
        return new LiqidClient(secure,
                               hostAddress,
                               portNumber,
                               false,
                               timeoutInSeconds,
                               false,
                               DEFAULT_RETRY_LIMIT,
                               DEFAULT_RETRY_DELAY_IN_SECONDS,
                               false,
                               DEFAULT_MAX_ASYNC_WAIT_TIME_IN_SECONDS);
    }

    public LiqidClient createDefaultClient(final String hostAddress) {
        var portNumber = DEFAULT_SECURE_HTTP ? DEFAULT_SECURE_PORT_NUMBER : DEFAULT_PORT_NUMBER;
        return new LiqidClient(DEFAULT_SECURE_HTTP,
                               hostAddress,
                               portNumber,
                               DEFAULT_IGNORE_CERTIFICATES,
                               DEFAULT_TIMEOUT_IN_SECONDS,
                               false,
                               0,
                               0,
                               false,
                               DEFAULT_MAX_ASYNC_WAIT_TIME_IN_SECONDS);
    }
}
