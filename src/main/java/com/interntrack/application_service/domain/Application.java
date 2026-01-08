package com.interntrack.application_service.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity representing an internship or job application.
 */
@Entity
@Table(name = "applications", indexes = {
    @Index(name = "idx_applications_user_id", columnList = "user_id"),
    @Index(name = "idx_applications_status", columnList = "status"),
    @Index(name = "idx_applications_deadline", columnList = "application_deadline"),
    @Index(name = "idx_applications_company_name", columnList = "company_name")
})
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    @NotNull(message = "User ID is required")
    private String userId;

    @Column(name = "company_name", nullable = false)
    @NotBlank(message = "Company name is required")
    @Size(max = 255, message = "Company name must not exceed 255 characters")
    private String companyName;

    @Column(name = "role_title", nullable = false)
    @NotBlank(message = "Role title is required")
    @Size(max = 255, message = "Role title must not exceed 255 characters")
    private String roleTitle;

    @Enumerated(EnumType.STRING)
    @Column(name = "application_type", nullable = false)
    @NotNull(message = "Application type is required")
    private ApplicationType applicationType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @NotNull(message = "Status is required")
    private ApplicationStatus status;

    @Column(name = "application_deadline")
    private LocalDate applicationDeadline;

    @Column(name = "applied_date")
    private LocalDate appliedDate;

    @Column(name = "location")
    @Size(max = 255, message = "Location must not exceed 255 characters")
    private String location;

    @Column(name = "job_posting_url", length = 2048)
    @Size(max = 2048, message = "Job posting URL must not exceed 2048 characters")
    private String jobPostingUrl;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Default constructor for JPA
    public Application() {
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    public ApplicationType getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(ApplicationType applicationType) {
        this.applicationType = applicationType;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public LocalDate getApplicationDeadline() {
        return applicationDeadline;
    }

    public void setApplicationDeadline(LocalDate applicationDeadline) {
        this.applicationDeadline = applicationDeadline;
    }

    public LocalDate getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(LocalDate appliedDate) {
        this.appliedDate = appliedDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJobPostingUrl() {
        return jobPostingUrl;
    }

    public void setJobPostingUrl(String jobPostingUrl) {
        this.jobPostingUrl = jobPostingUrl;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

