package com.example.EV_Co_ownership.User_and_ownership_service.Controller;

import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Users;
import com.example.EV_Co_ownership.User_and_ownership_service.Payloads.Request.RegisterRequest;
import com.example.EV_Co_ownership.User_and_ownership_service.Payloads.ResponseData;
import com.example.EV_Co_ownership.User_and_ownership_service.Service.LoginService;
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
    JwtUtilHelper jwtUtilHelper;
    @Autowired
    LoginService loginService;

    @PostMapping("/sign_in")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        System.out.println("username: " + email) ;
        System.out.println("password " + password);
        Users users = loginService.checkLogin(email, password);
        ResponseData responseData = new ResponseData();

        if (users != null) {
            String token = jwtUtilHelper.generateToken(users.getEmail());
            Map<String,Object> data = new HashMap<>();
            boolean isDeleted = users.isDeleted();
            data.put("userId" , users.getUser_id());
            data.put("email", users.getEmail());
            data.put("token", token);
            data.put("is_deleted", isDeleted);
            responseData.setData(data);
            responseData.setSuccess(true);
        }else {
            responseData.setData("Email or password is not correct");
            responseData.setSuccess(false);
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        ResponseData responseData = new ResponseData();
        try {
            boolean isSuccess = loginService.registerUser(request);
            if (isSuccess) {
                responseData.setData("User registered successfully. Please login.");
                responseData.setSuccess(true);
                return new ResponseEntity<>(responseData, HttpStatus.CREATED);
            } else {
                responseData.setData("Registration failed due to internal error.");
                responseData.setSuccess(false);
                return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (RuntimeException e) {
            responseData.setData(e.getMessage());
            responseData.setSuccess(false);
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> body) {
        ResponseData responseData = new ResponseData();
        String email = body.get("email");

        if (email == null || email.isBlank()) {
            responseData.setData("Email không được để trống.");
            responseData.setSuccess(false);
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }

        try {
            loginService.handleForgotPassword(email);
            responseData.setData("Nếu email của bạn tồn tại, một liên kết khôi phục đã được gửi.");
            responseData.setSuccess(true);
            return new ResponseEntity<>(responseData, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            responseData.setData("Đã xảy ra lỗi máy chủ nội bộ khi xử lý yêu cầu.");
            responseData.setSuccess(false);
            return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}