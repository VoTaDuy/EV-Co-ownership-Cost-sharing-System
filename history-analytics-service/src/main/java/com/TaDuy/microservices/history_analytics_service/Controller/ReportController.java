package com.TaDuy.microservices.history_analytics_service.Controller;

import com.TaDuy.microservices.history_analytics_service.Payloads.ResponseData;
import com.TaDuy.microservices.history_analytics_service.Service.Imp.GenimiServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private GenimiServiceImp genimiServiceImp;

    private boolean isValidDate(String input) {
        List<DateTimeFormatter> formatters = List.of(
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
        );

        for (DateTimeFormatter f : formatters) {
            try {
                LocalDate.parse(input, f);
                return true;
            } catch (Exception ignored) {}
        }
        return false;
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generateReport(@RequestBody Map<String, String> request) {
        ResponseData responseData = new ResponseData();

        try {
            String prompt = request.get("prompt");
            LocalDateTime startTime = LocalDateTime.parse(request.get("startTime"));
            LocalDateTime endTime = LocalDateTime.parse(request.get("endTime"));

            if (!isValidDate(String.valueOf(startTime)) || !isValidDate(String.valueOf(endTime))) {
                throw new IllegalArgumentException("Sai định dạng ngày (dd/MM/yyyy hoặc yyyy-MM-dd)");
            }

            Object result = genimiServiceImp.generateReport(prompt, startTime, endTime);
            responseData.setData(result);
            responseData.setSuccess(true);
            return new ResponseEntity<>(responseData, HttpStatus.OK);

        } catch (Exception e) {
            responseData.setDesc(e.getMessage());
            responseData.setSuccess(false);
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }
    }
}
