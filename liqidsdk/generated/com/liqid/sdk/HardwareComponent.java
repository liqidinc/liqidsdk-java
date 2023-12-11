// File: HardwareComponent.java
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
 * HardwareComponent
 * Describes the hardware details of a component
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HardwareComponent {

    public static class HardwareComponentWrapper extends Wrapper<HardwareComponent>{}

    /**
     * Running count of bad DLLPs for this component
     */
    @JsonProperty("bad_dllp")
    private Integer _badDLLPCount = null;

    public Integer getBadDLLPCount() {
        return _badDLLPCount;
    }

    public void setBadDLLPCount(Integer value) {
        _badDLLPCount = value;
    }

    /**
     * Running count of bad TLPs for this component
     */
    @JsonProperty("bad_tlp")
    private Integer _badTLPCount = null;

    public Integer getBadTLPCount() {
        return _badTLPCount;
    }

    public void setBadTLPCount(Integer value) {
        _badTLPCount = value;
    }

    /**
     * Running count of outgoing bytes
     */
    @JsonProperty("ebytes")
    private Integer _egressBytes = null;

    public Integer getEgressBytes() {
        return _egressBytes;
    }

    public void setEgressBytes(Integer value) {
        _egressBytes = value;
    }

    /**
     * Running count of all errors for this component
     */
    @JsonProperty("errs")
    private Integer _errorCount = null;

    public Integer getErrorCount() {
        return _errorCount;
    }

    public void setErrorCount(Integer value) {
        _errorCount = value;
    }

    /**
     * Fabric-unique global identifier to which this entity is connected.
     */
    @JsonProperty("fabr_gid")
    private String _fabricGlobalId = null;

    public Integer getFabricGlobalId() {
        return LiqidClientBase.hexStringToInteger(_fabricGlobalId);
    }

    public void setFabricGlobalId(Integer value) {
        _fabricGlobalId = String.format("0x%08x", value);
    }

    /**
     * Fabric identifier of the fabric to which this entity is connected.
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
     * Hardware flags displayed as a hex string prefixed by 'ox'
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
     * Hardware type
     */
    @JsonProperty("type")
    private String _hardwareType = null;

    public String getHardwareType() {
        return _hardwareType;
    }

    public void setHardwareType(String value) {
        _hardwareType = value;
    }

    /**
     * Running count of incoming bytes
     */
    @JsonProperty("ibytes")
    private Integer _ingressBytes = null;

    public Integer getIngressBytes() {
        return _ingressBytes;
    }

    public void setIngressBytes(Integer value) {
        _ingressBytes = value;
    }

    /**
     * Liqid coordinates for this component
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
     * Hardware name
     */
    @JsonProperty("name")
    private String _name = null;

    public String getName() {
        return _name;
    }

    public void setName(String value) {
        _name = value;
    }

    /**
     * Liqid coordinates for the component directly above this in the containing fabric topology
     */
    @JsonProperty("owner")
    private Coordinates _owner = null;

    public Coordinates getOwner() {
        return _owner;
    }

    public void setOwner(Coordinates value) {
        _owner = value;
    }

    /**
     * Vendor-unique device identifier
     */
    @JsonProperty("did")
    private String _pciDeviceId = null;

    public Integer getPCIDeviceId() {
        return LiqidClientBase.hexStringToInteger(_pciDeviceId);
    }

    public void setPCIDeviceId(Integer value) {
        _pciDeviceId = String.format("0x%04x", value);
    }

    /**
     * Number of PCI lanes for this component
     */
    @JsonProperty("lanes")
    private Integer _pciLaneCount = null;

    public Integer getPCILaneCount() {
        return _pciLaneCount;
    }

    public void setPCILaneCount(Integer value) {
        _pciLaneCount = value;
    }

    /**
     * Unique Vendor identifier
     */
    @JsonProperty("vid")
    private String _pciVendorId = null;

    public Integer getPCIVendorId() {
        return LiqidClientBase.hexStringToInteger(_pciVendorId);
    }

    public void setPCIVendorId(Integer value) {
        _pciVendorId = String.format("0x%04x", value);
    }

    /**
     * Number of ports for this component
     */
    @JsonProperty("port_cnt")
    private Integer _portCount = null;

    public Integer getPortCount() {
        return _portCount;
    }

    public void setPortCount(Integer value) {
        _portCount = value;
    }

    /**
     * Descriptions of the component ports
     */
    @JsonProperty("ports")
    private LinkedList<Port> _ports = null;

    public LinkedList<Port> getPorts() {
        return _ports;
    }

    public void setPorts(LinkedList<Port> value) {
        _ports = value;
    }

    /**
     * Running count of errors received for this component
     */
    @JsonProperty("rcv_errs")
    private Integer _receiverErrorCount = null;

    public Integer getReceiverErrorCount() {
        return _receiverErrorCount;
    }

    public void setReceiverErrorCount(Integer value) {
        _receiverErrorCount = value;
    }

    /**
     * Hardware revision string
     */
    @JsonProperty("rev")
    private String _revision = null;

    public String getRevision() {
        return _revision;
    }

    public void setRevision(String value) {
        _revision = value;
    }

    /**
     * Hardware state
     */
    @JsonProperty("state")
    private String _state = null;

    public String getState() {
        return _state;
    }

    public void setState(String value) {
        _state = value;
    }

    /**
     * TODO
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
    public HardwareComponent() {
        _ports = new LinkedList<Port>();
        _location = new Coordinates();
        _owner = new Coordinates();
    }

    /**
     * Parameterized Constructor
     */
    protected HardwareComponent(String hardwareType,
                                String name,
                                String flags,
                                String state,
                                Integer fabricId,
                                String fabricGlobalId,
                                String switchGlobalId,
                                String pciVendorId,
                                String pciDeviceId,
                                String revision,
                                Integer portCount,
                                LinkedList<Port> ports,
                                Integer pciLaneCount,
                                Integer receiverErrorCount,
                                Integer badTLPCount,
                                Integer badDLLPCount,
                                Integer errorCount,
                                Integer ingressBytes,
                                Integer egressBytes,
                                Coordinates location,
                                Coordinates owner) {
        _hardwareType = hardwareType;
        _name = name;
        _flags = flags;
        _state = state;
        _fabricId = fabricId;
        _fabricGlobalId = fabricGlobalId;
        _switchGlobalId = switchGlobalId;
        _pciVendorId = pciVendorId;
        _pciDeviceId = pciDeviceId;
        _revision = revision;
        _portCount = portCount;
        _ports = ports;
        _pciLaneCount = pciLaneCount;
        _receiverErrorCount = receiverErrorCount;
        _badTLPCount = badTLPCount;
        _badDLLPCount = badDLLPCount;
        _errorCount = errorCount;
        _ingressBytes = ingressBytes;
        _egressBytes = egressBytes;
        _location = location;
        _owner = owner;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_hardwareType:").append(getHardwareType());
        sb.append(", ").append("_name:").append(getName());
        sb.append(", ").append("_flags:").append(getFlags());
        sb.append(", ").append("_state:").append(getState());
        sb.append(", ").append("_fabricId:").append(getFabricId());
        sb.append(", ").append("_fabricGlobalId:").append(getFabricGlobalId());
        sb.append(", ").append("_switchGlobalId:").append(getSwitchGlobalId());
        sb.append(", ").append("_pciVendorId:").append(getPCIVendorId());
        sb.append(", ").append("_pciDeviceId:").append(getPCIDeviceId());
        sb.append(", ").append("_revision:").append(getRevision());
        sb.append(", ").append("_portCount:").append(getPortCount());
        sb.append(", ").append("_ports:").append(getPorts());
        sb.append(", ").append("_pciLaneCount:").append(getPCILaneCount());
        sb.append(", ").append("_receiverErrorCount:").append(getReceiverErrorCount());
        sb.append(", ").append("_badTLPCount:").append(getBadTLPCount());
        sb.append(", ").append("_badDLLPCount:").append(getBadDLLPCount());
        sb.append(", ").append("_errorCount:").append(getErrorCount());
        sb.append(", ").append("_ingressBytes:").append(getIngressBytes());
        sb.append(", ").append("_egressBytes:").append(getEgressBytes());
        sb.append(", ").append("_location:").append(getLocation());
        sb.append(", ").append("_owner:").append(getOwner());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private String _hardwareType = null;
        private String _name = null;
        private String _flags = null;
        private String _state = null;
        private Integer _fabricId = null;
        private String _fabricGlobalId = null;
        private String _switchGlobalId = null;
        private String _pciVendorId = null;
        private String _pciDeviceId = null;
        private String _revision = null;
        private Integer _portCount = null;
        private LinkedList<Port> _ports = new LinkedList<Port>();
        private Integer _pciLaneCount = null;
        private Integer _receiverErrorCount = null;
        private Integer _badTLPCount = null;
        private Integer _badDLLPCount = null;
        private Integer _errorCount = null;
        private Integer _ingressBytes = null;
        private Integer _egressBytes = null;
        private Coordinates _location = null;
        private Coordinates _owner = null;

        public Builder setHardwareType(String value) { _hardwareType = value; return this; }
        public Builder setName(String value) { _name = value; return this; }
        public Builder setFlags(String value) { _flags = value; return this; }
        public Builder setState(String value) { _state = value; return this; }
        public Builder setFabricId(Integer value) { _fabricId = value; return this; }
        public Builder setFabricGlobalId(String value) { _fabricGlobalId = value; return this; }
        public Builder setSwitchGlobalId(String value) { _switchGlobalId = value; return this; }
        public Builder setPCIVendorId(String value) { _pciVendorId = value; return this; }
        public Builder setPCIDeviceId(String value) { _pciDeviceId = value; return this; }
        public Builder setRevision(String value) { _revision = value; return this; }
        public Builder setPortCount(Integer value) { _portCount = value; return this; }
        public Builder addPorts(Port value) { _ports.add(value); return this; }
        public Builder setPCILaneCount(Integer value) { _pciLaneCount = value; return this; }
        public Builder setReceiverErrorCount(Integer value) { _receiverErrorCount = value; return this; }
        public Builder setBadTLPCount(Integer value) { _badTLPCount = value; return this; }
        public Builder setBadDLLPCount(Integer value) { _badDLLPCount = value; return this; }
        public Builder setErrorCount(Integer value) { _errorCount = value; return this; }
        public Builder setIngressBytes(Integer value) { _ingressBytes = value; return this; }
        public Builder setEgressBytes(Integer value) { _egressBytes = value; return this; }
        public Builder setLocation(Coordinates value) { _location = value; return this; }
        public Builder setOwner(Coordinates value) { _owner = value; return this; }

        public HardwareComponent build() {
            if (_hardwareType == null) {
                throw new RuntimeException("setHardwareType() was not invoked in Builder for class HardwareComponent");
            }
            if (_name == null) {
                throw new RuntimeException("setName() was not invoked in Builder for class HardwareComponent");
            }
            if (_flags == null) {
                throw new RuntimeException("setFlags() was not invoked in Builder for class HardwareComponent");
            }
            if (_state == null) {
                throw new RuntimeException("setState() was not invoked in Builder for class HardwareComponent");
            }
            if (_fabricId == null) {
                throw new RuntimeException("setFabricId() was not invoked in Builder for class HardwareComponent");
            }
            if (_fabricGlobalId == null) {
                throw new RuntimeException("setFabricGlobalId() was not invoked in Builder for class HardwareComponent");
            }
            if (_switchGlobalId == null) {
                throw new RuntimeException("setSwitchGlobalId() was not invoked in Builder for class HardwareComponent");
            }
            if (_pciVendorId == null) {
                throw new RuntimeException("setPCIVendorId() was not invoked in Builder for class HardwareComponent");
            }
            if (_pciDeviceId == null) {
                throw new RuntimeException("setPCIDeviceId() was not invoked in Builder for class HardwareComponent");
            }
            if (_revision == null) {
                throw new RuntimeException("setRevision() was not invoked in Builder for class HardwareComponent");
            }
            if (_portCount == null) {
                throw new RuntimeException("setPortCount() was not invoked in Builder for class HardwareComponent");
            }
            if (_ports.isEmpty()) {
                throw new RuntimeException("setPorts() was not invoked in Builder for class HardwareComponent");
            }
            if (_pciLaneCount == null) {
                throw new RuntimeException("setPCILaneCount() was not invoked in Builder for class HardwareComponent");
            }
            if (_receiverErrorCount == null) {
                throw new RuntimeException("setReceiverErrorCount() was not invoked in Builder for class HardwareComponent");
            }
            if (_badTLPCount == null) {
                throw new RuntimeException("setBadTLPCount() was not invoked in Builder for class HardwareComponent");
            }
            if (_badDLLPCount == null) {
                throw new RuntimeException("setBadDLLPCount() was not invoked in Builder for class HardwareComponent");
            }
            if (_errorCount == null) {
                throw new RuntimeException("setErrorCount() was not invoked in Builder for class HardwareComponent");
            }
            if (_ingressBytes == null) {
                throw new RuntimeException("setIngressBytes() was not invoked in Builder for class HardwareComponent");
            }
            if (_egressBytes == null) {
                throw new RuntimeException("setEgressBytes() was not invoked in Builder for class HardwareComponent");
            }
            if (_location == null) {
                throw new RuntimeException("setLocation() was not invoked in Builder for class HardwareComponent");
            }
            if (_owner == null) {
                throw new RuntimeException("setOwner() was not invoked in Builder for class HardwareComponent");
            }
            return new HardwareComponent(_hardwareType,
                                         _name,
                                         _flags,
                                         _state,
                                         _fabricId,
                                         _fabricGlobalId,
                                         _switchGlobalId,
                                         _pciVendorId,
                                         _pciDeviceId,
                                         _revision,
                                         _portCount,
                                         _ports,
                                         _pciLaneCount,
                                         _receiverErrorCount,
                                         _badTLPCount,
                                         _badDLLPCount,
                                         _errorCount,
                                         _ingressBytes,
                                         _egressBytes,
                                         _location,
                                         _owner);
        }
    }
}
