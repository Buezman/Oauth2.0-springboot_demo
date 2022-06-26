package com.buezman.spring_security_client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
}
