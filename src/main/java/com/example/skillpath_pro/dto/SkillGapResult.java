package com.example.skillpath_pro.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.skillpath_pro.model.SkillAnalysis.RoleSkill;
import com.example.skillpath_pro.model.SkillAnalysis.Skill;

public class SkillGapResult {
    private int totalScore;
    private List<RoleSkill> missingSkills;
    private Map<String, List<Skill>> categorizedSkills;
    private LocalDateTime analysisDate;
    private int progressPercentage;
    private List<String> recommendations;
    private Map<String, Integer> categoryScores;

    public SkillGapResult(int totalScore, List<RoleSkill> missingSkills) {
        this.totalScore = totalScore;
        this.missingSkills = missingSkills;
        this.analysisDate = LocalDateTime.now();
        this.categorizedSkills = new HashMap<>();
        this.recommendations = new ArrayList<>();
        this.categoryScores = new HashMap<>();
    }

    // Getters
    public int getTotalScore() {
        return totalScore;
    }

    public List<RoleSkill> getMissingSkills() {
        return missingSkills;
    }

    public Map<String, List<Skill>> getCategorizedSkills() {
        return categorizedSkills;
    }

    public LocalDateTime getAnalysisDate() {
        return analysisDate;
    }

    public int getProgressPercentage() {
        return progressPercentage;
    }

    public List<String> getRecommendations() {
        return recommendations;
    }

    public Map<String, Integer> getCategoryScores() {
        return categoryScores;
    }

    // Setters
    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public void setMissingSkills(List<RoleSkill> missingSkills) {
        this.missingSkills = missingSkills;
    }

    public void setCategorizedSkills(Map<String, List<Skill>> categorizedSkills) {
        this.categorizedSkills = categorizedSkills;
    }

    public void setAnalysisDate(LocalDateTime analysisDate) {
        this.analysisDate = analysisDate;
    }

    public void setProgressPercentage(int progressPercentage) {
        this.progressPercentage = progressPercentage;
    }

    public void setRecommendations(List<String> recommendations) {
        this.recommendations = recommendations;
    }

    public void setCategoryScores(Map<String, Integer> categoryScores) {
        this.categoryScores = categoryScores;
    }

    // Helper methods
    public void addCategoryScore(String category, int score) {
        this.categoryScores.put(category, score);
    }

    public void addRecommendation(String recommendation) {
        this.recommendations.add(recommendation);
    }

    public void addCategorizedSkills(String category, List<Skill> skills) {
        this.categorizedSkills.put(category, skills);
    }
}


