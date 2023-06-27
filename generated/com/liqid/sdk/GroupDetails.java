// File: GroupDetails.java
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
 * GroupDetails
 * Contains statistical information which is accumulated for the group.
 * Does not contain relations with devices; that information resides in the various device relators.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupDetails {

    public static class GroupDetailsWrapper extends Wrapper<GroupDetails>{}

    /**
     * Number of CPU cores in the group
     */
    @JsonProperty("cpu-core-count")
    private Integer _cpuCoreCount = null;
    public Integer getCpuCoreCount() { return _cpuCoreCount; }
    public void setCpuCoreCount(Integer value) { _cpuCoreCount = value; }

    /**
     * Number of CPUs in the group
     */
    @JsonProperty("cpu-count")
    private Integer _cpuCount = null;
    public Integer getCpuCount() { return _cpuCount; }
    public void setCpuCount(Integer value) { _cpuCount = value; }

    /**
     * Frequency of the CPUs in the group
     */
    @JsonProperty("cpu-frequency")
    private Double _cpuFrequency = null;
    public Double getCpuFrequency() { return _cpuFrequency; }
    public void setCpuFrequency(Double value) { _cpuFrequency = value; }

    /**
     * Number of PCI lanes dedicated to CPUs for this group
     */
    @JsonProperty("cpu-lanes")
    private Integer _cpuLanes = null;
    public Integer getCpuLanes() { return _cpuLanes; }
    public void setCpuLanes(Integer value) { _cpuLanes = value; }

    /**
     * Number of GPU cores in the group
     */
    @JsonProperty("gpu-cores")
    private Integer _gpuCores = null;
    public Integer getGPUCores() { return _gpuCores; }
    public void setGPUCores(Integer value) { _gpuCores = value; }

    /**
     * System-unique identifier for the group
     */
    @JsonProperty("grp_id")
    private Integer _groupId = null;
    public Integer getGroupId() { return _groupId; }
    public void setGroupId(Integer value) { _groupId = value; }

    /**
     * System-unique human-readable name of the group
     */
    @JsonProperty("group_name")
    private String _groupName = null;
    public String getGroupName() { return _groupName; }
    public void setGroupName(String value) { _groupName = value; }

    /**
     * Number of network adapters in the group
     */
    @JsonProperty("network-adapter-count")
    private Integer _networkAdapterCount = null;
    public Integer getNetworkAdapterCount() { return _networkAdapterCount; }
    public void setNetworkAdapterCount(Integer value) { _networkAdapterCount = value; }

    /**
     * Number of SSDs in the group
     */
    @JsonProperty("storage-drive-count")
    private Integer _storageDriveCount = null;
    public Integer getStorageDriveCount() { return _storageDriveCount; }
    public void setStorageDriveCount(Integer value) { _storageDriveCount = value; }

    /**
     * Total capacity of SSD storage in the group
     */
    @JsonProperty("total-capacity")
    private Long _totalCapacity = null;
    public Long getTotalCapacity() { return _totalCapacity; }
    public void setTotalCapacity(Long value) { _totalCapacity = value; }

    /**
     * Total amount of dynamic RAM in the group
     */
    @JsonProperty("total-dram")
    private Integer _totalDRAM = null;
    public Integer getTotalDRAM() { return _totalDRAM; }
    public void setTotalDRAM(Integer value) { _totalDRAM = value; }

    /**
     * Number of configured machines in the group
     */
    @JsonProperty("total-machines")
    private Integer _totalMachines = null;
    public Integer getTotalMachines() { return _totalMachines; }
    public void setTotalMachines(Integer value) { _totalMachines = value; }

    /**
     * Total throughput for this group
     */
    @JsonProperty("total-throughput")
    private String _totalThroughput = null;
    public String getTotalThroughput() { return _totalThroughput; }
    public void setTotalThroughput(String value) { _totalThroughput = value; }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public GroupDetails() {
    }

    /**
     * Parameterized Constructor
     */
    protected GroupDetails(Integer groupId,
                           String groupName,
                           Double cpuFrequency,
                           Integer cpuCount,
                           Integer cpuLanes,
                           Integer cpuCoreCount,
                           Integer totalDRAM,
                           Integer networkAdapterCount,
                           String totalThroughput,
                           Integer storageDriveCount,
                           Long totalCapacity,
                           Integer gpuCores,
                           Integer totalMachines) {
        _groupId = groupId;
        _groupName = groupName;
        _cpuFrequency = cpuFrequency;
        _cpuCount = cpuCount;
        _cpuLanes = cpuLanes;
        _cpuCoreCount = cpuCoreCount;
        _totalDRAM = totalDRAM;
        _networkAdapterCount = networkAdapterCount;
        _totalThroughput = totalThroughput;
        _storageDriveCount = storageDriveCount;
        _totalCapacity = totalCapacity;
        _gpuCores = gpuCores;
        _totalMachines = totalMachines;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_groupId:").append(getGroupId());
        sb.append(", ").append("_groupName:").append(getGroupName());
        sb.append(", ").append("_cpuFrequency:").append(getCpuFrequency());
        sb.append(", ").append("_cpuCount:").append(getCpuCount());
        sb.append(", ").append("_cpuLanes:").append(getCpuLanes());
        sb.append(", ").append("_cpuCoreCount:").append(getCpuCoreCount());
        sb.append(", ").append("_totalDRAM:").append(getTotalDRAM());
        sb.append(", ").append("_networkAdapterCount:").append(getNetworkAdapterCount());
        sb.append(", ").append("_totalThroughput:").append(getTotalThroughput());
        sb.append(", ").append("_storageDriveCount:").append(getStorageDriveCount());
        sb.append(", ").append("_totalCapacity:").append(getTotalCapacity());
        sb.append(", ").append("_gpuCores:").append(getGPUCores());
        sb.append(", ").append("_totalMachines:").append(getTotalMachines());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private Integer _groupId = null;
        private String _groupName = null;
        private Double _cpuFrequency = null;
        private Integer _cpuCount = null;
        private Integer _cpuLanes = null;
        private Integer _cpuCoreCount = null;
        private Integer _totalDRAM = null;
        private Integer _networkAdapterCount = null;
        private String _totalThroughput = null;
        private Integer _storageDriveCount = null;
        private Long _totalCapacity = null;
        private Integer _gpuCores = null;
        private Integer _totalMachines = null;

        public Builder setGroupId(Integer value) { _groupId = value; return this; }
        public Builder setGroupName(String value) { _groupName = value; return this; }
        public Builder setCpuFrequency(Double value) { _cpuFrequency = value; return this; }
        public Builder setCpuCount(Integer value) { _cpuCount = value; return this; }
        public Builder setCpuLanes(Integer value) { _cpuLanes = value; return this; }
        public Builder setCpuCoreCount(Integer value) { _cpuCoreCount = value; return this; }
        public Builder setTotalDRAM(Integer value) { _totalDRAM = value; return this; }
        public Builder setNetworkAdapterCount(Integer value) { _networkAdapterCount = value; return this; }
        public Builder setTotalThroughput(String value) { _totalThroughput = value; return this; }
        public Builder setStorageDriveCount(Integer value) { _storageDriveCount = value; return this; }
        public Builder setTotalCapacity(Long value) { _totalCapacity = value; return this; }
        public Builder setGPUCores(Integer value) { _gpuCores = value; return this; }
        public Builder setTotalMachines(Integer value) { _totalMachines = value; return this; }

        public GroupDetails build() {
            if (_groupId == null) {
                throw new RuntimeException("setGroupId() was not invoked in Builder for class GroupDetails");
            }
            if (_groupName == null) {
                throw new RuntimeException("setGroupName() was not invoked in Builder for class GroupDetails");
            }
            if (_cpuFrequency == null) {
                throw new RuntimeException("setCpuFrequency() was not invoked in Builder for class GroupDetails");
            }
            if (_cpuCount == null) {
                throw new RuntimeException("setCpuCount() was not invoked in Builder for class GroupDetails");
            }
            if (_cpuLanes == null) {
                throw new RuntimeException("setCpuLanes() was not invoked in Builder for class GroupDetails");
            }
            if (_cpuCoreCount == null) {
                throw new RuntimeException("setCpuCoreCount() was not invoked in Builder for class GroupDetails");
            }
            if (_totalDRAM == null) {
                throw new RuntimeException("setTotalDRAM() was not invoked in Builder for class GroupDetails");
            }
            if (_networkAdapterCount == null) {
                throw new RuntimeException("setNetworkAdapterCount() was not invoked in Builder for class GroupDetails");
            }
            if (_totalThroughput == null) {
                throw new RuntimeException("setTotalThroughput() was not invoked in Builder for class GroupDetails");
            }
            if (_storageDriveCount == null) {
                throw new RuntimeException("setStorageDriveCount() was not invoked in Builder for class GroupDetails");
            }
            if (_totalCapacity == null) {
                throw new RuntimeException("setTotalCapacity() was not invoked in Builder for class GroupDetails");
            }
            if (_gpuCores == null) {
                throw new RuntimeException("setGPUCores() was not invoked in Builder for class GroupDetails");
            }
            if (_totalMachines == null) {
                throw new RuntimeException("setTotalMachines() was not invoked in Builder for class GroupDetails");
            }
            return new GroupDetails(_groupId,
                                    _groupName,
                                    _cpuFrequency,
                                    _cpuCount,
                                    _cpuLanes,
                                    _cpuCoreCount,
                                    _totalDRAM,
                                    _networkAdapterCount,
                                    _totalThroughput,
                                    _storageDriveCount,
                                    _totalCapacity,
                                    _gpuCores,
                                    _totalMachines);
        }
    }
}
