package com.buezman.spring_security_client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebSecurityConfig {

    private static final String[] WHITELIST_URL = {
            "/hello", "/ai/v1/register", "/api/v1/verifyRegistration*", "/api/v1/resendVerificationToken*"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .authorizeHttpRequests()
                .antMatchers(WHITELIST_URL).permitAll();
        return http.build();
    }
}
