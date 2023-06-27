//
// Copyright (c) 2022 by Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class Wrapper<T> {

    public static class Response<T> {
        @JsonProperty("code") private int _code;
        @JsonProperty("data") private LinkedList<T> _data;
        @JsonProperty("errors") private ErrorMessage[] _errors;

        public int getCode() {
            return _code;
        }

        public LinkedList<T> getData() {
            return _data;
        }

        public ErrorMessage[] getErrors() {
            return _errors;
        }

        public Response() {}
        public Response(Collection<T> data) { _data = new LinkedList<>(data); }
        public Response(T data) { _data = new LinkedList<>(Collections.singletonList(data)); }
    }

    @JsonProperty("response") private Response<T> _response;

    public Wrapper() {}

    public Response<T> getResponse() { return _response; }
    public void setResponse(Response<T> response) { _response = response; }

    public boolean hasError() {
        if (_response == null) {
            return true;
        } else {
            if (_response._code > 0)
                return true;
            return _response._errors != null && _response._errors.length > 0;
        }
    }

    public void check() throws LiqidException {
        if (hasError()) {
            if (_response == null) {
                throw new LiqidException("No response provided");
            } else {
                throw new LiqidException(_response._code, _response._errors);
            }
        }
    }
}
