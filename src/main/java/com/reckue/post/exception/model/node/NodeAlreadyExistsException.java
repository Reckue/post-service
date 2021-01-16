package com.reckue.post.exception.model.node;

import com.reckue.libs.exception.ReckueException;
import lombok.Getter;

/**
 * Class NodeAlreadyExistsException is responsible for throwing
 * exception when the created Node already exists in database.
 *
 * @author Artur Magomedov
 */
@Getter
@SuppressWarnings("unused")
public class NodeAlreadyExistsException extends ReckueException {

    private final String message;

    public NodeAlreadyExistsException() {
        message = "Node Already Exists";
    }

    public NodeAlreadyExistsException(String id) {
        message = "Node by id " + id + " already exists";
    }
}
