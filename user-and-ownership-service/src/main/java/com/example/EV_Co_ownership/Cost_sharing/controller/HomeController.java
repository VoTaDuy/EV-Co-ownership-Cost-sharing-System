package com.example.EV_Co_ownership.Cost_sharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Thêm constructor này vào
    public HomeController() {
        System.out.println("=============================================");
        System.out.println("HomeController ĐÃ ĐƯỢC KHỞI TẠO!");
        System.out.println("=============================================");
    }

    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }
}