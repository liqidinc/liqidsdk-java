// File: Machine.java
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
import java.util.LinkedList;

/**
 * Machine
 * Describes a configured machine
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Machine {

    public static class MachineWrapper extends Wrapper<Machine>{}

    /**
     * Name of the compute device associated with this machine
     */
    @JsonProperty("comp_name")
    private String _computeName = null;

    public String getComputeName() {
        return _computeName;
    }

    public void setComputeName(String value) {
        _computeName = value;
    }

    /**
     * Connection history for this machine
     * Expressed as an array of ConnectionHistory entities
     */
    @JsonProperty("connection_history")
    private LinkedList<ConnectionHistory> _connectionHistory = null;

    public LinkedList<ConnectionHistory> getConnectionHistory() {
        return _connectionHistory;
    }

    public void setConnectionHistory(LinkedList<ConnectionHistory> value) {
        _connectionHistory = value;
    }

    /**
     * Date and time that this machine was created
     */
    @JsonProperty("mtime")
    private Long _createdTimestamp = null;

    public Long getCreatedTimestamp() {
        return _createdTimestamp;
    }

    public void setCreatedTimestamp(Long value) {
        _createdTimestamp = value;
    }

    /**
     * Fabric global identifier expressed in hexadecimal
     */
    @JsonProperty("fabr_gid")
    private String _fabricGlobalId = null;

    public Integer getFabricGlobalId() {
        // check for null - jackson likes to use beans, and beans like to use
        // getters and setters, and sometimes the field is null.
        if (_fabricGlobalId == null) return null;
        if (_fabricGlobalId.equals("n/a")) {
            return 0;
        }
        return LiqidClientBase.hexStringToInteger(_fabricGlobalId);
    }

    public void setFabricGlobalId(Integer value) {
        if (value.equals(0)) {
            _fabricGlobalId = "n/a";
            return;
        }
        _fabricGlobalId = String.format("0x%08x", value);
    }

    /**
     * Unique identifier of the fabric to which this machine belongs
     */
    @JsonProperty("fabr_id")
    private Integer _fabricId = null;

    public Integer getFabricId() {
        return _fabricId;
    }

    public void setFabricId(Integer value) {
        _fabricId = value;
    }

    /**
     * Unique identifier of the group to which this machine belongs
     */
    @JsonProperty("grp_id")
    private Integer _groupId = null;

    public Integer getGroupId() {
        return _groupId;
    }

    public void setGroupId(Integer value) {
        _groupId = value;
    }

    /**
     * Internal value
     */
    @JsonProperty("index")
    private Integer _index = null;

    public Integer getIndex() {
        return _index;
    }

    public void setIndex(Integer value) {
        _index = value;
    }

    /**
     * Unique identifier for this particular machine
     */
    @JsonProperty("mach_id")
    private Integer _machineId = null;

    public Integer getMachineId() {
        return _machineId;
    }

    public void setMachineId(Integer value) {
        _machineId = value;
    }

    /**
     * Name of this machine
     */
    @JsonProperty("mach_name")
    private String _machineName = null;

    public String getMachineName() {
        return _machineName;
    }

    public void setMachineName(String value) {
        _machineName = value;
    }

    /**
     * Name of this machine
     */
    @JsonProperty("p2p")
    private P2PType _p2PEnabled = P2PType.OFF;

    public P2PType getP2PEnabled() {
        return _p2PEnabled;
    }

    public void setP2PEnabled(P2PType value) {
        _p2PEnabled = value;
    }

    /**
     * Port global identifier expressed in hexadecimal
     */
    @JsonProperty("port_gid")
    private String _portGlobalId = null;

    public Integer getPortGlobalId() {
        return LiqidClientBase.hexStringToInteger(_portGlobalId);
    }

    public void setPortGlobalId(Integer value) {
        _portGlobalId = String.format("0x%06x", value);
    }

    /**
     * Switch global identifier expressed in hexadecimal
     */
    @JsonProperty("swit_gid")
    private String _switchGlobalId = null;

    public Integer getSwitchGlobalId() {
        return LiqidClientBase.hexStringToInteger(_switchGlobalId);
    }

    public void setSwitchGlobalId(Integer value) {
        _switchGlobalId = String.format("0x%06x", value);
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public Machine() {
        _connectionHistory = new LinkedList<ConnectionHistory>();
    }

    /**
     * Parameterized Constructor
     */
    protected Machine(Integer index,
                      Integer machineId,
                      Integer groupId,
                      Integer fabricId,
                      String fabricGlobalId,
                      String portGlobalId,
                      String switchGlobalId,
                      String computeName,
                      String machineName,
                      P2PType p2pEnabled,
                      Long createdTimestamp,
                      LinkedList<ConnectionHistory> connectionHistory) {
        _index = index;
        _machineId = machineId;
        _groupId = groupId;
        _fabricId = fabricId;
        _fabricGlobalId = fabricGlobalId;
        _portGlobalId = portGlobalId;
        _switchGlobalId = switchGlobalId;
        _computeName = computeName;
        _machineName = machineName;
        _p2PEnabled = p2pEnabled;
        _createdTimestamp = createdTimestamp;
        _connectionHistory = connectionHistory;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_index:").append(getIndex());
        sb.append(", ").append("_machineId:").append(getMachineId());
        sb.append(", ").append("_groupId:").append(getGroupId());
        sb.append(", ").append("_fabricId:").append(getFabricId());
        sb.append(", ").append("_fabricGlobalId:").append(getFabricGlobalId());
        sb.append(", ").append("_portGlobalId:").append(getPortGlobalId());
        sb.append(", ").append("_switchGlobalId:").append(getSwitchGlobalId());
        sb.append(", ").append("_computeName:").append(getComputeName());
        sb.append(", ").append("_machineName:").append(getMachineName());
        sb.append(", ").append("_p2PEnabled:").append(getP2PEnabled());
        sb.append(", ").append("_createdTimestamp:").append(getCreatedTimestamp());
        sb.append(", ").append("_connectionHistory:").append(getConnectionHistory());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private Integer _index = null;
        private Integer _machineId = null;
        private Integer _groupId = null;
        private Integer _fabricId = null;
        private String _fabricGlobalId = null;
        private String _portGlobalId = null;
        private String _switchGlobalId = null;
        private String _computeName = null;
        private String _machineName = null;
        private P2PType _p2PEnabled = P2PType.OFF;
        private Long _createdTimestamp = null;
        private LinkedList<ConnectionHistory> _connectionHistory = new LinkedList<ConnectionHistory>();

        public Builder setIndex(Integer value) { _index = value; return this; }
        public Builder setMachineId(Integer value) { _machineId = value; return this; }
        public Builder setGroupId(Integer value) { _groupId = value; return this; }
        public Builder setFabricId(Integer value) { _fabricId = value; return this; }
        public Builder setFabricGlobalId(String value) { _fabricGlobalId = value; return this; }
        public Builder setPortGlobalId(String value) { _portGlobalId = value; return this; }
        public Builder setSwitchGlobalId(String value) { _switchGlobalId = value; return this; }
        public Builder setComputeName(String value) { _computeName = value; return this; }
        public Builder setMachineName(String value) { _machineName = value; return this; }
        public Builder setP2PEnabled(P2PType value) { _p2PEnabled = value; return this; }
        public Builder setCreatedTimestamp(Long value) { _createdTimestamp = value; return this; }
        public Builder addConnectionHistory(ConnectionHistory value) { _connectionHistory.add(value); return this; }

        public Machine build() {
            if (_index == null) {
                throw new RuntimeException("setIndex() was not invoked in Builder for class Machine");
            }
            if (_machineId == null) {
                throw new RuntimeException("setMachineId() was not invoked in Builder for class Machine");
            }
            if (_groupId == null) {
                throw new RuntimeException("setGroupId() was not invoked in Builder for class Machine");
            }
            if (_fabricId == null) {
                throw new RuntimeException("setFabricId() was not invoked in Builder for class Machine");
            }
            if (_fabricGlobalId == null) {
                throw new RuntimeException("setFabricGlobalId() was not invoked in Builder for class Machine");
            }
            if (_portGlobalId == null) {
                throw new RuntimeException("setPortGlobalId() was not invoked in Builder for class Machine");
            }
            if (_switchGlobalId == null) {
                throw new RuntimeException("setSwitchGlobalId() was not invoked in Builder for class Machine");
            }
            if (_computeName == null) {
                throw new RuntimeException("setComputeName() was not invoked in Builder for class Machine");
            }
            if (_machineName == null) {
                throw new RuntimeException("setMachineName() was not invoked in Builder for class Machine");
            }
            if (_p2PEnabled == null) {
                throw new RuntimeException("setP2PEnabled() was not invoked in Builder for class Machine");
            }
            if (_createdTimestamp == null) {
                throw new RuntimeException("setCreatedTimestamp() was not invoked in Builder for class Machine");
            }
            if (_connectionHistory.isEmpty()) {
                throw new RuntimeException("setConnectionHistory() was not invoked in Builder for class Machine");
            }
            return new Machine(_index,
                               _machineId,
                               _groupId,
                               _fabricId,
                               _fabricGlobalId,
                               _portGlobalId,
                               _switchGlobalId,
                               _computeName,
                               _machineName,
                               _p2PEnabled,
                               _createdTimestamp,
                               _connectionHistory);
        }
    }
}
