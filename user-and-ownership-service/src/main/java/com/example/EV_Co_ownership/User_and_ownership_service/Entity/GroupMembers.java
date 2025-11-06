package com.example.EV_Co_ownership.User_and_ownership_service.Entity;

import com.example.EV_Co_ownership.User_and_ownership_service.Embeddable.GroupMemberId;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "group_members")
public class GroupMembers {

    @EmbeddedId
    private GroupMemberId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("groupId")
    @JoinColumn(name = "group_id")
    private Groups group;

    @Column(name = "ownership_ratio")
    private BigDecimal ownershipRatio;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public GroupMembers() {
    }

    public GroupMembers(Users user, Groups group, BigDecimal ratio, LocalDateTime time) {
        this.id = new GroupMemberId(user.getUser_id(), group.getGroupId());
        this.user = user;
        this.group = group;
        this.ownershipRatio = ratio;
        this.createdAt = time;
    }

    public GroupMemberId getId() {
        return id;
    }

    public void setId(GroupMemberId id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    public BigDecimal getOwnershipRatio() {
        return ownershipRatio;
    }

    public void setOwnershipRatio(BigDecimal ownershipRatio) {
        this.ownershipRatio = ownershipRatio;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}