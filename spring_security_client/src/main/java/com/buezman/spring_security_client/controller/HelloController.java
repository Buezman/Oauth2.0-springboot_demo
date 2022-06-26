package com.buezman.spring_security_client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("api/v1/user/hello")
    public String hello() {
        return "Welcome to Acapulco";
    }
}
