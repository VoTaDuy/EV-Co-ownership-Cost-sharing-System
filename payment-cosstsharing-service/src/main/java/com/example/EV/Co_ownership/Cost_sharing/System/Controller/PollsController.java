package com.example.EV.Co_ownership.Cost_sharing.System.Controller;

import com.example.EV.Co_ownership.Cost_sharing.System.DTO.PollsDTO;
import com.example.EV.Co_ownership.Cost_sharing.System.Entity.Polls;
import com.example.EV.Co_ownership.Cost_sharing.System.Payloads.ResponseData;
import com.example.EV.Co_ownership.Cost_sharing.System.Service.Imp.PollsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/polls")
public class PollsController {

    @Autowired
    PollsServiceImp pollsServiceImp;

    @PostMapping("/createPolls")
    public ResponseEntity<?> createPolls(@RequestBody PollsDTO dto) {
        ResponseData responseData = new ResponseData();
        PollsDTO created = pollsServiceImp.createPolls(dto);

        responseData.setData(created);
        responseData.setDesc("Tao Polls");
        return new ResponseEntity<>(responseData, HttpStatus.CREATED);

    }


}
