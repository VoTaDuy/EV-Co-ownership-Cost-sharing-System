package com.TaDuy.microservices.history_analytics_service.Repository;

import com.TaDuy.microservices.history_analytics_service.Entity.Reports;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends MongoRepository<Reports, String> {
}
