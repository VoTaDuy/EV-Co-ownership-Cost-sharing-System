package com.example.EV.Co_ownership.Cost_sharing.system.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer paymentId;

    @Column(name = "group_id")
    private int groupId;

    @Column(name = "user_id")
    private int userId;

    @ManyToOne
    @JoinColumn(name = "cost_id")
    private VehicleCost cost;

    @ManyToOne
    @JoinColumn(name = "fund_id")
    private GroupFund fund;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(length = 50)
    private String gateway;

    @Column(name = "gateway_order_id", length = 100)
    private String gatewayOrderId;

    @Column(name = "gateway_response", columnDefinition = "TEXT")
    private String gatewayResponse;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('pending','completed','failed','refunded') DEFAULT 'pending'")
    private PaymentStatus status = PaymentStatus.pending;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public enum PaymentStatus {
        pending, completed, failed, refunded
    }
}