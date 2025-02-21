package com.example.skillpath_pro.repository;

import com.example.skillpath_pro.model.SkillAnalysis.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findByUserId(Long userId);
    Optional<Skill> findByUserIdAndId(Long userId, Long id);
}