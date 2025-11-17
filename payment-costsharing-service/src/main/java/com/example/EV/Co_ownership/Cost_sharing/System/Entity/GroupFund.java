package com.example.EV.Co_ownership.Cost_sharing.System.Entity;

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
@Table(name = "group_fund")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class GroupFund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fund_id")
    private Integer fundId;

    @Column(name = "group_id", nullable = false, length = 255)
    private int groupId;

    @Column(name = "fund_name", nullable = false, length = 255)
    private String fundName;

    @Column(nullable = false, columnDefinition = "DECIMAL(15,2) DEFAULT 0.00")
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(name = "created_by")
    private int createdBy;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}