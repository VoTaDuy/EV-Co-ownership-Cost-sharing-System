package com.example.EV_Co_ownership.Cost_sharing.model; // <--- DÒNG NÀY RẤT QUAN TRỌNG

// Bỏ các annotation Entity đi để tránh lỗi nếu chưa import
// Bạn sẽ thêm lại chúng sau này

public class User {
    private Long id;
    private String fullName;
    private String email;
    private String passwordHash; // Trường này cần cho form đăng ký

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}