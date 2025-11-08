package com.example.EV_Co_ownership.User_and_ownership_service.Service;

import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Users;
import com.example.EV_Co_ownership.User_and_ownership_service.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    public Users checkLogin(String email, String password) {
        Users user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            System.out.println("User not found");
            return null;
        }

        if (password.equals(user.getPassword())) {
            System.out.println("User logged in");
            return user;
        } else {
            System.out.println("Wrong password");
            return null;
        }
    }
}
