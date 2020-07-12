package com.reckue.post.exceptions.models.nodes;

import com.reckue.post.exceptions.ModelNotFoundException;

public class NodeNotFoundException extends ModelNotFoundException {

    private final String message;

    public NodeNotFoundException() {
        message = "Node Not Found";
    }

    public NodeNotFoundException(String id) {
        message = "Node by id " + id + " is not found";
    }
}
