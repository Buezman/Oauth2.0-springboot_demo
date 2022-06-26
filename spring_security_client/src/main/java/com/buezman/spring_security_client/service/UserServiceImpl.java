package com.buezman.spring_security_client.service;

import com.buezman.spring_security_client.dto.PasswordDto;
import com.buezman.spring_security_client.dto.RegisterDto;
import com.buezman.spring_security_client.event.RegistrationCompleteEvent;
import com.buezman.spring_security_client.model.PasswordResetToken;
import com.buezman.spring_security_client.model.User;
import com.buezman.spring_security_client.model.VerificationToken;
import com.buezman.spring_security_client.repository.PasswordResetTokenRepository;
import com.buezman.spring_security_client.repository.UserRepository;
import com.buezman.spring_security_client.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final PasswordResetTokenRepository passwordResetTokenRepository;


    @Override
    public String registerUser(RegisterDto registerDto, HttpServletRequest request) {
        String password = registerDto.getPassword();
        String confirmPassword = registerDto.getConfirmPassword();
        if (!password.equals(confirmPassword))
            throw new BadCredentialsException("Password and confirm password do not match");
        User user = User.builder()
                .firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName())
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(password))
                .role("ROLE_USER")
                .build();
        userRepository.save(user);
        applicationEventPublisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return "Registration successful";
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    @Override
    public void saveUserVerificationToken(String token, User user) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationTokenRepository.save(verificationToken);
        //send mail to user
    }

    @Override
    public String verifyRegistration(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken == null) {
            return "Invalid token";
        }
        User user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if (verificationToken.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0) {
            verificationTokenRepository.delete(verificationToken);
            return "Token Expired";
        }
        user.setEnabled(true);
        userRepository.save(user);

        return "Verification Successful";
    }

    @Override
    public String resendVerificationToken(String oldToken, HttpServletRequest servletRequest) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(oldToken);
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationTokenRepository.save(verificationToken);
        User user = verificationToken.getUser();
        resendVerificationTokenMail(user, applicationUrl(servletRequest),verificationToken.getToken());

        return "Verification Link sent";
    }

    @Override
    public String resetPassword(PasswordDto passwordDto, HttpServletRequest servletRequest) {
        User user = userRepository.findUserByEmail(passwordDto.getEmail());
        if (user == null) return "Email not found";
        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = new PasswordResetToken(user, token);
        passwordResetTokenRepository.save(passwordResetToken);

        String url = applicationUrl(servletRequest) + "/api/v1/saveNewPassword?token=" + token;
        log.info("Click the link to reset your password: {}", url);
        return url;
    }

    @Override
    public String saveNewPassword(String token, PasswordDto passwordDto) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
        if (passwordResetToken == null) {
            return "Invalid token";
        }
        User user = passwordResetToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if (passwordResetToken.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0) {
            passwordResetTokenRepository.delete(passwordResetToken);
            return "Token Expired";
        }
        user.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
        userRepository.save(user);

        return "Password Reset Successful";
    }

    @Override
    public String changePassword(PasswordDto passwordDto) {
        User user = userRepository.findUserByEmail(passwordDto.getEmail());
        if (!passwordEncoder.matches(passwordDto.getOldPassword(), user.getPassword())) {
            return "Incorrect old Password";
        }
        user.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
        userRepository.save(user);

        return "Password Changed Successfully";
    }

    private void resendVerificationTokenMail(User user, String applicationUrl, String token) {
        String url = applicationUrl + "/api/v1/verifyRegistration?token=" + token;
        log.info("Click the link to verify your account: {}", url);
    }


}
