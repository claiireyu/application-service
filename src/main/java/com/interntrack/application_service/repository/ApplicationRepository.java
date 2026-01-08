package com.interntrack.application_service.repository;

import com.interntrack.application_service.domain.Application;
import com.interntrack.application_service.domain.ApplicationStatus;
import com.interntrack.application_service.domain.ApplicationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for Application entity.
 * Provides data access operations for applications.
 */
@Repository
public interface ApplicationRepository extends JpaRepository<Application, UUID> {

    /**
     * Find all applications by user ID.
     *
     * @param userId the user ID
     * @return list of applications for the user
     */
    List<Application> findByUserId(String userId);

    /**
     * Find all applications by user ID with pagination.
     *
     * @param userId the user ID
     * @param pageable pagination information
     * @return page of applications for the user
     */
    Page<Application> findByUserId(String userId, Pageable pageable);

    /**
     * Find application by ID and user ID.
     *
     * @param id the application ID
     * @param userId the user ID
     * @return optional application
     */
    Optional<Application> findByIdAndUserId(UUID id, String userId);

    /**
     * Find applications by status.
     *
     * @param status the application status
     * @return list of applications with the given status
     */
    List<Application> findByStatus(ApplicationStatus status);

    /**
     * Find applications by user ID and status.
     *
     * @param userId the user ID
     * @param status the application status
     * @return list of applications
     */
    List<Application> findByUserIdAndStatus(String userId, ApplicationStatus status);

    /**
     * Find applications by application type.
     *
     * @param applicationType the application type
     * @return list of applications with the given type
     */
    List<Application> findByApplicationType(ApplicationType applicationType);

    /**
     * Find applications by user ID and application type.
     *
     * @param userId the user ID
     * @param applicationType the application type
     * @return list of applications
     */
    List<Application> findByUserIdAndApplicationType(String userId, ApplicationType applicationType);

    /**
     * Find applications with deadline before or on the given date.
     *
     * @param date the deadline date
     * @return list of applications
     */
    List<Application> findByApplicationDeadlineLessThanEqual(LocalDate date);

    /**
     * Find applications by user ID with deadline before or on the given date.
     *
     * @param userId the user ID
     * @param date the deadline date
     * @return list of applications
     */
    List<Application> findByUserIdAndApplicationDeadlineLessThanEqual(String userId, LocalDate date);

    /**
     * Find applications by company name (case-insensitive).
     *
     * @param companyName the company name
     * @return list of applications
     */
    @Query("SELECT a FROM Application a WHERE LOWER(a.companyName) LIKE LOWER(CONCAT('%', :companyName, '%'))")
    List<Application> findByCompanyNameContainingIgnoreCase(@Param("companyName") String companyName);

    /**
     * Find applications by user ID and company name (case-insensitive).
     *
     * @param userId the user ID
     * @param companyName the company name
     * @return list of applications
     */
    @Query("SELECT a FROM Application a WHERE a.userId = :userId AND LOWER(a.companyName) LIKE LOWER(CONCAT('%', :companyName, '%'))")
    List<Application> findByUserIdAndCompanyNameContainingIgnoreCase(@Param("userId") String userId, @Param("companyName") String companyName);

    /**
     * Find applications with optional filters.
     */
    @Query("SELECT a FROM Application a WHERE a.userId = :userId " +
           "AND (COALESCE(:statuses, NULL) IS NULL OR a.status IN :statuses) " +
           "AND (:companyName IS NULL OR :companyName = '' OR LOWER(a.companyName) LIKE LOWER(CONCAT('%', CAST(:companyName AS string), '%'))) " +
           "AND (:applicationType IS NULL OR a.applicationType = :applicationType) " +
           "AND (:fromDeadline IS NULL OR a.applicationDeadline >= :fromDeadline) " +
           "AND (:toDeadline IS NULL OR a.applicationDeadline <= :toDeadline)")
    Page<Application> findByUserIdWithFilters(
            @Param("userId") String userId,
            @Param("statuses") List<ApplicationStatus> statuses,
            @Param("companyName") String companyName,
            @Param("applicationType") ApplicationType applicationType,
            @Param("fromDeadline") LocalDate fromDeadline,
            @Param("toDeadline") LocalDate toDeadline,
            Pageable pageable);
}

