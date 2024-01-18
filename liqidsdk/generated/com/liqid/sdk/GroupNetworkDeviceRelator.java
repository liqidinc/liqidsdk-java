// File: GroupNetworkDeviceRelator.java
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
 * GroupNetworkDeviceRelator
 * Describes a relation between a group and a network device
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupNetworkDeviceRelator {

    public static class GroupNetworkDeviceRelatorWrapper extends Wrapper<GroupNetworkDeviceRelator>{}

    /**
     * NetworkDeviceStatus entity for the device in the relation
     */
    @JsonProperty("deviceStatus")
    private NetworkDeviceStatus _deviceStatus = null;

    public NetworkDeviceStatus getDeviceStatus() {
        return _deviceStatus;
    }

    public GroupNetworkDeviceRelator setDeviceStatus(NetworkDeviceStatus value) {
        _deviceStatus = value;
        return this;
    }

    /**
     * Group entity for the group in the relation
     */
    @JsonProperty("group")
    private Group _group = null;

    public Group getGroup() {
        return _group;
    }

    public GroupNetworkDeviceRelator setGroup(Group value) {
        _group = value;
        return this;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public GroupNetworkDeviceRelator() {
        _deviceStatus = new NetworkDeviceStatus();
        _group = new Group();
    }

    /**
     * Parameterized Constructor
     */
    protected GroupNetworkDeviceRelator(NetworkDeviceStatus deviceStatus,
                                        Group group) {
        _deviceStatus = deviceStatus;
        _group = group;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_deviceStatus:").append(getDeviceStatus());
        sb.append(", ").append("_group:").append(getGroup());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private NetworkDeviceStatus _deviceStatus = null;
        private Group _group = null;

        public Builder setDeviceStatus(NetworkDeviceStatus value) { _deviceStatus = value; return this; }
        public Builder setGroup(Group value) { _group = value; return this; }

        public GroupNetworkDeviceRelator build() {
            if (_deviceStatus == null) {
                throw new RuntimeException("setDeviceStatus() was not invoked in Builder for class GroupNetworkDeviceRelator");
            }
            if (_group == null) {
                throw new RuntimeException("setGroup() was not invoked in Builder for class GroupNetworkDeviceRelator");
            }
            return new GroupNetworkDeviceRelator(_deviceStatus,
                                                 _group);
        }
    }
}
