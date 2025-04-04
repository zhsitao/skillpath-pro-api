package com.example.skillpath_pro.service;

import com.example.skillpath_pro.dto.ProfileUpdateRequest;
import com.example.skillpath_pro.model.User;
import com.example.skillpath_pro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Map<String, String>> updateProfile(ProfileUpdateRequest request) {
        Map<String, String> response = new HashMap<>();

        // Validate career stage
        if (request.getCareerStage() == null || request.getCareerStage().isEmpty()) {
            response.put("error", "Career stage is required.");
            return ResponseEntity.badRequest().body(response);
        }

        // Get the logged-in user (for simplicity, assume user ID is 1)
        User user = userRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("User not found."));

        // Update user profile fields
        user.setCareerStage(request.getCareerStage());
        user.setWorkExperience(request.getWorkExperience());

        // Save the updated user
        userRepository.save(user);

        response.put("success", "Profile updated successfully.");
        return ResponseEntity.ok(response);
    }
}
