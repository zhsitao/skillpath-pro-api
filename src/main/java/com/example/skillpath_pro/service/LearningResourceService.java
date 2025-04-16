package com.example.skillpath_pro.service;



import org.springframework.stereotype.Service;

import com.example.skillpath_pro.model.LearningPlan;
import com.example.skillpath_pro.model.LearningResource;

import java.util.*;

@Service
public class LearningResourceService {
    private final Map<String, LearningResource> resources;
    private final Map<String, LearningPlan> userPlans;

    public LearningResourceService() {
        this.resources = new HashMap<>();
        this.userPlans = new HashMap<>();
        initializeSampleData();
    }

    private void initializeSampleData() {
        // Sample 1: Docker Course
        LearningResource docker = new LearningResource();
        docker.setId("1");
        docker.setTitle("Docker Essentials");
        docker.setDescription("Learn Docker fundamentals and containerization");
        docker.setProvider("freeCodeCamp");
        docker.setType("COURSE");
        docker.setDurationHours(10);
        docker.setDifficulty("Intermediate");
        docker.setIsFree(true);
        docker.setSkillCategory("DevOps");
        docker.setUrl("https://example.com/docker");
        docker.setPrice(0.0);
        docker.setStatus("AVAILABLE");
        resources.put(docker.getId(), docker);

        // Sample 2: AWS Certification
        LearningResource aws = new LearningResource();
        aws.setId("2");
        aws.setTitle("AWS Cloud Practitioner");
        aws.setDescription("Comprehensive AWS certification preparation");
        aws.setProvider("AWS Training");
        aws.setType("CERTIFICATION");
        aws.setDurationHours(40);
        aws.setDifficulty("Beginner");
        aws.setIsFree(false);
        aws.setSkillCategory("Cloud");
        aws.setUrl("https://example.com/aws");
        aws.setPrice(299.99);
        aws.setStatus("AVAILABLE");
        resources.put(aws.getId(), aws);

        // Sample 3: React Tutorial
        LearningResource react = new LearningResource();
        react.setId("3");
        react.setTitle("React.js Fundamentals");
        react.setDescription("Master React.js basics and hooks");
        react.setProvider("Udemy");
        react.setType("COURSE");
        react.setDurationHours(15);
        react.setDifficulty("Beginner");
        react.setIsFree(false);
        react.setSkillCategory("Frontend");
        react.setUrl("https://example.com/react");
        react.setPrice(89.99);
        react.setStatus("AVAILABLE");
        resources.put(react.getId(), react);
    }

    public List<LearningResource> getAllResources() {
        return new ArrayList<>(resources.values());
    }

    public LearningResource getResourceById(String id) {
        return resources.get(id);
    }

    public List<LearningResource> filterResources(Boolean freeOnly, String type, Integer maxDuration) {
        return resources.values().stream()
            .filter(r -> freeOnly == null || r.getIsFree() == freeOnly)
            .filter(r -> type == null || type.isEmpty() || r.getType().equals(type))
            .filter(r -> maxDuration == null || r.getDurationHours() <= maxDuration)
            .toList();
    }

    public LearningPlan getUserPlan(String userId) {
        return userPlans.computeIfAbsent(userId, k -> {
            LearningPlan plan = new LearningPlan();
            plan.setUserId(userId);
            return plan;
        });
    }

    public void addToPlan(String userId, String resourceId) {
        LearningPlan plan = getUserPlan(userId);
        LearningResource resource = resources.get(resourceId);
        
        if (resource != null && !plan.getPlannedResources().contains(resource)) {
            plan.getPlannedResources().add(resource);
        }
    }

    public void markAsCompleted(String userId, String resourceId) {
        LearningPlan plan = getUserPlan(userId);
        LearningResource resource = resources.get(resourceId);
        
        if (resource != null) {
            plan.getPlannedResources().remove(resource);
            if (!plan.getCompletedResources().contains(resource)) {
                plan.getCompletedResources().add(resource);
            }
        }
    }

    public void removeFromPlan(String userId, String resourceId) {
        LearningPlan plan = getUserPlan(userId);
        LearningResource resource = resources.get(resourceId);
        
        if (resource != null) {
            plan.getPlannedResources().remove(resource);
            plan.getCompletedResources().remove(resource);
        }
    }
}
