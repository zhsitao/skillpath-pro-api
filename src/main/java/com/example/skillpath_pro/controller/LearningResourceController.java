package com.example.skillpath_pro.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.skillpath_pro.model.LearningPlan;
import com.example.skillpath_pro.model.LearningResource;
import com.example.skillpath_pro.service.LearningResourceService;

import java.util.List;

@RestController
@RequestMapping("/api/learning")
@CrossOrigin(origins = "http://localhost:3000")
public class LearningResourceController {

    private final LearningResourceService service;

    @Autowired
    public LearningResourceController(LearningResourceService service) {
        this.service = service;
    }

    @GetMapping("/resources")
    public ResponseEntity<List<LearningResource>> getResources(
            @RequestParam(required = false) Boolean freeOnly,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer maxDuration) {
        return ResponseEntity.ok(service.filterResources(freeOnly, type, maxDuration));
    }

    @GetMapping("/resources/{id}")
    public ResponseEntity<LearningResource> getResourceById(@PathVariable String id) {
        LearningResource resource = service.getResourceById(id);
        return resource != null ? ResponseEntity.ok(resource) : ResponseEntity.notFound().build();
    }

    @GetMapping("/plan/{userId}")
    public ResponseEntity<LearningPlan> getUserPlan(@PathVariable String userId) {
        return ResponseEntity.ok(service.getUserPlan(userId));
    }

    @PostMapping("/plan/{userId}/add/{resourceId}")
    public ResponseEntity<Void> addToPlan(
            @PathVariable String userId,
            @PathVariable String resourceId) {
        service.addToPlan(userId, resourceId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/plan/{userId}/complete/{resourceId}")
    public ResponseEntity<Void> markAsCompleted(
            @PathVariable String userId,
            @PathVariable String resourceId) {
        service.markAsCompleted(userId, resourceId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/plan/{userId}/remove/{resourceId}")
    public ResponseEntity<Void> removeFromPlan(
            @PathVariable String userId,
            @PathVariable String resourceId) {
        service.removeFromPlan(userId, resourceId);
        return ResponseEntity.ok().build();
    }
}