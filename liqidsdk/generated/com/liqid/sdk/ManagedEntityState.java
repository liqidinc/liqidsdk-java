// File: ManagedEntityState.java
//
// Copyright (c) 2022-2023 Liqid, Inc. All rights reserved.
// 
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//
// Liqid SDK - Version 3.4
// This file was automatically generated - do not modify it directly.
//

package com.liqid.sdk;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.PropertyAccessor;

/**
 * ManagedEntityState
 * Describes the state of a managed entity entry
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ManagedEntityState {

    public static class ManagedEntityStateWrapper extends Wrapper<ManagedEntityState>{}

    /**
     * Indicates whether the entity entry is active
     */
    @JsonProperty("active")
    private Boolean _active = null;

    public Boolean getActive() {
        return _active;
    }

    public ManagedEntityState setActive(Boolean value) {
        _active = value;
        return this;
    }

    /**
     * Indicates whether the entity entry is required
     */
    @JsonProperty("required")
    private Boolean _required = null;

    public Boolean getRequired() {
        return _required;
    }

    public ManagedEntityState setRequired(Boolean value) {
        _required = value;
        return this;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public ManagedEntityState() {
    }

    /**
     * Parameterized Constructor
     */
    protected ManagedEntityState(Boolean active,
                                 Boolean required) {
        _active = active;
        _required = required;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_active:").append(getActive());
        sb.append(", ").append("_required:").append(getRequired());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private Boolean _active = null;
        private Boolean _required = null;

        public Builder setActive(Boolean value) { _active = value; return this; }
        public Builder setRequired(Boolean value) { _required = value; return this; }

        public ManagedEntityState build() {
            if (_active == null) {
                throw new RuntimeException("setActive() was not invoked in Builder for class ManagedEntityState");
            }
            if (_required == null) {
                throw new RuntimeException("setRequired() was not invoked in Builder for class ManagedEntityState");
            }
            return new ManagedEntityState(_active,
                                          _required);
        }
    }
}
