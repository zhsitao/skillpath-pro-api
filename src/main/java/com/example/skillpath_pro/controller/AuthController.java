package com.example.skillpath_pro.controller;

import com.example.skillpath_pro.model.User;
import com.example.skillpath_pro.repository.UserRepository;
import com.example.skillpath_pro.service.AuthService;
import com.example.skillpath_pro.util.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthService authService;

    public AuthController(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();

        if (!EmailValidator.isValid(user.getEmail())) {
            response.put("success", "false");
            response.put("message", "Invalid email format!");
            return ResponseEntity.badRequest().body(response);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        response.put("success", "true");
        response.put("message", "User registered successfully!");
        return ResponseEntity.ok(response);
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

