package com.example.skillpath_pro.repository;

import com.example.skillpath_pro.model.UserSkill;
import com.example.skillpath_pro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserSkillRepository extends JpaRepository<UserSkill, Long> {
    List<UserSkill> findByUser(User user);
}
