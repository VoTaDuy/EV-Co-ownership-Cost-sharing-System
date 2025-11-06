package com.example.EV.Co_ownership.Cost_sharing.System.Controller;

import com.example.EV.Co_ownership.Cost_sharing.System.Entity.UserGroups;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.UserGroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usergroups")
public class UserGroupsController {
    @Autowired
    private UserGroupsService userGroupsService;

    @GetMapping("/all")
    public List<UserGroups> getAll() {
        return userGroupsService.getAllUserGroups();
    }

    @GetMapping("/{id}")
    public UserGroups getById(@PathVariable int id) {
        return userGroupsService.getUserGroupById(id);
    }

    @GetMapping("/group/{group_id}")
    public List<UserGroups> getByGroupId(@PathVariable int group_id) {
        return userGroupsService.getUserGroupsByGroupId(group_id);
    }

    @GetMapping("/user/{user_id}")
    public List<UserGroups> getByUserId(@PathVariable int user_id) {
        return userGroupsService.getUserGroupsByUserId(user_id);
    }

    @PostMapping("/add")
    public UserGroups create(@RequestBody UserGroups userGroup) {
        return userGroupsService.createUserGroup(userGroup);
    }

    @PutMapping("/{id}")
    public UserGroups update(@PathVariable int id, @RequestBody UserGroups userGroup) {
        return userGroupsService.updateUserGroup(id, userGroup);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        userGroupsService.deleteUserGroup(id);
        return ResponseEntity.ok("Đã xóa user_group có ID = " + id);
    }
}