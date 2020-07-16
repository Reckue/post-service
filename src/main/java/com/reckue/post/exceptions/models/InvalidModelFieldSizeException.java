package com.reckue.post.exceptions.models;

import com.reckue.post.exceptions.ReckueException;
import lombok.Getter;

/**
 * Class TagNotFoundException is responsible for throwing
 * exception when the received Tag is not found in the database.
 *
 * @author Sherzod Mamadaliev
 */
@Getter
public class InvalidModelFieldSizeException extends ReckueException {

    private String message;

    public InvalidModelFieldSizeException() {
        message = "Invalid Model Field Size";
    }

    public InvalidModelFieldSizeException(String message) {
        this.message = message;
    }
}
