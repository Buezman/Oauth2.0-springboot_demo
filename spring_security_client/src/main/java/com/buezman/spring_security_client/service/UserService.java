package com.buezman.spring_security_client.service;

import com.buezman.spring_security_client.dto.PasswordDto;
import com.buezman.spring_security_client.dto.RegisterDto;
import com.buezman.spring_security_client.model.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    String registerUser(RegisterDto registerDto, HttpServletRequest request);

    void saveUserVerificationToken(String token, User user);

    String verifyRegistration(String token);

    String resendVerificationToken(String oldToken, HttpServletRequest servletRequest);

    String resetPassword(PasswordDto passwordDto, HttpServletRequest servletRequest);

    String saveNewPassword(String token, PasswordDto passwordDto);

    String changePassword(PasswordDto passwordDto);
}
