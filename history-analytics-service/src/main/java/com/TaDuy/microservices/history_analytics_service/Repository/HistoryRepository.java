package com.TaDuy.microservices.history_analytics_service.Repository;

import com.TaDuy.microservices.history_analytics_service.Entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
    public interface HistoryRepository extends JpaRepository<History, Integer> {
    List<History> findByRecordTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

    List<History> findByUserIdAndRecordTimeBetween(Integer userId, LocalDateTime startTime, LocalDateTime endTime);

    List<History> findHistoryByUserId(Integer userId);
}
