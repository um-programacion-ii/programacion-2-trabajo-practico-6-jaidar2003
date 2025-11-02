package com.example.dataservice.exception;

/**
 * Base exception class for the data service.
 */
public class DataServiceException extends RuntimeException {

    public DataServiceException(String message) {
        super(message);
    }

    public DataServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}