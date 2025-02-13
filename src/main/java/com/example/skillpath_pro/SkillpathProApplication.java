package com.example.skillpath_pro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.skillpath_pro.model.Role;
import com.example.skillpath_pro.model.Skill;
import com.example.skillpath_pro.repository.RoleRepository;
import com.example.skillpath_pro.repository.SkillRepository;

@SpringBootApplication
public class SkillpathProApplication {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SkillRepository skillRepository;

    public static void main(String[] args) {
        SpringApplication.run(SkillpathProApplication.class, args);
    }

    @Bean
    ApplicationRunner init() {
        return args -> {
            initRole();
            initSkills();
        };
    }

    void initRole() {
        List<Role> roles = List.of(
                // Web Development
                new Role("Frontend Developer", "Web Development"), new Role("Backend Developer", "Web Development"),
                new Role("Full Stack Developer", "Web Development"), new Role("Web Designer", "Web Development"),
                new Role("UI Developer", "Web Development"), new Role("API Developer", "Web Development"),

                // Cloud & Infrastructure
                new Role("DevOps Engineer", "Cloud & Infrastructure"),
                new Role("Cloud Architect", "Cloud & Infrastructure"),
                new Role("Site Reliability Engineer (SRE)", "Cloud & Infrastructure"),
                new Role("Systems Administrator", "Cloud & Infrastructure"),

                // CyberSecurity
                new Role("Cybersecurity Analyst", "CyberSecurity"), new Role("Penetration Tester", "CyberSecurity"),

                // Mobile Development
                new Role("Mobile App Developer (iOS)", "Mobile Development"),
                new Role("Mobile App Developer (Android)", "Mobile Development"),
                new Role("React Native Developer", "Mobile Development"),
                new Role("Flutter Developer", "Mobile Development"),

                // Data & Analytics
                new Role("Data Scientist", "Data & Analytics"), new Role("Data Engineer", "Data & Analytics"),
                new Role("Machine Learning Engineer", "Data & Analytics"),
                new Role("Business Intelligence Analyst", "Data & Analytics"));

        roleRepository.saveAll(roles);
        roleRepository.findAll().forEach(role -> System.out.println(role.getTitle()));
    }

    void initSkills() {
        Role frontendDeveloper = roleRepository.findByTitle("Frontend Developer").get();
        Role backendDeveloper = roleRepository.findByTitle("Backend Developer").get();
        Role fullStackDeveloper = roleRepository.findByTitle("Full Stack Developer").get();
        Role webDesigner = roleRepository.findByTitle("Web Designer").get();
        Role uiDeveloper = roleRepository.findByTitle("UI Developer").get();
        Role apiDeveloper = roleRepository.findByTitle("API Developer").get();

        Role devopsEngineer = roleRepository.findByTitle("DevOps Engineer").get();
        Role cloudArchitect = roleRepository.findByTitle("Cloud Architect").get();
        Role siteReliabilityEngineer = roleRepository.findByTitle("Site Reliability Engineer (SRE)").get();
        Role systemsAdministrator = roleRepository.findByTitle("Systems Administrator").get();

        Role cybersecurityAnalyst = roleRepository.findByTitle("Cybersecurity Analyst").get();
        Role penetrationTester = roleRepository.findByTitle("Penetration Tester").get();

        List<Skill> skills = List.of(
                // Web Development - Frontend Developer
                new Skill("JavaScript", "Programming Language", 1.0, frontendDeveloper),
                new Skill("React", "Framework", 0.9, frontendDeveloper),
                new Skill("HTML/CSS", "Programming Language", 0.8, frontendDeveloper),

                // Web Development - Backend Developer
                new Skill("Java", "Programming Language", 1.0, backendDeveloper),
                new Skill("Spring Boot", "Framework", 0.9, backendDeveloper),
                new Skill("SQL", "Database", 0.8, backendDeveloper),

                // Web Development - Full Stack Developer
                new Skill("JavaScript", "Programming Language", 1.0, fullStackDeveloper),
                new Skill("Node.js", "Framework", 0.9, fullStackDeveloper),
                new Skill("MongoDB", "Database", 0.8, fullStackDeveloper),

                // Web Development - Web Designer
                new Skill("Figma", "Software Tool", 1.0, webDesigner),
                new Skill("Adobe XD", "Software Tool", 0.9, webDesigner),
                new Skill("UI/UX Design Principles", "Concept", 0.8, webDesigner),

                // Web Development - UI Developer
                new Skill("CSS", "Programming Language", 1.0, uiDeveloper),
                new Skill("Bootstrap", "Framework", 0.9, uiDeveloper),
                new Skill("Accessibility", "Concept", 0.8, uiDeveloper),

                // Web Development - API Developer
                new Skill("RESTful APIs", "Concept", 1.0, apiDeveloper),
                new Skill("Postman", "Software Tool", 0.9, apiDeveloper),
                new Skill("Swagger", "Software Tool", 0.8, apiDeveloper),

                // Cloud & Infrastructure - DevOps Engineer
                new Skill("Docker", "Software Tool", 1.0, devopsEngineer),
                new Skill("Kubernetes", "Software Tool", 0.9, devopsEngineer),
                new Skill("CI/CD Pipelines", "Concept", 0.8, devopsEngineer),

                // Cloud & Infrastructure - Cloud Architect
                new Skill("AWS", "Cloud Platform", 1.0, cloudArchitect),
                new Skill("Terraform", "Software Tool", 0.9, cloudArchitect),
                new Skill("Microservices Architecture", "Concept", 0.8, cloudArchitect),

                // Cloud & Infrastructure - Site Reliability Engineer (SRE)
                new Skill("Prometheus", "Software Tool", 1.0, siteReliabilityEngineer),
                new Skill("Grafana", "Software Tool", 0.9, siteReliabilityEngineer),
                new Skill("Incident Management", "Concept", 0.8, siteReliabilityEngineer),

                // Cloud & Infrastructure - Systems Administrator
                new Skill("Linux", "Operating System", 1.0, systemsAdministrator),
                new Skill("Shell Scripting", "Programming Language", 0.9, systemsAdministrator),
                new Skill("Networking", "Concept", 0.8, systemsAdministrator),

                // CyberSecurity - Cybersecurity Analyst
                new Skill("SIEM Tools", "Software Tool", 1.0, cybersecurityAnalyst),
                new Skill("Threat Analysis", "Concept", 0.9, cybersecurityAnalyst),
                new Skill("Firewall Configuration", "Concept", 0.8, cybersecurityAnalyst),

                // CyberSecurity - Penetration Tester
                new Skill("Kali Linux", "Software Tool", 1.0, penetrationTester),
                new Skill("Metasploit", "Software Tool", 0.9, penetrationTester),
                new Skill("OWASP Top 10", "Concept", 0.8, penetrationTester));

        skillRepository.saveAll(skills);
        skillRepository.findAll()
                .forEach(skill -> System.out.println(skill.getRole().getTitle() + ": " + skill.getName()));
    }
}
