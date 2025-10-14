package com.TaDuy.microservices.history_analytics_service.Repository;

import com.TaDuy.microservices.history_analytics_service.Entity.aiAnalysis;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface aiAnalysisRepository extends MongoRepository<aiAnalysis, String > {
}
