// File: Group.java
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
 * Group
 * Describes a configured group which contains a free device pool and some number of configured machines.
 * This struct does not contain information regarding the related entities; that information must be obtained via other functions/methods.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Group {

    public static class GroupWrapper extends Wrapper<Group>{}

    /**
     * The identifier of the fabric to which this group belongs
     */
    @JsonProperty("fabr_id")
    private Integer _fabricId = null;
    public Integer getFabricId() { return _fabricId; }
    public void setFabricId(Integer value) { _fabricId = value; }

    /**
     * The unique (among the contained fabric) identifier of this group
     */
    @JsonProperty("grp_id")
    private Integer _groupId = null;
    public Integer getGroupId() { return _groupId; }
    public void setGroupId(Integer value) { _groupId = value; }

    /**
     * The unique (among the contained fabric) name of this group
     */
    @JsonProperty("group_name")
    private String _groupName = null;
    public String getGroupName() { return _groupName; }
    public void setGroupName(String value) { _groupName = value; }

    /**
     * Obsolete - should always be -1
     */
    @JsonProperty("pod_id")
    private Integer _podId = -1;
    public Integer getPodId() { return _podId; }
    public void setPodId(Integer value) { _podId = value; }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public Group() {
    }

    /**
     * Parameterized Constructor
     */
    protected Group(Integer fabricId,
                    Integer groupId,
                    String groupName,
                    Integer podId) {
        _fabricId = fabricId;
        _groupId = groupId;
        _groupName = groupName;
        _podId = podId;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_fabricId:").append(getFabricId());
        sb.append(", ").append("_groupId:").append(getGroupId());
        sb.append(", ").append("_groupName:").append(getGroupName());
        sb.append(", ").append("_podId:").append(getPodId());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private Integer _fabricId = null;
        private Integer _groupId = null;
        private String _groupName = null;
        private Integer _podId = -1;

        public Builder setFabricId(Integer value) { _fabricId = value; return this; }
        public Builder setGroupId(Integer value) { _groupId = value; return this; }
        public Builder setGroupName(String value) { _groupName = value; return this; }
        public Builder setPodId(Integer value) { _podId = value; return this; }

        public Group build() {
            if (_fabricId == null) {
                throw new RuntimeException("setFabricId() was not invoked in Builder for class Group");
            }
            if (_groupId == null) {
                throw new RuntimeException("setGroupId() was not invoked in Builder for class Group");
            }
            if (_groupName == null) {
                throw new RuntimeException("setGroupName() was not invoked in Builder for class Group");
            }
            return new Group(_fabricId,
                             _groupId,
                             _groupName,
                             _podId);
        }
    }
}
