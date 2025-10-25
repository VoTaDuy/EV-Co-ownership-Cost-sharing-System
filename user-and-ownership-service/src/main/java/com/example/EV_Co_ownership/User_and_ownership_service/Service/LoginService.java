package com.example.EV_Co_ownership.User_and_ownership_service.Service;

import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Roles;
import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Users;
import com.example.EV_Co_ownership.User_and_ownership_service.Payloads.Request.RegisterRequest;
import com.example.EV_Co_ownership.User_and_ownership_service.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoginService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public Users checkLogin(String email, String password) {
        Users user = userRepository.findByEmail(email);
        if (user == null) {
            System.out.println("User not found");
            return null;
        }
        System.out.println("Input email: " + email);
        System.out.println("Input password: " + password);

        if (password.equals(user.getPassword())) {
            System.out.println("User logged in");
            return user;
        }else  {
            System.out.println("Wrong password");
            return null;
        }
    }
    public boolean registerUser(RegisterRequest registerRequest) {
        if (userRepository.findByEmail(registerRequest.getEmail()) != null){
            throw new RuntimeException("Email already exists");
        }
        Users user = new Users();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        System.out.println("Input email: " + registerRequest.getEmail());
        Roles role = new Roles();
        role.setRole_id(registerRequest.getRole_id());
        user.setRoles(role);
        user.setIs_verified(false);
        user.setCreated_at(LocalDateTime.now());
        user.setDeleted(false);
        userRepository.save(user);

        try {
            System.out.println("Inserting user");
            userRepository.save(user);
            System.out.println("Inserted user");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
