package com.example.skillpath_pro.controller;

import com.example.skillpath_pro.dto.ProfileUpdateRequest;
import com.example.skillpath_pro.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/update")
    public ResponseEntity<Map<String, String>> updateProfile(@RequestBody ProfileUpdateRequest request) {
        return profileService.updateProfile(request);
    }
}
