package com.example.EV_Co_ownership.User_and_ownership_service.Controller;


import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Users;
import com.example.EV_Co_ownership.User_and_ownership_service.Payloads.ResponseData;
import com.example.EV_Co_ownership.User_and_ownership_service.Service.LoginService;
import com.example.EV_Co_ownership.User_and_ownership_service.Service.UserService;
import com.example.EV_Co_ownership.User_and_ownership_service.Utils.JwtUtilHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    JwtUtilHelper  jwtUtilHelper;

    @Autowired
    LoginService loginService;

    @PostMapping("/sign_in")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        System.out.println("username: " + email) ;
        System.out.println("password " + password);
        Users users = loginService.checkLogin(email, password);
        ResponseData responseData = new ResponseData();

        if (users != null) {
            String token = jwtUtilHelper.generateToken(users.getPassword());
            Map<String,Object> data = new HashMap<>();
            boolean isDeleted = users.isDeleted();
            data.put("userId" , users.getUser_id());
            data.put("email", users.getEmail());
            data.put("token", token);
            data.put("is_deleted", isDeleted);
            responseData.setData(data);
            responseData.setSuccess(true);
        }else {
            responseData.setData("email or password is not correct");
            responseData.setSuccess(false);
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);

    }
}
