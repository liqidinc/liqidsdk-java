// File: GroupPool.java
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
 * GroupPool
 * Describes a group pool
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupPool {

    public static class GroupPoolWrapper extends Wrapper<GroupPool>{}

    /**
     * Liqid coordinates for the switch which controls this group pool
     */
    @JsonProperty("coordinates")
    private Coordinates _coordinates = null;

    public Coordinates getCoordinates() {
        return _coordinates;
    }

    public void setCoordinates(Coordinates value) {
        _coordinates = value;
    }

    /**
     * Fabric identifier for the fabric which contains the group
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
     * Unique identifier of the group
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
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public GroupPool() {
        _coordinates = new Coordinates();
    }

    /**
     * Parameterized Constructor
     */
    protected GroupPool(Coordinates coordinates,
                        Integer fabricId,
                        Integer groupId) {
        _coordinates = coordinates;
        _fabricId = fabricId;
        _groupId = groupId;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_coordinates:").append(getCoordinates());
        sb.append(", ").append("_fabricId:").append(getFabricId());
        sb.append(", ").append("_groupId:").append(getGroupId());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private Coordinates _coordinates = null;
        private Integer _fabricId = null;
        private Integer _groupId = null;

        public Builder setCoordinates(Coordinates value) { _coordinates = value; return this; }
        public Builder setFabricId(Integer value) { _fabricId = value; return this; }
        public Builder setGroupId(Integer value) { _groupId = value; return this; }

        public GroupPool build() {
            if (_coordinates == null) {
                throw new RuntimeException("setCoordinates() was not invoked in Builder for class GroupPool");
            }
            if (_fabricId == null) {
                throw new RuntimeException("setFabricId() was not invoked in Builder for class GroupPool");
            }
            if (_groupId == null) {
                throw new RuntimeException("setGroupId() was not invoked in Builder for class GroupPool");
            }
            return new GroupPool(_coordinates,
                                 _fabricId,
                                 _groupId);
        }
    }
}
