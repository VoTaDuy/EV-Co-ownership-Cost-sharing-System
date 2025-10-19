package com.TaDuy.microservices.history_analytics_service.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "ai_analysis")
public class AiAnalysis {
    @Id
    private String analysis_id;
    private String usage_id;
    @DBRef
    private Reports reports;
    private String key_metrics;
    private LocalDateTime analysis_date;
    private String model_used;
}
