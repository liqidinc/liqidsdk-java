//
// Copyright (c) 2023 by Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk;

/**
 * Conventional builder for the LiqidClient class
 */
public class LiqidClientBuilder {

    public static final boolean DEFAULT_IGNORE_CERTIFICATES = false;
    public static final int DEFAULT_MAX_ASYNC_WAIT_TIME_IN_SECONDS = 300;
    public static final int DEFAULT_PORT_NUMBER = 8080;
    public static final int DEFAULT_RETRY_DELAY_IN_SECONDS = 10;
    public static final int DEFAULT_RETRY_LIMIT = 5;
    public static final boolean DEFAULT_SECURE_HTTP = false;
    public static final int DEFAULT_SECURE_PORT_NUMBER = 8443;
    public static final int DEFAULT_TIMEOUT_IN_SECONDS = 60;

    private String _hostAddress = null;
    private Boolean _ignoreCertificates = DEFAULT_IGNORE_CERTIFICATES;
    private Integer _maxAsyncWaitTimeInSeconds = DEFAULT_MAX_ASYNC_WAIT_TIME_IN_SECONDS;
    private Integer _portNumber = DEFAULT_PORT_NUMBER;
    private Integer _retryDelayInSeconds = DEFAULT_RETRY_DELAY_IN_SECONDS;
    private Integer _retryLimit = DEFAULT_RETRY_LIMIT;
    private Boolean _retryOnServerError = false;
    private Boolean _secureHTTP = DEFAULT_SECURE_HTTP;
    private Integer _timeoutInSeconds = DEFAULT_TIMEOUT_IN_SECONDS;
    private Boolean _waitForAsyncCompletion = false;

    public LiqidClientBuilder setHostAddress(String value) { _hostAddress = value; return this; }
    public LiqidClientBuilder setIgnoreCertificates(Boolean value) { _ignoreCertificates = value; return this; }
    public LiqidClientBuilder setMaxAsyncWaitTimeInSeconds(Integer value) { _maxAsyncWaitTimeInSeconds = value; return this; }
    public LiqidClientBuilder setPortNumber(Integer value) { _portNumber = value; return this; }
    public LiqidClientBuilder setRetryDelayInSeconds(Integer value) { _retryDelayInSeconds = value; return this; }
    public LiqidClientBuilder setRetryLimit(Integer value) { _retryLimit = value; return this; }
    public LiqidClientBuilder setRetryOnServerError(Boolean value) { _retryOnServerError = value; return this; }
    public LiqidClientBuilder setSecureHTTP(Boolean value) { _secureHTTP = value; return this; }
    public LiqidClientBuilder setTimeoutInSeconds(Integer value) { _timeoutInSeconds = value; return this; }
    public LiqidClientBuilder setWaitForAsyncCompletion(Boolean value) { _waitForAsyncCompletion = value; return this; }

    public LiqidClient build() throws LiqidException {
        if (_hostAddress == null) {
            throw new LiqidException("Host address not specified");
        }

        return new LiqidClient(_secureHTTP,
                               _hostAddress,
                               _portNumber,
                               _ignoreCertificates,
                               _timeoutInSeconds,
                               _retryOnServerError,
                               _retryLimit,
                               _retryDelayInSeconds,
                               _waitForAsyncCompletion,
                               _maxAsyncWaitTimeInSeconds);
    }
}
