package com.example.EV.Co_ownership.Cost_sharing.system.Controller;


import com.example.EV.Co_ownership.Cost_sharing.system.DTO.GroupMemberDTO;
import com.example.EV.Co_ownership.Cost_sharing.system.Service.Imp.GroupMemberServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/payment/group_member")
@CrossOrigin(origins = "http://localhost:3000")
public class GroupMemberController {

    @Autowired
    GroupMemberServiceImp groupMemberServiceImp;

    @GetMapping("{userId}")
    public ResponseEntity<?> responseEntity (@PathVariable int userId){
        List<GroupMemberDTO> groupMemberDTOList = groupMemberServiceImp.getGroupForUserId(userId);
        return new ResponseEntity<>(groupMemberDTOList, HttpStatus.OK);
    }
}
