package com.example.SpringAddressBookApp.service;

import com.example.SpringAddressBookApp.dto.ForgotPasswordRequest;
import com.example.SpringAddressBookApp.dto.ResetPasswordRequest;
import com.example.SpringAddressBookApp.model.AuthUser;
import com.example.SpringAddressBookApp.model.PasswordResetToken;
import com.example.SpringAddressBookApp.repository.AuthUserRepository;
import com.example.SpringAddressBookApp.repository.PasswordResetTokenRepository;
import jakarta.transaction.Transactional;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetService {

    private final AuthUserRepository authUserRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final JavaMailSender mailSender;
    private final BCryptPasswordEncoder passwordEncoder;

    public PasswordResetService(AuthUserRepository authUserRepository,
                                PasswordResetTokenRepository tokenRepository,
                                JavaMailSender mailSender) {
        this.authUserRepository = authUserRepository;
        this.tokenRepository = tokenRepository;
        this.mailSender = mailSender;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void forgotPassword(String email) {
        Optional<AuthUser> userOptional = authUserRepository.findByEmail(email);

        // Use .orElseThrow() to handle missing user
        AuthUser user = userOptional.orElseThrow(() ->
                new RuntimeException("User not found with email: " + email));

        String token = UUID.randomUUID().toString();

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setEmail(user.getEmail());
        resetToken.setExpiryDate(LocalDateTime.now().plusMinutes(30));
        tokenRepository.save(resetToken);

        String resetUrl = "http://localhost:8080/auth/reset-password?token=" + token;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Reset Your Password");
        message.setText("Click the link below to reset your password:\n" + resetUrl);
        mailSender.send(message);
    }


    @Transactional
    public void resetPassword(String token, String newPassword) {
        Optional<PasswordResetToken> tokenOptional = tokenRepository.findByToken(token);
        if (tokenOptional.isEmpty() || tokenOptional.get().getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Invalid or expired token.");
        }

        String email = tokenOptional.get().getEmail();
        Optional<AuthUser> userOptional = authUserRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found.");
        }

        AuthUser user = userOptional.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        authUserRepository.save(user);

        tokenRepository.delete(tokenOptional.get()); // Invalidate token after reset
    }
}
