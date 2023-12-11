// File: NetworkManagedEnclosure.java
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
 * NetworkManagedEnclosure
 * Describes the access information required to manage an enclosure
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NetworkManagedEnclosure {

    public static class NetworkManagedEnclosureWrapper extends Wrapper<NetworkManagedEnclosure>{}

    /**
     * Credentials necessary for managing the device
     */
    @JsonProperty("credentials")
    private Credentials _credentials = null;

    public Credentials getCredentials() {
        return _credentials;
    }

    public void setCredentials(Credentials value) {
        _credentials = value;
    }

    /**
     * Enclosure name
     */
    @JsonProperty("enclosure_name")
    private String _enclosureName = null;

    public String getEnclosureName() {
        return _enclosureName;
    }

    public void setEnclosureName(String value) {
        _enclosureName = value;
    }

    /**
     * IP Address or DNS host name of the manager for the managed device
     */
    @JsonProperty("ip_address")
    private String _ipAddress = null;

    public String getIPAddress() {
        return _ipAddress;
    }

    public void setIPAddress(String value) {
        _ipAddress = value;
    }

    /**
     * Entity management type
     */
    @JsonProperty("type")
    private ManageableType _managerType = null;

    public ManageableType getManagerType() {
        return _managerType;
    }

    public void setManagerType(ManageableType value) {
        _managerType = value;
    }

    /**
     * Port number for managing the device
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
    public NetworkManagedEnclosure() {
        _credentials = new Credentials();
    }

    /**
     * Parameterized Constructor
     */
    protected NetworkManagedEnclosure(Credentials credentials,
                                      String ipAddress,
                                      Integer portNumber,
                                      ManageableType managerType,
                                      String enclosureName) {
        _credentials = credentials;
        _ipAddress = ipAddress;
        _portNumber = portNumber;
        _managerType = managerType;
        _enclosureName = enclosureName;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_credentials:").append(getCredentials());
        sb.append(", ").append("_ipAddress:").append(getIPAddress());
        sb.append(", ").append("_portNumber:").append(getPortNumber());
        sb.append(", ").append("_managerType:").append(getManagerType());
        sb.append(", ").append("_enclosureName:").append(getEnclosureName());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private Credentials _credentials = null;
        private String _ipAddress = null;
        private Integer _portNumber = null;
        private ManageableType _managerType = null;
        private String _enclosureName = null;

        public Builder setCredentials(Credentials value) { _credentials = value; return this; }
        public Builder setIPAddress(String value) { _ipAddress = value; return this; }
        public Builder setPortNumber(Integer value) { _portNumber = value; return this; }
        public Builder setManagerType(ManageableType value) { _managerType = value; return this; }
        public Builder setEnclosureName(String value) { _enclosureName = value; return this; }

        public NetworkManagedEnclosure build() {
            if (_credentials == null) {
                throw new RuntimeException("setCredentials() was not invoked in Builder for class NetworkManagedEnclosure");
            }
            if (_ipAddress == null) {
                throw new RuntimeException("setIPAddress() was not invoked in Builder for class NetworkManagedEnclosure");
            }
            if (_portNumber == null) {
                throw new RuntimeException("setPortNumber() was not invoked in Builder for class NetworkManagedEnclosure");
            }
            if (_managerType == null) {
                throw new RuntimeException("setManagerType() was not invoked in Builder for class NetworkManagedEnclosure");
            }
            if (_enclosureName == null) {
                throw new RuntimeException("setEnclosureName() was not invoked in Builder for class NetworkManagedEnclosure");
            }
            return new NetworkManagedEnclosure(_credentials,
                                               _ipAddress,
                                               _portNumber,
                                               _managerType,
                                               _enclosureName);
        }
    }
}
