package com.buezman.spring_security_client.repository;

import com.buezman.spring_security_client.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
}
