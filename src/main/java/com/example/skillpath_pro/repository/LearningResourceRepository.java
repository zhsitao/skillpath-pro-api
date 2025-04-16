package com.example.skillpath_pro.repository;

import com.example.skillpath_pro.model.LearningResource;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LearningResourceRepository extends JpaRepository<LearningResource, Long> {
    List<LearningResource> findByRelatedSkillsContaining(String skillName);
}
