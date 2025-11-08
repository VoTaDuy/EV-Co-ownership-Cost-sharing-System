package com.TaDuy.microservices.history_analytics_service.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aa")
public class AController {

    @GetMapping("")
    public String dcum(){
        return "anh duy dep trai vcl";
    }
}
