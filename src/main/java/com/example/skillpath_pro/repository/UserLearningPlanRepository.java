package com.example.skillpath_pro.repository;

import com.example.skillpath_pro.model.User;
import com.example.skillpath_pro.model.UserLearningPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface UserLearningPlanRepository extends JpaRepository<UserLearningPlan, Long> {
    List<UserLearningPlan> findByUser(User user);
    
    @Modifying
    @Query("DELETE FROM UserLearningPlan p WHERE p.user = :user AND p.resource.id = :resourceId")
    void deleteByUserAndResource_Id(@Param("user") User user, @Param("resourceId") Long resourceId);
}
