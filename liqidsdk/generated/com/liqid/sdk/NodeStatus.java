// File: NodeStatus.java
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
 * NodeStatus
 * Status information regarding one particular node.
 * A node should be thought of as a unique host or CPU.
 * The use of the word 'node' does not imply any association with a clustered system.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NodeStatus {

    public static class NodeStatusWrapper extends Wrapper<NodeStatus>{}

    /**
     * TODO
     */
    @JsonProperty("comps")
    private Integer _comps = null;

    public Integer getComps() {
        return _comps;
    }

    public void setComps(Integer value) {
        _comps = value;
    }

    /**
     * Configuration identifier
     */
    @JsonProperty("cid")
    private Integer _configIdentifier = null;

    public Integer getConfigIdentifier() {
        return _configIdentifier;
    }

    public void setConfigIdentifier(Integer value) {
        _configIdentifier = value;
    }

    /**
     * Configuration version
     */
    @JsonProperty("cfg_vers")
    private Integer _configVersion = null;

    public Integer getConfigVersion() {
        return _configVersion;
    }

    public void setConfigVersion(Integer value) {
        _configVersion = value;
    }

    /**
     * Current time setting of the node
     */
    @JsonProperty("currtime")
    private String _currentTime = null;

    public String getCurrentTime() {
        return _currentTime;
    }

    public void setCurrentTime(String value) {
        _currentTime = value;
    }

    /**
     * Identifier of the containing fabric
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
     * Flag settings expressed as a hex value prefixed by '0x'
     */
    @JsonProperty("flags")
    private String _flags = null;

    public Long getFlags() {
        return LiqidClientBase.hexStringToLong(_flags);
    }

    public void setFlags(Long value) {
        _flags = String.format("0x%016x", value);
    }

    /**
     * Number of links for this node
     */
    @JsonProperty("links")
    private Integer _linkCount = null;

    public Integer getLinkCount() {
        return _linkCount;
    }

    public void setLinkCount(Integer value) {
        _linkCount = value;
    }

    /**
     * Liqid coordinates of this node
     */
    @JsonProperty("location")
    private Coordinates _location = null;

    public Coordinates getLocation() {
        return _location;
    }

    public void setLocation(Coordinates value) {
        _location = value;
    }

    /**
     * Operating system which is running on the node
     */
    @JsonProperty("os_type")
    private OperatingSystemType _operatingSystem = null;

    public OperatingSystemType getOperatingSystem() {
        return _operatingSystem;
    }

    public void setOperatingSystem(OperatingSystemType value) {
        _operatingSystem = value;
    }

    /**
     * Current running state of the node
     */
    @JsonProperty("run")
    private RunType _runState = null;

    public RunType getRunState() {
        return _runState;
    }

    public void setRunState(RunType value) {
        _runState = value;
    }

    /**
     * Software version for the node
     */
    @JsonProperty("sw_vers")
    private Integer _softwareVersion = null;

    public Integer getSoftwareVersion() {
        return _softwareVersion;
    }

    public void setSoftwareVersion(Integer value) {
        _softwareVersion = value;
    }

    /**
     * Number of targets
     */
    @JsonProperty("targs")
    private Integer _targetCount = null;

    public Integer getTargetCount() {
        return _targetCount;
    }

    public void setTargetCount(Integer value) {
        _targetCount = value;
    }

    /**
     * Amount of time the system has been up
     */
    @JsonProperty("uptime")
    private String _upTime = null;

    public String getUpTime() {
        return _upTime;
    }

    public void setUpTime(String value) {
        _upTime = value;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public NodeStatus() {
        _location = new Coordinates();
    }

    /**
     * Parameterized Constructor
     */
    protected NodeStatus(Integer configVersion,
                         Integer configIdentifier,
                         Integer comps,
                         String currentTime,
                         Integer fabricId,
                         String flags,
                         Integer linkCount,
                         Coordinates location,
                         OperatingSystemType operatingSystem,
                         RunType runState,
                         Integer softwareVersion,
                         Integer targetCount,
                         String upTime) {
        _configVersion = configVersion;
        _configIdentifier = configIdentifier;
        _comps = comps;
        _currentTime = currentTime;
        _fabricId = fabricId;
        _flags = flags;
        _linkCount = linkCount;
        _location = location;
        _operatingSystem = operatingSystem;
        _runState = runState;
        _softwareVersion = softwareVersion;
        _targetCount = targetCount;
        _upTime = upTime;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_configVersion:").append(getConfigVersion());
        sb.append(", ").append("_configIdentifier:").append(getConfigIdentifier());
        sb.append(", ").append("_comps:").append(getComps());
        sb.append(", ").append("_currentTime:").append(getCurrentTime());
        sb.append(", ").append("_fabricId:").append(getFabricId());
        sb.append(", ").append("_flags:").append(getFlags());
        sb.append(", ").append("_linkCount:").append(getLinkCount());
        sb.append(", ").append("_location:").append(getLocation());
        sb.append(", ").append("_operatingSystem:").append(getOperatingSystem());
        sb.append(", ").append("_runState:").append(getRunState());
        sb.append(", ").append("_softwareVersion:").append(getSoftwareVersion());
        sb.append(", ").append("_targetCount:").append(getTargetCount());
        sb.append(", ").append("_upTime:").append(getUpTime());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private Integer _configVersion = null;
        private Integer _configIdentifier = null;
        private Integer _comps = null;
        private String _currentTime = null;
        private Integer _fabricId = null;
        private String _flags = null;
        private Integer _linkCount = null;
        private Coordinates _location = null;
        private OperatingSystemType _operatingSystem = null;
        private RunType _runState = null;
        private Integer _softwareVersion = null;
        private Integer _targetCount = null;
        private String _upTime = null;

        public Builder setConfigVersion(Integer value) { _configVersion = value; return this; }
        public Builder setConfigIdentifier(Integer value) { _configIdentifier = value; return this; }
        public Builder setComps(Integer value) { _comps = value; return this; }
        public Builder setCurrentTime(String value) { _currentTime = value; return this; }
        public Builder setFabricId(Integer value) { _fabricId = value; return this; }
        public Builder setFlags(String value) { _flags = value; return this; }
        public Builder setLinkCount(Integer value) { _linkCount = value; return this; }
        public Builder setLocation(Coordinates value) { _location = value; return this; }
        public Builder setOperatingSystem(OperatingSystemType value) { _operatingSystem = value; return this; }
        public Builder setRunState(RunType value) { _runState = value; return this; }
        public Builder setSoftwareVersion(Integer value) { _softwareVersion = value; return this; }
        public Builder setTargetCount(Integer value) { _targetCount = value; return this; }
        public Builder setUpTime(String value) { _upTime = value; return this; }

        public NodeStatus build() {
            if (_configVersion == null) {
                throw new RuntimeException("setConfigVersion() was not invoked in Builder for class NodeStatus");
            }
            if (_configIdentifier == null) {
                throw new RuntimeException("setConfigIdentifier() was not invoked in Builder for class NodeStatus");
            }
            if (_comps == null) {
                throw new RuntimeException("setComps() was not invoked in Builder for class NodeStatus");
            }
            if (_currentTime == null) {
                throw new RuntimeException("setCurrentTime() was not invoked in Builder for class NodeStatus");
            }
            if (_fabricId == null) {
                throw new RuntimeException("setFabricId() was not invoked in Builder for class NodeStatus");
            }
            if (_flags == null) {
                throw new RuntimeException("setFlags() was not invoked in Builder for class NodeStatus");
            }
            if (_linkCount == null) {
                throw new RuntimeException("setLinkCount() was not invoked in Builder for class NodeStatus");
            }
            if (_location == null) {
                throw new RuntimeException("setLocation() was not invoked in Builder for class NodeStatus");
            }
            if (_operatingSystem == null) {
                throw new RuntimeException("setOperatingSystem() was not invoked in Builder for class NodeStatus");
            }
            if (_runState == null) {
                throw new RuntimeException("setRunState() was not invoked in Builder for class NodeStatus");
            }
            if (_softwareVersion == null) {
                throw new RuntimeException("setSoftwareVersion() was not invoked in Builder for class NodeStatus");
            }
            if (_targetCount == null) {
                throw new RuntimeException("setTargetCount() was not invoked in Builder for class NodeStatus");
            }
            if (_upTime == null) {
                throw new RuntimeException("setUpTime() was not invoked in Builder for class NodeStatus");
            }
            return new NodeStatus(_configVersion,
                                  _configIdentifier,
                                  _comps,
                                  _currentTime,
                                  _fabricId,
                                  _flags,
                                  _linkCount,
                                  _location,
                                  _operatingSystem,
                                  _runState,
                                  _softwareVersion,
                                  _targetCount,
                                  _upTime);
        }
    }
}
