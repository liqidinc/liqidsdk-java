// File: MachineDetails.java
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
 * MachineDetails
 * Additional details for a particular machine
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MachineDetails {

    public static class MachineDetailsWrapper extends Wrapper<MachineDetails>{}

    /**
     * Boot device
     */
    @JsonProperty("boot_device")
    private String _bootDevice = null;
    public String getBootDevice() { return _bootDevice; }
    public void setBootDevice(String value) { _bootDevice = value; }

    /**
     * Description of the boot image
     */
    @JsonProperty("boot_image")
    private String _bootImage = null;
    public String getBootImage() { return _bootImage; }
    public void setBootImage(String value) { _bootImage = value; }

    /**
     * Number of CPU cores for the machine
     */
    @JsonProperty("cpu-cores")
    private Integer _cpuCoreCount = null;
    public Integer getCPUCoreCount() { return _cpuCoreCount; }
    public void setCPUCoreCount(Integer value) { _cpuCoreCount = value; }

    /**
     * Cycle frequency for the CPU in this machine
     */
    @JsonProperty("cpu-frequency")
    private String _cpuFrequency = null;
    public String getCPUFrequency() { return _cpuFrequency; }
    public void setCPUFrequency(String value) { _cpuFrequency = value; }

    /**
     * Describes the CPU sockets for this machine
     */
    @JsonProperty("cpu-sockets")
    private String _cpuSockets = null;
    public String getCPUSockets() { return _cpuSockets; }
    public void setCPUSockets(String value) { _cpuSockets = value; }

    /**
     * TODO
     */
    @JsonProperty("cpuSocketsField")
    private String _cpuSocketsField = null;
    public String getCPUSocketsField() { return _cpuSocketsField; }
    public void setCPUSocketsField(String value) { _cpuSocketsField = value; }

    /**
     * Number of CPU threads for the machine
     */
    @JsonProperty("cpu-threads")
    private Integer _cpuThreadCount = null;
    public Integer getCPUThreadCount() { return _cpuThreadCount; }
    public void setCPUThreadCount(Integer value) { _cpuThreadCount = value; }

    /**
     * Timestamp for when the machine was created
     */
    @JsonProperty("created")
    private Long _createdAt = null;
    public Long getCreatedAt() { return _createdAt; }
    public void setCreatedAt(Long value) { _createdAt = value; }

    /**
     * Describes the dynamic RAM for this machine
     */
    @JsonProperty("dram-memory")
    private String _dynamicRAM = null;
    public String getDynamicRAM() { return _dynamicRAM; }
    public void setDynamicRAM(String value) { _dynamicRAM = value; }

    /**
     * TODO
     */
    @JsonProperty("fabric-connect")
    private String _fabricConnect = null;
    public String getFabricConnect() { return _fabricConnect; }
    public void setFabricConnect(String value) { _fabricConnect = value; }

    /**
     * Number of FPGAs connected to the machine
     */
    @JsonProperty("fpga-count")
    private Integer _fpgaCount = null;
    public Integer getFPGACount() { return _fpgaCount; }
    public void setFPGACount(Integer value) { _fpgaCount = value; }

    /**
     * FPGA dynamic RAM size
     */
    @JsonProperty("fpga-dram-size")
    private Integer _fpgadramSize = null;
    public Integer getFPGADRAMSize() { return _fpgadramSize; }
    public void setFPGADRAMSize(Integer value) { _fpgadramSize = value; }

    /**
     * FPGA speed
     */
    @JsonProperty("fpga-speed")
    private String _fpgaSpeed = null;
    public String getFPGASpeed() { return _fpgaSpeed; }
    public void setFPGASpeed(String value) { _fpgaSpeed = value; }

    /**
     * Number of GPU cores for the machine
     */
    @JsonProperty("gpu-cores")
    private Integer _gpuCoreCount = null;
    public Integer getGPUCoreCount() { return _gpuCoreCount; }
    public void setGPUCoreCount(Integer value) { _gpuCoreCount = value; }

    /**
     * Number of GPUs connected to the machine
     */
    @JsonProperty("gpu-count")
    private Integer _gpuCount = null;
    public Integer getGPUCount() { return _gpuCount; }
    public void setGPUCount(Integer value) { _gpuCount = value; }

    /**
     * IP Address (or DNS name) of the machine
     */
    @JsonProperty("ip_address")
    private String _ipAddress = null;
    public String getIPAddress() { return _ipAddress; }
    public void setIPAddress(String value) { _ipAddress = value; }

    /**
     * IP Address (or DNS name) of the IPMI management port for the machine
     */
    @JsonProperty("ipmi_address")
    private String _ipmiAddress = null;
    public String getIPMIAddress() { return _ipmiAddress; }
    public void setIPMIAddress(String value) { _ipmiAddress = value; }

    /**
     * Timestamp for when the machine was last modified
     */
    @JsonProperty("modified")
    private Long _lastModifiedAt = null;
    public Long getLastModifiedAt() { return _lastModifiedAt; }
    public void setLastModifiedAt(Long value) { _lastModifiedAt = value; }

    /**
     * Unique machine identifier
     */
    @JsonProperty("mach_id")
    private Integer _machineId = null;
    public Integer getMachineId() { return _machineId; }
    public void setMachineId(Integer value) { _machineId = value; }

    /**
     * Machine name
     */
    @JsonProperty("mach_name")
    private String _machineName = null;
    public String getMachineName() { return _machineName; }
    public void setMachineName(String value) { _machineName = value; }

    /**
     * Number of network adapters in the machine
     */
    @JsonProperty("network-adapter-count")
    private Integer _networkAdapterCount = null;
    public Integer getNetworkAdapterCount() { return _networkAdapterCount; }
    public void setNetworkAdapterCount(Integer value) { _networkAdapterCount = value; }

    /**
     * Operating system name
     */
    @JsonProperty("os_name")
    private String _operatingSystem = null;
    public String getOperatingSystem() { return _operatingSystem; }
    public void setOperatingSystem(String value) { _operatingSystem = value; }

    /**
     * Number of storage drives in the machine
     */
    @JsonProperty("storage-drive-count")
    private Integer _storageDriveCount = null;
    public Integer getStorageDriveCount() { return _storageDriveCount; }
    public void setStorageDriveCount(Integer value) { _storageDriveCount = value; }

    /**
     * Total capacity in this machine
     */
    @JsonProperty("total-capacity")
    private Long _totalCapacity = null;
    public Long getTotalCapacity() { return _totalCapacity; }
    public void setTotalCapacity(Long value) { _totalCapacity = value; }

    /**
     * TODO
     */
    @JsonProperty("total-throughput")
    private String _totalThroughput = null;
    public String getTotalThroughput() { return _totalThroughput; }
    public void setTotalThroughput(String value) { _totalThroughput = value; }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public MachineDetails() {
    }

    /**
     * Parameterized Constructor
     */
    protected MachineDetails(String cpuSocketsField,
                             Integer machineId,
                             String machineName,
                             Integer cpuThreadCount,
                             String cpuFrequency,
                             Integer cpuCoreCount,
                             String cpuSockets,
                             String dynamicRAM,
                             String fabricConnect,
                             Integer networkAdapterCount,
                             String totalThroughput,
                             Integer storageDriveCount,
                             Long totalCapacity,
                             Integer gpuCount,
                             Integer gpuCoreCount,
                             String operatingSystem,
                             String bootImage,
                             String bootDevice,
                             String ipAddress,
                             String ipmiAddress,
                             Integer fpgaCount,
                             String fpgaSpeed,
                             Integer fpgadramSize,
                             Long createdAt,
                             Long lastModifiedAt) {
        _cpuSocketsField = cpuSocketsField;
        _machineId = machineId;
        _machineName = machineName;
        _cpuThreadCount = cpuThreadCount;
        _cpuFrequency = cpuFrequency;
        _cpuCoreCount = cpuCoreCount;
        _cpuSockets = cpuSockets;
        _dynamicRAM = dynamicRAM;
        _fabricConnect = fabricConnect;
        _networkAdapterCount = networkAdapterCount;
        _totalThroughput = totalThroughput;
        _storageDriveCount = storageDriveCount;
        _totalCapacity = totalCapacity;
        _gpuCount = gpuCount;
        _gpuCoreCount = gpuCoreCount;
        _operatingSystem = operatingSystem;
        _bootImage = bootImage;
        _bootDevice = bootDevice;
        _ipAddress = ipAddress;
        _ipmiAddress = ipmiAddress;
        _fpgaCount = fpgaCount;
        _fpgaSpeed = fpgaSpeed;
        _fpgadramSize = fpgadramSize;
        _createdAt = createdAt;
        _lastModifiedAt = lastModifiedAt;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_cpuSocketsField:").append(getCPUSocketsField());
        sb.append(", ").append("_machineId:").append(getMachineId());
        sb.append(", ").append("_machineName:").append(getMachineName());
        sb.append(", ").append("_cpuThreadCount:").append(getCPUThreadCount());
        sb.append(", ").append("_cpuFrequency:").append(getCPUFrequency());
        sb.append(", ").append("_cpuCoreCount:").append(getCPUCoreCount());
        sb.append(", ").append("_cpuSockets:").append(getCPUSockets());
        sb.append(", ").append("_dynamicRAM:").append(getDynamicRAM());
        sb.append(", ").append("_fabricConnect:").append(getFabricConnect());
        sb.append(", ").append("_networkAdapterCount:").append(getNetworkAdapterCount());
        sb.append(", ").append("_totalThroughput:").append(getTotalThroughput());
        sb.append(", ").append("_storageDriveCount:").append(getStorageDriveCount());
        sb.append(", ").append("_totalCapacity:").append(getTotalCapacity());
        sb.append(", ").append("_gpuCount:").append(getGPUCount());
        sb.append(", ").append("_gpuCoreCount:").append(getGPUCoreCount());
        sb.append(", ").append("_operatingSystem:").append(getOperatingSystem());
        sb.append(", ").append("_bootImage:").append(getBootImage());
        sb.append(", ").append("_bootDevice:").append(getBootDevice());
        sb.append(", ").append("_ipAddress:").append(getIPAddress());
        sb.append(", ").append("_ipmiAddress:").append(getIPMIAddress());
        sb.append(", ").append("_fpgaCount:").append(getFPGACount());
        sb.append(", ").append("_fpgaSpeed:").append(getFPGASpeed());
        sb.append(", ").append("_fpgadramSize:").append(getFPGADRAMSize());
        sb.append(", ").append("_createdAt:").append(getCreatedAt());
        sb.append(", ").append("_lastModifiedAt:").append(getLastModifiedAt());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private String _cpuSocketsField = null;
        private Integer _machineId = null;
        private String _machineName = null;
        private Integer _cpuThreadCount = null;
        private String _cpuFrequency = null;
        private Integer _cpuCoreCount = null;
        private String _cpuSockets = null;
        private String _dynamicRAM = null;
        private String _fabricConnect = null;
        private Integer _networkAdapterCount = null;
        private String _totalThroughput = null;
        private Integer _storageDriveCount = null;
        private Long _totalCapacity = null;
        private Integer _gpuCount = null;
        private Integer _gpuCoreCount = null;
        private String _operatingSystem = null;
        private String _bootImage = null;
        private String _bootDevice = null;
        private String _ipAddress = null;
        private String _ipmiAddress = null;
        private Integer _fpgaCount = null;
        private String _fpgaSpeed = null;
        private Integer _fpgadramSize = null;
        private Long _createdAt = null;
        private Long _lastModifiedAt = null;

        public Builder setCPUSocketsField(String value) { _cpuSocketsField = value; return this; }
        public Builder setMachineId(Integer value) { _machineId = value; return this; }
        public Builder setMachineName(String value) { _machineName = value; return this; }
        public Builder setCPUThreadCount(Integer value) { _cpuThreadCount = value; return this; }
        public Builder setCPUFrequency(String value) { _cpuFrequency = value; return this; }
        public Builder setCPUCoreCount(Integer value) { _cpuCoreCount = value; return this; }
        public Builder setCPUSockets(String value) { _cpuSockets = value; return this; }
        public Builder setDynamicRAM(String value) { _dynamicRAM = value; return this; }
        public Builder setFabricConnect(String value) { _fabricConnect = value; return this; }
        public Builder setNetworkAdapterCount(Integer value) { _networkAdapterCount = value; return this; }
        public Builder setTotalThroughput(String value) { _totalThroughput = value; return this; }
        public Builder setStorageDriveCount(Integer value) { _storageDriveCount = value; return this; }
        public Builder setTotalCapacity(Long value) { _totalCapacity = value; return this; }
        public Builder setGPUCount(Integer value) { _gpuCount = value; return this; }
        public Builder setGPUCoreCount(Integer value) { _gpuCoreCount = value; return this; }
        public Builder setOperatingSystem(String value) { _operatingSystem = value; return this; }
        public Builder setBootImage(String value) { _bootImage = value; return this; }
        public Builder setBootDevice(String value) { _bootDevice = value; return this; }
        public Builder setIPAddress(String value) { _ipAddress = value; return this; }
        public Builder setIPMIAddress(String value) { _ipmiAddress = value; return this; }
        public Builder setFPGACount(Integer value) { _fpgaCount = value; return this; }
        public Builder setFPGASpeed(String value) { _fpgaSpeed = value; return this; }
        public Builder setFPGADRAMSize(Integer value) { _fpgadramSize = value; return this; }
        public Builder setCreatedAt(Long value) { _createdAt = value; return this; }
        public Builder setLastModifiedAt(Long value) { _lastModifiedAt = value; return this; }

        public MachineDetails build() {
            if (_cpuSocketsField == null) {
                throw new RuntimeException("setCPUSocketsField() was not invoked in Builder for class MachineDetails");
            }
            if (_machineId == null) {
                throw new RuntimeException("setMachineId() was not invoked in Builder for class MachineDetails");
            }
            if (_machineName == null) {
                throw new RuntimeException("setMachineName() was not invoked in Builder for class MachineDetails");
            }
            if (_cpuThreadCount == null) {
                throw new RuntimeException("setCPUThreadCount() was not invoked in Builder for class MachineDetails");
            }
            if (_cpuFrequency == null) {
                throw new RuntimeException("setCPUFrequency() was not invoked in Builder for class MachineDetails");
            }
            if (_cpuCoreCount == null) {
                throw new RuntimeException("setCPUCoreCount() was not invoked in Builder for class MachineDetails");
            }
            if (_cpuSockets == null) {
                throw new RuntimeException("setCPUSockets() was not invoked in Builder for class MachineDetails");
            }
            if (_dynamicRAM == null) {
                throw new RuntimeException("setDynamicRAM() was not invoked in Builder for class MachineDetails");
            }
            if (_fabricConnect == null) {
                throw new RuntimeException("setFabricConnect() was not invoked in Builder for class MachineDetails");
            }
            if (_networkAdapterCount == null) {
                throw new RuntimeException("setNetworkAdapterCount() was not invoked in Builder for class MachineDetails");
            }
            if (_totalThroughput == null) {
                throw new RuntimeException("setTotalThroughput() was not invoked in Builder for class MachineDetails");
            }
            if (_storageDriveCount == null) {
                throw new RuntimeException("setStorageDriveCount() was not invoked in Builder for class MachineDetails");
            }
            if (_totalCapacity == null) {
                throw new RuntimeException("setTotalCapacity() was not invoked in Builder for class MachineDetails");
            }
            if (_gpuCount == null) {
                throw new RuntimeException("setGPUCount() was not invoked in Builder for class MachineDetails");
            }
            if (_gpuCoreCount == null) {
                throw new RuntimeException("setGPUCoreCount() was not invoked in Builder for class MachineDetails");
            }
            if (_operatingSystem == null) {
                throw new RuntimeException("setOperatingSystem() was not invoked in Builder for class MachineDetails");
            }
            if (_bootImage == null) {
                throw new RuntimeException("setBootImage() was not invoked in Builder for class MachineDetails");
            }
            if (_bootDevice == null) {
                throw new RuntimeException("setBootDevice() was not invoked in Builder for class MachineDetails");
            }
            if (_ipAddress == null) {
                throw new RuntimeException("setIPAddress() was not invoked in Builder for class MachineDetails");
            }
            if (_ipmiAddress == null) {
                throw new RuntimeException("setIPMIAddress() was not invoked in Builder for class MachineDetails");
            }
            if (_fpgaCount == null) {
                throw new RuntimeException("setFPGACount() was not invoked in Builder for class MachineDetails");
            }
            if (_fpgaSpeed == null) {
                throw new RuntimeException("setFPGASpeed() was not invoked in Builder for class MachineDetails");
            }
            if (_fpgadramSize == null) {
                throw new RuntimeException("setFPGADRAMSize() was not invoked in Builder for class MachineDetails");
            }
            if (_createdAt == null) {
                throw new RuntimeException("setCreatedAt() was not invoked in Builder for class MachineDetails");
            }
            if (_lastModifiedAt == null) {
                throw new RuntimeException("setLastModifiedAt() was not invoked in Builder for class MachineDetails");
            }
            return new MachineDetails(_cpuSocketsField,
                                      _machineId,
                                      _machineName,
                                      _cpuThreadCount,
                                      _cpuFrequency,
                                      _cpuCoreCount,
                                      _cpuSockets,
                                      _dynamicRAM,
                                      _fabricConnect,
                                      _networkAdapterCount,
                                      _totalThroughput,
                                      _storageDriveCount,
                                      _totalCapacity,
                                      _gpuCount,
                                      _gpuCoreCount,
                                      _operatingSystem,
                                      _bootImage,
                                      _bootDevice,
                                      _ipAddress,
                                      _ipmiAddress,
                                      _fpgaCount,
                                      _fpgaSpeed,
                                      _fpgadramSize,
                                      _createdAt,
                                      _lastModifiedAt);
        }
    }
}
