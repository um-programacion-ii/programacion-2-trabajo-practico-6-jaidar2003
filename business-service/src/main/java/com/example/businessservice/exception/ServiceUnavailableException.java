package com.example.businessservice.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception thrown when the data service is unavailable or there are connectivity issues.
 */
public class ServiceUnavailableException extends DataServiceException {

    /**
     * Constructs a new ServiceUnavailableException with the specified detail message.
     *
     * @param message the detail message
     */
    public ServiceUnavailableException(String message) {
        super(message, HttpStatus.SERVICE_UNAVAILABLE);
    }

    /**
     * Constructs a new ServiceUnavailableException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause
     */
    public ServiceUnavailableException(String message, Throwable cause) {
        super(message, cause, HttpStatus.SERVICE_UNAVAILABLE);
    }
}