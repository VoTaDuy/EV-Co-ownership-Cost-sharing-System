package com.TaDuy.microservices.history_analytics_service.Repository;

import com.TaDuy.microservices.history_analytics_service.Entity.AiAnalysis;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AiAnalysisRepository extends MongoRepository<AiAnalysis, String> {
}

