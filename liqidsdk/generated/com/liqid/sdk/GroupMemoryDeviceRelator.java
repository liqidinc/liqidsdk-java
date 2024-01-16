// File: GroupMemoryDeviceRelator.java
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
 * GroupMemoryDeviceRelator
 * Describes a relation between a group and a memory device
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupMemoryDeviceRelator {

    public static class GroupMemoryDeviceRelatorWrapper extends Wrapper<GroupMemoryDeviceRelator>{}

    /**
     * MemoryDeviceStatus entity for the device in the relation
     */
    @JsonProperty("deviceStatus")
    private MemoryDeviceStatus _deviceStatus = null;

    public MemoryDeviceStatus getDeviceStatus() {
        return _deviceStatus;
    }

    public void setDeviceStatus(MemoryDeviceStatus value) {
        _deviceStatus = value;
    }

    /**
     * Group entity for the group in the relation
     */
    @JsonProperty("group")
    private Group _group = null;

    public Group getGroup() {
        return _group;
    }

    public void setGroup(Group value) {
        _group = value;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public GroupMemoryDeviceRelator() {
        _deviceStatus = new MemoryDeviceStatus();
        _group = new Group();
    }

    /**
     * Parameterized Constructor
     */
    protected GroupMemoryDeviceRelator(MemoryDeviceStatus deviceStatus,
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

        private MemoryDeviceStatus _deviceStatus = null;
        private Group _group = null;

        public Builder setDeviceStatus(MemoryDeviceStatus value) { _deviceStatus = value; return this; }
        public Builder setGroup(Group value) { _group = value; return this; }

        public GroupMemoryDeviceRelator build() {
            if (_deviceStatus == null) {
                throw new RuntimeException("setDeviceStatus() was not invoked in Builder for class GroupMemoryDeviceRelator");
            }
            if (_group == null) {
                throw new RuntimeException("setGroup() was not invoked in Builder for class GroupMemoryDeviceRelator");
            }
            return new GroupMemoryDeviceRelator(_deviceStatus,
                                                _group);
        }
    }
}
