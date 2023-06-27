// File: GroupGPUDeviceRelator.java
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
 * GroupGPUDeviceRelator
 * Describes a relation between a group and an GPU device
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupGPUDeviceRelator {

    public static class GroupGPUDeviceRelatorWrapper extends Wrapper<GroupGPUDeviceRelator>{}

    /**
     * GPUDeviceStatus entity for the device in the relation
     */
    @JsonProperty("deviceStatus")
    private GPUDeviceStatus _deviceStatus = null;
    public GPUDeviceStatus getDeviceStatus() { return _deviceStatus; }
    public void setDeviceStatus(GPUDeviceStatus value) { _deviceStatus = value; }

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
    public GroupGPUDeviceRelator() {
        _deviceStatus = new GPUDeviceStatus();
        _group = new Group();
    }

    /**
     * Parameterized Constructor
     */
    protected GroupGPUDeviceRelator(GPUDeviceStatus deviceStatus,
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

        private GPUDeviceStatus _deviceStatus = null;
        private Group _group = null;

        public Builder setDeviceStatus(GPUDeviceStatus value) { _deviceStatus = value; return this; }
        public Builder setGroup(Group value) { _group = value; return this; }

        public GroupGPUDeviceRelator build() {
            if (_deviceStatus == null) {
                throw new RuntimeException("setDeviceStatus() was not invoked in Builder for class GroupGPUDeviceRelator");
            }
            if (_group == null) {
                throw new RuntimeException("setGroup() was not invoked in Builder for class GroupGPUDeviceRelator");
            }
            return new GroupGPUDeviceRelator(_deviceStatus,
                                             _group);
        }
    }
}
