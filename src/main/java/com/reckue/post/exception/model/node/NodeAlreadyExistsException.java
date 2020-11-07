package com.reckue.post.exception.model.node;

import com.reckue.post.exception.ModelAlreadyExistsException;
import lombok.Getter;

/**
 * Class NodeAlreadyExistsException is responsible for throwing
 * exception when the created Node already exists in database.
 *
 * @author Artur Magomedov
 */
@Getter
@SuppressWarnings("unused")
public class NodeAlreadyExistsException extends ModelAlreadyExistsException {

    private final String message;

    public NodeAlreadyExistsException() {
        message = "Node Already Exists";
    }

    public NodeAlreadyExistsException(String id) {
        message = "Node by id " + id + " already exists";
    }
}
