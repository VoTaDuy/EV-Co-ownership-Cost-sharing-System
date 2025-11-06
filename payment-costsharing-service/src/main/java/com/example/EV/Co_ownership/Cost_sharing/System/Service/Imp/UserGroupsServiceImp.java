package com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp;


import com.example.EV.Co_ownership.Cost_sharing.System.Entity.UserGroups;

import java.util.List;

public interface UserGroupsServiceImp {
    List<UserGroups> getAllUserGroups();
    UserGroups getUserGroupById(int id);
    List<UserGroups> getUserGroupsByGroupId(int groupId);
    List<UserGroups> getUserGroupsByUserId(int userId);
    UserGroups createUserGroup(UserGroups userGroup);
    UserGroups updateUserGroup(int id, UserGroups userGroup);
    void deleteUserGroup(int id);

}


