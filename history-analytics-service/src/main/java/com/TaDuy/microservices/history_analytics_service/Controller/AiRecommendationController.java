package com.TaDuy.microservices.history_analytics_service.Controller;

import com.TaDuy.microservices.history_analytics_service.Payloads.ResponseData;
import com.TaDuy.microservices.history_analytics_service.Service.Imp.GenimiServiceImp;
import com.TaDuy.microservices.history_analytics_service.Service.Imp.RecommendationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/past")
public class AiRecommendationController {

    @Autowired
    private GenimiServiceImp genimiServiceImp;

    @Autowired
    private RecommendationServiceImp recommendationServiceImp;

    @PostMapping("/generate")
    public ResponseEntity<?> generateRecommendation(@RequestBody Map<Integer, String> request) {
        ResponseData responseData = new ResponseData();

        try {
            Integer userId = Integer.valueOf(request.get("userId"));
            String daysRange = request.get("daysRange");

            String aiText = recommendationServiceImp.generateRecommendation(userId, daysRange);

            Map<String, Object> structuredResult = Map.of(
                    "success", true,
                    "userId", userId,
                    "recommendations", Map.of(
                            "vehicleType", "SUV, SUV hạng sang, SUV tiết kiệm nhiên liệu, SUV 7 chỗ",
                            "rentalDuration", "2-3 ngày, các gói thuê ngắn ngày",
                            "otherRecommendations", new String[]{
                                    "Kiểm tra và bảo dưỡng xe định kỳ",
                                    "Cung cấp khuyến mãi cho khách hàng thân thiết",
                                    "Gợi ý các dịch vụ đi kèm như thuê tài xế, bảo hiểm, GPS",
                                    "Thu thập phản hồi từ user",
                                    "Xác minh tính nhất quán của dữ liệu lịch sử"
                            }
                    )
            );

            responseData.setData(structuredResult);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } catch (Exception e) {
            responseData.setDesc(e.getMessage());
            return new ResponseEntity<>(responseData, HttpStatus.FAILED_DEPENDENCY);
        }
    }

}
