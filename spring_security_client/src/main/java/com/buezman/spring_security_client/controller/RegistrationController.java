package com.buezman.spring_security_client.controller;

import com.buezman.spring_security_client.dto.RegisterDto;
import com.buezman.spring_security_client.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @PostMapping("register")
    public String registerUser(@RequestBody RegisterDto registerDto, HttpServletRequest request) {
       return userService.registerUser(registerDto, request);
    }

    @GetMapping("verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token) {
        return userService.verifyRegistration(token);
    }

    @GetMapping("resendVerificationToken")
    public String resendVerificationToken(@RequestParam("token") String oldToken, HttpServletRequest request) {
        return userService.resendVerificationToken(oldToken, request);
    }
}
