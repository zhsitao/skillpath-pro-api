package com.example.skillpath_pro.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.skillpath_pro.dto.SkillGapResult;
import com.example.skillpath_pro.model.SkillAnalysis.SkillProgress;
import com.example.skillpath_pro.services.SkillGapAnalysisService;



@RestController
@RequestMapping("/api/skill-gap")
public class SkillGapController {
    private final SkillGapAnalysisService gapAnalysisService;
    private static final Logger logger = LoggerFactory.getLogger(SkillGapController.class);

    @Autowired
    public SkillGapController(SkillGapAnalysisService gapAnalysisService) {
        this.gapAnalysisService = gapAnalysisService;
    }

    @GetMapping("/{userId}/{roleId}")
    public ResponseEntity<?> getSkillGap(@PathVariable Long userId, @PathVariable Long roleId) {
        logger.info("Calculating skill gap for user {} and role {}", userId, roleId);
        
        if (userId == null || userId <= 0) {
            logger.warn("Invalid user ID provided: {}", userId);
            return ResponseEntity.badRequest().body("Invalid user ID");
        }

        if (roleId == null || roleId <= 0) {
            logger.warn("Invalid role ID provided: {}", roleId);
            return ResponseEntity.badRequest().body("Invalid role ID");
        }

        try {
            SkillGapResult result = gapAnalysisService.calculateGap(userId, roleId);
            if (result == null) {
                logger.warn("No skill gap data found for user {} and role {}", userId, roleId);
                return ResponseEntity.notFound().build();
            }
            logger.info("Successfully calculated skill gap for user {} and role {}", userId, roleId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Error calculating skill gap: {}", e.getMessage());
            return ResponseEntity.internalServerError().body("Error calculating skill gap");
        }
    }

    @GetMapping("/progress/{userId}")
    public ResponseEntity<List<SkillProgress>> getSkillProgress(@PathVariable Long userId) {
        logger.info("Fetching skill progress for user {}", userId);
        List<SkillProgress> progress = gapAnalysisService.getProgressHistory(userId);
        logger.info("Successfully retrieved progress history for user {}", userId);
        return ResponseEntity.ok(progress);
    }

    
}




