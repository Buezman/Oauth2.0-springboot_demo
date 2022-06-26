package com.buezman.spring_security_client.repository;

import com.buezman.spring_security_client.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
}
