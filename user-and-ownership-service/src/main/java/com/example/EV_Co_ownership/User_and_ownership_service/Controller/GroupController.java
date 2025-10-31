package com.example.EV_Co_ownership.User_and_ownership_service.Controller;

import com.example.EV_Co_ownership.User_and_ownership_service.Entity.GroupMembers;
import com.example.EV_Co_ownership.User_and_ownership_service.Entity.GroupUsers;
import com.example.EV_Co_ownership.User_and_ownership_service.Service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/create")
    public GroupUsers createGroup(@RequestBody GroupUsers group) {
        return groupService.createGroup(group);
    }

    @PostMapping("/{groupId}/add-member/{userId}")
    public GroupMembers addMember(
            @PathVariable int groupId,
            @PathVariable int userId,
            @RequestParam(required = false, defaultValue = "0") Float ownershipRatio) {
        return groupService.addMember(userId, groupId, ownershipRatio);
    }

    @GetMapping("/user/{userId}")
    public List<GroupMembers> getGroupsByUser(@PathVariable int userId) {
        return groupService.getGroupsByUserId(userId);
    }

    @GetMapping("/{groupId}/members")
    public List<GroupMembers> getMembersByGroup(@PathVariable int groupId) {
        return groupService.getMembersByGroupId(groupId);
    }
}
