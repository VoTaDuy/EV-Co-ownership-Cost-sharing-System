package com.TaDuy.microservices.history_analytics_service.DTO;

import java.time.LocalDateTime;

public class reportDTO {
    private String report_id;

    private String user_id;

    private String vehicle_id;

    private String type;

    private String format;

    private LocalDateTime generated_at;

    public String getReport_id() {
        return report_id;
    }

    public void setReport_id(String report_id) {
        this.report_id = report_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(String vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public LocalDateTime getGenerated_at() {
        return generated_at;
    }

    public void setGenerated_at(LocalDateTime generated_at) {
        this.generated_at = generated_at;
    }
}
