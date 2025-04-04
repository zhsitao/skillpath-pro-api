package com.example.skillpath_pro.repository;

import com.example.skillpath_pro.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}