package com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp;

import com.example.EV.Co_ownership.Cost_sharing.System.Entity.GroupTable;

import java.util.List;

public interface GroupTableServiceImp {
    GroupTable createGroup(GroupTable group);
    List<GroupTable> getAllGroups();
    GroupTable getGroupById(int id);
    GroupTable updateGroup(int id, GroupTable updatedGroup);
    void deleteGroup(int id);
}
