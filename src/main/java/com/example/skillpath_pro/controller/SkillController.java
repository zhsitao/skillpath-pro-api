package com.example.skillpath_pro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.example.skillpath_pro.model.Role;
import com.example.skillpath_pro.model.Skill;
import com.example.skillpath_pro.repository.RoleRepository;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin(origins = "http://localhost:5173")
public class SkillController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public ResponseEntity<List<Skill>> getSkillsByRole(@RequestParam Long roleId) {
        try {
            return ResponseEntity.ok(roleRepository.findById(roleId).map(Role::getSkills)
                    .orElse(Collections.emptyList()).stream()
                    .sorted(Comparator.comparingDouble(Skill::getPriority).reversed()).collect(Collectors.toList()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
