package com.example.skillpath_pro.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "learning_resources")
public class LearningResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String provider;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private double cost;

    @Column
    private String description;

    @ElementCollection
    @CollectionTable(name = "resource_skills",
            joinColumns = @JoinColumn(name = "resource_id"))
    @Column(name = "skill_name")
    private Set<String> relatedSkills = new HashSet<>();

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getRelatedSkills() {
        return relatedSkills;
    }

    public void setRelatedSkills(Set<String> relatedSkills) {
        this.relatedSkills = relatedSkills;
    }
}
