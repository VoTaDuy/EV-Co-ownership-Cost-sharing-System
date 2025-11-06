package com.TaDuy.microservices.history_analytics_service.Service.Imp;

import com.TaDuy.microservices.history_analytics_service.DTO.HistoryDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface HistoryServiceImp {
    List<HistoryDTO> getAllHistory();
    List<HistoryDTO> getHistoryByUserId(String userId);
    String convertHistoryListToString(LocalDateTime startTime, LocalDateTime endTime);
}
