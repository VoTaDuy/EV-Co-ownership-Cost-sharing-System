package com.TaDuy.microservices.history_analytics_service.Repository;

import com.TaDuy.microservices.history_analytics_service.Entity.History;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends MongoRepository<History, String> {
    List<History> findByRecordTimeBetween(String startTime, String endTime);

    List<History> findByUserIdAndRecordTimeBetween(String userId, String startTime, String endTime);

    List<History> findHistoryByUserId(String userId);
}
