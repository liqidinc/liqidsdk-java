// File: Coordinates.java
//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
// 
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//
// Liqid SDK - Version 3.2.0
// This file was automatically generated - do not modify it directly.
//

package com.liqid.sdk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Coordinates
 * Describes a unique Liqid coordinate.
 * Most of the members of this entity are obsolete.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coordinates {

    public static class CoordinatesWrapper extends Wrapper<Coordinates>{}

    /**
     * Describes the relative position of a particular coordinate-addressable Liqid entity.
     */
    @JsonProperty("node")
    private Integer _node = null;
    public Integer getNode() { return _node; }
    public void setNode(Integer value) { _node = value; }

    /**
     * Obsolete - should always be zero
     */
    @JsonProperty("rack")
    private Integer _rack = 0;
    public Integer getRack() { return _rack; }
    public void setRack(Integer value) { _rack = value; }

    /**
     * Obsolete - should always be zero
     */
    @JsonProperty("shelf")
    private Integer _shelf = 0;
    public Integer getShelf() { return _shelf; }
    public void setShelf(Integer value) { _shelf = value; }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public Coordinates() {
    }

    /**
     * Parameterized Constructor
     */
    protected Coordinates(Integer rack,
                          Integer shelf,
                          Integer node) {
        _rack = rack;
        _shelf = shelf;
        _node = node;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_rack:").append(getRack());
        sb.append(", ").append("_shelf:").append(getShelf());
        sb.append(", ").append("_node:").append(getNode());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private Integer _rack = 0;
        private Integer _shelf = 0;
        private Integer _node = null;

        public Builder setRack(Integer value) { _rack = value; return this; }
        public Builder setShelf(Integer value) { _shelf = value; return this; }
        public Builder setNode(Integer value) { _node = value; return this; }

        public Coordinates build() {
            if (_node == null) {
                throw new RuntimeException("setNode() was not invoked in Builder for class Coordinates");
            }
            return new Coordinates(_rack,
                                   _shelf,
                                   _node);
        }
    }
}
