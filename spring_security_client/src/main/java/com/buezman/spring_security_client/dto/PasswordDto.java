package com.buezman.spring_security_client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordDto {
    String email;
    String oldPassword;
    String newPassword;
}
