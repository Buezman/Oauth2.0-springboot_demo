package com.buezman.oauthServer.config;

import com.buezman.oauthServer.service.CustomAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
public class DefaultSecurityConfig {

    private final CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    public void bindAuthenticationProvider(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider);
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequest -> authorizeRequest.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults());
        return http.build();
    }
}
