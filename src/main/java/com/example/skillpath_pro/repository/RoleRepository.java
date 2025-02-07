package com.example.skillpath_pro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.skillpath_pro.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
