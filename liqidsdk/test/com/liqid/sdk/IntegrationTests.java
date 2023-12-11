//
// Copyright (c) 2022-2023 by Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk;

import com.bearsnake.klog.Level;
import com.bearsnake.klog.Logger;
import com.bearsnake.klog.StdOutWriter;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class IntegrationTests {

    public static final String HOST = "10.10.14.236";
    public static final String LABEL = "IntegrationTests";
    public static final String USERNAME = "jose";
    public static final String PASSWORD = "jose";

    private LiqidClient createClient() throws LiqidException {
        var client = new LiqidClientBuilder().setHostAddress(HOST).build();
        setupLogging(client);
        return client;
    }

    private void setupLogging(
        final LiqidClient client
    ) {
        try {
            client._logger = new Logger("Test", Level.TRACE);
            client._logger.addWriter(new StdOutWriter(Level.TRACE));
        } catch (IOException ex) {
            System.err.println("Cannot set up logging:" + ex);
        }
    }

    //  Credentials testing --------------------------------------------------------------------------------------------
    @Test
    public void Test_HasCredentialsWithNoneSet() throws LiqidException {
        var client = createClient();
        assertFalse(client.hasCredentials());
    }

    @Test
    public void Test_IsLoggedInWhileNotLoggedIn() throws LiqidException {
        var client = createClient();
        assertFalse(client.isLoggedIn());
    }

    @Test(expected = LiqidException.class)
    public void Test_ClearCredentialsWithNoneSet() throws LiqidException {
        LiqidClient client = null;
        try {
            client = createClient();
        } catch (LiqidException ex) {
            assertTrue(false);
        }

        client.clearCredentials();
    }

    @Test
    public void Test_SetCredentials() throws LiqidException {
        var client = createClient();
        client.setCredentials(USERNAME, PASSWORD);
        assertTrue(client.hasCredentials());
    }

    @Test
    public void Test_ClearCredentials() throws LiqidException {
        var client = createClient();
        client.setCredentials(USERNAME, PASSWORD);
        client.clearCredentials();
    }

    @Test(expected = LiqidException.class)
    public void Test_LoginWithCredentialsSet() throws LiqidException {
        var client = createClient();
        client.setCredentials(USERNAME, PASSWORD);
        client.login(LABEL, USERNAME, PASSWORD);
    }

    @Test(expected = LiqidException.class)
    public void Test_LogoutWhileNotLoggedIn() throws LiqidException {
        var client = createClient();
        client.logout();
    }

    @Test
    public void Test_Login() throws LiqidException {
        var client = createClient();
        client.login(LABEL, USERNAME, PASSWORD);
    }

    @Test(expected = LiqidException.class)
    public void Test_LoginWithBadCredentials() throws LiqidException {
        var client = createClient();
        client.login(LABEL, "Frau", "Blucher");
    }

    @Test
    public void Test_Logout() throws LiqidException {
        var client = createClient();
        client.login(LABEL, USERNAME, PASSWORD);
        client.logout();
    }

    @Test(expected = LiqidException.class)
    public void Test_SetCredentialsWhileLoggedIn() throws LiqidException {
        var client = createClient();
        client.login(LABEL, USERNAME, PASSWORD);
        client.setCredentials(USERNAME, PASSWORD);
    }

    //  Group CRD ------------------------------------------------------------------------------------------------------

    @Test
    public void Test_GroupOperations() throws LiqidException {
        var client = createClient();
        client.login(LABEL, USERNAME, PASSWORD);
        client.clearGroups();
        var groups = client.getGroups();
        assertEquals(0, groups.size());

        var groupName = "Mika";
        var expGroupId = client.getNextGroupId();
        var group = client.createGroup(groupName);
        assertEquals(groupName, group.getGroupName());
        assertEquals(expGroupId, group.getGroupId());

        group = client.getGroup(group.getGroupId());
        assertEquals(groupName, group.getGroupName());
        assertEquals(expGroupId, group.getGroupId());

        var groupId = client.getGroupIdByName(group.getGroupName());
        assertEquals(expGroupId, groupId);

        var groupDetails = client.getGroupDetails(expGroupId);
        assertEquals(expGroupId, groupDetails.getGroupId());
        assertEquals(groupName, groupDetails.getGroupName());

        client.deleteGroup(groupId);
    }

    @Test
    public void Test_AddDeviceToGroup() throws LiqidException {
        var client = new LiqidClientBuilder().setHostAddress(HOST)
                                             .setPortNumber(LiqidClientBuilder.DEFAULT_PORT_NUMBER)
                                             .setWaitForAsyncCompletion(true)
                                             .build();
        setupLogging(client);
        client.login(LABEL, USERNAME, PASSWORD);

        //  this test requires a machine with at least one cpu and one gpu.
        client.clearGroups();
        var cpus = client.getComputeDevicesStatus();
        assertFalse(cpus.isEmpty());
        var cpuId = cpus.get(0).getDeviceId();

        var gpus = client.getGPUDevicesStatus();
        assertFalse(gpus.isEmpty());
        var gpuId = gpus.get(0).getDeviceId();

        var group = client.createGroup("TestGroup");
        var groupId = group.getGroupId();
        client.groupPoolEdit(groupId);
        client.addDeviceToGroup(cpuId, groupId);
        client.addDeviceToGroup(gpuId, groupId);
        client.groupPoolDone(group.getGroupId());

        var devCheck = client.getDevices(null, groupId, null);
        assertEquals(2, devCheck.size());
        client.deleteGroup(group.getGroupId());
    }

    @Test
    public void Test_RemoveDeviceFromGroup() throws LiqidException {
        var client = new LiqidClientBuilder().setHostAddress(HOST)
                                             .setPortNumber(LiqidClientBuilder.DEFAULT_PORT_NUMBER)
                                             .setWaitForAsyncCompletion(true)
                                             .build();
        setupLogging(client);
        client.login(LABEL, USERNAME, PASSWORD);

        client.clearGroups();
        var allDevs = client.getAllDevicesStatus();
        assertFalse(allDevs.isEmpty());

        var group = client.createGroup("TestGroup");
        var groupId = group.getGroupId();
        client.groupPoolEdit(groupId);
        for (var dev : allDevs) {
            client.addDeviceToGroup(dev.getDeviceId(), groupId);
        }
        client.groupPoolDone(groupId);

        var dev = allDevs.get(0);
        client.groupPoolEdit(groupId);
        client.removeDeviceFromGroup(dev.getDeviceId(), groupId);
        client.groupPoolDone(groupId);

        assertEquals(allDevs.size() - 1, client.getUnattachedDevicesForGroup(null, groupId).size());

        client.deleteGroup(group.getGroupId());
    }

    //  Other tests ----------------------------------------------------------------------------------------------------
    @Test
    public void Test_CreateDeleteUserDesc() throws LiqidException {
        String newDescription = "Aranjuez";
        var desc = new DeviceUserDescription();
        desc.setUserDescription(newDescription);

        var client = createClient();
        client.login(LABEL, USERNAME, PASSWORD);

        var devInfoList = client.getComputeDeviceInfo();
        assertTrue(devInfoList.size() > 0);
        var devInfo = devInfoList.get(0);
        var devType = devInfo.getDeviceInfoType();
        var devId = devInfo.getDeviceIdentifier();
        var devDesc = devInfo.getUserDescription();
        System.out.printf("Creating udesc for deviceType:%s Id:%s Current UDesc:%s\n", devType, devId, devDesc);

        var updateDevType = LiqidClient.deviceTypeToQueryDeviceType(devType);
        client.createDeviceDescription(updateDevType, devId, newDescription);
        devInfoList = client.getComputeDeviceInfo();
        devInfo = devInfoList.get(0);
        devType = devInfo.getDeviceInfoType();
        devId = devInfo.getDeviceIdentifier();
        devDesc = devInfo.getUserDescription();
        System.out.printf("Checking udesc after update - deviceType:%s Id:%s Current UDesc:%s\n", devType, devId, devDesc);
        assertEquals(newDescription, devInfo.getUserDescription());

        client.deleteDeviceDescription(updateDevType, devId);
        devInfoList = client.getComputeDeviceInfo();
        devInfo = devInfoList.get(0);
        devDesc = devInfo.getUserDescription();
        System.out.printf("Checking udesc after delete - deviceType:%s Id:%s Current UDesc:%s\n", devType, devId, devDesc);
        assertEquals("n/a", devInfo.getUserDescription());
    }

    @Test
    public void Test_GetAllDevicesStatus() throws LiqidException {
        var client = createClient();
        client.login(LABEL, USERNAME, PASSWORD);

        var deviceStatusList = client.getAllDevicesStatus();
        int compCount = 0;
        for (var stat : deviceStatusList) {
            if (stat.getDeviceType() == DeviceType.COMPUTE) {
                compCount++;
            }
            System.out.printf("Type:%s Id:%d Name:%s%n",
                    stat.getDeviceType(),
                    stat.getDeviceId(),
                    stat.getName());
        }
        assertTrue(compCount > 0);
    }

    @Test
    public void Test_GetDeviceCounters() throws LiqidException {
        var client = createClient();
        client.login(LABEL, USERNAME, PASSWORD);

        var counters = client.getDeviceCounters();
        System.out.printf("CPUs:%d FPGAs:%d GPUs:%d Links:%d PLXs:%d Targets:%d\n",
                counters.getCPUCount(),
                counters.getFPGACount(),
                counters.getGPUCount(),
                counters.getLinkCount(),
                counters.getPLXCount(),
                counters.getTargetCount());
        //  We cannot predict much with respect to the actual counters... if we didn't throw, we're probably good
        assertTrue(counters.getCPUCount() > 0);
    }

    @Test
    public void Test_GetCPUDeviceInfo() throws LiqidException {
        var client = createClient();
        client.login(LABEL, USERNAME, PASSWORD);

        var infos = client.getComputeDeviceInfo();
        for (var info : infos) {
            System.out.printf("Type:%s Id:%s Name:%s\n", info.getDeviceInfoType(), info.getPCIDeviceId(), info.getName());
        }
        //  We cannot predict much with respect to the actual counters... if we didn't throw, we're probably good
        assertTrue(infos.size() > 0);
    }

    @Test
    public void Test_GetCPUDeviceInfoByName() throws LiqidException {
        var client = createClient();
        client.login(LABEL, USERNAME, PASSWORD);

        var infos = client.getComputeDeviceInfo();
        var name = infos.get(0).getName();
        var info = client.getComputeDeviceInfoByName(name);
        assertEquals(name, info.getName());
        assertEquals(infos.get(0).getPCIDeviceId(), info.getPCIDeviceId());
    }

    @Test
    public void Test_GetVersions() throws LiqidException {
        var client = createClient();
        client.login(LABEL, USERNAME, PASSWORD);

        var versions = client.getVersions();
        for (var componentVersion : versions) {
            System.out.println("Component:" + componentVersion.getComponent() + " Version:" + componentVersion.getVersion());
        }

        assertEquals(8, versions.size());
    }

    @Test
    public void Test_EnableDisableP2P() throws LiqidException {
        var client = new LiqidClientBuilder().setHostAddress(HOST)
                                             .setPortNumber(LiqidClientBuilder.DEFAULT_PORT_NUMBER)
                                             .setWaitForAsyncCompletion(true)
                                             .build();
        setupLogging(client);
        client.login(LABEL, USERNAME, PASSWORD);

        //  this test requires a machine with a cpu and at least two gpus.
        client.clearGroups();
        var cpus = client.getComputeDevicesStatus();
        assertFalse(cpus.isEmpty());
        var cpuId = cpus.get(0).getDeviceId();

        var gpus = client.getGPUDevicesStatus();
        assertTrue(gpus.size() >= 2);
        var gpuId1 = gpus.get(0).getDeviceId();
        var gpuId2 = gpus.get(1).getDeviceId();

        var group = client.createGroup("P2PGroup");
        var groupId = group.getGroupId();
        client.groupPoolEdit(groupId);
        client.addDeviceToGroup(cpuId, groupId);
        client.addDeviceToGroup(gpuId1, groupId);
        client.addDeviceToGroup(gpuId2, groupId);
        client.groupPoolDone(group.getGroupId());

        var machine = client.createMachine(groupId, "P2PMachine");
        var machId = machine.getMachineId();
        client.editFabric(machId);
        client.addDeviceToMachine(cpuId, groupId, machId);
        client.addDeviceToMachine(gpuId1, groupId, machId);
        client.addDeviceToMachine(gpuId2, groupId, machId);
        client.reprogramFabric(machId);

        var result = client.setP2PEnabled(machId, P2PType.ON);
        assertEquals(P2PType.ON, result.getP2PEnabled());

        result = client.setP2PEnabled(machId, P2PType.OFF);
        assertEquals(P2PType.OFF, result.getP2PEnabled());

        client.deleteGroup(group.getGroupId());
    }
}
