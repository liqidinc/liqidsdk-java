// File: GroupStorageDeviceRelator.java
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
 * GroupStorageDeviceRelator
 * Describes a relation between a group and a storage device
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupStorageDeviceRelator {

    public static class GroupStorageDeviceRelatorWrapper extends Wrapper<GroupStorageDeviceRelator>{}

    /**
     * StorageDeviceStatus entity for the device in the relation
     */
    @JsonProperty("deviceStatus")
    private StorageDeviceStatus _deviceStatus = null;

    public StorageDeviceStatus getDeviceStatus() {
        return _deviceStatus;
    }

    public void setDeviceStatus(StorageDeviceStatus value) {
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
    public GroupStorageDeviceRelator() {
        _deviceStatus = new StorageDeviceStatus();
        _group = new Group();
    }

    /**
     * Parameterized Constructor
     */
    protected GroupStorageDeviceRelator(StorageDeviceStatus deviceStatus,
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

        private StorageDeviceStatus _deviceStatus = null;
        private Group _group = null;

        public Builder setDeviceStatus(StorageDeviceStatus value) { _deviceStatus = value; return this; }
        public Builder setGroup(Group value) { _group = value; return this; }

        public GroupStorageDeviceRelator build() {
            if (_deviceStatus == null) {
                throw new RuntimeException("setDeviceStatus() was not invoked in Builder for class GroupStorageDeviceRelator");
            }
            if (_group == null) {
                throw new RuntimeException("setGroup() was not invoked in Builder for class GroupStorageDeviceRelator");
            }
            return new GroupStorageDeviceRelator(_deviceStatus,
                                                 _group);
        }
    }
}
