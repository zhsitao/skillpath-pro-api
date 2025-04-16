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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class SkillController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserSkillRepository userSkillRepository;

    @GetMapping("/users/{userId}/skills")
    public ResponseEntity<List<UserSkill>> getUserSkills(@PathVariable Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<UserSkill> skills = userSkillRepository.findByUser(userOpt.get());
        return ResponseEntity.ok(skills);
    }

    @PostMapping("/users/{userId}/skills")
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
        for (Map<String, String> skillUpdate : skills) {
            String skillName = skillUpdate.get("name");
            String proficiency = skillUpdate.get("proficiency");
            
            UserSkill userSkill = new UserSkill();
            userSkill.setUser(user);
            userSkill.setSkillName(skillName);
            userSkill.setProficiency(proficiency);
            newSkills.add(userSkill);
        }

        userSkillRepository.saveAll(newSkills);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/{userId}/roles/{roleId}/gap-analysis")
    public ResponseEntity<Map<String, Object>> getRoleGapAnalysis(
            @PathVariable Long userId, 
            @PathVariable Long roleId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Role> roleOpt = roleRepository.findById(roleId);
        
        if (userOpt.isEmpty() || roleOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOpt.get();
        Role role = roleOpt.get();
        List<UserSkill> userSkills = userSkillRepository.findByUser(user);

        Map<String, String> userSkillMap = userSkills.stream()
            .collect(Collectors.toMap(UserSkill::getSkillName, UserSkill::getProficiency));

        List<Map<String, Object>> missingRequired = new ArrayList<>();
        List<Map<String, Object>> missingOptional = new ArrayList<>();
        List<Map<String, Object>> partialSkills = new ArrayList<>();
        List<Map<String, Object>> completedSkills = new ArrayList<>();
        List<Map<String, Object>> otherSkills = new ArrayList<>();

        // Analyze required skills
        for (Map.Entry<String, String> entry : role.getRequiredSkills().entrySet()) {
            String skillName = entry.getKey();
            String requiredLevel = entry.getValue();
            String userLevel = userSkillMap.get(skillName);
            
            Map<String, Object> skillInfo = new HashMap<>();
            skillInfo.put("name", skillName);
            skillInfo.put("requiredLevel", requiredLevel);
            skillInfo.put("currentLevel", userLevel);
            skillInfo.put("description", role.getSkillDescriptions().get(skillName));

            if (userLevel == null) {
                missingRequired.add(skillInfo);
            } else {
                if (isSkillLevelSufficient(userLevel, requiredLevel)) {
                    completedSkills.add(skillInfo);
                } else {
                    partialSkills.add(skillInfo);
                }
            }
        }

        // Analyze optional skills
        for (Map.Entry<String, String> entry : role.getOptionalSkills().entrySet()) {
            String skillName = entry.getKey();
            String recommendedLevel = entry.getValue();
            String userLevel = userSkillMap.get(skillName);
            
            Map<String, Object> skillInfo = new HashMap<>();
            skillInfo.put("name", skillName);
            skillInfo.put("recommendedLevel", recommendedLevel);
            skillInfo.put("currentLevel", userLevel);
            skillInfo.put("description", role.getSkillDescriptions().get(skillName));
            
            if (userLevel == null) {
                missingOptional.add(skillInfo);
            } else {
                skillInfo.put("completed", isSkillLevelSufficient(userLevel, recommendedLevel));
                completedSkills.add(skillInfo);
            }
        }

        // Add other user skills that are not part of the role
        for (UserSkill skill : userSkills) {
            String skillName = skill.getSkillName();
            if (!role.getRequiredSkills().containsKey(skillName) && 
                !role.getOptionalSkills().containsKey(skillName)) {
                Map<String, Object> skillInfo = new HashMap<>();
                skillInfo.put("name", skillName);
                skillInfo.put("currentLevel", skill.getProficiency());
                otherSkills.add(skillInfo);
            }
        }

        // Calculate progress
        double progress = calculateProgress(user, role);

        Map<String, Object> result = new HashMap<>();
        result.put("progress", Math.min(100, Math.round(progress)));
        result.put("missingRequired", missingRequired);
        result.put("missingOptional", missingOptional);
        result.put("partialSkills", partialSkills);
        result.put("completedSkills", completedSkills);
        result.put("otherSkills", otherSkills);

        return ResponseEntity.ok(result);
    }

    private double calculateProgress(User user, Role role) {
        List<UserSkill> userSkills = userSkillRepository.findByUser(user);
        Map<String, String> userSkillMap = userSkills.stream()
            .collect(Collectors.toMap(UserSkill::getSkillName, UserSkill::getProficiency));

        int totalRequired = role.getRequiredSkills().size();
        double requiredProgress = 0;
        int totalOptional = role.getOptionalSkills().size();
        double optionalProgress = 0;

        // Calculate progress for required skills (80% of total)
        for (Map.Entry<String, String> entry : role.getRequiredSkills().entrySet()) {
            String skillName = entry.getKey();
            String requiredLevel = entry.getValue();
            String userLevel = userSkillMap.get(skillName);

            if (userLevel != null) {
                if (isSkillLevelSufficient(userLevel, requiredLevel)) {
                    requiredProgress += 1.0;
                    // Add bonus for exceeding required level
                    if (getLevelValue(userLevel) > getLevelValue(requiredLevel)) {
                        requiredProgress += 0.2; // 20% bonus for exceeding requirement
                    }
                } else {
                    // Partial credit based on level
                    double ratio = (double) getLevelValue(userLevel) / getLevelValue(requiredLevel);
                    requiredProgress += ratio * 0.5; // Up to 50% credit for partial completion
                }
            }
        }

        // Calculate progress for optional skills (20% of total)
        for (Map.Entry<String, String> entry : role.getOptionalSkills().entrySet()) {
            String skillName = entry.getKey();
            String recommendedLevel = entry.getValue();
            String userLevel = userSkillMap.get(skillName);

            if (userLevel != null) {
                if (isSkillLevelSufficient(userLevel, recommendedLevel)) {
                    optionalProgress += 1.0;
                    if (getLevelValue(userLevel) > getLevelValue(recommendedLevel)) {
                        optionalProgress += 0.2; // 20% bonus for exceeding requirement
                    }
                } else {
                    double ratio = (double) getLevelValue(userLevel) / getLevelValue(recommendedLevel);
                    optionalProgress += ratio * 0.5;
                }
            }
        }

        // Calculate final progress (80% from required skills, 20% from optional skills)
        double requiredScore = totalRequired > 0 ? (requiredProgress / totalRequired) * 80 : 80;
        double optionalScore = totalOptional > 0 ? (optionalProgress / totalOptional) * 20 : 20;

        return requiredScore + optionalScore;
    }

    private boolean isSkillLevelSufficient(String userLevel, String requiredLevel) {
        int userValue = getLevelValue(userLevel);
        int requiredValue = getLevelValue(requiredLevel);
        return userValue >= requiredValue;
    }

    private int getLevelValue(String level) {
        switch (level) {
            case "ADVANCED": return 3;
            case "INTERMEDIATE": return 2;
            case "BEGINNER": return 1;
            default: return 0;
        }
    }
}
