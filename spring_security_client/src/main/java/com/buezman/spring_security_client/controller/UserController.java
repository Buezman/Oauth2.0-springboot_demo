package com.buezman.spring_security_client.controller;


import com.buezman.spring_security_client.dto.PasswordDto;
import com.buezman.spring_security_client.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class UserController {

    private final UserService userService;

    @PostMapping("resetPassword")
    public String resetPassword(@RequestBody PasswordDto passwordDto, HttpServletRequest servletRequest) {
        return userService.resetPassword(passwordDto, servletRequest);
    }

    @PostMapping("saveNewPassword")
    public String saveNewPassword(@RequestParam("token") String token, @RequestBody PasswordDto passwordDto) {
        return userService.saveNewPassword(token, passwordDto);
    }

    @PostMapping("/user/changePassword")
    public String changePassword(@RequestBody PasswordDto passwordDto) {
        return userService.changePassword(passwordDto);
    }
}
