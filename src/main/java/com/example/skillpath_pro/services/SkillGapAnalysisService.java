package com.example.skillpath_pro.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.skillpath_pro.dto.SkillGapResult;
import com.example.skillpath_pro.model.SkillAnalysis.RoleSkill;
import com.example.skillpath_pro.model.SkillAnalysis.Skill;
import com.example.skillpath_pro.model.SkillAnalysis.SkillProgress;
import com.example.skillpath_pro.repository.RoleSkillRepository;
import com.example.skillpath_pro.repository.SkillProgressRepository;
import com.example.skillpath_pro.repository.SkillRepository;

@Service
public class SkillGapAnalysisService {
    private static final Map<String, Integer> PROFICIENCY_SCORES = Map.of(
        "BEGINNER", 33,
        "INTERMEDIATE", 66,
        "ADVANCED", 100
    );

    private final SkillRepository skillRepository;
    private final RoleSkillRepository roleSkillRepository;
    private final SkillProgressRepository progressRepository;

    @Autowired
    public SkillGapAnalysisService(
            com.example.skillpath_pro.repository.SkillRepository skillRepository,
            com.example.skillpath_pro.repository.RoleSkillRepository roleSkillRepository,
            com.example.skillpath_pro.repository.SkillProgressRepository progressRepository) {
        this.skillRepository = skillRepository;
        this.roleSkillRepository = roleSkillRepository;
        this.progressRepository = progressRepository;
    }
    public SkillGapResult calculateGap(Long userId, Long roleId) {
        List<Skill> userSkills = skillRepository.findByUserId(userId);
        List<RoleSkill> requiredSkills = roleSkillRepository.findByRoleId(roleId);
        
        if (requiredSkills.isEmpty()) {
            return new SkillGapResult(0, new ArrayList<>());
        }

        double totalMatchScore = 0;
        List<RoleSkill> missingSkills = new ArrayList<>();
        int requiredSkillCount = requiredSkills.size();

        for (RoleSkill required : requiredSkills) {
            Optional<Skill> userSkill = userSkills.stream()
                .filter(s -> s.getSkillName().equals(required.getSkillName()))
                .findFirst();

            if (userSkill.isPresent()) {
                int matchScore = calculateSkillMatch(userSkill.get(), required);
                totalMatchScore += matchScore;
            } else {
                missingSkills.add(required);
            }
        }

        int finalScore = (int) Math.round((totalMatchScore / (requiredSkillCount * 100)) * 100);
        return new SkillGapResult(finalScore, missingSkills);
    }

    private int calculateSkillMatch(Skill userSkill, RoleSkill requiredSkill) {
        int userLevel = PROFICIENCY_SCORES.getOrDefault(userSkill.getProficiencyLevel().toUpperCase(), 0);
        int requiredLevel = PROFICIENCY_SCORES.getOrDefault(requiredSkill.getRequiredProficiency().toUpperCase(), 0);

        if (userLevel >= requiredLevel) {
            return 100;
        }
        return (int) ((double) userLevel / requiredLevel * 100);
    }

    public int calculateProgress(Long userId) {
        List<Skill> userSkills = skillRepository.findByUserId(userId);
        if (userSkills.isEmpty()) {
            return 0;
        }

        int totalScore = userSkills.stream()
            .mapToInt(skill -> PROFICIENCY_SCORES.getOrDefault(skill.getProficiencyLevel().toUpperCase(), 0))
            .sum();

        return totalScore / userSkills.size();
    }

    public List<SkillProgress> getProgressHistory(Long userId) {
        List<Skill> userSkills = skillRepository.findByUserId(userId);
        
        SkillProgress currentProgress = new SkillProgress();
        currentProgress.setUserId(userId);
        currentProgress.setTotalSkills(userSkills.size());
        currentProgress.setOverallProgress(calculateProgress(userId));
        currentProgress.setAssessmentDate(new Date());
        currentProgress.setSkillCount(userSkills.size());
        
        return List.of(currentProgress);
    }
    
}

