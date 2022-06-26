package com.buezman.oauthServer.repository;

import com.buezman.oauthServer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
}
