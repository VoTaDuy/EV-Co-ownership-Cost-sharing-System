package com.TaDuy.microservices.history_analytics_service.Service;

import com.TaDuy.microservices.history_analytics_service.Config.GenimiConfig;
import com.TaDuy.microservices.history_analytics_service.Entity.History;
import com.TaDuy.microservices.history_analytics_service.Repository.HistoryRepository;
import com.TaDuy.microservices.history_analytics_service.Service.Imp.HistoryServiceImp;
import com.TaDuy.microservices.history_analytics_service.Service.Imp.RecommendationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;


@Service
public class RecommendationService implements RecommendationServiceImp {

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    HistoryServiceImp historyServiceImp;

    @Autowired
    private GenimiConfig genimiConfig;

    private final RestTemplate restTemplate = new RestTemplate();

    public String generateRecommendation(String userId, String daysRange) {
        String url = genimiConfig.getUrl() + "?key=" + genimiConfig.getApiKey();

        int days = (daysRange != null) ? Integer.parseInt(daysRange) : 10;
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = endTime.minusDays(days);

        String historyText = historyServiceImp.convertUserHistoryToString(userId, startTime.toString(), endTime.toString());
        String fullPrompt = "Dựa trên lịch sử của user " + userId + " từ " +
                startTime + " đến " + endTime + ":\n" + historyText +
                "\nHãy đưa ra gợi ý thuê xe, thời gian, và các khuyến nghị khác.";

        Map<String, Object> body = Map.of(
                "contents", new Object[]{
                        Map.of("parts", new Object[]{Map.of("text", fullPrompt)})
                }
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

        if (response.getBody() == null) return "No response from Genimi.";

        try {
            Map content = (Map) ((Map)((java.util.List<?>)response.getBody().get("candidates")).get(0)).get("content");
            Map part = (Map) ((java.util.List)content.get("parts")).get(0);
            return part.get("text").toString();
        } catch (Exception e) {
            return "Error analyzing AI response: " + e.getMessage();
        }
    }


}
