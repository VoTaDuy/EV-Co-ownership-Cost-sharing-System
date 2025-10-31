package com.example.EV_Co_ownership.User_and_ownership_service.Entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GroupMemberId implements Serializable {

    private int user_id;
    private int group_id;

    public GroupMemberId() {}

    public GroupMemberId(int user_id, int group_id) {
        this.user_id = user_id;
        this.group_id = group_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupMemberId)) return false;
        GroupMemberId that = (GroupMemberId) o;
        return user_id == that.user_id && group_id == that.group_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, group_id);
    }
}
