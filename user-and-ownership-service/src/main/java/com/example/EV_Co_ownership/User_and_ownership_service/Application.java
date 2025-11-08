package com.example.EV_Co_ownership.User_and_ownership_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.EV_Co_ownership")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}