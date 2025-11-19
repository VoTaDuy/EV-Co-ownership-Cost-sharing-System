package com.example.EV_Co_ownership.User_and_ownership_service.Service;

import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Roles;
import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Users;
import com.example.EV_Co_ownership.User_and_ownership_service.Payloads.Request.RegisterRequest;
import com.example.EV_Co_ownership.User_and_ownership_service.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public boolean registerUser(RegisterRequest registerRequest) {
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        Users user = new Users();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());

        Roles role = new Roles();
        role.setRole_id(registerRequest.getRole_id());
        user.setRoles(role);

        user.setVerified(false);
        user.setCreatedAt(LocalDateTime.now());
        user.setDeleted(false);

        userRepository.save(user); // sẽ throw exception nếu có lỗi
        return true;
    }
}
