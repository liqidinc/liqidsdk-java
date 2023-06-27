// File: GroupComputeDeviceRelator.java
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
 * GroupComputeDeviceRelator
 * Describes a relation between a group and a compute device
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupComputeDeviceRelator {

    public static class GroupComputeDeviceRelatorWrapper extends Wrapper<GroupComputeDeviceRelator>{}

    /**
     * A ComputeDeviceStatus entity for the device in the relation
     */
    @JsonProperty("deviceStatus")
    private ComputeDeviceStatus _deviceStatus = null;
    public ComputeDeviceStatus getDeviceStatus() { return _deviceStatus; }
    public void setDeviceStatus(ComputeDeviceStatus value) { _deviceStatus = value; }

    /**
     * Group entity for the group in the relation
     */
    @JsonProperty("group")
    private Group _group = null;
    public Group getGroup() { return _group; }
    public void setGroup(Group value) { _group = value; }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public GroupComputeDeviceRelator() {
        _deviceStatus = new ComputeDeviceStatus();
        _group = new Group();
    }

    /**
     * Parameterized Constructor
     */
    protected GroupComputeDeviceRelator(ComputeDeviceStatus deviceStatus,
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

        private ComputeDeviceStatus _deviceStatus = null;
        private Group _group = null;

        public Builder setDeviceStatus(ComputeDeviceStatus value) { _deviceStatus = value; return this; }
        public Builder setGroup(Group value) { _group = value; return this; }

        public GroupComputeDeviceRelator build() {
            if (_deviceStatus == null) {
                throw new RuntimeException("setDeviceStatus() was not invoked in Builder for class GroupComputeDeviceRelator");
            }
            if (_group == null) {
                throw new RuntimeException("setGroup() was not invoked in Builder for class GroupComputeDeviceRelator");
            }
            return new GroupComputeDeviceRelator(_deviceStatus,
                                                 _group);
        }
    }
}
