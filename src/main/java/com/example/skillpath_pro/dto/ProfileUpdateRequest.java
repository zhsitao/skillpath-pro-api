package com.example.skillpath_pro.dto;

import java.util.Set;

public class ProfileUpdateRequest {
    private String careerStage;
    private String workExperience;
    private Set<String> skills; // Skill names

    // Getters and Setters
    public String getCareerStage() {
        return careerStage;
    }

    public void setCareerStage(String careerStage) {
        this.careerStage = careerStage;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }
}
