package com.example.EV_Co_ownership.User_and_ownership_service.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "group_members")
public class GroupMembers {

    @EmbeddedId
    private GroupMemberId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("group_id")
    @JoinColumn(name = "group_id")
    private GroupUsers group;

    @Column(name = "ownership_ratio")
    private Float ownership_ratio;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public GroupMemberId getId() { return id; }
    public void setId(GroupMemberId id) { this.id = id; }

    public Users getUser() { return user; }
    public void setUser(Users user) { this.user = user; }

    public GroupUsers getGroup() { return group; }
    public void setGroup(GroupUsers group) { this.group = group; }

    public Float getOwnership_ratio() { return ownership_ratio; }
    public void setOwnership_ratio(Float ownership_ratio) { this.ownership_ratio = ownership_ratio; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
