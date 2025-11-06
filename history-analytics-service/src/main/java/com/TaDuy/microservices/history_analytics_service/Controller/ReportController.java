package com.TaDuy.microservices.history_analytics_service.Controller;

import com.TaDuy.microservices.history_analytics_service.Payloads.ResponseData;
import com.TaDuy.microservices.history_analytics_service.Service.Imp.GenimiServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private GenimiServiceImp genimiServiceImp;

    @PostMapping("/generate")
    public ResponseEntity<?> generateReport(@RequestBody Map<String,String> request) {
        ResponseData responseData = new ResponseData();

        try {
            String prompt = request.get("prompt");
            LocalDateTime startTime = LocalDateTime.parse(request.get("startTime"));
            LocalDateTime endTime = LocalDateTime.parse(request.get("endTime"));
            Object result = genimiServiceImp.generateReport(prompt, startTime, endTime);
            responseData.setData(result);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }catch (Exception e){
            responseData.setDesc(e.getMessage());
            return new ResponseEntity<>(responseData, HttpStatus.FAILED_DEPENDENCY);
        }
    }
}
