//
// Copyright (c) 2022 by Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk;

public class LiqidClientFactory {

    public static final boolean DEFAULT_SECURE_HTTP = false;
    public static final int DEFAULT_PORT_NUMBER = 8080;
    public static final int DEFAULT_SECURE_PORT_NUMBER = 8443;
    public static final int DEFAULT_TIMEOUT_IN_SECONDS = 15;

    public LiqidClient createClient(final boolean secure,
                                    final String hostAddress,
                                    final int portNumber,
                                    final int timeoutInSeconds) {
        return new LiqidClient(secure, hostAddress, portNumber, false, timeoutInSeconds);
    }

    public LiqidClient createDefaultClient(final String hostAddress) {
        var portNumber = DEFAULT_SECURE_HTTP ? DEFAULT_SECURE_PORT_NUMBER : DEFAULT_PORT_NUMBER;
        return new LiqidClient(DEFAULT_SECURE_HTTP, hostAddress, portNumber, false, DEFAULT_TIMEOUT_IN_SECONDS);
    }
}
