package com.example.skillpath_pro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.skillpath_pro.model.Role;
import com.example.skillpath_pro.repository.RoleRepository;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "http://localhost:5173")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping()
    public ResponseEntity<Map<String, List<Map<String, Object>>>> getAllRoles() {
        try {
            List<Role> roles = roleRepository.findAll();
            if (roles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Map<String, List<Map<String, Object>>> result = new HashMap<>();
            roleRepository.findAll().forEach(role -> {
                result.computeIfAbsent(role.getCategory(), _k -> new ArrayList<>())
                        .add(Map.of(
                                "id", role.getRoleId(),
                                "title", role.getTitle(),
                                "category", role.getCategory()));
            });

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
