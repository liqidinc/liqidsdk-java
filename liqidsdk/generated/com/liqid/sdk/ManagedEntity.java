// File: ManagedEntity.java
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
 * ManagedEntity
 * Describes information regarding a particular vendor and model of a manageable device
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ManagedEntity {

    public static class ManagedEntityWrapper extends Wrapper<ManagedEntity>{}

    /**
     * Capacity of the entity
     */
    @JsonProperty("capacity")
    private Integer _capacity = null;

    public Integer getCapacity() {
        return _capacity;
    }

    public void setCapacity(Integer value) {
        _capacity = value;
    }

    /**
     * Known values are 'no' and 'multi'
     */
    @JsonProperty("companion_device")
    private String _companionDevice = null;

    public String getCompanionDevice() {
        return _companionDevice;
    }

    public void setCompanionDevice(String value) {
        _companionDevice = value;
    }

    /**
     * Cycle speed of cpu entity
     */
    @JsonProperty("core_mhz")
    private Integer _coreMHZ = null;

    public Integer getCoreMHZ() {
        return _coreMHZ;
    }

    public void setCoreMHZ(Integer value) {
        _coreMHZ = value;
    }

    /**
     * Name of the managed entity
     */
    @JsonProperty("description")
    private String _description = null;

    public String getDescription() {
        return _description;
    }

    public void setDescription(String value) {
        _description = value;
    }

    /**
     * Type of the device
     */
    @JsonProperty("device_type")
    private String _deviceType = null;

    public String getDeviceType() {
        return _deviceType;
    }

    public void setDeviceType(String value) {
        _deviceType = value;
    }

    /**
     * A portion of the PCI device identification string which can be used to determine device type
     */
    @JsonProperty("discovery_token")
    private String _discoveryToken = null;

    public String getDiscoveryToken() {
        return _discoveryToken;
    }

    public void setDiscoveryToken(String value) {
        _discoveryToken = value;
    }

    /**
     * Size of Dynamic RAM
     */
    @JsonProperty("dram_size")
    private Integer _dramSize = null;

    public Integer getDRAMSize() {
        return _dramSize;
    }

    public void setDRAMSize(Integer value) {
        _dramSize = value;
    }

    /**
     * Type of Dynamic RAM
     */
    @JsonProperty("dram_type")
    private String _dramType = null;

    public String getDRAMType() {
        return _dramType;
    }

    public void setDRAMType(String value) {
        _dramType = value;
    }

    /**
     * Describes the multi-variate state of this entry
     */
    @JsonProperty("entry_description")
    private ManagedEntityState _entryDescription = null;

    public ManagedEntityState getEntryDescription() {
        return _entryDescription;
    }

    public void setEntryDescription(ManagedEntityState value) {
        _entryDescription = value;
    }

    /**
     * Indicates the type of this managed entity
     */
    @JsonProperty("type")
    private String _managedEntityType = null;

    public String getManagedEntityType() {
        return _managedEntityType;
    }

    public void setManagedEntityType(String value) {
        _managedEntityType = value;
    }

    /**
     * Manufacturer/vendor name
     */
    @JsonProperty("device_manufacturer")
    private String _manufacturer = null;

    public String getManufacturer() {
        return _manufacturer;
    }

    public void setManufacturer(String value) {
        _manufacturer = value;
    }

    /**
     * Describes the model of the device
     */
    @JsonProperty("model")
    private String _model = null;

    public String getModel() {
        return _model;
    }

    public void setModel(String value) {
        _model = value;
    }

    /**
     * Number of cores for CPU entities
     */
    @JsonProperty("cores")
    private Integer _numberOfCores = null;

    public Integer getNumberOfCores() {
        return _numberOfCores;
    }

    public void setNumberOfCores(Integer value) {
        _numberOfCores = value;
    }

    /**
     * Number of threads for CPU entities
     */
    @JsonProperty("threads")
    private Integer _numberOfThreads = null;

    public Integer getNumberOfThreads() {
        return _numberOfThreads;
    }

    public void setNumberOfThreads(Integer value) {
        _numberOfThreads = value;
    }

    /**
     * Vendor-unique identity of the PCI device
     */
    @JsonProperty("did")
    private String _pciDeviceId = null;

    public String getPCIDeviceId() {
        return _pciDeviceId;
    }

    public void setPCIDeviceId(String value) {
        _pciDeviceId = value;
    }

    /**
     * Unique identity of the PCI vendor
     */
    @JsonProperty("vid")
    private String _pciVendorId = null;

    public String getPCIVendorId() {
        return _pciVendorId;
    }

    public void setPCIVendorId(String value) {
        _pciVendorId = value;
    }

    /**
     * Cycle speed of the entity
     */
    @JsonProperty("speed")
    private Integer _speed = null;

    public Integer getSpeed() {
        return _speed;
    }

    public void setSpeed(Integer value) {
        _speed = value;
    }

    /**
     * Indicates whether Single-Root IO Virtualization (SRIOV) is enabled for this entity
     */
    @JsonProperty("sriov")
    private Boolean _sriovEnabled = null;

    public Boolean getSRIOVEnabled() {
        return _sriovEnabled;
    }

    public void setSRIOVEnabled(Boolean value) {
        _sriovEnabled = value;
    }

    /**
     * TODO
     */
    @JsonProperty("unique")
    private String _unique = null;

    public String getUnique() {
        return _unique;
    }

    public void setUnique(String value) {
        _unique = value;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public ManagedEntity() {
        _entryDescription = new ManagedEntityState();
    }

    /**
     * Parameterized Constructor
     */
    protected ManagedEntity(String deviceType,
                            String description,
                            String pciVendorId,
                            String pciDeviceId,
                            String model,
                            Integer numberOfCores,
                            Integer numberOfThreads,
                            Integer speed,
                            Integer capacity,
                            Boolean sriovEnabled,
                            String managedEntityType,
                            String unique,
                            Integer coreMHZ,
                            Integer dramSize,
                            String dramType,
                            String manufacturer,
                            String discoveryToken,
                            String companionDevice,
                            ManagedEntityState entryDescription) {
        _deviceType = deviceType;
        _description = description;
        _pciVendorId = pciVendorId;
        _pciDeviceId = pciDeviceId;
        _model = model;
        _numberOfCores = numberOfCores;
        _numberOfThreads = numberOfThreads;
        _speed = speed;
        _capacity = capacity;
        _sriovEnabled = sriovEnabled;
        _managedEntityType = managedEntityType;
        _unique = unique;
        _coreMHZ = coreMHZ;
        _dramSize = dramSize;
        _dramType = dramType;
        _manufacturer = manufacturer;
        _discoveryToken = discoveryToken;
        _companionDevice = companionDevice;
        _entryDescription = entryDescription;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_deviceType:").append(getDeviceType());
        sb.append(", ").append("_description:").append(getDescription());
        sb.append(", ").append("_pciVendorId:").append(getPCIVendorId());
        sb.append(", ").append("_pciDeviceId:").append(getPCIDeviceId());
        sb.append(", ").append("_model:").append(getModel());
        sb.append(", ").append("_numberOfCores:").append(getNumberOfCores());
        sb.append(", ").append("_numberOfThreads:").append(getNumberOfThreads());
        sb.append(", ").append("_speed:").append(getSpeed());
        sb.append(", ").append("_capacity:").append(getCapacity());
        sb.append(", ").append("_sriovEnabled:").append(getSRIOVEnabled());
        sb.append(", ").append("_managedEntityType:").append(getManagedEntityType());
        sb.append(", ").append("_unique:").append(getUnique());
        sb.append(", ").append("_coreMHZ:").append(getCoreMHZ());
        sb.append(", ").append("_dramSize:").append(getDRAMSize());
        sb.append(", ").append("_dramType:").append(getDRAMType());
        sb.append(", ").append("_manufacturer:").append(getManufacturer());
        sb.append(", ").append("_discoveryToken:").append(getDiscoveryToken());
        sb.append(", ").append("_companionDevice:").append(getCompanionDevice());
        sb.append(", ").append("_entryDescription:").append(getEntryDescription());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private String _deviceType = null;
        private String _description = null;
        private String _pciVendorId = null;
        private String _pciDeviceId = null;
        private String _model = null;
        private Integer _numberOfCores = null;
        private Integer _numberOfThreads = null;
        private Integer _speed = null;
        private Integer _capacity = null;
        private Boolean _sriovEnabled = null;
        private String _managedEntityType = null;
        private String _unique = null;
        private Integer _coreMHZ = null;
        private Integer _dramSize = null;
        private String _dramType = null;
        private String _manufacturer = null;
        private String _discoveryToken = null;
        private String _companionDevice = null;
        private ManagedEntityState _entryDescription = null;

        public Builder setDeviceType(String value) { _deviceType = value; return this; }
        public Builder setDescription(String value) { _description = value; return this; }
        public Builder setPCIVendorId(String value) { _pciVendorId = value; return this; }
        public Builder setPCIDeviceId(String value) { _pciDeviceId = value; return this; }
        public Builder setModel(String value) { _model = value; return this; }
        public Builder setNumberOfCores(Integer value) { _numberOfCores = value; return this; }
        public Builder setNumberOfThreads(Integer value) { _numberOfThreads = value; return this; }
        public Builder setSpeed(Integer value) { _speed = value; return this; }
        public Builder setCapacity(Integer value) { _capacity = value; return this; }
        public Builder setSRIOVEnabled(Boolean value) { _sriovEnabled = value; return this; }
        public Builder setManagedEntityType(String value) { _managedEntityType = value; return this; }
        public Builder setUnique(String value) { _unique = value; return this; }
        public Builder setCoreMHZ(Integer value) { _coreMHZ = value; return this; }
        public Builder setDRAMSize(Integer value) { _dramSize = value; return this; }
        public Builder setDRAMType(String value) { _dramType = value; return this; }
        public Builder setManufacturer(String value) { _manufacturer = value; return this; }
        public Builder setDiscoveryToken(String value) { _discoveryToken = value; return this; }
        public Builder setCompanionDevice(String value) { _companionDevice = value; return this; }
        public Builder setEntryDescription(ManagedEntityState value) { _entryDescription = value; return this; }

        public ManagedEntity build() {
            if (_deviceType == null) {
                throw new RuntimeException("setDeviceType() was not invoked in Builder for class ManagedEntity");
            }
            if (_description == null) {
                throw new RuntimeException("setDescription() was not invoked in Builder for class ManagedEntity");
            }
            if (_pciVendorId == null) {
                throw new RuntimeException("setPCIVendorId() was not invoked in Builder for class ManagedEntity");
            }
            if (_pciDeviceId == null) {
                throw new RuntimeException("setPCIDeviceId() was not invoked in Builder for class ManagedEntity");
            }
            if (_model == null) {
                throw new RuntimeException("setModel() was not invoked in Builder for class ManagedEntity");
            }
            if (_numberOfCores == null) {
                throw new RuntimeException("setNumberOfCores() was not invoked in Builder for class ManagedEntity");
            }
            if (_numberOfThreads == null) {
                throw new RuntimeException("setNumberOfThreads() was not invoked in Builder for class ManagedEntity");
            }
            if (_speed == null) {
                throw new RuntimeException("setSpeed() was not invoked in Builder for class ManagedEntity");
            }
            if (_capacity == null) {
                throw new RuntimeException("setCapacity() was not invoked in Builder for class ManagedEntity");
            }
            if (_sriovEnabled == null) {
                throw new RuntimeException("setSRIOVEnabled() was not invoked in Builder for class ManagedEntity");
            }
            if (_managedEntityType == null) {
                throw new RuntimeException("setManagedEntityType() was not invoked in Builder for class ManagedEntity");
            }
            if (_unique == null) {
                throw new RuntimeException("setUnique() was not invoked in Builder for class ManagedEntity");
            }
            if (_coreMHZ == null) {
                throw new RuntimeException("setCoreMHZ() was not invoked in Builder for class ManagedEntity");
            }
            if (_dramSize == null) {
                throw new RuntimeException("setDRAMSize() was not invoked in Builder for class ManagedEntity");
            }
            if (_dramType == null) {
                throw new RuntimeException("setDRAMType() was not invoked in Builder for class ManagedEntity");
            }
            if (_manufacturer == null) {
                throw new RuntimeException("setManufacturer() was not invoked in Builder for class ManagedEntity");
            }
            if (_discoveryToken == null) {
                throw new RuntimeException("setDiscoveryToken() was not invoked in Builder for class ManagedEntity");
            }
            if (_companionDevice == null) {
                throw new RuntimeException("setCompanionDevice() was not invoked in Builder for class ManagedEntity");
            }
            if (_entryDescription == null) {
                throw new RuntimeException("setEntryDescription() was not invoked in Builder for class ManagedEntity");
            }
            return new ManagedEntity(_deviceType,
                                     _description,
                                     _pciVendorId,
                                     _pciDeviceId,
                                     _model,
                                     _numberOfCores,
                                     _numberOfThreads,
                                     _speed,
                                     _capacity,
                                     _sriovEnabled,
                                     _managedEntityType,
                                     _unique,
                                     _coreMHZ,
                                     _dramSize,
                                     _dramType,
                                     _manufacturer,
                                     _discoveryToken,
                                     _companionDevice,
                                     _entryDescription);
        }
    }
}
