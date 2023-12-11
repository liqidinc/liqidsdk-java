// File: Port.java
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
 * Port
 * Describes a switch port
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Port {

    public static class PortWrapper extends Wrapper<Port>{}

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
     * A PortCPU struct describing the CPU which is connected to this port
     */
    @JsonProperty("cpu")
    private PortCPU _cpu = null;

    public PortCPU getCPU() {
        return _cpu;
    }

    public void setCPU(PortCPU value) {
        _cpu = value;
    }

    /**
     * Describes this entity's current state
     */
    @JsonProperty("device_state")
    private String _deviceState = null;

    public String getDeviceState() {
        return _deviceState;
    }

    public void setDeviceState(String value) {
        _deviceState = value;
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
     * Internal index of this port
     */
    @JsonProperty("index")
    private String _index = null;

    public String getIndex() {
        return _index;
    }

    public void setIndex(String value) {
        _index = value;
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
     * Hardware type
     */
    @JsonProperty("type")
    private String _portType = null;

    public String getPortType() {
        return _portType;
    }

    public void setPortType(String value) {
        _portType = value;
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
     * Array of Switch objects describing the switches which are connected to this port
     */
    @JsonProperty("switches")
    private LinkedList<Switch> _switches = null;

    public LinkedList<Switch> getSwitches() {
        return _switches;
    }

    public void setSwitches(LinkedList<Switch> value) {
        _switches = value;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public Port() {
        _switches = new LinkedList<Switch>();
        _cpu = new PortCPU();
    }

    /**
     * Parameterized Constructor
     */
    protected Port(String portType,
                   String index,
                   String flags,
                   String state,
                   String fabricGlobalId,
                   Integer pciLaneCount,
                   Integer receiverErrorCount,
                   Integer badTLPCount,
                   Integer badDLLPCount,
                   Integer errorCount,
                   Integer ingressBytes,
                   Integer egressBytes,
                   LinkedList<Switch> switches,
                   PortCPU cpu,
                   String deviceState) {
        _portType = portType;
        _index = index;
        _flags = flags;
        _state = state;
        _fabricGlobalId = fabricGlobalId;
        _pciLaneCount = pciLaneCount;
        _receiverErrorCount = receiverErrorCount;
        _badTLPCount = badTLPCount;
        _badDLLPCount = badDLLPCount;
        _errorCount = errorCount;
        _ingressBytes = ingressBytes;
        _egressBytes = egressBytes;
        _switches = switches;
        _cpu = cpu;
        _deviceState = deviceState;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_portType:").append(getPortType());
        sb.append(", ").append("_index:").append(getIndex());
        sb.append(", ").append("_flags:").append(getFlags());
        sb.append(", ").append("_state:").append(getState());
        sb.append(", ").append("_fabricGlobalId:").append(getFabricGlobalId());
        sb.append(", ").append("_pciLaneCount:").append(getPCILaneCount());
        sb.append(", ").append("_receiverErrorCount:").append(getReceiverErrorCount());
        sb.append(", ").append("_badTLPCount:").append(getBadTLPCount());
        sb.append(", ").append("_badDLLPCount:").append(getBadDLLPCount());
        sb.append(", ").append("_errorCount:").append(getErrorCount());
        sb.append(", ").append("_ingressBytes:").append(getIngressBytes());
        sb.append(", ").append("_egressBytes:").append(getEgressBytes());
        sb.append(", ").append("_switches:").append(getSwitches());
        sb.append(", ").append("_cpu:").append(getCPU());
        sb.append(", ").append("_deviceState:").append(getDeviceState());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private String _portType = null;
        private String _index = null;
        private String _flags = null;
        private String _state = null;
        private String _fabricGlobalId = null;
        private Integer _pciLaneCount = null;
        private Integer _receiverErrorCount = null;
        private Integer _badTLPCount = null;
        private Integer _badDLLPCount = null;
        private Integer _errorCount = null;
        private Integer _ingressBytes = null;
        private Integer _egressBytes = null;
        private LinkedList<Switch> _switches = new LinkedList<Switch>();
        private PortCPU _cpu = null;
        private String _deviceState = null;

        public Builder setPortType(String value) { _portType = value; return this; }
        public Builder setIndex(String value) { _index = value; return this; }
        public Builder setFlags(String value) { _flags = value; return this; }
        public Builder setState(String value) { _state = value; return this; }
        public Builder setFabricGlobalId(String value) { _fabricGlobalId = value; return this; }
        public Builder setPCILaneCount(Integer value) { _pciLaneCount = value; return this; }
        public Builder setReceiverErrorCount(Integer value) { _receiverErrorCount = value; return this; }
        public Builder setBadTLPCount(Integer value) { _badTLPCount = value; return this; }
        public Builder setBadDLLPCount(Integer value) { _badDLLPCount = value; return this; }
        public Builder setErrorCount(Integer value) { _errorCount = value; return this; }
        public Builder setIngressBytes(Integer value) { _ingressBytes = value; return this; }
        public Builder setEgressBytes(Integer value) { _egressBytes = value; return this; }
        public Builder addSwitches(Switch value) { _switches.add(value); return this; }
        public Builder setCPU(PortCPU value) { _cpu = value; return this; }
        public Builder setDeviceState(String value) { _deviceState = value; return this; }

        public Port build() {
            if (_portType == null) {
                throw new RuntimeException("setPortType() was not invoked in Builder for class Port");
            }
            if (_index == null) {
                throw new RuntimeException("setIndex() was not invoked in Builder for class Port");
            }
            if (_flags == null) {
                throw new RuntimeException("setFlags() was not invoked in Builder for class Port");
            }
            if (_state == null) {
                throw new RuntimeException("setState() was not invoked in Builder for class Port");
            }
            if (_fabricGlobalId == null) {
                throw new RuntimeException("setFabricGlobalId() was not invoked in Builder for class Port");
            }
            if (_pciLaneCount == null) {
                throw new RuntimeException("setPCILaneCount() was not invoked in Builder for class Port");
            }
            if (_receiverErrorCount == null) {
                throw new RuntimeException("setReceiverErrorCount() was not invoked in Builder for class Port");
            }
            if (_badTLPCount == null) {
                throw new RuntimeException("setBadTLPCount() was not invoked in Builder for class Port");
            }
            if (_badDLLPCount == null) {
                throw new RuntimeException("setBadDLLPCount() was not invoked in Builder for class Port");
            }
            if (_errorCount == null) {
                throw new RuntimeException("setErrorCount() was not invoked in Builder for class Port");
            }
            if (_ingressBytes == null) {
                throw new RuntimeException("setIngressBytes() was not invoked in Builder for class Port");
            }
            if (_egressBytes == null) {
                throw new RuntimeException("setEgressBytes() was not invoked in Builder for class Port");
            }
            if (_switches.isEmpty()) {
                throw new RuntimeException("setSwitches() was not invoked in Builder for class Port");
            }
            if (_cpu == null) {
                throw new RuntimeException("setCPU() was not invoked in Builder for class Port");
            }
            if (_deviceState == null) {
                throw new RuntimeException("setDeviceState() was not invoked in Builder for class Port");
            }
            return new Port(_portType,
                            _index,
                            _flags,
                            _state,
                            _fabricGlobalId,
                            _pciLaneCount,
                            _receiverErrorCount,
                            _badTLPCount,
                            _badDLLPCount,
                            _errorCount,
                            _ingressBytes,
                            _egressBytes,
                            _switches,
                            _cpu,
                            _deviceState);
        }
    }
}
