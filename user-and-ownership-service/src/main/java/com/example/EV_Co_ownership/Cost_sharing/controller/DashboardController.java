package com.example.EV_Co_ownership.Cost_sharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard") // Tất cả URL trong controller này sẽ bắt đầu bằng /dashboard
public class DashboardController {

    @GetMapping("/profile")
    public String showProfilePage(Model model) {
        // TODO: Lấy thông tin Profile của user đang đăng nhập từ database
        // Tạm thời, chúng ta sẽ dùng dữ liệu mẫu
        model.addAttribute("fullName", "Nguyễn Văn A");
        model.addAttribute("nationalId", "123456789");
        model.addAttribute("phoneNumber", "0909123456");

        return "dashboard/profile"; // Trả về file /templates/dashboard/profile.html
    }
}