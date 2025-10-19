package com.TaDuy.microservices.history_analytics_service.Service;


import com.TaDuy.microservices.history_analytics_service.Config.GenimiConfig;
import com.TaDuy.microservices.history_analytics_service.Service.Imp.GenimiServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

import static javax.swing.UIManager.get;

@Service
public class GenimiService implements GenimiServiceImp {

    @Autowired
    private GenimiConfig genimiConfig;

    private final RestTemplate restTemplate = new RestTemplate();


    @Override
    public String generateReport(String prompt) {
        String url = genimiConfig.getUrl() + "?key=" + genimiConfig.getApiKey();

        System.out.println(url);

        Map<String, Object> body = Map.of(
                "contents", new Object[]{
                        Map.of("parts", new Object[]{Map.of("text", prompt)})
                }
        );
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String,Object>> request = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

        if (response.getBody() == null) return "no response from genimi. ";
        try {
            Map content = (Map) ((Map)((java.util.List<?>)response.getBody().get("candidates")).get(0)).get("content");
            Map part = (Map) ((java.util.List)content.get("parts")).get(0);

            return part.get("text").toString();
        }catch (Exception e ){
            return "Error analysis response from genimi: " + e.getMessage();
        }
    }
}
