package com.TaDuy.microservices.history_analytics_service.Controller;

import com.TaDuy.microservices.history_analytics_service.Payloads.ResponseData;
import com.TaDuy.microservices.history_analytics_service.Service.Imp.GenimiServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
            String startTimeStr = request.get("startTime");
            String endTimeStr = request.get("endTime");

            // Sử dụng định dạng ISO hoặc định dạng bạn gửi
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime startTime = LocalDateTime.parse(startTimeStr, formatter);
            LocalDateTime endTime = LocalDateTime.parse(endTimeStr, formatter);

            Object result = genimiServiceImp.generateReport(prompt, startTimeStr, endTimeStr);
            responseData.setData(result);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } catch (Exception e) {
            responseData.setDesc(e.getMessage());
            return new ResponseEntity<>(responseData, HttpStatus.FAILED_DEPENDENCY);
        }
    }

}
