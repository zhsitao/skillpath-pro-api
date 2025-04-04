package com.example.skillpath_pro.controller;

import com.example.skillpath_pro.model.Profile;
import com.example.skillpath_pro.model.User;
import com.example.skillpath_pro.repository.ProfileRepository;
import com.example.skillpath_pro.repository.UserRepository;
import com.example.skillpath_pro.service.AuthService;
import com.example.skillpath_pro.service.EmailService;
import com.example.skillpath_pro.util.EmailValidator;
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

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthService authService;

    @Autowired
    private EmailService emailService;

    public AuthController(UserRepository userRepository, ProfileRepository profileRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();

        // Validate email format
        if (!EmailValidator.isValid(user.getEmail())) {
            response.put("success", "false");
            response.put("message", "Invalid email format!");
            return ResponseEntity.badRequest().body(response);
        }

        // Check if email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            response.put("success", "false");
            response.put("message", "Email is already registered!");
            return ResponseEntity.badRequest().body(response);
        }

        // Hash the password and activate the user
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true); // Automatically activate the user
        userRepository.save(user);

        // Simulate email sending
        emailService.simulateEmail(user.getEmail());

        // Create and associate a default profile
        Profile profile = new Profile();
        profile.setUser(user); // Link the profile to the user
        profile.setCareerStage("Recent Graduate"); // Default career stage
        profile.setSkills(new ArrayList<>()); // Empty skills list
        profile.setWorkExperience("0-1 years"); // Default work experience
        profileRepository.save(profile); // Save the profile in the database

        // Return success response
        response.put("success", "true");
        response.put("message", "Signup successful! Email sent successfully.");
        return ResponseEntity.ok(response);
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

