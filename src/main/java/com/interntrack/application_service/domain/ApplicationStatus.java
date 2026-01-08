package com.interntrack.application_service.domain;

/**
 * Enum representing the status of an application.
 */
public enum ApplicationStatus {
    /**
     * Considering applying or preparing materials.
     */
    PLANNING,
    
    /**
     * Application submitted.
     */
    APPLIED,
    
    /**
     * Any stage of interviews (phone screen, onsite, etc.).
     */
    INTERVIEW,
    
    /**
     * Offer extended.
     */
    OFFER,
    
    /**
     * No longer in process, rejected or no response.
     */
    REJECTED,
    
    /**
     * User chose to withdraw.
     */
    WITHDRAWN
}

