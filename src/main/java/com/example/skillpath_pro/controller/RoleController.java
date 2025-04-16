package com.example.skillpath_pro.controller;

import com.example.skillpath_pro.model.Role;
import com.example.skillpath_pro.model.User;
import com.example.skillpath_pro.repository.RoleRepository;
import com.example.skillpath_pro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "http://localhost:5173")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles(@RequestParam(required = false) Long userId) {
        List<Role> roles = roleRepository.findAll();
        
        if (userId != null) {
            // Filter out roles that user already has
            Optional<User> userOpt = userRepository.findById(userId);
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                Set<Role> userRoles = user.getRoles();
                roles.removeIf(userRoles::contains);
            }
        }
        
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRole(@PathVariable Long id) {
        return roleRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
