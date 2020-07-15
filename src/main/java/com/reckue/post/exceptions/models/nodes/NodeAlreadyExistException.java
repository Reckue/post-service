package com.reckue.post.exceptions.models.nodes;

import com.reckue.post.exceptions.ModelAlreadyExistsException;
import lombok.Getter;

/**
 * Class NodeAlreadyExistException is responsible for throwing
 * exception when the created Node already exists in database.
 *
 * @author Artur Magomedov
 */
@Getter
public class NodeAlreadyExistException extends ModelAlreadyExistsException {

    private final String message;

    public NodeAlreadyExistException() {
        message = "Node Already Exist";
    }

    public NodeAlreadyExistException(String id) {
        message = "Node by id " + id + " already exist";
    }
}
