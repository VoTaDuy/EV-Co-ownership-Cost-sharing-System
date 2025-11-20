package com.example.EV_Co_ownership.User_and_ownership_service.Controller;

import com.example.EV_Co_ownership.User_and_ownership_service.Payloads.ResponseData;
import com.example.EV_Co_ownership.User_and_ownership_service.Service.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(
        origins = "http://localhost:3000",
        allowedHeaders = "*",
        allowCredentials = "true",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}
)
@RestController
@RequestMapping("/user/forgot-password")
public class ForgotPasswordController {

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @PostMapping
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> body) {
        ResponseData responseData = new ResponseData();
        String email = body.get("email");

        if (email == null || email.isBlank()) {
            responseData.setData("Email không được để trống.");
            responseData.setSuccess(false);
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }

        try {
            forgotPasswordService.handleForgotPassword(email);
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
