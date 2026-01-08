package com.interntrack.application_service.service;

/**
 * Exception thrown when an application is not found or not accessible by the user.
 */
public class ApplicationNotFoundException extends RuntimeException {

    public ApplicationNotFoundException(String message) {
        super(message);
    }

    public ApplicationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

