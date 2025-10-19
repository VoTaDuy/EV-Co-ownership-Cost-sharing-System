package com.TaDuy.microservices.history_analytics_service.Service;


import com.TaDuy.microservices.history_analytics_service.Config.GenimiConfig;
import com.TaDuy.microservices.history_analytics_service.Service.Imp.GenimiServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

@Service
public class GenimiService implements GenimiServiceImp {

    @Autowired
    private GenimiConfig genimiConfig;

    private final RestTemplate restTemplate = new RestTemplate();


    @Override
    public String generateReport(String prompt) {
        String url = genimiConfig.getUrl() + "?key=" + genimiConfig.getApiKey();

        Map<String, Object> body = Map.of(
                "Contents", new Object[]{
                        Map.of("parts", new Object[]{Map.of("text", prompt)})
                }
        );
        return "";
    }
}
