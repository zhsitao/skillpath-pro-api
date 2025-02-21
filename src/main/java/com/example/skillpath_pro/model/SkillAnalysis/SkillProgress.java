package com.example.skillpath_pro.model.SkillAnalysis;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "skill_progress")
public class SkillProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long userId;
    private int overallProgress;
    
    @Column(name = "skill_id")
    private Long skillId;
    
    private String proficiencyLevel;
    
    @Column(name = "assessment_date")
    private LocalDateTime assessmentDate;
    
    private int score;
    private int totalSkills;
    private int skillCount;

    // Getters
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public int getOverallProgress() {
        return overallProgress;
    }

    public Long getSkillId() {
        return skillId;
    }

    public String getProficiencyLevel() {
        return proficiencyLevel;
    }

    public LocalDateTime getAssessmentDate() {
        return assessmentDate;
    }

    public int getScore() {
        return score;
    }

    public int getTotalSkills() {
        return totalSkills;
    }

    public int getSkillCount() {
        return skillCount;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setOverallProgress(int overallProgress) {
        this.overallProgress = overallProgress;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public void setProficiencyLevel(String proficiencyLevel) {
        this.proficiencyLevel = proficiencyLevel;
    }

    public void setAssessmentDate(Date date) {
        this.assessmentDate = LocalDateTime.now();
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTotalSkills(int totalSkills) {
        this.totalSkills = totalSkills;
    }

    public void setSkillCount(int skillCount) {
        this.skillCount = skillCount;
    }
}
