package com.TaDuy.microservices.history_analytics_service.Entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "reports")
public class Reports {
    @Id
    private String report_id;

    private String user_id;

    private String vehicle_id;

    private String type;

    private String format;

    private LocalDateTime generated_at;

    private String pdfUrl;

    private String excelUrl;

}
