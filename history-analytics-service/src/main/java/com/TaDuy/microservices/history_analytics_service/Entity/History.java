package com.TaDuy.microservices.history_analytics_service.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "usage_record")
public class History {

    @Id
    @Column(name = "usage_id")
    private String usageId;

    @Column(name = "booking_id")
    private String bookingId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "vehicle_id")
    private String vehicleId;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "check_in_time")
    private String checkInTime;

    @Column(name = "check_out_time")
    private String checkOutTime;

    @Column(name = "vehicle_condition")
    private String vehicleCondition;

    @Column(name = "distance")
    private Double distance;

    @Column(name = "record_time")
    private LocalDateTime recordTime;

    public String getUsageId() {
        return usageId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public String getVehicleCondition() {
        return vehicleCondition;
    }

    public Double getDistance() {
        return distance;
    }

    public LocalDateTime getRecordTime() {
        return recordTime;
    }
}
