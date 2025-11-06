package com.example.EV_Co_ownership.User_and_ownership_service.Payloads.Request;

public class CreateGroupRequest {

    private String groupName;
    private String description;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}