package com.interntrack.application_service.service;

import com.interntrack.application_service.domain.Application;
import com.interntrack.application_service.domain.ApplicationStatus;
import com.interntrack.application_service.domain.ApplicationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Service interface for managing applications.
 */
public interface ApplicationService {
    Application createApplication(String userId, Application application);
    Application getApplicationById(UUID id, String userId);
    Page<Application> getApplications(String userId, List<ApplicationStatus> statuses, String companyName, 
                                       ApplicationType applicationType, LocalDate fromDeadline, 
                                       LocalDate toDeadline, Pageable pageable);
    Application updateApplication(UUID id, String userId, Application application);
    Application partialUpdateApplication(UUID id, String userId, Application application);
    void deleteApplication(UUID id, String userId);
}

