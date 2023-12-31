//
// Copyright (c) 2022 by Liqid, Inc. All rights reserved.
// This file was automatically generated by autogen - do not modify.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk;

public class LiqidException extends Exception {

    public LiqidException(final String message) {
        super(message);
    }

    public LiqidException(final int code,
                          final ErrorMessage[] errors) {
        super(formatError(code, errors));
    }

    public LiqidException(final String message,
                          final Throwable cause) {
        super(message, cause);
    }

    private static String formatError(final int code,
                                      final ErrorMessage[] errors) {
        var sb = new StringBuilder();
        sb.append("json outer code=").append(code);
        if (errors != null && errors.length > 0) {
            sb.append(" errors=");
            for (var err : errors) {
                sb.append("[")
                        .append(err.getType())
                        .append(":")
                        .append(err.getCode())
                        .append(":")
                        .append(err.getMessage())
                        .append("]");
            }
        }
        return sb.toString();
    }
}
