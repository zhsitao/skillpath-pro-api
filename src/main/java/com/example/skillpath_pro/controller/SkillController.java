package com.example.skillpath_pro.controller;

import com.example.skillpath_pro.model.Role;
import com.example.skillpath_pro.model.User;
import com.example.skillpath_pro.model.UserSkill;
import com.example.skillpath_pro.repository.RoleRepository;
import com.example.skillpath_pro.repository.UserRepository;
import com.example.skillpath_pro.repository.UserSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin(origins = "http://localhost:5173")
public class SkillController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserSkillRepository userSkillRepository;

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Role> getRole(@PathVariable Long id) {
        return roleRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/user/{userId}/role/{roleId}")
    public ResponseEntity<?> setUserRole(@PathVariable Long userId, @PathVariable Long roleId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Role> roleOpt = roleRepository.findById(roleId);

        if (userOpt.isEmpty() || roleOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOpt.get();
        user.setTargetRole(roleOpt.get());
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}/skills")
    public ResponseEntity<List<UserSkill>> getUserSkills(@PathVariable Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<UserSkill> skills = userSkillRepository.findByUser(userOpt.get());
        return ResponseEntity.ok(skills);
    }

    @PostMapping("/user/{userId}/skills")
    public ResponseEntity<?> updateUserSkills(@PathVariable Long userId, 
                                            @RequestBody List<Map<String, String>> skills) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOpt.get();
        List<UserSkill> existingSkills = userSkillRepository.findByUser(user);
        userSkillRepository.deleteAll(existingSkills);

        List<UserSkill> newSkills = new ArrayList<>();
        for (Map<String, String> skill : skills) {
            UserSkill userSkill = new UserSkill();
            userSkill.setUser(user);
            userSkill.setSkillName(skill.get("name"));
            userSkill.setProficiency(skill.get("proficiency"));
            newSkills.add(userSkill);
        }

        userSkillRepository.saveAll(newSkills);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}/gap-analysis")
    public ResponseEntity<Map<String, Object>> getGapAnalysis(@PathVariable Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOpt.get();
        if (user.getTargetRole() == null) {
            return ResponseEntity.badRequest().build();
        }

        List<UserSkill> userSkills = userSkillRepository.findByUser(user);
        Role targetRole = user.getTargetRole();

        // Calculate progress
        Set<String> requiredSkills = targetRole.getRequiredSkills();
        Map<String, String> userSkillMap = new HashMap<>();
        for (UserSkill skill : userSkills) {
            userSkillMap.put(skill.getSkillName(), skill.getProficiency());
        }

        int totalRequired = requiredSkills.size();
        int completed = 0;
        List<Map<String, String>> missingSkills = new ArrayList<>();

        for (String skill : requiredSkills) {
            String proficiency = userSkillMap.get(skill);
            if (proficiency != null) {
                if (proficiency.equals("ADVANCED")) completed++;
                else if (proficiency.equals("INTERMEDIATE")) completed += 0.6;
                else if (proficiency.equals("BEGINNER")) completed += 0.3;
            } else {
                Map<String, String> missing = new HashMap<>();
                missing.put("name", skill);
                missing.put("status", "MISSING");
                missingSkills.add(missing);
            }
        }

        double progress = totalRequired > 0 ? (completed * 100.0) / totalRequired : 0;

        Map<String, Object> result = new HashMap<>();
        result.put("progress", Math.round(progress));
        result.put("missingSkills", missingSkills);
        result.put("currentSkills", userSkills);
        result.put("requiredSkills", requiredSkills);
        result.put("optionalSkills", targetRole.getOptionalSkills());

        return ResponseEntity.ok(result);
    }
}
