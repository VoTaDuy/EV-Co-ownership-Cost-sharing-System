package com.example.EV_Co_ownership.User_and_ownership_service.Controller;

import com.example.EV_Co_ownership.User_and_ownership_service.DTO.MemberDTO;
import com.example.EV_Co_ownership.User_and_ownership_service.Payloads.Request.AddMemberRequest;
import com.example.EV_Co_ownership.User_and_ownership_service.Payloads.Request.CreateGroupRequest;
import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Groups;
import com.example.EV_Co_ownership.User_and_ownership_service.Service.GroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/groups")
@CrossOrigin("*")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/create")
    public ResponseEntity<?> createGroup(@RequestBody CreateGroupRequest request) {
        if (request.getGroupName() == null || request.getGroupName().isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Tên nhóm không được để trống."));
        }

        try {
            Groups newGroup = groupService.createGroup(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(newGroup);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/add-member")
    public ResponseEntity<?> addMember(@RequestBody AddMemberRequest request) {
        if (request.getOwnershipRatio() == null
                || request.getOwnershipRatio().compareTo(BigDecimal.ZERO) < 0
                || request.getOwnershipRatio().compareTo(BigDecimal.valueOf(100)) > 0) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Tỷ lệ sở hữu phải từ 0% đến 100%"));
        }

        try {
            MemberDTO newMember = groupService.addOrUpdateMember(
                    request.getGroupId(),
                    request.getUserId(),
                    request.getOwnershipRatio()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(newMember);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", e.getMessage()));
        }
    }
}
