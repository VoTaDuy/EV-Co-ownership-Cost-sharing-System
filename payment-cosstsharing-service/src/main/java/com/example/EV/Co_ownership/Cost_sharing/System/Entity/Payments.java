package com.example.EV.Co_ownership.Cost_sharing.System.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "payments")
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payment_id;

    @Column (name = "group_id")
    private int group_id;
    @Column (name = "user_id")
    private int user_id;
    @Column (name = "cost_id")
    private int cost_id;
    @Column (name = "fund_id")
    private int fund_id;
    @Column (name = "amount")
    private BigDecimal amount;
    @Column (name = "payment_date")
    private LocalDateTime payment_date;
    @Column (name = "created_at")
    private LocalDateTime created_at;

    @Enumerated(EnumType.STRING)
    @Column (name = "status")
    private status status;
    public enum status{
        PENDING,FAILED,COMPLETED
    }


}
