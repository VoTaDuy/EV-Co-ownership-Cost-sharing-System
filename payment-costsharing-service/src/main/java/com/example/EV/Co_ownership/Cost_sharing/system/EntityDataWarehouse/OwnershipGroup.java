package com.example.EV.Co_ownership.Cost_sharing.system.EntityDataWarehouse;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity(name = "ownership_groups")
public class OwnershipGroup {

    @Id
    @Column(name = "group_id")
    private int groupId;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "owner_id")
    private int ownerId;

    @Column(name = "vehicle_id")
    private int vehicleId;

    @Column(name = "member_count")
    private int memberCount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public int getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
