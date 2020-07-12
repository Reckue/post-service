package com.reckue.post.exceptions.models.nodes;

import com.reckue.post.exceptions.ModelAlreadyExistsException;

public class NodeAlreadyExistException extends ModelAlreadyExistsException {

    private final String message;

    public NodeAlreadyExistException() {
        message = "Node Already Exist";
    }

    public NodeAlreadyExistException(String id) {
        message = "Node by id " + id + " already exist";
    }
}
