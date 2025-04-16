package com.example.skillpath_pro.repository;

import com.example.skillpath_pro.model.User;
import com.example.skillpath_pro.model.UserLearningPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserLearningPlanRepository extends JpaRepository<UserLearningPlan, Long> {
    List<UserLearningPlan> findByUser(User user);
    void deleteByUserAndResource_Id(User user, Long resourceId);
}
