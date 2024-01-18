// File: DeviceCounters.java
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
 * DeviceCounters
 * Counts of discovered devices by device type
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceCounters {

    public static class DeviceCountersWrapper extends Wrapper<DeviceCounters>{}

    /**
     * Number of discovered compute devices
     */
    @JsonProperty("comp_cnt")
    private Integer _cpuCount = null;

    public Integer getCPUCount() {
        return _cpuCount;
    }

    public DeviceCounters setCPUCount(Integer value) {
        _cpuCount = value;
        return this;
    }

    /**
     * Number of discovered FPGA devices
     */
    @JsonProperty("fpga_cnt")
    private Integer _fpgaCount = null;

    public Integer getFPGACount() {
        return _fpgaCount;
    }

    public DeviceCounters setFPGACount(Integer value) {
        _fpgaCount = value;
        return this;
    }

    /**
     * Number of discovered GPU devices
     */
    @JsonProperty("gpu_cnt")
    private Integer _gpuCount = null;

    public Integer getGPUCount() {
        return _gpuCount;
    }

    public DeviceCounters setGPUCount(Integer value) {
        _gpuCount = value;
        return this;
    }

    /**
     * Number of discovered link (e.g., network) devices
     */
    @JsonProperty("link_cnt")
    private Integer _linkCount = null;

    public Integer getLinkCount() {
        return _linkCount;
    }

    public DeviceCounters setLinkCount(Integer value) {
        _linkCount = value;
        return this;
    }

    /**
     * Number of discovered PLX devices
     */
    @JsonProperty("plx_cnt")
    private Integer _plxCount = null;

    public Integer getPLXCount() {
        return _plxCount;
    }

    public DeviceCounters setPLXCount(Integer value) {
        _plxCount = value;
        return this;
    }

    /**
     * Number of discovered target devices
     */
    @JsonProperty("targ_cnt")
    private Integer _targetCount = null;

    public Integer getTargetCount() {
        return _targetCount;
    }

    public DeviceCounters setTargetCount(Integer value) {
        _targetCount = value;
        return this;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public DeviceCounters() {
    }

    /**
     * Parameterized Constructor
     */
    protected DeviceCounters(Integer cpuCount,
                             Integer fpgaCount,
                             Integer gpuCount,
                             Integer linkCount,
                             Integer plxCount,
                             Integer targetCount) {
        _cpuCount = cpuCount;
        _fpgaCount = fpgaCount;
        _gpuCount = gpuCount;
        _linkCount = linkCount;
        _plxCount = plxCount;
        _targetCount = targetCount;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_cpuCount:").append(getCPUCount());
        sb.append(", ").append("_fpgaCount:").append(getFPGACount());
        sb.append(", ").append("_gpuCount:").append(getGPUCount());
        sb.append(", ").append("_linkCount:").append(getLinkCount());
        sb.append(", ").append("_plxCount:").append(getPLXCount());
        sb.append(", ").append("_targetCount:").append(getTargetCount());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private Integer _cpuCount = null;
        private Integer _fpgaCount = null;
        private Integer _gpuCount = null;
        private Integer _linkCount = null;
        private Integer _plxCount = null;
        private Integer _targetCount = null;

        public Builder setCPUCount(Integer value) { _cpuCount = value; return this; }
        public Builder setFPGACount(Integer value) { _fpgaCount = value; return this; }
        public Builder setGPUCount(Integer value) { _gpuCount = value; return this; }
        public Builder setLinkCount(Integer value) { _linkCount = value; return this; }
        public Builder setPLXCount(Integer value) { _plxCount = value; return this; }
        public Builder setTargetCount(Integer value) { _targetCount = value; return this; }

        public DeviceCounters build() {
            if (_cpuCount == null) {
                throw new RuntimeException("setCPUCount() was not invoked in Builder for class DeviceCounters");
            }
            if (_fpgaCount == null) {
                throw new RuntimeException("setFPGACount() was not invoked in Builder for class DeviceCounters");
            }
            if (_gpuCount == null) {
                throw new RuntimeException("setGPUCount() was not invoked in Builder for class DeviceCounters");
            }
            if (_linkCount == null) {
                throw new RuntimeException("setLinkCount() was not invoked in Builder for class DeviceCounters");
            }
            if (_plxCount == null) {
                throw new RuntimeException("setPLXCount() was not invoked in Builder for class DeviceCounters");
            }
            if (_targetCount == null) {
                throw new RuntimeException("setTargetCount() was not invoked in Builder for class DeviceCounters");
            }
            return new DeviceCounters(_cpuCount,
                                      _fpgaCount,
                                      _gpuCount,
                                      _linkCount,
                                      _plxCount,
                                      _targetCount);
        }
    }
}
