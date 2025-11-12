package com.TaDuy.microservices.history_analytics_service.Service;
import com.TaDuy.microservices.history_analytics_service.DTO.HistoryDTO;
import com.TaDuy.microservices.history_analytics_service.Entity.History;
import com.TaDuy.microservices.history_analytics_service.Repository.HistoryRepository;
import com.TaDuy.microservices.history_analytics_service.Service.Imp.HistoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryService implements HistoryServiceImp {
    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    HistoryRepository historyRepository;

    @Override
    public List<HistoryDTO> getAllHistory() {
        List<History> historyList = historyRepository.findAll();
        List<HistoryDTO> historyDTOList = new ArrayList<>();

        for (History history : historyList) {
            HistoryDTO dto = new HistoryDTO();
            dto.setId(history.getId());
            dto.setUsageId(history.getUsageId());
            dto.setBookingId(history.getBookingId());
            dto.setUserId(history.getUserId());
            dto.setVehicleId(history.getVehicleId());
            dto.setStartDate(history.getStartDate());
            dto.setEndDate(history.getEndDate());
            dto.setCheckInTime(history.getCheckInTime());
            dto.setCheckOutTime(history.getCheckOutTime());
            dto.setVehicleCondition(history.getVehicleCondition());
            dto.setDistance(history.getDistance());
            dto.setRecordTime(history.getRecordTime());
            historyDTOList.add(dto);
        }

        return historyDTOList;
    }

    @Override
    public List<HistoryDTO> getHistoryByUserId(String userId) {
        List<History> historyList = historyRepository.findHistoryByUserId(userId);
        List<HistoryDTO> historyDTOList = new ArrayList<>();

        for (History history : historyList) {
            HistoryDTO dto = new HistoryDTO();
            dto.setId(history.getId());
            dto.setUsageId(history.getUsageId());
            dto.setBookingId(history.getBookingId());
            dto.setUserId(history.getUserId());
            dto.setVehicleId(history.getVehicleId());
            dto.setStartDate(history.getStartDate());
            dto.setEndDate(history.getEndDate());
            dto.setCheckInTime(history.getCheckInTime());
            dto.setCheckOutTime(history.getCheckOutTime());
            dto.setVehicleCondition(history.getVehicleCondition());
            dto.setDistance(history.getDistance());
            dto.setRecordTime(history.getRecordTime());
            historyDTOList.add(dto);
        }

        return historyDTOList;
    }


    @Override
    public String convertHistoryListToString(String startTimeStr, String endTimeStr) {
        List<History> historyList = historyRepository.findByRecordTimeBetween(startTimeStr, endTimeStr);

        if (historyList.isEmpty()) {
            return "No history records in this time range.";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("History Report\n");
        sb.append(String.format("From: %s -> To: %s\n\n", startTimeStr, endTimeStr));

        int index = 1;
        for (History h : historyList) {
            sb.append(String.format(
                    "%d. UsageID: %s | BookingID: %s | UserID: %s | VehicleID: %s\n" +
                            "   Start Date: %s | End Date: %s | Check-in: %s | Check-out: %s | Distance: %.2f km\n" +
                            "   Vehicle Condition: %s\n\n",
                    index++,
                    h.getUsageId() != null ? h.getUsageId() : "N/A",
                    h.getBookingId() != null ? h.getBookingId() : "N/A",
                    h.getUserId() != null ? h.getUserId() : "N/A",
                    h.getVehicleId() != null ? h.getVehicleId() : "N/A",
                    h.getStartDate() != null ? h.getStartDate() : "N/A",
                    h.getEndDate() != null ? h.getEndDate() : "N/A",
                    h.getCheckInTime() != null ? h.getCheckInTime() : "N/A",
                    h.getCheckOutTime() != null ? h.getCheckOutTime() : "N/A",
                    h.getDistance() != null ? h.getDistance() : 0.0,
                    h.getVehicleCondition() != null ? h.getVehicleCondition() : "N/A"
            ));
        }

        return sb.toString();
    }
    @Override
    public String convertUserHistoryToString(String userId, String startTime, String endTime) {
        List<History> historyList = historyRepository.findByUserIdAndRecordTimeBetween(userId, startTime, endTime);
        if (historyList.isEmpty()) {
            return "No history records for user " + userId + " in this time range.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("History for user ").append(userId).append("\n");
        sb.append(String.format("From: %s -> To: %s\n\n", startTime, endTime));

        int index = 1;
        for (History h : historyList) {
            sb.append(String.format(
                    "%d. UsageID: %s | BookingID: %s | VehicleID: %s\n" +
                            "   Start: %s | End: %s | Distance: %.2f km | Vehicle Condition: %s\n\n",
                    index++,
                    h.getUsageId() != null ? h.getUsageId() : "N/A",
                    h.getBookingId() != null ? h.getBookingId() : "N/A",
                    h.getVehicleId() != null ? h.getVehicleId() : "N/A",
                    h.getStartDate() != null ? h.getStartDate() : "N/A",
                    h.getEndDate() != null ? h.getEndDate() : "N/A",
                    h.getDistance() != null ? h.getDistance() : 0.0,
                    h.getVehicleCondition() != null ? h.getVehicleCondition() : "N/A"
            ));
        }
        return sb.toString();
    }
}


