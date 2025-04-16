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
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/{userId}/roles")
    public ResponseEntity<List<Map<String, Object>>> getUserRoles(@PathVariable Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<Map<String, Object>> roleInfos = new ArrayList<>();
        User user = userOpt.get();
        
        for (Role role : user.getRoles()) {
            Map<String, Object> roleInfo = new HashMap<>();
            roleInfo.put("id", role.getId());
            roleInfo.put("title", role.getTitle());
            roleInfo.put("description", role.getDescription());
            roleInfo.put("category", role.getCategory());
            roleInfos.add(roleInfo);
        }
        
        return ResponseEntity.ok(roleInfos);
    }

    @PostMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<?> addUserRole(@PathVariable Long userId, @PathVariable Long roleId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Role> roleOpt = roleRepository.findById(roleId);

        if (userOpt.isEmpty() || roleOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOpt.get();
        Role role = roleOpt.get();
        
        Set<Role> userRoles = user.getRoles();
        if (userRoles == null) {
            userRoles = new HashSet<>();
        }
        userRoles.add(role);
        user.setRoles(userRoles);
        
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<?> removeUserRole(@PathVariable Long userId, @PathVariable Long roleId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOpt.get();
        user.getRoles().removeIf(role -> role.getId().equals(roleId));
        userRepository.save(user);
        
        return ResponseEntity.ok().build();
    }
}
