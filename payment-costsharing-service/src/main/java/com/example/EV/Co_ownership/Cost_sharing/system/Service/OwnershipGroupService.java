package com.example.EV.Co_ownership.Cost_sharing.system.Service;

import com.example.EV.Co_ownership.Cost_sharing.system.DTO.OwnershipGroupDTO;
import com.example.EV.Co_ownership.Cost_sharing.system.EntityDataWarehouse.OwnershipGroup;
import com.example.EV.Co_ownership.Cost_sharing.system.RepositoryData.OwnershipGroupRepository;
import com.example.EV.Co_ownership.Cost_sharing.system.Service.Imp.OwnershipGroupServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnershipGroupService implements OwnershipGroupServiceImp {

    @Autowired
    OwnershipGroupRepository ownershipGroupRepository;

    @Override
    public OwnershipGroupDTO getDetailGroup(int groupId) {
        OwnershipGroup ownershipGroup = ownershipGroupRepository.findByGroupId(groupId);
        OwnershipGroupDTO ownershipGroupDTO = new OwnershipGroupDTO();
        ownershipGroupDTO.setGroupId(ownershipGroup.getGroupId());
        ownershipGroupDTO.setGroupName(ownershipGroup.getGroupName());
        ownershipGroupDTO.setOwnerId(ownershipGroup.getOwnerId());
        ownershipGroupDTO.setVehicleId(ownershipGroup.getVehicleId());
        return ownershipGroupDTO;
    }
}
