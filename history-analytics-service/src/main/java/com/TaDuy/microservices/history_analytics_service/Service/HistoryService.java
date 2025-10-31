package com.TaDuy.microservices.history_analytics_service.Service;

import com.TaDuy.microservices.history_analytics_service.DTO.HistoryDTO;
import com.TaDuy.microservices.history_analytics_service.Entity.History;
import com.TaDuy.microservices.history_analytics_service.Repository.HistoryRepository;
import com.TaDuy.microservices.history_analytics_service.Service.Imp.HistoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        List<History> historyList  = historyRepository.findByRecordedAtBetween(startTime, endTime);
        if (historyList.isEmpty()){
            return "no history in this time";
        }

        StringBuilder sb = new StringBuilder();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        sb.append("History Report \n");
        sb.append(String.format("Từ: %s -> Đến: %s\n\n",
                startTime.format(dateTimeFormatter),
                endTime.format(dateTimeFormatter)));
        int index = 1;
        for (History h : historyList){
            sb.append(String.format("%d. UserID: %s | VehicleID: %s | Distance: %.2f km | Cost: %.2f | Fuel: %.2f :\n",
                    index++,
                    h.getUserId(),
                    h.getVehicleId(),
                    h.getDistanceKm(),
                    h.getStartTime() != null ? h.getStartTime().format(dateTimeFormatter) :"N/A",
                    h.getEndTime() != null ? h.getEndTime().format(dateTimeFormatter): "N/A",
                    h.getUsageCost(),
                    h.getFuelUsed())
            );
        }
        return sb.toString();
    }


}


