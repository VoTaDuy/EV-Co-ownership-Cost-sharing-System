package com.TaDuy.microservices.history_analytics_service.Controller;

import com.TaDuy.microservices.history_analytics_service.Payloads.ResponseData;
import com.TaDuy.microservices.history_analytics_service.Service.Imp.GenimiServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AiReportController {

    @Autowired
    private GenimiServiceImp genimiServiceImp;


//    @PostMapping(
//            value = "/gen",
//            consumes = "application/json",
//            produces = "application/json")
//    public ResponseEntity<?> generateReport(@RequestBody String input){
//        ResponseData  responseData = new ResponseData();
//        try {
//            return new ResponseEntity<>(genimiServiceImp.generateReport(input), HttpStatus.OK);
//        }catch (Exception e){
//            responseData.setData("failure Generate");
//            responseData.setDesc(e.getMessage());
//            return new ResponseEntity<>(responseData, HttpStatus.FAILED_DEPENDENCY);
//        }


//    }

}
