package com.example.skillpath_pro.model;

public class LearningResource {
    private String id;
    private String title;
    private String description;
    private String provider;
    private String type;
    private int durationHours;
    private String difficulty;
    private boolean isFree;
    private String skillCategory;
    private String url;
    private double price;
    private String status;

    // Getters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getProvider() { return provider; }
    public String getType() { return type; }
    public int getDurationHours() { return durationHours; }
    public String getDifficulty() { return difficulty; }
    public boolean getIsFree() { return isFree; }
    public String getSkillCategory() { return skillCategory; }
    public String getUrl() { return url; }
    public double getPrice() { return price; }
    public String getStatus() { return status; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setProvider(String provider) { this.provider = provider; }
    public void setType(String type) { this.type = type; }
    public void setDurationHours(int durationHours) { this.durationHours = durationHours; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    public void setIsFree(boolean isFree) { this.isFree = isFree; }
    public void setSkillCategory(String skillCategory) { this.skillCategory = skillCategory; }
    public void setUrl(String url) { this.url = url; }
    public void setPrice(double price) { this.price = price; }
    public void setStatus(String status) { this.status = status; }
}