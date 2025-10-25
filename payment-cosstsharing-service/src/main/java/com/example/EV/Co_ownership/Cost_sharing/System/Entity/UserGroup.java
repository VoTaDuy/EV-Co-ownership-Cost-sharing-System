package com.example.EV.Co_ownership.Cost_sharing.System.Entity;

import com.example.EV.Co_ownership.Cost_sharing.System.Enum.UserGroupRole;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity (name = "user_group")
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_group_id;

    @Column(name = "group_id")
    private int group_id;

    @Column(name = "group_name")
    private String group_name;

    @Column(name = "description")
    private String description;

    @Column(name = "created_by")
    private String created_by;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "role")
    private UserGroupRole role;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column(name = "upload_at")
    private LocalDateTime upload_at;

    public int getUser_group_id() {
        return user_group_id;
    }

    public void setUser_group_id(int user_group_id) {
        this.user_group_id = user_group_id;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public UserGroupRole getRole() {
        return role;
    }

    public void setRole(UserGroupRole role) {
        this.role = role;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpload_at() {
        return upload_at;
    }

    public void setUpload_at(LocalDateTime upload_at) {
        this.upload_at = upload_at;
    }
}
