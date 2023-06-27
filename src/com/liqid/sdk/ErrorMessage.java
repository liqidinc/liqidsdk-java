//
// Copyright (c) 2022 by Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorMessage {
    @JsonProperty("code") private int _Code;
    @JsonProperty("message") private String _Message;
    @JsonProperty("type") private String _Type;

    public int getCode() {
        return _Code;
    }

    public String getMessage() {
        return _Message;
    }

    public String getType() {
        return _Type;
    }
}
