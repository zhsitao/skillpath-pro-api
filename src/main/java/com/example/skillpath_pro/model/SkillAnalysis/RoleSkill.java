package com.example.skillpath_pro.model.SkillAnalysis;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "role_skills")
public class RoleSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "role_id", nullable = false)
    private Long roleId;
    
    @Column(name = "skill_name", nullable = false)
    private String skillName;
    
    @Column(name = "required_proficiency")
    private String requiredProficiency;
    
    @Column(name = "is_mandatory")
    private boolean isMandatory;

    public RoleSkill() {
    }

    // Fix this constructor
    public RoleSkill(Long roleId, String skillName, String requiredProficiency) {
        this.roleId = roleId;
        this.skillName = skillName;
        this.requiredProficiency = requiredProficiency;
        this.isMandatory = true;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getRequiredProficiency() {
        return requiredProficiency;
    }

    public void setRequiredProficiency(String requiredProficiency) {
        this.requiredProficiency = requiredProficiency;
    }
}

