package com.TaDuy.microservices.history_analytics_service.Controller;


import com.TaDuy.microservices.history_analytics_service.DTO.HistoryDTO;
import com.TaDuy.microservices.history_analytics_service.Payloads.ResponseData;
import com.TaDuy.microservices.history_analytics_service.Service.Imp.HistoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/history")
public class HistoryController {
    @Autowired
    HistoryServiceImp historyServiceImp;

    @GetMapping("/get")
    public ResponseEntity<?> getAllHistory(){
        ResponseData responseData = new ResponseData();
        return new ResponseEntity<>(historyServiceImp.getAllHistory(),HttpStatus.OK);
    }




}
