package com.example.skillpath_pro.service;

import com.example.skillpath_pro.model.User;
import com.example.skillpath_pro.repository.UserRepository;
import com.example.skillpath_pro.util.TokenUtil;
import com.example.skillpath_pro.util.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtService jwtService;

    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConfirmationToken(TokenUtil.generateToken());
        user.setTokenExpiry(LocalDateTime.now().plusHours(24)); // Token expires in 24 hours
        user.setActive(true); // User is inactive until email is confirmed
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

    public ResponseEntity<Map<String, String>> login(String email, String password) {
        Map<String, String> response = new HashMap<>();

        // Validate email format
        if (!EmailValidator.isValid(email)) {
            response.put("error", "Invalid email format.");
            return ResponseEntity.badRequest().body(response);
        }

        // Check if user exists
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            response.put("error", "Account not found. Please sign up.");
            return ResponseEntity.badRequest().body(response);
        }

        // Check if account is locked
        if (user.isLocked() && user.getLockExpiry().isAfter(LocalDateTime.now())) {
            response.put("error", "Account locked. Reset your password or try again in 15 minutes.");
            return ResponseEntity.badRequest().body(response);
        }

        // Check if email is verified
        if (!user.isActive()) {
            response.put("error", "Please verify your email first.");
            return ResponseEntity.badRequest().body(response);
        }

        // Verify password
        if (!passwordEncoder.matches(password, user.getPassword())) {
            user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);

            // Lock account after 3 failed attempts
            if (user.getFailedLoginAttempts() >= 3) {
                user.setLocked(true);
                user.setLockExpiry(LocalDateTime.now().plusMinutes(15));
            }

            userRepository.save(user);
            response.put("error", "Invalid email or password.");
            return ResponseEntity.badRequest().body(response);
        }

        // Reset failed login attempts on successful login
        user.setFailedLoginAttempts(0);
        user.setLocked(false);
        user.setLockExpiry(null);
        userRepository.save(user);

        // Generate JWT token
        String token = jwtService.generateToken(user);
        response.put("token", token);
        response.put("userId", user.getUserId().toString());
        response.put("message", "Login successful.");
        return ResponseEntity.ok(response);
    }
}
