package com.TaDuy.microservices.history_analytics_service.Service.Imp;

import java.time.LocalDateTime;

public interface GenimiServiceImp  {
        String generateReport(String prompt, String startTime, String endTime);
}
