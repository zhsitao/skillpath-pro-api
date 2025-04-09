package com.example.skillpath_pro.controller;

import com.example.skillpath_pro.model.Profile;
import com.example.skillpath_pro.model.User;
import com.example.skillpath_pro.repository.ProfileRepository;
import com.example.skillpath_pro.repository.UserRepository;
import com.example.skillpath_pro.service.AuthService;
import com.example.skillpath_pro.service.EmailService;
import com.example.skillpath_pro.util.EmailValidator;
import com.example.skillpath_pro.dto.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthService authService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        // Check if the user already exists
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Email is already in use.");
        }

        // Create and save the user
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword())); //hash the password
        user.setActive(true); // Hard coding the user as active for bypassing email verification
        userRepository.save(user);

        // Create a default profile for the user
        Profile profile = new Profile();
        profile.setUser(user);
        profile.setCareerStage("Recent Graduate"); // Default career stage
        profile.setSkills(new ArrayList<>()); // Empty skills list
        profile.setWorkExperience("0-1 years"); // Default work experience
        profileRepository.save(profile);

        return ResponseEntity.ok("User registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");
        return authService.login(email, password);
    }

    @GetMapping("/confirm")
    public ResponseEntity<String> confirmEmail(@RequestParam("token") String token) {
        try {
            authService.confirmUser(token);
            return ResponseEntity.ok("Account successfully activated. Redirecting to profile setup...");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

