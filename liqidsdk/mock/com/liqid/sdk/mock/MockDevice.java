// Copyright (c) 2024 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.mock;

import com.liqid.sdk.ComputeDeviceInfo;
import com.liqid.sdk.ComputeDeviceStatus;
import com.liqid.sdk.DeviceInfo;
import com.liqid.sdk.DeviceStatus;
import com.liqid.sdk.DeviceType;
import com.liqid.sdk.FPGADeviceInfo;
import com.liqid.sdk.FPGADeviceStatus;
import com.liqid.sdk.GPUDeviceInfo;
import com.liqid.sdk.GPUDeviceStatus;
import com.liqid.sdk.MemoryDeviceInfo;
import com.liqid.sdk.MemoryDeviceStatus;
import com.liqid.sdk.NetworkDeviceInfo;
import com.liqid.sdk.NetworkDeviceStatus;
import com.liqid.sdk.StorageDeviceInfo;
import com.liqid.sdk.StorageDeviceStatus;

import java.util.Objects;

public class MockDevice implements Comparable<MockDevice> {

    private final DeviceInfo _deviceInfo;
    private final DeviceStatus _deviceStatus;

    MockDevice(
        final DeviceStatus deviceStatus,
        final DeviceInfo deviceInfo
    ) {
        _deviceInfo = deviceInfo;
        _deviceStatus = deviceStatus;
    }

    public Integer getDeviceId() { return _deviceStatus.getDeviceId(); }
    public DeviceInfo getDeviceInfo() { return _deviceInfo; }
    public String getDeviceName() { return _deviceStatus.getName(); }
    public DeviceStatus getDeviceStatus() { return _deviceStatus; }
    public DeviceType getDeviceType() { return _deviceStatus.getDeviceType(); }

    public ComputeDeviceStatus getComputeDeviceStatus() { return (ComputeDeviceStatus) _deviceStatus; }
    public FPGADeviceStatus getFPGADeviceStatus() { return (FPGADeviceStatus) _deviceStatus; }
    public GPUDeviceStatus getGPUDeviceStatus() { return (GPUDeviceStatus) _deviceStatus; }
    public MemoryDeviceStatus getMemoryDeviceStatus() { return (MemoryDeviceStatus) _deviceStatus; }
    public NetworkDeviceStatus getNetworkDeviceStatus() { return (NetworkDeviceStatus) _deviceStatus; }
    public StorageDeviceStatus getStorageDeviceStatus() { return (StorageDeviceStatus) _deviceStatus; }

    public ComputeDeviceInfo getComputeDeviceInfo() { return (ComputeDeviceInfo) _deviceInfo; }
    public FPGADeviceInfo getFPGADeviceInfo() { return (FPGADeviceInfo) _deviceInfo; }
    public GPUDeviceInfo getGPUDeviceInfo() { return (GPUDeviceInfo) _deviceInfo; }
    public MemoryDeviceInfo getMemoryDeviceInfo() { return (MemoryDeviceInfo) _deviceInfo; }
    public NetworkDeviceInfo getNetworkDeviceInfo() { return (NetworkDeviceInfo) _deviceInfo; }
    public StorageDeviceInfo getStorageDeviceInfo() { return (StorageDeviceInfo) _deviceInfo; }

    @Override
    public boolean equals(
        final Object obj
    ) {
        if (obj instanceof MockDevice d) {
            return Objects.equals(getDeviceId(), d.getDeviceId());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getDeviceId().hashCode();
    }

    @Override
    public String toString() {
        return String.format("{type=%s, id=0x%08x, name=%s}", getDeviceType(), getDeviceId(), getDeviceName());
    }

    @Override
    public int compareTo(MockDevice device) {
        return getDeviceId().compareTo(device.getDeviceId());
    }
}
