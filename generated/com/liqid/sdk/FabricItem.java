// File: FabricItem.java
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
 * FabricItem
 * Describes a Liqid entity, the aggregate of which comprises the fabric.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FabricItem {

    public static class FabricItemWrapper extends Wrapper<FabricItem>{}

    /**
     * Describes the particular type of this device.
     */
    @JsonProperty("deviceType")
    private DeviceType _deviceType = null;
    public DeviceType getDeviceType() { return _deviceType; }
    public void setDeviceType(DeviceType value) { _deviceType = value; }

    /**
     * Describes the hardware characteristics of this device.
     */
    @JsonProperty("hardwareComponent")
    private HardwareComponent _hardwareComponent = null;
    public HardwareComponent getHardwareComponent() { return _hardwareComponent; }
    public void setHardwareComponent(HardwareComponent value) { _hardwareComponent = value; }

    /**
     * Node identifier of this entity.
     */
    @JsonProperty("id")
    private Integer _id = null;
    public Integer getId() { return _id; }
    public void setId(Integer value) { _id = value; }

    /**
     * Name associated with this entity.
     */
    @JsonProperty("name")
    private String _name = null;
    public String getName() { return _name; }
    public void setName(String value) { _name = value; }

    /**
     * Node identifier of the entity directly above this node in the fabric hierarchy.
     */
    @JsonProperty("parentId")
    private Integer _parentId = null;
    public Integer getParentId() { return _parentId; }
    public void setParentId(Integer value) { _parentId = value; }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public FabricItem() {
        _hardwareComponent = new HardwareComponent();
    }

    /**
     * Parameterized Constructor
     */
    protected FabricItem(String name,
                         Integer id,
                         Integer parentId,
                         DeviceType deviceType,
                         HardwareComponent hardwareComponent) {
        _name = name;
        _id = id;
        _parentId = parentId;
        _deviceType = deviceType;
        _hardwareComponent = hardwareComponent;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_name:").append(getName());
        sb.append(", ").append("_id:").append(getId());
        sb.append(", ").append("_parentId:").append(getParentId());
        sb.append(", ").append("_deviceType:").append(getDeviceType());
        sb.append(", ").append("_hardwareComponent:").append(getHardwareComponent());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private String _name = null;
        private Integer _id = null;
        private Integer _parentId = null;
        private DeviceType _deviceType = null;
        private HardwareComponent _hardwareComponent = null;

        public Builder setName(String value) { _name = value; return this; }
        public Builder setId(Integer value) { _id = value; return this; }
        public Builder setParentId(Integer value) { _parentId = value; return this; }
        public Builder setDeviceType(DeviceType value) { _deviceType = value; return this; }
        public Builder setHardwareComponent(HardwareComponent value) { _hardwareComponent = value; return this; }

        public FabricItem build() {
            if (_name == null) {
                throw new RuntimeException("setName() was not invoked in Builder for class FabricItem");
            }
            if (_id == null) {
                throw new RuntimeException("setId() was not invoked in Builder for class FabricItem");
            }
            if (_parentId == null) {
                throw new RuntimeException("setParentId() was not invoked in Builder for class FabricItem");
            }
            if (_deviceType == null) {
                throw new RuntimeException("setDeviceType() was not invoked in Builder for class FabricItem");
            }
            if (_hardwareComponent == null) {
                throw new RuntimeException("setHardwareComponent() was not invoked in Builder for class FabricItem");
            }
            return new FabricItem(_name,
                                  _id,
                                  _parentId,
                                  _deviceType,
                                  _hardwareComponent);
        }
    }
}
