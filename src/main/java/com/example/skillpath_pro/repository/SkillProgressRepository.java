package com.example.skillpath_pro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.skillpath_pro.model.SkillAnalysis.SkillProgress;

@Repository
public interface SkillProgressRepository extends JpaRepository<SkillProgress, Long> {
    List<SkillProgress> findByUserIdOrderByAssessmentDateDesc(Long userId);
}

