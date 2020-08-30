package com.reckue.post.exceptions.models.nodes;

import com.reckue.post.exceptions.ModelNotFoundException;
import lombok.Getter;

/**
 * Class NodeNotFoundException is responsible for throwing
 * exception when the received Node is not found in the database.
 *
 * @author Artur Magomedov
 */
@Getter
@SuppressWarnings("unused")
public class NodeNotFoundException extends ModelNotFoundException {

    private final String message;

    public NodeNotFoundException() {
        message = "Node Not Found";
    }

    public NodeNotFoundException(String id) {
        message = "Node by id " + id + " is not found";
    }
}
