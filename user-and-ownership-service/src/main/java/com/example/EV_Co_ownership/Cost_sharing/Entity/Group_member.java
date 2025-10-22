package com.example.EV_Co_ownership.Cost_sharing.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "group_member")

public class Group_member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @Column(name = "group_id")
    private int group_id;

    @Column(name = "ownership_ratio")
    private int ownership_ratio;

    @Column(name = "created_at")
    private String created_at;

    public int getUsers_id() {
        return user_id;
    }

    public void setUsers_id(int users_id) {
        this.user_id = users_id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getOwnership_ratio() {
        return ownership_ratio;
    }

    public void setOwnership_ratio(int ownership_ratio) {
        this.ownership_ratio = ownership_ratio;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
