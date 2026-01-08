package com.interntrack.application_service.controller;

import com.interntrack.application_service.domain.Application;
import com.interntrack.application_service.domain.ApplicationStatus;
import com.interntrack.application_service.domain.ApplicationType;
import com.interntrack.application_service.dto.ApplicationRequest;
import com.interntrack.application_service.dto.ApplicationResponse;
import com.interntrack.application_service.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * REST controller for application management.
 */
@RestController
@RequestMapping("/api/v1/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ResponseEntity<ApplicationResponse> createApplication(
            @RequestHeader("X-User-Id") String userId,
            @Valid @RequestBody ApplicationRequest request) {
        Application application = toEntity(request);
        Application created = applicationService.createApplication(userId, application);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponse> getApplication(
            @PathVariable UUID id,
            @RequestHeader("X-User-Id") String userId) {
        Application application = applicationService.getApplicationById(id, userId);
        return ResponseEntity.ok(toResponse(application));
    }

    @GetMapping
    public ResponseEntity<Page<ApplicationResponse>> getApplications(
            @RequestHeader("X-User-Id") String userId,
            @RequestParam(required = false) List<ApplicationStatus> status,
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) ApplicationType applicationType,
            @RequestParam(required = false) LocalDate fromDeadline,
            @RequestParam(required = false) LocalDate toDeadline,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "20") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDirection) {
        
        Sort sort = createSort(sortBy, sortDirection);
        Pageable pageable = PageRequest.of(page, size, sort);
        
        Page<Application> applications = applicationService.getApplications(
                userId, status, companyName, applicationType, fromDeadline, toDeadline, pageable);
        
        Page<ApplicationResponse> response = applications.map(this::toResponse);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationResponse> updateApplication(
            @PathVariable UUID id,
            @RequestHeader("X-User-Id") String userId,
            @Valid @RequestBody ApplicationRequest request) {
        Application application = toEntity(request);
        Application updated = applicationService.updateApplication(id, userId, application);
        return ResponseEntity.ok(toResponse(updated));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApplicationResponse> partialUpdateApplication(
            @PathVariable UUID id,
            @RequestHeader("X-User-Id") String userId,
            @RequestBody ApplicationRequest request) {
        Application application = toEntity(request);
        Application updated = applicationService.partialUpdateApplication(id, userId, application);
        return ResponseEntity.ok(toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(
            @PathVariable UUID id,
            @RequestHeader("X-User-Id") String userId) {
        applicationService.deleteApplication(id, userId);
        return ResponseEntity.noContent().build();
    }

    private Application toEntity(ApplicationRequest request) {
        Application application = new Application();
        application.setCompanyName(request.getCompanyName());
        application.setRoleTitle(request.getRoleTitle());
        application.setApplicationType(request.getApplicationType());
        application.setStatus(request.getStatus());
        application.setApplicationDeadline(request.getApplicationDeadline());
        application.setAppliedDate(request.getAppliedDate());
        application.setLocation(request.getLocation());
        application.setJobPostingUrl(request.getJobPostingUrl());
        application.setNotes(request.getNotes());
        return application;
    }

    private ApplicationResponse toResponse(Application application) {
        ApplicationResponse response = new ApplicationResponse();
        response.setId(application.getId());
        response.setCompanyName(application.getCompanyName());
        response.setRoleTitle(application.getRoleTitle());
        response.setApplicationType(application.getApplicationType());
        response.setStatus(application.getStatus());
        response.setApplicationDeadline(application.getApplicationDeadline());
        response.setAppliedDate(application.getAppliedDate());
        response.setLocation(application.getLocation());
        response.setJobPostingUrl(application.getJobPostingUrl());
        response.setNotes(application.getNotes());
        response.setCreatedAt(application.getCreatedAt());
        response.setUpdatedAt(application.getUpdatedAt());
        return response;
    }

    private Sort createSort(String sortBy, String sortDirection) {
        if (sortBy == null || sortBy.isEmpty()) {
            return Sort.by(Sort.Direction.DESC, "createdAt");
        }
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDirection) 
                ? Sort.Direction.DESC 
                : Sort.Direction.ASC;
        
        // Map API sort fields to entity fields (use entity field names, not DB column names)
        String field = switch (sortBy.toLowerCase()) {
            case "deadline" -> "applicationDeadline";
            case "createdat" -> "createdAt";
            case "companyname" -> "companyName";
            case "status" -> "status";
            default -> sortBy;
        };
        
        return Sort.by(direction, field);
    }
}

