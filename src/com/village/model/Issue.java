package com.village.model;

import java.sql.Timestamp;

public class Issue {
    private int id;
    private String title;
    private String description;
    private String category;
    private String status;
    private int reportedBy;
    private Timestamp createdAt;

    public Issue() {}

    public Issue(int id, String title, String description, String category, String status, int reportedBy, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.status = status;
        this.reportedBy = reportedBy;
        this.createdAt = createdAt;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getReportedBy() { return reportedBy; }
    public void setReportedBy(int reportedBy) { this.reportedBy = reportedBy; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
