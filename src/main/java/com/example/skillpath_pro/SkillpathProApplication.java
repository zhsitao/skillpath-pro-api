package com.example.skillpath_pro;

import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.skillpath_pro.model.Role;
import com.example.skillpath_pro.repository.RoleRepository;

@SpringBootApplication
public class SkillpathProApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkillpathProApplication.class, args);
    }

    @Bean
    ApplicationRunner init(RoleRepository roleRepository) {
        return args -> {
            initRole(roleRepository);
        };
    }

    void initRole(RoleRepository roleRepository) {
        List<Role> roles = List.of(
                // Web Development
                new Role("Frontend Developer", "Web Development"),
                new Role("Backend Developer", "Web Development"),
                new Role("Full Stack Developer", "Web Development"),
                new Role("Web Designer", "Web Development"),
                new Role("UI Developer", "Web Development"),
                new Role("API Developer", "Web Development"),

                // Cloud & Infrastructure
                new Role("DevOps Engineer", "Cloud & Infrastructure"),
                new Role("Cloud Architect", "Cloud & Infrastructure"),
                new Role("Site Reliability Engineer (SRE)", "Cloud & Infrastructure"),
                new Role("Systems Administrator", "Cloud & Infrastructure"),

                // CyberSecurity
                new Role("Cybersecurity Analyst", "CyberSecurity"),
                new Role("Penetration Tester", "CyberSecurity"),

                // Mobile Development
                new Role("Mobile App Developer (iOS)", "Mobile Development"),
                new Role("Mobile App Developer (Android)", "Mobile Development"),
                new Role("React Native Developer", "Mobile Development"),
                new Role("Flutter Developer", "Mobile Development"),

                // Data & Analytics
                new Role("Data Scientist", "Data & Analytics"),
                new Role("Data Engineer", "Data & Analytics"),
                new Role("Machine Learning Engineer", "Data & Analytics"),
                new Role("Business Intelligence Analyst", "Data & Analytics"));

        roleRepository.saveAll(roles);
        roleRepository.findAll().forEach(System.out::println);
    }
}
