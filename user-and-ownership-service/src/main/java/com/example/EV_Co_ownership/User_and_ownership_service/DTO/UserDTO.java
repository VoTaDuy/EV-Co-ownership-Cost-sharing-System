package com.example.EV_Co_ownership.User_and_ownership_service.DTO;

import com.fasterxml.jackson.annotation.JsonProperty; // <-- THÊM IMPORT NÀY

import java.time.LocalDateTime;
import java.util.UUID;

public class UserDTO {

    private UUID user_id;
    private String email;
    private int role_id;
    private String password;
    private boolean isVerified;
    private boolean isDeleted = false;
    private LocalDateTime createdAt;

    @JsonProperty("user_id")
    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("role_id")
    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("is_verified")
    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    @JsonProperty("isDeleted")
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @JsonProperty("created_at")
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}