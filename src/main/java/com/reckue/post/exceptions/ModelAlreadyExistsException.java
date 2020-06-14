package com.reckue.post.exceptions;

import lombok.Getter;

/**
 * Class ModelAlreadyExistsException is responsible for throwing
 * exception when the created model already exists in database.
 *
 * @author Daria Smirnova
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
     * @param message information about exception
     */
    @SuppressWarnings("unused")
    public ModelAlreadyExistsException(String message) {
        this.message = message;
    }
}
