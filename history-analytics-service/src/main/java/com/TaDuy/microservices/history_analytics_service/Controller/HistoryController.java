package com.TaDuy.microservices.history_analytics_service.Controller;


import com.TaDuy.microservices.history_analytics_service.DTO.HistoryDTO;
import com.TaDuy.microservices.history_analytics_service.Payloads.ResponseData;
import com.TaDuy.microservices.history_analytics_service.Service.Imp.HistoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/history")
public class HistoryController {
    @Autowired
    HistoryServiceImp historyServiceImp;

    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getHistoryByUserId(@PathVariable  String userId){
        List<HistoryDTO> historyDTOList  =historyServiceImp.getHistoryByUserId(userId);

        return new ResponseEntity<>(historyDTOList ,HttpStatus.OK);
    };


//    @GetMapping("/get/{groupId}")
//    public ResponseEntity<?> getHistoryByGroupId(){
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }


    @GetMapping("/get")
    public ResponseEntity<?> getAllHistory(){
        historyServiceImp.getAllHistory();
        return new ResponseEntity<>(historyServiceImp,HttpStatus.OK);
    }

    public ResponseEntity<?> getPromptFromHistory(){

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
