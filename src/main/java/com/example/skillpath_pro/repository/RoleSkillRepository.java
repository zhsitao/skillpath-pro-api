package com.example.skillpath_pro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.skillpath_pro.model.SkillAnalysis.RoleSkill;

@Repository
public interface RoleSkillRepository extends JpaRepository<RoleSkill, Long> {
    List<RoleSkill> findByRoleId(Long roleId);
}

