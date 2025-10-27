package com.example.EV.Co_ownership.Cost_sharing.System.DTO;

import com.example.EV.Co_ownership.Cost_sharing.System.Entity.VehicleCost;
import com.example.EV.Co_ownership.Cost_sharing.System.Enum.PollStatus;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
public class PollsDTO {
    private int id;
    private int group_id;
    private int cost_id;
    private String description;
    private int created_by;
    private LocalDateTime created_at;
    private LocalDateTime expires_at;
    private PollStatus status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getCost_id() {
        return cost_id;
    }

    public void setCost_id(int cost_id) {
        this.cost_id = cost_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(LocalDateTime expires_at) {
        this.expires_at = expires_at;
    }

    public PollStatus getStatus() {
        return status;
    }

    public void setStatus(PollStatus status) {
        this.status = status;
    }


}
