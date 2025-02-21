package com.example.skillpath_pro.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.skillpath_pro.model.SkillAnalysis.Skill;
import com.example.skillpath_pro.repository.SkillRepository;

@RestController
@RequestMapping("/api/skills")
public class UserSkillController {
    private static final Logger logger = LoggerFactory.getLogger(UserSkillController.class);
    private final SkillRepository userSkillRepository;

    public UserSkillController(SkillRepository userSkillRepository) {
        this.userSkillRepository = userSkillRepository;
    }

    @PostMapping
    public ResponseEntity<String> saveUserSkills(@RequestBody List<Skill> skills) {
        logger.info("Received request to save user skills: {}", skills);
        for (Skill skill : skills) {
            if (skill.getProficiencyLevel() == null) {
                logger.warn("Proficiency level is null for skill: {}", skill);
                return ResponseEntity.badRequest().body("Please rate all selected skills.");
            }
            userSkillRepository.save(skill);
        }
        logger.info("Skills saved successfully.");
        return ResponseEntity.ok("Skills saved successfully.");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Skill>> getUserSkills(@PathVariable Long userId) {
        logger.info("Received request to get skills for user ID: {}", userId);
        List<Skill> skills = userSkillRepository.findByUserId(userId);
        logger.info("Returning skills for user ID {}: {}", userId, skills);
        return ResponseEntity.ok(skills);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUserSkills(@PathVariable Long userId, @RequestBody List<Skill> skills) {
        logger.info("Received request to update user skills for user ID: {}", userId);
        if (skills == null || skills.isEmpty()) {
            logger.warn("No skills provided in the request.");
            return ResponseEntity.badRequest().body("No skills provided.");
        }
        for (Skill skill : skills) {
            if (skill.getProficiencyLevel() == null) {
                logger.warn("Proficiency level is null for skill: {}", skill);
                return ResponseEntity.badRequest().body("Please rate all selected skills.");
            }
            skill.setUserId(userId); // Ensure the skill is associated with the correct user
            userSkillRepository.save(skill);
            logger.info("Updated skill: {}", skill);
        }
        logger.info("Skills updated successfully.");
        return ResponseEntity.ok("Skills updated successfully.");
    }

    @DeleteMapping("/{userId}/{id}")
    public ResponseEntity<String> deleteUserSkill(@PathVariable Long userId, @PathVariable Long id) {
        logger.info("Received request to delete skill ID {} for user ID: {}",  userId, id);
        
        Optional<Skill> skillOptional = userSkillRepository.findByUserIdAndId(userId, id);

        if (skillOptional.isEmpty()) {
            logger.warn("Skill ID {} not found for user ID: {}",  userId, id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Skill not found.");
        }

        userSkillRepository.delete(skillOptional.get());
        logger.info("Skill ID {} deleted successfully for user ID: {}", userId, id);
        return ResponseEntity.ok("Skill deleted successfully.");
    }
}
