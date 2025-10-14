package com.TaDuy.microservices.history_analytics_service.DTO;


import java.time.LocalDateTime;

public class aiAnalysisDTO {
    private String analysis_id;
    private String usage_id;
    private String report_id;
    private String key_metrics;
    private LocalDateTime analysis_date;
    private String model_used;

    public String getAnalysis_id() {
        return analysis_id;
    }

    public void setAnalysis_id(String analysis_id) {
        this.analysis_id = analysis_id;
    }

    public String getUsage_id() {
        return usage_id;
    }

    public void setUsage_id(String usage_id) {
        this.usage_id = usage_id;
    }

    public String getReport_id() {
        return report_id;
    }

    public void setReport_id(String report_id) {
        this.report_id = report_id;
    }

    public String getKey_metrics() {
        return key_metrics;
    }

    public void setKey_metrics(String key_metrics) {
        this.key_metrics = key_metrics;
    }

    public LocalDateTime getAnalysis_date() {
        return analysis_date;
    }

    public void setAnalysis_date(LocalDateTime analysis_date) {
        this.analysis_date = analysis_date;
    }

    public String getModel_used() {
        return model_used;
    }

    public void setModel_used(String model_used) {
        this.model_used = model_used;
    }
}
