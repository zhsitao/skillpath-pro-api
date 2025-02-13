package com.example.skillpath_pro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.skillpath_pro.model.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}
