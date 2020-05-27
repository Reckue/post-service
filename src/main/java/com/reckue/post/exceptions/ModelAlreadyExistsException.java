package com.reckue.post.exceptions;

import lombok.Getter;

/**
 * Model Already Exists Exception.
 * This exception is thrown when the model
 * to be created already exists in the database.
 */
@Getter
public class ModelAlreadyExistsException extends RuntimeException {

    private final String message;

    /**
     * Default constructor with default message value.
     */
    @SuppressWarnings("unused")
    public ModelAlreadyExistsException() {
        this.message = "Model Already Exists";
    }

    /**
     * Constructor with message parameter for save information about exception.
     *
     * @param message information about exception.
     */
    @SuppressWarnings("unused")
    public ModelAlreadyExistsException(String message) {
        this.message = message;
    }
}
