// File: AvailableCoordinates.java
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
 * AvailableCoordinates
 * A description of an available REST target including IP addressing information and Liqid Coordinates
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AvailableCoordinates {

    public static class AvailableCoordinatesWrapper extends Wrapper<AvailableCoordinates>{}

    /**
     * Liqid coordinates of the REST target
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
     * DNS name or dotted-decimal IP address of the REST target
     */
    @JsonProperty("address")
    private String _ipAddress = null;

    public String getIPAddress() {
        return _ipAddress;
    }

    public void setIPAddress(String value) {
        _ipAddress = value;
    }

    /**
     * UDP port number of the REST target
     */
    @JsonProperty("port")
    private Integer _portNumber = null;

    public Integer getPortNumber() {
        return _portNumber;
    }

    public void setPortNumber(Integer value) {
        _portNumber = value;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public AvailableCoordinates() {
        _coordinates = new Coordinates();
    }

    /**
     * Parameterized Constructor
     */
    protected AvailableCoordinates(String ipAddress,
                                   Integer portNumber,
                                   Coordinates coordinates) {
        _ipAddress = ipAddress;
        _portNumber = portNumber;
        _coordinates = coordinates;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_ipAddress:").append(getIPAddress());
        sb.append(", ").append("_portNumber:").append(getPortNumber());
        sb.append(", ").append("_coordinates:").append(getCoordinates());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private String _ipAddress = null;
        private Integer _portNumber = null;
        private Coordinates _coordinates = null;

        public Builder setIPAddress(String value) { _ipAddress = value; return this; }
        public Builder setPortNumber(Integer value) { _portNumber = value; return this; }
        public Builder setCoordinates(Coordinates value) { _coordinates = value; return this; }

        public AvailableCoordinates build() {
            if (_ipAddress == null) {
                throw new RuntimeException("setIPAddress() was not invoked in Builder for class AvailableCoordinates");
            }
            if (_portNumber == null) {
                throw new RuntimeException("setPortNumber() was not invoked in Builder for class AvailableCoordinates");
            }
            if (_coordinates == null) {
                throw new RuntimeException("setCoordinates() was not invoked in Builder for class AvailableCoordinates");
            }
            return new AvailableCoordinates(_ipAddress,
                                            _portNumber,
                                            _coordinates);
        }
    }
}
