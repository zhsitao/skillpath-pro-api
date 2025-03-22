package com.example.skillpath_pro.service;

import com.example.skillpath_pro.model.User;
import com.example.skillpath_pro.repository.UserRepository;
import com.example.skillpath_pro.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConfirmationToken(TokenUtil.generateToken());
        user.setTokenExpiry(LocalDateTime.now().plusHours(24)); // Token expires in 24 hours
        user.setActive(false); // User is inactive until email is confirmed
        userRepository.save(user);

        // Send confirmation email
        emailService.sendConfirmationEmail(user.getEmail(), user.getConfirmationToken());
    }

    public Optional<User> confirmUser(String token) {
        Optional<User> userOptional = userRepository.findByConfirmationToken(token);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Check if the token has expired
            if (user.getTokenExpiry().isBefore(LocalDateTime.now())) {
                throw new IllegalArgumentException("Token has expired.");
            }

            // Activate the user and clear the token
            user.setActive(true);
            user.setConfirmationToken(null);
            user.setTokenExpiry(null);
            userRepository.save(user);

            return Optional.of(user);
        }

        return Optional.empty();
    }
}