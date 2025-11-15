package com.example.EV.Co_ownership.Cost_sharing.System.Entity;

import com.example.EV.Co_ownership.Cost_sharing.System.Enum.VehicleCostStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicle_cost")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cost_id")
    private Integer costId;

    @Column(name = "group_id")
    private int groupId;

    @ManyToOne
    @JoinColumn(name = "fund_id")
    private GroupFund fund;

    @Column(name = "vehicle_id", length = 255)
    private int vehicleId;

    @Column(name = "cost_name", nullable = false, length = 255)
    private String costName;

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('pending','paid','cancelled') DEFAULT 'pending'")
    private VehicleCostStatus status = VehicleCostStatus.pending;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}