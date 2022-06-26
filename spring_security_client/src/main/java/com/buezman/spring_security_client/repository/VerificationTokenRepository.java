package com.buezman.spring_security_client.repository;

import com.buezman.spring_security_client.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);
}
