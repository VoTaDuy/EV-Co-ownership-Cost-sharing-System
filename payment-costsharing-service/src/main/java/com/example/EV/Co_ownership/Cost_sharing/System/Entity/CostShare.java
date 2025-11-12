package com.example.EV.Co_ownership.Cost_sharing.System.Entity;

import com.example.EV.Co_ownership.Cost_sharing.System.Enum.CostShareStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cost_shares")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CostShare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "share_id")
    private Integer shareId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cost_id", nullable = false)
    private VehicleCost cost;

    @Column(name = "user_id", nullable = false, length = 255)
    private String userId;

    @Column(name = "share_percentage", nullable = false, columnDefinition = "DECIMAL(5,2)")
    private BigDecimal sharePercentage;

    @Column(name = "amount_due", nullable = false, columnDefinition = "DECIMAL(15,2)")
    private BigDecimal amountDue;

    @Column(name = "settled_amount", columnDefinition = "DECIMAL(15,2) DEFAULT 0.00")
    private BigDecimal settledAmount = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('unpaid','partial','paid') DEFAULT 'unpaid'")
    private CostShareStatus status = CostShareStatus.unpaid;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}