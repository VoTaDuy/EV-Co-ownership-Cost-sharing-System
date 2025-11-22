package com.example.EV_Co_ownership.User_and_ownership_service.Controller;

import com.example.EV_Co_ownership.User_and_ownership_service.Payloads.Request.RegisterRequest;
import com.example.EV_Co_ownership.User_and_ownership_service.Payloads.ResponseData;
import com.example.EV_Co_ownership.User_and_ownership_service.Service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        ResponseData responseData = new ResponseData();
        try {
            boolean isSuccess = registerService.registerUser(request);
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
}
