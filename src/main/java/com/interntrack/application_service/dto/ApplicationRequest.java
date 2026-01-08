package com.interntrack.application_service.dto;

import com.interntrack.application_service.domain.ApplicationStatus;
import com.interntrack.application_service.domain.ApplicationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/**
 * DTO for creating and updating applications.
 */
public class ApplicationRequest {
    
    @NotBlank(message = "Company name is required")
    @Size(max = 255, message = "Company name must not exceed 255 characters")
    private String companyName;
    
    @NotBlank(message = "Role title is required")
    @Size(max = 255, message = "Role title must not exceed 255 characters")
    private String roleTitle;
    
    @NotNull(message = "Application type is required")
    private ApplicationType applicationType;
    
    private ApplicationStatus status;
    
    private LocalDate applicationDeadline;
    
    private LocalDate appliedDate;
    
    @Size(max = 255, message = "Location must not exceed 255 characters")
    private String location;
    
    @Size(max = 2048, message = "Job posting URL must not exceed 2048 characters")
    private String jobPostingUrl;
    
    private String notes;

    // Getters and Setters
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
}

