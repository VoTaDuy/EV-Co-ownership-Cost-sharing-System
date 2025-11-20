package com.example.EV_Co_ownership.User_and_ownership_service.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
public class UserDTO {
    private int userId;
    private String email;
    private int role_id;
    private String password;
    private boolean isVerified;
    private boolean isDeleted;
    private LocalDateTime createdAt;
}