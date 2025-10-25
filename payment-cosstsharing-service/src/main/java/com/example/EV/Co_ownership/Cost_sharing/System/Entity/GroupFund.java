package com.example.EV.Co_ownership.Cost_sharing.System.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity (name = "group_fund")
public class GroupFund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fund_id;


    @Column (name = "fund_name")
    private String fund_name;

    @Column (name = "balance")
    private double balance;

    @Column (name = "created_by")
    private String created_by;

    @Column (name = "created_at")
    private LocalDateTime created_at;

    @Column (name = "update_at")
    private LocalDateTime updated_at;


}
