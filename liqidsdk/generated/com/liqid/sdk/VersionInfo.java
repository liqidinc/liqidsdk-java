// File: VersionInfo.java
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
 * VersionInfo
 * Describes the versions of the various software components which comprise the Director
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VersionInfo {

    public static class VersionInfoWrapper extends Wrapper<VersionInfo>{}

    /**
     * Development branch from which this component was built
     */
    @JsonProperty("branch")
    private String _branch = null;

    public String getBranch() {
        return _branch;
    }

    public void setBranch(String value) {
        _branch = value;
    }

    /**
     * 
     */
    @JsonProperty("changeset")
    private String _changeSet = null;

    public String getChangeSet() {
        return _changeSet;
    }

    public void setChangeSet(String value) {
        _changeSet = value;
    }

    /**
     * Name of the software component
     */
    @JsonProperty("component")
    private String _component = null;

    public String getComponent() {
        return _component;
    }

    public void setComponent(String value) {
        _component = value;
    }

    /**
     * Date the component was built
     */
    @JsonProperty("date")
    private String _date = null;

    public String getDate() {
        return _date;
    }

    public void setDate(String value) {
        _date = value;
    }

    /**
     * 
     */
    @JsonProperty("changeset_short")
    private String _shortChangeSet = null;

    public String getShortChangeSet() {
        return _shortChangeSet;
    }

    public void setShortChangeSet(String value) {
        _shortChangeSet = value;
    }

    /**
     * Date the component was built
     */
    @JsonProperty("date_short")
    private String _shortDate = null;

    public String getShortDate() {
        return _shortDate;
    }

    public void setShortDate(String value) {
        _shortDate = value;
    }

    /**
     * Component version string
     */
    @JsonProperty("version")
    private String _version = null;

    public String getVersion() {
        return _version;
    }

    public void setVersion(String value) {
        _version = value;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public VersionInfo() {
    }

    /**
     * Parameterized Constructor
     */
    protected VersionInfo(String branch,
                          String changeSet,
                          String shortChangeSet,
                          String component,
                          String date,
                          String shortDate,
                          String version) {
        _branch = branch;
        _changeSet = changeSet;
        _shortChangeSet = shortChangeSet;
        _component = component;
        _date = date;
        _shortDate = shortDate;
        _version = version;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_branch:").append(getBranch());
        sb.append(", ").append("_changeSet:").append(getChangeSet());
        sb.append(", ").append("_shortChangeSet:").append(getShortChangeSet());
        sb.append(", ").append("_component:").append(getComponent());
        sb.append(", ").append("_date:").append(getDate());
        sb.append(", ").append("_shortDate:").append(getShortDate());
        sb.append(", ").append("_version:").append(getVersion());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private String _branch = null;
        private String _changeSet = null;
        private String _shortChangeSet = null;
        private String _component = null;
        private String _date = null;
        private String _shortDate = null;
        private String _version = null;

        public Builder setBranch(String value) { _branch = value; return this; }
        public Builder setChangeSet(String value) { _changeSet = value; return this; }
        public Builder setShortChangeSet(String value) { _shortChangeSet = value; return this; }
        public Builder setComponent(String value) { _component = value; return this; }
        public Builder setDate(String value) { _date = value; return this; }
        public Builder setShortDate(String value) { _shortDate = value; return this; }
        public Builder setVersion(String value) { _version = value; return this; }

        public VersionInfo build() {
            if (_branch == null) {
                throw new RuntimeException("setBranch() was not invoked in Builder for class VersionInfo");
            }
            if (_changeSet == null) {
                throw new RuntimeException("setChangeSet() was not invoked in Builder for class VersionInfo");
            }
            if (_shortChangeSet == null) {
                throw new RuntimeException("setShortChangeSet() was not invoked in Builder for class VersionInfo");
            }
            if (_component == null) {
                throw new RuntimeException("setComponent() was not invoked in Builder for class VersionInfo");
            }
            if (_date == null) {
                throw new RuntimeException("setDate() was not invoked in Builder for class VersionInfo");
            }
            if (_shortDate == null) {
                throw new RuntimeException("setShortDate() was not invoked in Builder for class VersionInfo");
            }
            if (_version == null) {
                throw new RuntimeException("setVersion() was not invoked in Builder for class VersionInfo");
            }
            return new VersionInfo(_branch,
                                   _changeSet,
                                   _shortChangeSet,
                                   _component,
                                   _date,
                                   _shortDate,
                                   _version);
        }
    }
}
