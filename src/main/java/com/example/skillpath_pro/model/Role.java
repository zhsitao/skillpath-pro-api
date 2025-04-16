package com.example.skillpath_pro.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column
    private String description;

    @Column
    private String category;

    @ElementCollection
    @CollectionTable(name = "role_required_skills")
    @MapKeyColumn(name = "skill_name")
    @Column(name = "required_level")
    private Map<String, String> requiredSkills = new HashMap<>();

    @ElementCollection
    @CollectionTable(name = "role_optional_skills")
    @MapKeyColumn(name = "skill_name")
    @Column(name = "recommended_level")
    private Map<String, String> optionalSkills = new HashMap<>();

    @ElementCollection
    @CollectionTable(name = "role_skill_descriptions")
    @MapKeyColumn(name = "skill_name")
    @Column(name = "description")
    private Map<String, String> skillDescriptions = new HashMap<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Map<String, String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(Map<String, String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public Map<String, String> getOptionalSkills() {
        return optionalSkills;
    }

    public void setOptionalSkills(Map<String, String> optionalSkills) {
        this.optionalSkills = optionalSkills;
    }

    public Map<String, String> getSkillDescriptions() {
        return skillDescriptions;
    }

    public void setSkillDescriptions(Map<String, String> skillDescriptions) {
        this.skillDescriptions = skillDescriptions;
    }
}
