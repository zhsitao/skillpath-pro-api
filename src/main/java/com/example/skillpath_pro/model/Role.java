package com.example.skillpath_pro.model;

import jakarta.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String category;

    // Getters and Setters
}
