package com.example.skillpath_pro.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column
    private String category;
    
    @ElementCollection
    @CollectionTable(name = "role_required_skills", 
        joinColumns = @JoinColumn(name = "role_id"))
    private Set<String> requiredSkills;

    @ElementCollection
    @CollectionTable(name = "role_optional_skills", 
        joinColumns = @JoinColumn(name = "role_id"))
    private Set<String> optionalSkills;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(Set<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public Set<String> getOptionalSkills() {
        return optionalSkills;
    }

    public void setOptionalSkills(Set<String> optionalSkills) {
        this.optionalSkills = optionalSkills;
    }
}
