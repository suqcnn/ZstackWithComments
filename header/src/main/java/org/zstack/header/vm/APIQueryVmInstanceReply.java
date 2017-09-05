package org.zstack.header.vm;

import org.zstack.header.allocator.HostAllocatorConstant;
import org.zstack.header.query.APIQueryReply;
import org.zstack.header.rest.RestResponse;
import org.zstack.header.volume.VolumeInventory;
import org.zstack.header.volume.VolumeState;
import org.zstack.header.volume.VolumeStatus;
import org.zstack.header.volume.VolumeType;
import org.zstack.utils.data.SizeUnit;

import java.sql.Timestamp;
import java.util.List;

import static org.codehaus.groovy.runtime.InvokerHelper.asList;

@RestResponse(allTo = "inventories")
public class APIQueryVmInstanceReply extends APIQueryReply {
    private List<VmInstanceInventory> inventories;

    public List<VmInstanceInventory> getInventories() {
        return inventories;
    }

    public void setInventories(List<VmInstanceInventory> inventories) {
        this.inventories = inventories;
    }

    public static APIQueryVmInstanceReply __example__() {
        APIQueryVmInstanceReply reply = new APIQueryVmInstanceReply();

        String defaultL3Uuid = uuid();
        String rootVolumeUuid = uuid();

        VmInstanceInventory vm = new VmInstanceInventory();
        vm.setName("Test-VM");
        vm.setUuid(uuid());
        vm.setAllocatorStrategy(HostAllocatorConstant.LAST_HOST_PREFERRED_ALLOCATOR_STRATEGY_TYPE);
        vm.setClusterUuid(uuid());
        vm.setCpuNum(1);
        vm.setCreateDate(new Timestamp(System.currentTimeMillis()));
        vm.setDefaultL3NetworkUuid(defaultL3Uuid);
        vm.setDescription("web server VM");
        vm.setHostUuid(uuid());
        vm.setHypervisorType("KVM");
        vm.setImageUuid(uuid());
        vm.setInstanceOfferingUuid(uuid());
        vm.setLastHostUuid(uuid());
        vm.setMemorySize(SizeUnit.GIGABYTE.toByte(8));
        vm.setPlatform("Linux");
        vm.setRootVolumeUuid(rootVolumeUuid);
        vm.setState(VmInstanceState.Running.toString());
        vm.setType(VmInstanceConstant.USER_VM_TYPE);
        vm.setLastOpDate(new Timestamp(System.currentTimeMillis()));
        vm.setZoneUuid(uuid());

        VolumeInventory vol = new VolumeInventory();
        vol.setName(String.format("Root-Volume-For-VM-%s", vm.getUuid()));
        vol.setCreateDate(new Timestamp(System.currentTimeMillis()));
        vol.setLastOpDate(new Timestamp(System.currentTimeMillis()));
        vol.setType(VolumeType.Root.toString());
        vol.setUuid(rootVolumeUuid);
        vol.setSize(SizeUnit.GIGABYTE.toByte(100));
        vol.setActualSize(SizeUnit.GIGABYTE.toByte(20));
        vol.setDeviceId(0);
        vol.setState(VolumeState.Enabled.toString());
        vol.setFormat("qcow2");
        vol.setDiskOfferingUuid(uuid());
        vol.setInstallPath(String.format("/zstack_ps/rootVolumes/acct-36c27e8ff05c4780bf6d2fa65700f22e/vol-%s/%s.qcow2", rootVolumeUuid, rootVolumeUuid));
        vol.setStatus(VolumeStatus.Ready.toString());
        vol.setPrimaryStorageUuid(uuid());
        vol.setVmInstanceUuid(vm.getUuid());
        vol.setRootImageUuid(vm.getImageUuid());
        vm.setAllVolumes(asList(vol));

        VmNicInventory nic = new VmNicInventory();
        nic.setVmInstanceUuid(vm.getUuid());
        nic.setCreateDate(vm.getCreateDate());
        nic.setLastOpDate(vm.getLastOpDate());
        nic.setDeviceId(0);
        nic.setGateway("192.168.1.1");
        nic.setIp("192.168.1.10");
        nic.setL3NetworkUuid(defaultL3Uuid);
        nic.setNetmask("255.255.255.0");
        nic.setMac("00:0c:29:bd:99:fc");
        nic.setUsedIpUuid(uuid());
        nic.setUuid(uuid());
        vm.setVmNics(asList(nic));

        reply.setInventories(asList(vm));

        return reply;
    }
}
