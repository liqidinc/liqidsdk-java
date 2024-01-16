// File: ConnectionHistory.java
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
 * ConnectionHistory
 * Describes one connection to a machine
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectionHistory {

    public static class ConnectionHistoryWrapper extends Wrapper<ConnectionHistory>{}

    /**
     * Time which device was attached
     */
    @JsonProperty("atime")
    private Long _attachTime = null;

    public Long getAttachTime() {
        return _attachTime;
    }

    public void setAttachTime(Long value) {
        _attachTime = value;
    }

    /**
     * Time which device was detached
     */
    @JsonProperty("dtime")
    private Long _detachTime = null;

    public Long getDetachTime() {
        return _detachTime;
    }

    public void setDetachTime(Long value) {
        _detachTime = value;
    }

    /**
     * Type of the connecting device
     */
    @JsonProperty("dev_type")
    private PreDeviceType _deviceType = null;

    public PreDeviceType getDeviceType() {
        return _deviceType;
    }

    public void setDeviceType(PreDeviceType value) {
        _deviceType = value;
    }

    /**
     * Fabric global identifier
     */
    @JsonProperty("fabr_gid")
    private String _fabricGlobalId = null;

    public Integer getFabricGlobalId() {
        return LiqidClientBase.hexStringToInteger(_fabricGlobalId);
    }

    public void setFabricGlobalId(Integer value) {
        _fabricGlobalId = String.format("0x%08x", value);
    }

    /**
     * 
     */
    @JsonProperty("free")
    private Boolean _free = null;

    public Boolean getFree() {
        return _free;
    }

    public void setFree(Boolean value) {
        _free = value;
    }

    /**
     * 
     */
    @JsonProperty("name")
    private String _name = null;

    public String getName() {
        return _name;
    }

    public void setName(String value) {
        _name = value;
    }

    /**
     * Owner global identifier
     */
    @JsonProperty("owner_gid")
    private String _ownerGlobalId = null;

    public String getOwnerGlobalId() {
        return _ownerGlobalId;
    }

    public void setOwnerGlobalId(String value) {
        _ownerGlobalId = value;
    }

    /**
     * User-specified description
     */
    @JsonProperty("udesc")
    private String _userDescription = null;

    public String getUserDescription() {
        return _userDescription;
    }

    public void setUserDescription(String value) {
        _userDescription = value;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public ConnectionHistory() {
    }

    /**
     * Parameterized Constructor
     */
    protected ConnectionHistory(Long attachTime,
                                PreDeviceType deviceType,
                                Long detachTime,
                                String fabricGlobalId,
                                Boolean free,
                                String name,
                                String ownerGlobalId,
                                String userDescription) {
        _attachTime = attachTime;
        _deviceType = deviceType;
        _detachTime = detachTime;
        _fabricGlobalId = fabricGlobalId;
        _free = free;
        _name = name;
        _ownerGlobalId = ownerGlobalId;
        _userDescription = userDescription;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_attachTime:").append(getAttachTime());
        sb.append(", ").append("_deviceType:").append(getDeviceType());
        sb.append(", ").append("_detachTime:").append(getDetachTime());
        sb.append(", ").append("_fabricGlobalId:").append(getFabricGlobalId());
        sb.append(", ").append("_free:").append(getFree());
        sb.append(", ").append("_name:").append(getName());
        sb.append(", ").append("_ownerGlobalId:").append(getOwnerGlobalId());
        sb.append(", ").append("_userDescription:").append(getUserDescription());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private Long _attachTime = null;
        private PreDeviceType _deviceType = null;
        private Long _detachTime = null;
        private String _fabricGlobalId = null;
        private Boolean _free = null;
        private String _name = null;
        private String _ownerGlobalId = null;
        private String _userDescription = null;

        public Builder setAttachTime(Long value) { _attachTime = value; return this; }
        public Builder setDeviceType(PreDeviceType value) { _deviceType = value; return this; }
        public Builder setDetachTime(Long value) { _detachTime = value; return this; }
        public Builder setFabricGlobalId(String value) { _fabricGlobalId = value; return this; }
        public Builder setFree(Boolean value) { _free = value; return this; }
        public Builder setName(String value) { _name = value; return this; }
        public Builder setOwnerGlobalId(String value) { _ownerGlobalId = value; return this; }
        public Builder setUserDescription(String value) { _userDescription = value; return this; }

        public ConnectionHistory build() {
            if (_attachTime == null) {
                throw new RuntimeException("setAttachTime() was not invoked in Builder for class ConnectionHistory");
            }
            if (_deviceType == null) {
                throw new RuntimeException("setDeviceType() was not invoked in Builder for class ConnectionHistory");
            }
            if (_detachTime == null) {
                throw new RuntimeException("setDetachTime() was not invoked in Builder for class ConnectionHistory");
            }
            if (_fabricGlobalId == null) {
                throw new RuntimeException("setFabricGlobalId() was not invoked in Builder for class ConnectionHistory");
            }
            if (_free == null) {
                throw new RuntimeException("setFree() was not invoked in Builder for class ConnectionHistory");
            }
            if (_name == null) {
                throw new RuntimeException("setName() was not invoked in Builder for class ConnectionHistory");
            }
            if (_ownerGlobalId == null) {
                throw new RuntimeException("setOwnerGlobalId() was not invoked in Builder for class ConnectionHistory");
            }
            if (_userDescription == null) {
                throw new RuntimeException("setUserDescription() was not invoked in Builder for class ConnectionHistory");
            }
            return new ConnectionHistory(_attachTime,
                                         _deviceType,
                                         _detachTime,
                                         _fabricGlobalId,
                                         _free,
                                         _name,
                                         _ownerGlobalId,
                                         _userDescription);
        }
    }
}
