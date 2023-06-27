// File: NetworkManagedCPU.java
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
 * NetworkManagedCPU
 * Describes the access information required to manage a CPU device (e.g., via IPMI)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NetworkManagedCPU {

    public static class NetworkManagedCPUWrapper extends Wrapper<NetworkManagedCPU>{}

    /**
     * CPU name
     */
    @JsonProperty("cpu_name")
    private String _cpuName = null;
    public String getCPUName() { return _cpuName; }
    public void setCPUName(String value) { _cpuName = value; }

    /**
     * Credentials necessary for managing the device
     */
    @JsonProperty("credentials")
    private Credentials _credentials = null;
    public Credentials getCredentials() { return _credentials; }
    public void setCredentials(Credentials value) { _credentials = value; }

    /**
     * IP Address or DNS host name of the manager for the managed device
     */
    @JsonProperty("ip_address")
    private String _ipAddress = null;
    public String getIPAddress() { return _ipAddress; }
    public void setIPAddress(String value) { _ipAddress = value; }

    /**
     * Entity management type
     */
    @JsonProperty("type")
    private ManageableType _managerType = null;
    public ManageableType getManagerType() { return _managerType; }
    public void setManagerType(ManageableType value) { _managerType = value; }

    /**
     * Port number for managing the device
     */
    @JsonProperty("port")
    private Integer _portNumber = null;
    public Integer getPortNumber() { return _portNumber; }
    public void setPortNumber(Integer value) { _portNumber = value; }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public NetworkManagedCPU() {
        _credentials = new Credentials();
    }

    /**
     * Parameterized Constructor
     */
    protected NetworkManagedCPU(Credentials credentials,
                                String ipAddress,
                                Integer portNumber,
                                ManageableType managerType,
                                String cpuName) {
        _credentials = credentials;
        _ipAddress = ipAddress;
        _portNumber = portNumber;
        _managerType = managerType;
        _cpuName = cpuName;
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
        sb.append(", ").append("_cpuName:").append(getCPUName());
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
        private String _cpuName = null;

        public Builder setCredentials(Credentials value) { _credentials = value; return this; }
        public Builder setIPAddress(String value) { _ipAddress = value; return this; }
        public Builder setPortNumber(Integer value) { _portNumber = value; return this; }
        public Builder setManagerType(ManageableType value) { _managerType = value; return this; }
        public Builder setCPUName(String value) { _cpuName = value; return this; }

        public NetworkManagedCPU build() {
            if (_credentials == null) {
                throw new RuntimeException("setCredentials() was not invoked in Builder for class NetworkManagedCPU");
            }
            if (_ipAddress == null) {
                throw new RuntimeException("setIPAddress() was not invoked in Builder for class NetworkManagedCPU");
            }
            if (_portNumber == null) {
                throw new RuntimeException("setPortNumber() was not invoked in Builder for class NetworkManagedCPU");
            }
            if (_managerType == null) {
                throw new RuntimeException("setManagerType() was not invoked in Builder for class NetworkManagedCPU");
            }
            if (_cpuName == null) {
                throw new RuntimeException("setCPUName() was not invoked in Builder for class NetworkManagedCPU");
            }
            return new NetworkManagedCPU(_credentials,
                                         _ipAddress,
                                         _portNumber,
                                         _managerType,
                                         _cpuName);
        }
    }
}
