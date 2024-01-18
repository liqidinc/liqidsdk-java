// File: MachineMemoryDeviceRelator.java
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
 * MachineMemoryDeviceRelator
 * Describes a relation between a machine and a memory device
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MachineMemoryDeviceRelator {

    public static class MachineMemoryDeviceRelatorWrapper extends Wrapper<MachineMemoryDeviceRelator>{}

    /**
     * A GroupMemoryDeviceRelator entity for the device in the relation
     */
    @JsonProperty("groupDeviceRelator")
    private GroupMemoryDeviceRelator _groupDeviceRelator = null;

    public GroupMemoryDeviceRelator getGroupDeviceRelator() {
        return _groupDeviceRelator;
    }

    public MachineMemoryDeviceRelator setGroupDeviceRelator(GroupMemoryDeviceRelator value) {
        _groupDeviceRelator = value;
        return this;
    }

    /**
     * Machine entity for the machine in the relation
     */
    @JsonProperty("machine")
    private Machine _machine = null;

    public Machine getMachine() {
        return _machine;
    }

    public MachineMemoryDeviceRelator setMachine(Machine value) {
        _machine = value;
        return this;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public MachineMemoryDeviceRelator() {
        _groupDeviceRelator = new GroupMemoryDeviceRelator();
        _machine = new Machine();
    }

    /**
     * Parameterized Constructor
     */
    protected MachineMemoryDeviceRelator(GroupMemoryDeviceRelator groupDeviceRelator,
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

        private GroupMemoryDeviceRelator _groupDeviceRelator = null;
        private Machine _machine = null;

        public Builder setGroupDeviceRelator(GroupMemoryDeviceRelator value) { _groupDeviceRelator = value; return this; }
        public Builder setMachine(Machine value) { _machine = value; return this; }

        public MachineMemoryDeviceRelator build() {
            if (_groupDeviceRelator == null) {
                throw new RuntimeException("setGroupDeviceRelator() was not invoked in Builder for class MachineMemoryDeviceRelator");
            }
            if (_machine == null) {
                throw new RuntimeException("setMachine() was not invoked in Builder for class MachineMemoryDeviceRelator");
            }
            return new MachineMemoryDeviceRelator(_groupDeviceRelator,
                                                  _machine);
        }
    }
}
