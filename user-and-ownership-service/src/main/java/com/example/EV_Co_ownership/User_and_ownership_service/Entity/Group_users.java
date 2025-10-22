package com.example.EV_Co_ownership.User_and_ownership_service.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "group_users")

public class Group_users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int group_id;

    @Column(name = "group_name")
    private String group_name;

    @Column(name = "description text")
    private String description_text;

    @Column(name = "created_date")
    private String created_at;

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getDescription_text() {
        return description_text;
    }

    public void setDescription_text(String description_text) {
        this.description_text = description_text;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
