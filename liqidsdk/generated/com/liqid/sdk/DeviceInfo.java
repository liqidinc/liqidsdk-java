// File: DeviceInfo.java
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
 * DeviceInfo
 * All information other than status, for a given device
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceInfo {

    public static class DeviceInfoWrapper extends Wrapper<DeviceInfo>{}

    /**
     * Bus Generation
     */
    @JsonProperty("busgen")
    private String _busGeneration = null;

    public String getBusGeneration() {
        return _busGeneration;
    }

    public DeviceInfo setBusGeneration(String value) {
        _busGeneration = value;
        return this;
    }

    /**
     * Bus Width
     */
    @JsonProperty("buswidth")
    private String _busWidth = null;

    public String getBusWidth() {
        return _busWidth;
    }

    public DeviceInfo setBusWidth(String value) {
        _busWidth = value;
        return this;
    }

    /**
     * Connection Type
     */
    @JsonProperty("conn_type")
    private String _connectionType = null;

    public String getConnectionType() {
        return _connectionType;
    }

    public DeviceInfo setConnectionType(String value) {
        _connectionType = value;
        return this;
    }

    /**
     * Device class
     */
    @JsonProperty("class")
    private String _deviceClass = null;

    public String getDeviceClass() {
        return _deviceClass;
    }

    public DeviceInfo setDeviceClass(String value) {
        _deviceClass = value;
        return this;
    }

    /**
     * Device Identifier
     */
    @JsonProperty("dev_id")
    private String _deviceIdentifier = null;

    public Integer getDeviceIdentifier() {
        return LiqidClientBase.hexStringToInteger(_deviceIdentifier);
    }

    public DeviceInfo setDeviceIdentifier(Integer value) {
        _deviceIdentifier = String.format("0x%08x", value);
        return this;
    }

    /**
     * Device Type
     */
    @JsonProperty("device_type")
    private DeviceType _deviceInfoType = null;

    public DeviceType getDeviceInfoType() {
        return _deviceInfoType;
    }

    public DeviceInfo setDeviceInfoType(DeviceType value) {
        _deviceInfoType = value;
        return this;
    }

    /**
     * Device State
     */
    @JsonProperty("device_state")
    private String _deviceState = null;

    public String getDeviceState() {
        return _deviceState;
    }

    public DeviceInfo setDeviceState(String value) {
        _deviceState = value;
        return this;
    }

    /**
     * Fabric global identifier
     */
    @JsonProperty("fabr_gid")
    private String _fabricGlobalId = null;

    public Integer getFabricGlobalId() {
        return LiqidClientBase.hexStringToInteger(_fabricGlobalId);
    }

    public DeviceInfo setFabricGlobalId(Integer value) {
        _fabricGlobalId = String.format("0x%08x", value);
        return this;
    }

    /**
     * Fabric Type
     */
    @JsonProperty("fabric_type")
    private FabricType _fabricType = null;

    public FabricType getFabricType() {
        return _fabricType;
    }

    public DeviceInfo setFabricType(FabricType value) {
        _fabricType = value;
        return this;
    }

    /**
     * Family
     */
    @JsonProperty("family")
    private String _family = null;

    public String getFamily() {
        return _family;
    }

    public DeviceInfo setFamily(String value) {
        _family = value;
        return this;
    }

    /**
     * Device firmware revision
     */
    @JsonProperty("fw_rev")
    private String _firmwareRevision = null;

    public String getFirmwareRevision() {
        return _firmwareRevision;
    }

    public DeviceInfo setFirmwareRevision(String value) {
        _firmwareRevision = value;
        return this;
    }

    /**
     * Flags
     */
    @JsonProperty("flags")
    private String _flags = null;

    public Long getFlags() {
        return LiqidClientBase.hexStringToLong(_flags);
    }

    public DeviceInfo setFlags(Long value) {
        _flags = String.format("0x%016x", value);
        return this;
    }

    /**
     * Internal index of this device
     */
    @JsonProperty("index")
    private Integer _index = null;

    public Integer getIndex() {
        return _index;
    }

    public DeviceInfo setIndex(Integer value) {
        _index = value;
        return this;
    }

    /**
     * Liqid Coordinates for this device
     */
    @JsonProperty("location")
    private Coordinates _location = null;

    public Coordinates getLocation() {
        return _location;
    }

    public DeviceInfo setLocation(Coordinates value) {
        _location = value;
        return this;
    }

    /**
     * Model of this device
     */
    @JsonProperty("model")
    private String _model = null;

    public String getModel() {
        return _model;
    }

    public DeviceInfo setModel(String value) {
        _model = value;
        return this;
    }

    /**
     * Device name
     */
    @JsonProperty("name")
    private String _name = null;

    public String getName() {
        return _name;
    }

    public DeviceInfo setName(String value) {
        _name = value;
        return this;
    }

    /**
     * Liqid Coordinates of the device above this one in the hierarchy
     */
    @JsonProperty("owner")
    private Coordinates _owner = null;

    public Coordinates getOwner() {
        return _owner;
    }

    public DeviceInfo setOwner(Coordinates value) {
        _owner = value;
        return this;
    }

    /**
     * Device name
     */
    @JsonProperty("part_num")
    private String _partNumber = null;

    public String getPartNumber() {
        return _partNumber;
    }

    public DeviceInfo setPartNumber(String value) {
        _partNumber = value;
        return this;
    }

    /**
     * Vendor-unique device identifier expressed in hex as a '0x'-prefixed string
     */
    @JsonProperty("pci_device")
    private String _pciDeviceId = null;

    public String getPCIDeviceId() {
        return _pciDeviceId;
    }

    public DeviceInfo setPCIDeviceId(String value) {
        _pciDeviceId = value;
        return this;
    }

    /**
     * PCI Vendor identifier expressed in hex as a '0x'-prefixed string
     */
    @JsonProperty("pci_vendor")
    private String _pciVendorId = null;

    public String getPCIVendorId() {
        return _pciVendorId;
    }

    public DeviceInfo setPCIVendorId(String value) {
        _pciVendorId = value;
        return this;
    }

    /**
     * Obsolete value - should always be -1
     */
    @JsonProperty("pod_id")
    private Integer _podId = -1;

    public Integer getPodId() {
        return _podId;
    }

    public DeviceInfo setPodId(Integer value) {
        _podId = value;
        return this;
    }

    /**
     * Device serial number
     */
    @JsonProperty("serial_num")
    private String _serialNumber = null;

    public String getSerialNumber() {
        return _serialNumber;
    }

    public DeviceInfo setSerialNumber(String value) {
        _serialNumber = value;
        return this;
    }

    /**
     * Obsolete
     */
    @JsonProperty("sled_id")
    private String _sledId = null;

    public Integer getSledId() {
        return LiqidClientBase.hexStringToInteger(_sledId);
    }

    public DeviceInfo setSledId(Integer value) {
        _sledId = String.format("0x%08x", value);
        return this;
    }

    /**
     * Device-specific information
     */
    @JsonProperty("unique")
    private String _unique = null;

    public String getUnique() {
        return _unique;
    }

    public DeviceInfo setUnique(String value) {
        _unique = value;
        return this;
    }

    /**
     * User-supplied description
     */
    @JsonProperty("udesc")
    private String _userDescription = null;

    public String getUserDescription() {
        return _userDescription;
    }

    public DeviceInfo setUserDescription(String value) {
        _userDescription = value;
        return this;
    }

    /**
     * Vendor name
     */
    @JsonProperty("vendor")
    private String _vendor = null;

    public String getVendor() {
        return _vendor;
    }

    public DeviceInfo setVendor(String value) {
        _vendor = value;
        return this;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public DeviceInfo() {
        _location = new Coordinates();
        _owner = new Coordinates();
    }

    /**
     * Parameterized Constructor
     */
    protected DeviceInfo(String busGeneration,
                         String busWidth,
                         String deviceClass,
                         String connectionType,
                         String deviceIdentifier,
                         String deviceState,
                         DeviceType deviceInfoType,
                         String fabricGlobalId,
                         FabricType fabricType,
                         String family,
                         String flags,
                         String firmwareRevision,
                         Integer index,
                         Coordinates location,
                         String model,
                         String name,
                         Coordinates owner,
                         String partNumber,
                         String pciDeviceId,
                         String pciVendorId,
                         Integer podId,
                         String serialNumber,
                         String sledId,
                         String userDescription,
                         String unique,
                         String vendor) {
        _busGeneration = busGeneration;
        _busWidth = busWidth;
        _deviceClass = deviceClass;
        _connectionType = connectionType;
        _deviceIdentifier = deviceIdentifier;
        _deviceState = deviceState;
        _deviceInfoType = deviceInfoType;
        _fabricGlobalId = fabricGlobalId;
        _fabricType = fabricType;
        _family = family;
        _flags = flags;
        _firmwareRevision = firmwareRevision;
        _index = index;
        _location = location;
        _model = model;
        _name = name;
        _owner = owner;
        _partNumber = partNumber;
        _pciDeviceId = pciDeviceId;
        _pciVendorId = pciVendorId;
        _podId = podId;
        _serialNumber = serialNumber;
        _sledId = sledId;
        _userDescription = userDescription;
        _unique = unique;
        _vendor = vendor;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_busGeneration:").append(getBusGeneration());
        sb.append(", ").append("_busWidth:").append(getBusWidth());
        sb.append(", ").append("_deviceClass:").append(getDeviceClass());
        sb.append(", ").append("_connectionType:").append(getConnectionType());
        sb.append(", ").append("_deviceIdentifier:").append(getDeviceIdentifier());
        sb.append(", ").append("_deviceState:").append(getDeviceState());
        sb.append(", ").append("_deviceInfoType:").append(getDeviceInfoType());
        sb.append(", ").append("_fabricGlobalId:").append(getFabricGlobalId());
        sb.append(", ").append("_fabricType:").append(getFabricType());
        sb.append(", ").append("_family:").append(getFamily());
        sb.append(", ").append("_flags:").append(getFlags());
        sb.append(", ").append("_firmwareRevision:").append(getFirmwareRevision());
        sb.append(", ").append("_index:").append(getIndex());
        sb.append(", ").append("_location:").append(getLocation());
        sb.append(", ").append("_model:").append(getModel());
        sb.append(", ").append("_name:").append(getName());
        sb.append(", ").append("_owner:").append(getOwner());
        sb.append(", ").append("_partNumber:").append(getPartNumber());
        sb.append(", ").append("_pciDeviceId:").append(getPCIDeviceId());
        sb.append(", ").append("_pciVendorId:").append(getPCIVendorId());
        sb.append(", ").append("_podId:").append(getPodId());
        sb.append(", ").append("_serialNumber:").append(getSerialNumber());
        sb.append(", ").append("_sledId:").append(getSledId());
        sb.append(", ").append("_userDescription:").append(getUserDescription());
        sb.append(", ").append("_unique:").append(getUnique());
        sb.append(", ").append("_vendor:").append(getVendor());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private String _busGeneration = null;
        private String _busWidth = null;
        private String _deviceClass = null;
        private String _connectionType = null;
        private String _deviceIdentifier = null;
        private String _deviceState = null;
        private DeviceType _deviceInfoType = null;
        private String _fabricGlobalId = null;
        private FabricType _fabricType = null;
        private String _family = null;
        private String _flags = null;
        private String _firmwareRevision = null;
        private Integer _index = null;
        private Coordinates _location = null;
        private String _model = null;
        private String _name = null;
        private Coordinates _owner = null;
        private String _partNumber = null;
        private String _pciDeviceId = null;
        private String _pciVendorId = null;
        private Integer _podId = -1;
        private String _serialNumber = null;
        private String _sledId = null;
        private String _userDescription = null;
        private String _unique = null;
        private String _vendor = null;

        public Builder setBusGeneration(String value) { _busGeneration = value; return this; }
        public Builder setBusWidth(String value) { _busWidth = value; return this; }
        public Builder setDeviceClass(String value) { _deviceClass = value; return this; }
        public Builder setConnectionType(String value) { _connectionType = value; return this; }
        public Builder setDeviceIdentifier(String value) { _deviceIdentifier = value; return this; }
        public Builder setDeviceState(String value) { _deviceState = value; return this; }
        public Builder setDeviceInfoType(DeviceType value) { _deviceInfoType = value; return this; }
        public Builder setFabricGlobalId(String value) { _fabricGlobalId = value; return this; }
        public Builder setFabricType(FabricType value) { _fabricType = value; return this; }
        public Builder setFamily(String value) { _family = value; return this; }
        public Builder setFlags(String value) { _flags = value; return this; }
        public Builder setFirmwareRevision(String value) { _firmwareRevision = value; return this; }
        public Builder setIndex(Integer value) { _index = value; return this; }
        public Builder setLocation(Coordinates value) { _location = value; return this; }
        public Builder setModel(String value) { _model = value; return this; }
        public Builder setName(String value) { _name = value; return this; }
        public Builder setOwner(Coordinates value) { _owner = value; return this; }
        public Builder setPartNumber(String value) { _partNumber = value; return this; }
        public Builder setPCIDeviceId(String value) { _pciDeviceId = value; return this; }
        public Builder setPCIVendorId(String value) { _pciVendorId = value; return this; }
        public Builder setPodId(Integer value) { _podId = value; return this; }
        public Builder setSerialNumber(String value) { _serialNumber = value; return this; }
        public Builder setSledId(String value) { _sledId = value; return this; }
        public Builder setUserDescription(String value) { _userDescription = value; return this; }
        public Builder setUnique(String value) { _unique = value; return this; }
        public Builder setVendor(String value) { _vendor = value; return this; }

        public DeviceInfo build() {
            if (_busGeneration == null) {
                throw new RuntimeException("setBusGeneration() was not invoked in Builder for class DeviceInfo");
            }
            if (_busWidth == null) {
                throw new RuntimeException("setBusWidth() was not invoked in Builder for class DeviceInfo");
            }
            if (_deviceClass == null) {
                throw new RuntimeException("setDeviceClass() was not invoked in Builder for class DeviceInfo");
            }
            if (_connectionType == null) {
                throw new RuntimeException("setConnectionType() was not invoked in Builder for class DeviceInfo");
            }
            if (_deviceIdentifier == null) {
                throw new RuntimeException("setDeviceIdentifier() was not invoked in Builder for class DeviceInfo");
            }
            if (_deviceState == null) {
                throw new RuntimeException("setDeviceState() was not invoked in Builder for class DeviceInfo");
            }
            if (_deviceInfoType == null) {
                throw new RuntimeException("setDeviceInfoType() was not invoked in Builder for class DeviceInfo");
            }
            if (_fabricGlobalId == null) {
                throw new RuntimeException("setFabricGlobalId() was not invoked in Builder for class DeviceInfo");
            }
            if (_fabricType == null) {
                throw new RuntimeException("setFabricType() was not invoked in Builder for class DeviceInfo");
            }
            if (_family == null) {
                throw new RuntimeException("setFamily() was not invoked in Builder for class DeviceInfo");
            }
            if (_flags == null) {
                throw new RuntimeException("setFlags() was not invoked in Builder for class DeviceInfo");
            }
            if (_firmwareRevision == null) {
                throw new RuntimeException("setFirmwareRevision() was not invoked in Builder for class DeviceInfo");
            }
            if (_index == null) {
                throw new RuntimeException("setIndex() was not invoked in Builder for class DeviceInfo");
            }
            if (_location == null) {
                throw new RuntimeException("setLocation() was not invoked in Builder for class DeviceInfo");
            }
            if (_model == null) {
                throw new RuntimeException("setModel() was not invoked in Builder for class DeviceInfo");
            }
            if (_name == null) {
                throw new RuntimeException("setName() was not invoked in Builder for class DeviceInfo");
            }
            if (_owner == null) {
                throw new RuntimeException("setOwner() was not invoked in Builder for class DeviceInfo");
            }
            if (_partNumber == null) {
                throw new RuntimeException("setPartNumber() was not invoked in Builder for class DeviceInfo");
            }
            if (_pciDeviceId == null) {
                throw new RuntimeException("setPCIDeviceId() was not invoked in Builder for class DeviceInfo");
            }
            if (_pciVendorId == null) {
                throw new RuntimeException("setPCIVendorId() was not invoked in Builder for class DeviceInfo");
            }
            if (_serialNumber == null) {
                throw new RuntimeException("setSerialNumber() was not invoked in Builder for class DeviceInfo");
            }
            if (_sledId == null) {
                throw new RuntimeException("setSledId() was not invoked in Builder for class DeviceInfo");
            }
            if (_userDescription == null) {
                throw new RuntimeException("setUserDescription() was not invoked in Builder for class DeviceInfo");
            }
            if (_unique == null) {
                throw new RuntimeException("setUnique() was not invoked in Builder for class DeviceInfo");
            }
            if (_vendor == null) {
                throw new RuntimeException("setVendor() was not invoked in Builder for class DeviceInfo");
            }
            return new DeviceInfo(_busGeneration,
                                  _busWidth,
                                  _deviceClass,
                                  _connectionType,
                                  _deviceIdentifier,
                                  _deviceState,
                                  _deviceInfoType,
                                  _fabricGlobalId,
                                  _fabricType,
                                  _family,
                                  _flags,
                                  _firmwareRevision,
                                  _index,
                                  _location,
                                  _model,
                                  _name,
                                  _owner,
                                  _partNumber,
                                  _pciDeviceId,
                                  _pciVendorId,
                                  _podId,
                                  _serialNumber,
                                  _sledId,
                                  _userDescription,
                                  _unique,
                                  _vendor);
        }
    }
}
