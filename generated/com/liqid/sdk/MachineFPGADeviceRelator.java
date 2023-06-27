// File: MachineFPGADeviceRelator.java
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
 * MachineFPGADeviceRelator
 * Describes a relation between a machine and a FPGA device
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MachineFPGADeviceRelator {

    public static class MachineFPGADeviceRelatorWrapper extends Wrapper<MachineFPGADeviceRelator>{}

    /**
     * A GroupFPGADeviceRelator entity for the device in the relation
     */
    @JsonProperty("groupDeviceRelator")
    private GroupFPGADeviceRelator _groupDeviceRelator = null;
    public GroupFPGADeviceRelator getGroupDeviceRelator() { return _groupDeviceRelator; }
    public void setGroupDeviceRelator(GroupFPGADeviceRelator value) { _groupDeviceRelator = value; }

    /**
     * Machine entity for the machine in the relation
     */
    @JsonProperty("machine")
    private Machine _machine = null;
    public Machine getMachine() { return _machine; }
    public void setMachine(Machine value) { _machine = value; }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public MachineFPGADeviceRelator() {
        _groupDeviceRelator = new GroupFPGADeviceRelator();
        _machine = new Machine();
    }

    /**
     * Parameterized Constructor
     */
    protected MachineFPGADeviceRelator(GroupFPGADeviceRelator groupDeviceRelator,
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

        private GroupFPGADeviceRelator _groupDeviceRelator = null;
        private Machine _machine = null;

        public Builder setGroupDeviceRelator(GroupFPGADeviceRelator value) { _groupDeviceRelator = value; return this; }
        public Builder setMachine(Machine value) { _machine = value; return this; }

        public MachineFPGADeviceRelator build() {
            if (_groupDeviceRelator == null) {
                throw new RuntimeException("setGroupDeviceRelator() was not invoked in Builder for class MachineFPGADeviceRelator");
            }
            if (_machine == null) {
                throw new RuntimeException("setMachine() was not invoked in Builder for class MachineFPGADeviceRelator");
            }
            return new MachineFPGADeviceRelator(_groupDeviceRelator,
                                                _machine);
        }
    }
}
