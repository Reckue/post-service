package com.reckue.post.exceptions;

import lombok.Getter;

/**
 * Class ModelNotFoundException is responsible for throwing
 * exception when the received model is not found in the database.
 *
 * @author Daria Smirnova
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
     *
     * @param message information about exception
     */
    @SuppressWarnings("unused")
    public ModelNotFoundException(String message) {
        this.message = message;
    }
}
