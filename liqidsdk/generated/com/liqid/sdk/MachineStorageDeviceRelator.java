// File: MachineStorageDeviceRelator.java
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
 * MachineStorageDeviceRelator
 * Describes a relation between a machine and a storage device
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MachineStorageDeviceRelator {

    public static class MachineStorageDeviceRelatorWrapper extends Wrapper<MachineStorageDeviceRelator>{}

    /**
     * A GroupStorageDeviceRelator entity for the device in the relation
     */
    @JsonProperty("groupDeviceRelator")
    private GroupStorageDeviceRelator _groupDeviceRelator = null;

    public GroupStorageDeviceRelator getGroupDeviceRelator() {
        return _groupDeviceRelator;
    }

    public void setGroupDeviceRelator(GroupStorageDeviceRelator value) {
        _groupDeviceRelator = value;
    }

    /**
     * Machine entity for the machine in the relation
     */
    @JsonProperty("machine")
    private Machine _machine = null;

    public Machine getMachine() {
        return _machine;
    }

    public void setMachine(Machine value) {
        _machine = value;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public MachineStorageDeviceRelator() {
        _groupDeviceRelator = new GroupStorageDeviceRelator();
        _machine = new Machine();
    }

    /**
     * Parameterized Constructor
     */
    protected MachineStorageDeviceRelator(GroupStorageDeviceRelator groupDeviceRelator,
                                          Machine machine) {
        _groupDeviceRelator = groupDeviceRelator;
        _machine = machine;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_groupDeviceRelator:").append(getGroupDeviceRelator());
        sb.append(", ").append("_machine:").append(getMachine());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private GroupStorageDeviceRelator _groupDeviceRelator = null;
        private Machine _machine = null;

        public Builder setGroupDeviceRelator(GroupStorageDeviceRelator value) { _groupDeviceRelator = value; return this; }
        public Builder setMachine(Machine value) { _machine = value; return this; }

        public MachineStorageDeviceRelator build() {
            if (_groupDeviceRelator == null) {
                throw new RuntimeException("setGroupDeviceRelator() was not invoked in Builder for class MachineStorageDeviceRelator");
            }
            if (_machine == null) {
                throw new RuntimeException("setMachine() was not invoked in Builder for class MachineStorageDeviceRelator");
            }
            return new MachineStorageDeviceRelator(_groupDeviceRelator,
                                                   _machine);
        }
    }
}
