package com.TaDuy.microservices.history_analytics_service.Service;

import com.TaDuy.microservices.history_analytics_service.DTO.HistoryDTO;
import com.TaDuy.microservices.history_analytics_service.Entity.History;
import com.TaDuy.microservices.history_analytics_service.Repository.HistoryRepository;
import com.TaDuy.microservices.history_analytics_service.Service.Imp.HistoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryService implements HistoryServiceImp {

    @Autowired
    HistoryRepository historyRepository;

    @Override
    public List<HistoryDTO> getAllHistory() {
        List<History> historyList= historyRepository.findAll();
        List<HistoryDTO> historyDTOList= new ArrayList<>();
        for (History history : historyList){
            HistoryDTO historyDTO = new HistoryDTO();
            historyDTO.setHistory_id(history.getHistory_id());
            historyDTO.setVehicleId(history.getVehicleId());
            historyDTO.setUserId(history.getUserId());
            historyDTO.setDistanceKm(history.getDistanceKm());
            historyDTO.setStartTime(history.getStartTime());
            historyDTO.setEndTime(history.getEndTime());
            historyDTO.setUsageCost(history.getUsageCost());
            historyDTO.setFuelUsed(history.getFuelUsed());
            historyDTO.setRecordedAt(history.getRecordedAt());
            historyDTOList.add(historyDTO);
        }
        return historyDTOList;
    }

    @Override
    public String convertHistoryListToString(LocalDateTime startTime, LocalDateTime endTime) {
        List<History> historyList  = historyRepository.findGeneratedAtBetween(startTime, endTime);

        return "";
    }


}


