package com.example.skillpath_pro.model;


import java.util.ArrayList;
import java.util.List;

public class LearningPlan {
    private String userId;
    private List<LearningResource> plannedResources;
    private List<LearningResource> completedResources;

    public LearningPlan() {
        this.plannedResources = new ArrayList<>();
        this.completedResources = new ArrayList<>();
    }

    // Getters
    public String getUserId() { return userId; }
    public List<LearningResource> getPlannedResources() { return plannedResources; }
    public List<LearningResource> getCompletedResources() { return completedResources; }

    // Setters
    public void setUserId(String userId) { this.userId = userId; }
    public void setPlannedResources(List<LearningResource> plannedResources) { 
        this.plannedResources = plannedResources; 
    }
    public void setCompletedResources(List<LearningResource> completedResources) { 
        this.completedResources = completedResources; 
    }
}