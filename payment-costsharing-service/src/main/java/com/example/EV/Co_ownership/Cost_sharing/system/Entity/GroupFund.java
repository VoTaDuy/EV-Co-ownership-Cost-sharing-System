package com.example.EV.Co_ownership.Cost_sharing.system.Entity;

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

    @Column(name = "group_id")
    private int groupId;

    @Column(name = "fund_name")
    private String fundName;

    @Column(name = "balance", nullable = false, precision = 15, scale = 2)
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