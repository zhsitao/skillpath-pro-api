package com.example.skillpath_pro.service;

import com.example.skillpath_pro.model.User;
import com.example.skillpath_pro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public boolean isPasswordStrong(String password) {
        String passwordRegex = "^(?=.*[A-Z])(?=.*\\d).{8,}$";
        return Pattern.matches(passwordRegex, password);
    }

    public boolean isEmailRegistered(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public void registerUser(User user) {
        // Hash password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Generate email verification token
        String token = generateVerificationToken(user);

        // Save user to database
        userRepository.save(user);

        // Send confirmation email
        sendConfirmationEmail(user.getEmail(), token);
    }

    private String generateVerificationToken(User user) {
        // Generate a random token (e.g., UUID)
        String token = java.util.UUID.randomUUID().toString();
        // Save token to the database with expiration time
        // (Implementation omitted for brevity)
        return token;
    }

    private void sendConfirmationEmail(String email, String token) {
        // Send email with verification link
        // (Implementation omitted for brevity)
    }

    public boolean validateVerificationToken(String token) {
        // Check if token exists and is not expired
        // (Implementation omitted for brevity)
        return true; // Replace with actual validation logic
    }

    public void activateUserAccount(String token) {
        // Activate user account associated with the token
        // (Implementation omitted for brevity)
    }
}
