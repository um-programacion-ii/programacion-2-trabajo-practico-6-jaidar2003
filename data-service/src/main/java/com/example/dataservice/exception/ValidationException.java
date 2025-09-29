package com.example.dataservice.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * Exception thrown when input validation fails.
 */
public class ValidationException extends DataServiceException {

    private final Map<String, String> errors;

    public ValidationException(String message) {
        super(message);
        this.errors = new HashMap<>();
    }

    public ValidationException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }

    /**
     * Add a validation error.
     *
     * @param field the field that failed validation
     * @param message the error message
     * @return this exception instance for method chaining
     */
    public ValidationException addError(String field, String message) {
        errors.put(field, message);
        return this;
    }

    /**
     * Get all validation errors.
     *
     * @return a map of field names to error messages
     */
    public Map<String, String> getErrors() {
        return errors;
    }

    /**
     * Check if there are any validation errors.
     *
     * @return true if there are validation errors
     */
    public boolean hasErrors() {
        return !errors.isEmpty();
    }
}