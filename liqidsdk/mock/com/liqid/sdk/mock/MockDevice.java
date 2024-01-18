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
import com.liqid.sdk.PreDevice;
import com.liqid.sdk.StorageDeviceInfo;
import com.liqid.sdk.StorageDeviceStatus;

import java.util.Objects;

public class MockDevice {

    private final DeviceInfo _deviceInfo;
    private final DeviceStatus _deviceStatus;

    MockDevice(
        final DeviceStatus deviceStatus,
        final DeviceInfo deviceInfo
    ) {
        _deviceInfo = deviceInfo;
        _deviceStatus = deviceStatus;
    }

    Integer getDeviceId() { return _deviceStatus.getDeviceId(); }
    DeviceInfo getDeviceInfo() { return _deviceInfo; }
    String getDeviceName() { return _deviceStatus.getName(); }
    DeviceStatus getDeviceStatus() { return _deviceStatus; }
    DeviceType getDeviceType() { return _deviceStatus.getDeviceType(); }

    ComputeDeviceStatus getComputeDeviceStatus() { return (ComputeDeviceStatus) _deviceStatus; }
    FPGADeviceStatus getFPGADeviceStatus() { return (FPGADeviceStatus) _deviceStatus; }
    GPUDeviceStatus getGPUDeviceStatus() { return (GPUDeviceStatus) _deviceStatus; }
    MemoryDeviceStatus getMemoryDeviceStatus() { return (MemoryDeviceStatus) _deviceStatus; }
    NetworkDeviceStatus getNetworkDeviceStatus() { return (NetworkDeviceStatus) _deviceStatus; }
    StorageDeviceStatus getStorageDeviceStatus() { return (StorageDeviceStatus) _deviceStatus; }

    ComputeDeviceInfo getComputeDeviceInfo() { return (ComputeDeviceInfo) _deviceInfo; }
    FPGADeviceInfo getFPGADeviceInfo() { return (FPGADeviceInfo) _deviceInfo; }
    GPUDeviceInfo getGPUDeviceInfo() { return (GPUDeviceInfo) _deviceInfo; }
    MemoryDeviceInfo getMemoryDeviceInfo() { return (MemoryDeviceInfo) _deviceInfo; }
    NetworkDeviceInfo getNetworkDeviceInfo() { return (NetworkDeviceInfo) _deviceInfo; }
    StorageDeviceInfo getStorageDeviceInfo() { return (StorageDeviceInfo) _deviceInfo; }

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
        return String.format("{id=%d, name=%s, type=%s}", getDeviceId(), getDeviceName(), getDeviceType());
    }
}
