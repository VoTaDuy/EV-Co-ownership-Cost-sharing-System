package com.example.EV_Co_ownership.User_and_ownership_service.Controller;

import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Users;
import com.example.EV_Co_ownership.User_and_ownership_service.Payloads.ResponseData;
import com.example.EV_Co_ownership.User_and_ownership_service.Service.LoginService;
import com.example.EV_Co_ownership.User_and_ownership_service.Utils.JwtUtilHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/login")
public class LoginController {

    @Autowired
    private JwtUtilHelper jwtUtilHelper;

    @Autowired  
    private LoginService loginService;

    @PostMapping("/sign_in")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        System.out.println("username: " + email);
        System.out.println("password: " + password);
        Users users = loginService.checkLogin(email, password);
        ResponseData responseData = new ResponseData();

        if (users != null) {
            String token = jwtUtilHelper.generateToken(users.getEmail());
            Map<String, Object> data = new HashMap<>();
            data.put("userId", users.getUserId());
            data.put("email", users.getEmail());
            data.put("role_id", users.getRoles());
            data.put("token", token);
            data.put("is_deleted", users.isDeleted());

            responseData.setData(data);
            responseData.setSuccess(true);
        } else {
            responseData.setData("Email or password is not correct");
            responseData.setSuccess(false);
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
