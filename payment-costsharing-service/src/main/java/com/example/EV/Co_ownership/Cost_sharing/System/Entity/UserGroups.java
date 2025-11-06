package com.example.EV.Co_ownership.Cost_sharing.System.Entity;

import com.example.EV.Co_ownership.Cost_sharing.System.Enum.UserGroupRole;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "user_group")
public class UserGroups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_group_id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupTable group_id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "role")
    private UserGroupRole role;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    public int getUser_group_id() {
        return user_group_id;
    }

    public void setUser_group_id(int user_group_id) {
        this.user_group_id = user_group_id;
    }

    public GroupTable getGroup_id() {
        return group_id;
    }

    public void setGroup_id(GroupTable group_id) {
        this.group_id = group_id;
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

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}
