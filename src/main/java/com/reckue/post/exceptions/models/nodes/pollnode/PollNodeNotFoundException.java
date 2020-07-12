package com.reckue.post.exceptions.models.nodes.pollnode;

import com.reckue.post.exceptions.ModelNotFoundException;

public class PollNodeNotFoundException extends ModelNotFoundException {

    private final String message;

    public PollNodeNotFoundException() {
        this.message = "PollNode Not Found";
    }

    public PollNodeNotFoundException(String id) {
        this.message = "PollNode by id " + id + " is not found";
    }
}
