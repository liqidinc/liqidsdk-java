// File: MachineGPUDeviceRelator.java
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
 * MachineGPUDeviceRelator
 * Describes a relation between a machine and a GPU device
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MachineGPUDeviceRelator {

    public static class MachineGPUDeviceRelatorWrapper extends Wrapper<MachineGPUDeviceRelator>{}

    /**
     * A GroupGPUDeviceRelator entity for the device in the relation
     */
    @JsonProperty("groupDeviceRelator")
    private GroupGPUDeviceRelator _groupDeviceRelator = null;

    public GroupGPUDeviceRelator getGroupDeviceRelator() {
        return _groupDeviceRelator;
    }

    public MachineGPUDeviceRelator setGroupDeviceRelator(GroupGPUDeviceRelator value) {
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

    public MachineGPUDeviceRelator setMachine(Machine value) {
        _machine = value;
        return this;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public MachineGPUDeviceRelator() {
        _groupDeviceRelator = new GroupGPUDeviceRelator();
        _machine = new Machine();
    }

    /**
     * Parameterized Constructor
     */
    protected MachineGPUDeviceRelator(GroupGPUDeviceRelator groupDeviceRelator,
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

        private GroupGPUDeviceRelator _groupDeviceRelator = null;
        private Machine _machine = null;

        public Builder setGroupDeviceRelator(GroupGPUDeviceRelator value) { _groupDeviceRelator = value; return this; }
        public Builder setMachine(Machine value) { _machine = value; return this; }

        public MachineGPUDeviceRelator build() {
            if (_groupDeviceRelator == null) {
                throw new RuntimeException("setGroupDeviceRelator() was not invoked in Builder for class MachineGPUDeviceRelator");
            }
            if (_machine == null) {
                throw new RuntimeException("setMachine() was not invoked in Builder for class MachineGPUDeviceRelator");
            }
            return new MachineGPUDeviceRelator(_groupDeviceRelator,
                                               _machine);
        }
    }
}
