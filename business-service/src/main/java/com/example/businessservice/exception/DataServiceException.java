package com.example.businessservice.exception;

import org.springframework.http.HttpStatus;

/**
 * Base exception class for data service communication errors.
 */
public class DataServiceException extends RuntimeException {

    private final HttpStatus status;

    /**
     * Constructs a new DataServiceException with the specified detail message and HTTP status.
     *
     * @param message the detail message
     * @param status the HTTP status
     */
    public DataServiceException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    /**
     * Constructs a new DataServiceException with the specified detail message, cause, and HTTP status.
     *
     * @param message the detail message
     * @param cause the cause
     * @param status the HTTP status
     */
    public DataServiceException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }

    /**
     * Returns the HTTP status associated with this exception.
     *
     * @return the HTTP status
     */
    public HttpStatus getStatus() {
        return status;
    }
}