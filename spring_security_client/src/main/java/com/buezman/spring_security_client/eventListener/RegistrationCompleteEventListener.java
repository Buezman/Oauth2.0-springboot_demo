package com.buezman.spring_security_client.eventListener;

import com.buezman.spring_security_client.event.RegistrationCompleteEvent;
import com.buezman.spring_security_client.model.User;
import com.buezman.spring_security_client.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    private final UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveUserVerificationToken(token, user);
        String url = event.getApplicationUrl() + "/api/v1/verifyRegistration?token=" + token;
        log.info("Click the link to verify your account: {}", url);
    }
}
