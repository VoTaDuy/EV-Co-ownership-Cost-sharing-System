package com.example.EV.Co_ownership.Cost_sharing.system.EntityDataWarehouse;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;


@Entity(name = "group_members")
public class GroupMember {

    @Id
    @Column(name = "member_id")
    private int memberId;

    @Column(name = "group_id")
    private int groupId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "role")
    private String role;

    @Column(name = "points")
    private int points;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public int getMemberId() {
        return memberId;
    }

    public int getGroupId() {
        return groupId;
    }

    public int getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }

    public int getPoints() {
        return points;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
