// File: FabricToggleComposite.java
//
// Copyright (c) 2022-2023 Liqid, Inc. All rights reserved.
// 
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//
// Liqid SDK - Version 3.3.0
// This file was automatically generated - do not modify it directly.
//

package com.liqid.sdk;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.PropertyAccessor;

/**
 * FabricToggleComposite
 * Describes the result of a fabric change operation
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FabricToggleComposite {

    public static class FabricToggleCompositeWrapper extends Wrapper<FabricToggleComposite>{}

    /**
     * Describes the value added to the fabric
     */
    @JsonProperty("flag")
    private NameValuePair _controlFlag = null;

    public NameValuePair getControlFlag() {
        return _controlFlag;
    }

    public void setControlFlag(NameValuePair value) {
        _controlFlag = value;
    }

    /**
     * Describes the LIQID coordinates of the director for the updated fabric
     */
    @JsonProperty("coordinates")
    private Coordinates _coordinates = null;

    public Coordinates getCoordinates() {
        return _coordinates;
    }

    public void setCoordinates(Coordinates value) {
        _coordinates = value;
    }

    /**
     * Describes the operation which was requested
     */
    @JsonProperty("options")
    private FabricToggleCompositeOption _options = null;

    public FabricToggleCompositeOption getOptions() {
        return _options;
    }

    public void setOptions(FabricToggleCompositeOption value) {
        _options = value;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public FabricToggleComposite() {
        _coordinates = new Coordinates();
        _controlFlag = new NameValuePair();
    }

    /**
     * Parameterized Constructor
     */
    protected FabricToggleComposite(Coordinates coordinates,
                                    NameValuePair controlFlag,
                                    FabricToggleCompositeOption options) {
        _coordinates = coordinates;
        _controlFlag = controlFlag;
        _options = options;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_coordinates:").append(getCoordinates());
        sb.append(", ").append("_controlFlag:").append(getControlFlag());
        sb.append(", ").append("_options:").append(getOptions());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private Coordinates _coordinates = null;
        private NameValuePair _controlFlag = null;
        private FabricToggleCompositeOption _options = null;

        public Builder setCoordinates(Coordinates value) { _coordinates = value; return this; }
        public Builder setControlFlag(NameValuePair value) { _controlFlag = value; return this; }
        public Builder setOptions(FabricToggleCompositeOption value) { _options = value; return this; }

        public FabricToggleComposite build() {
            if (_coordinates == null) {
                throw new RuntimeException("setCoordinates() was not invoked in Builder for class FabricToggleComposite");
            }
            if (_controlFlag == null) {
                throw new RuntimeException("setControlFlag() was not invoked in Builder for class FabricToggleComposite");
            }
            if (_options == null) {
                throw new RuntimeException("setOptions() was not invoked in Builder for class FabricToggleComposite");
            }
            return new FabricToggleComposite(_coordinates,
                                             _controlFlag,
                                             _options);
        }
    }
}
