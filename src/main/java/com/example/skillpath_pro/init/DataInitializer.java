package com.example.skillpath_pro.init;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.skillpath_pro.model.SkillAnalysis.RoleSkill;
import com.example.skillpath_pro.model.SkillAnalysis.Skill;
import com.example.skillpath_pro.repository.RoleSkillRepository;
import com.example.skillpath_pro.repository.SkillRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private SkillRepository skillRepository;
    
    @Autowired
    private RoleSkillRepository roleSkillRepository;

    @Override
    public void run(String... args) {
        // Add user skills
        List<Skill> userSkills = Arrays.asList(
            new Skill(2L, "Java", "INTERMEDIATE"),
            new Skill(2L, "Python", "BEGINNER"),
            new Skill(2L, "React", "ADVANCED"),
            new Skill(2L, "Docker", "BEGINNER")
        );
        skillRepository.saveAll(userSkills);

        // Add role skills (for roleId 1)
        List<RoleSkill> roleSkills = Arrays.asList(
            new RoleSkill(1L, "Java", "ADVANCED"),
            new RoleSkill(1L, "Python", "INTERMEDIATE"),
            new RoleSkill(1L, "React", "ADVANCED"),
            new RoleSkill(1L, "Docker", "INTERMEDIATE"),
            new RoleSkill(1L, "Kubernetes", "BEGINNER")
        );
        roleSkillRepository.saveAll(roleSkills);
    }
}
