package com.example.skillpath_pro.controller;

import com.example.skillpath_pro.model.*;
import com.example.skillpath_pro.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/learning")
@CrossOrigin(origins = "http://localhost:5173")
public class LearningController {
    
    @Autowired
    private LearningResourceRepository resourceRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserLearningPlanRepository planRepository;
    
    @GetMapping("/resources")
    public ResponseEntity<List<LearningResource>> getAllResources(
            @RequestParam(required = false) Boolean freeOnly,
            @RequestParam(required = false) String skillName) {
        List<LearningResource> resources;
        
        if (skillName != null) {
            resources = resourceRepository.findByRelatedSkillsContaining(skillName);
        } else {
            resources = resourceRepository.findAll();
        }
        
        if (Boolean.TRUE.equals(freeOnly)) {
            resources = resources.stream()
                .filter(r -> r.getCost() == 0)
                .collect(Collectors.toList());
        }
        
        return ResponseEntity.ok(resources);
    }
    
    @GetMapping("/users/{userId}/plan")
    public ResponseEntity<List<Map<String, Object>>> getUserPlan(@PathVariable Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        List<UserLearningPlan> plans = planRepository.findByUser(userOpt.get());
        List<Map<String, Object>> planInfo = plans.stream().map(plan -> {
            Map<String, Object> info = new HashMap<>();
            LearningResource resource = plan.getResource();
            info.put("id", resource.getId());
            info.put("name", resource.getName());
            info.put("provider", resource.getProvider());
            info.put("duration", resource.getDuration());
            info.put("type", resource.getType());
            info.put("cost", resource.getCost());
            info.put("status", plan.getStatus());
            return info;
        }).collect(Collectors.toList());
        
        return ResponseEntity.ok(planInfo);
    }
    
    @PostMapping("/users/{userId}/plan/{resourceId}")
    public ResponseEntity<?> addToPlan(
            @PathVariable Long userId,
            @PathVariable Long resourceId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<LearningResource> resourceOpt = resourceRepository.findById(resourceId);
        
        if (userOpt.isEmpty() || resourceOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        UserLearningPlan plan = new UserLearningPlan();
        plan.setUser(userOpt.get());
        plan.setResource(resourceOpt.get());
        plan.setStatus("Not Started");
        
        planRepository.save(plan);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/users/{userId}/plan/{resourceId}")
    public ResponseEntity<?> updatePlanStatus(
            @PathVariable Long userId,
            @PathVariable Long resourceId,
            @RequestBody Map<String, String> update) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        List<UserLearningPlan> plans = planRepository.findByUser(userOpt.get());
        Optional<UserLearningPlan> planOpt = plans.stream()
            .filter(p -> p.getResource().getId().equals(resourceId))
            .findFirst();
            
        if (planOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        UserLearningPlan plan = planOpt.get();
        plan.setStatus(update.get("status"));
        planRepository.save(plan);
        
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/users/{userId}/plan/{resourceId}")
    public ResponseEntity<?> removeFromPlan(
            @PathVariable Long userId,
            @PathVariable Long resourceId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        planRepository.deleteByUserAndResource_Id(userOpt.get(), resourceId);
        return ResponseEntity.ok().build();
    }
}
