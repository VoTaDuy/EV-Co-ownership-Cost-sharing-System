package com.example.EV_Co_ownership.Cost_sharing.controller;

import com.example.EV_Co_ownership.Cost_sharing.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController { // <--- Dòng khai báo class bắt đầu ở đây

    // Hiển thị form đăng ký khi người dùng truy cập /register
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Xử lý dữ liệu từ form đăng ký khi người dùng nhấn nút submit
    @PostMapping("/register")
    public String processRegistration(@ModelAttribute User user) {
        // TODO: Xử lý logic lưu user vào database ở đây
        System.out.println("Đã đăng ký cho user: " + user.getEmail());
        return "redirect:/login";
    }

    // Hiển thị form đăng nhập
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // Xử lý việc đăng nhập (HIỆN TẠI CHỈ LÀ GIẢ LẬP)
    @PostMapping("/login")
    public String processLogin() {
        // TODO: Xử lý logic xác thực người dùng thực tế ở đây.
        System.out.println("Đăng nhập thành công (giả lập)!");
        return "redirect:/dashboard/profile";
    }

} // <--- Dấu ngoặc nhọn kết thúc class ở đây. Mọi thứ phải nằm bên trong.