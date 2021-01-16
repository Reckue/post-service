package com.reckue.post.exception.model.node;

import com.reckue.libs.exception.ReckueException;
import lombok.Getter;

/**
 * Class NodeNotFoundException is responsible for throwing
 * exception when the received Node is not found in the database.
 *
 * @author Artur Magomedov
 */
@Getter
@SuppressWarnings("unused")
public class NodeNotFoundException extends ReckueException {

    private final String message;

    public NodeNotFoundException() {
        message = "Node Not Found";
    }

    public NodeNotFoundException(String id) {
        message = "Node by id " + id + " is not found";
    }
}
