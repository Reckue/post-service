package com.reckue.post.exceptions;

import lombok.Getter;

/**
 * Model Not Found Exception.
 * This exception is thrown when the model to be obtained is not found in the database.
 */
@Getter
public class ModelNotFoundException extends RuntimeException {

    public String message;

    /**
     * Default constructor with default message value.
     */
    @SuppressWarnings("unused")
    public ModelNotFoundException() {
        this.message = "Model Not Found";
    }

    /**
     * Constructor with message parameter for save information about exception.
     * @param message information about exception.
     */
    @SuppressWarnings("unused")
    public ModelNotFoundException(String message) {
        this.message = message;
    }
}