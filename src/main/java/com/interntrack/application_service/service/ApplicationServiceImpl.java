package com.interntrack.application_service.service;

import com.interntrack.application_service.domain.Application;
import com.interntrack.application_service.domain.ApplicationStatus;
import com.interntrack.application_service.domain.ApplicationType;
import com.interntrack.application_service.repository.ApplicationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of ApplicationService.
 */
@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public Application createApplication(String userId, Application application) {
        validateApplication(application);
        application.setUserId(userId);
        
        // Default status logic
        if (application.getStatus() == null) {
            application.setStatus(application.getAppliedDate() != null 
                ? ApplicationStatus.APPLIED 
                : ApplicationStatus.PLANNING);
        }
        
        return applicationRepository.save(application);
    }

    @Override
    @Transactional(readOnly = true)
    public Application getApplicationById(UUID id, String userId) {
        return applicationRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ApplicationNotFoundException(
                        "Application not found or not accessible"));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Application> getApplications(String userId, List<ApplicationStatus> statuses, 
                                            String companyName, ApplicationType applicationType,
                                            LocalDate fromDeadline, LocalDate toDeadline, 
                                            Pageable pageable) {
        return applicationRepository.findByUserIdWithFilters(
                userId,
                statuses != null && !statuses.isEmpty() ? statuses : null,
                StringUtils.hasText(companyName) ? companyName : null,
                applicationType,
                fromDeadline,
                toDeadline,
                pageable);
    }

    @Override
    public Application updateApplication(UUID id, String userId, Application application) {
        Application existing = getApplicationById(id, userId);
        validateApplication(application);
        
        existing.setCompanyName(application.getCompanyName());
        existing.setRoleTitle(application.getRoleTitle());
        existing.setApplicationType(application.getApplicationType());
        existing.setStatus(application.getStatus());
        existing.setApplicationDeadline(application.getApplicationDeadline());
        existing.setAppliedDate(application.getAppliedDate());
        existing.setLocation(application.getLocation());
        existing.setJobPostingUrl(application.getJobPostingUrl());
        existing.setNotes(application.getNotes());
        
        return applicationRepository.save(existing);
    }

    @Override
    public Application partialUpdateApplication(UUID id, String userId, Application application) {
        Application existing = getApplicationById(id, userId);
        
        if (application.getCompanyName() != null) {
            if (application.getCompanyName().isBlank()) {
                throw new IllegalArgumentException("Company name cannot be blank");
            }
            existing.setCompanyName(application.getCompanyName());
        }
        if (application.getRoleTitle() != null) {
            if (application.getRoleTitle().isBlank()) {
                throw new IllegalArgumentException("Role title cannot be blank");
            }
            existing.setRoleTitle(application.getRoleTitle());
        }
        if (application.getApplicationType() != null) {
            existing.setApplicationType(application.getApplicationType());
        }
        if (application.getStatus() != null) {
            existing.setStatus(application.getStatus());
        }
        if (application.getApplicationDeadline() != null) {
            existing.setApplicationDeadline(application.getApplicationDeadline());
        }
        if (application.getAppliedDate() != null) {
            existing.setAppliedDate(application.getAppliedDate());
        }
        if (application.getLocation() != null) {
            existing.setLocation(application.getLocation());
        }
        if (application.getJobPostingUrl() != null) {
            existing.setJobPostingUrl(application.getJobPostingUrl());
        }
        if (application.getNotes() != null) {
            existing.setNotes(application.getNotes());
        }
        
        return applicationRepository.save(existing);
    }

    @Override
    public void deleteApplication(UUID id, String userId) {
        Application existing = getApplicationById(id, userId);
        applicationRepository.delete(existing);
    }

    private void validateApplication(Application application) {
        if (application == null) {
            throw new IllegalArgumentException("Application cannot be null");
        }
        if (!StringUtils.hasText(application.getCompanyName())) {
            throw new IllegalArgumentException("Company name is required");
        }
        if (!StringUtils.hasText(application.getRoleTitle())) {
            throw new IllegalArgumentException("Role title is required");
        }
        if (application.getApplicationType() == null) {
            throw new IllegalArgumentException("Application type is required");
        }
    }
}

