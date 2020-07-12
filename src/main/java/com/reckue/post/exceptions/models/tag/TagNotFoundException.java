package com.reckue.post.exceptions.models.tag;

import com.reckue.post.exceptions.ModelNotFoundException;

public class TagNotFoundException extends ModelNotFoundException {

    private final String message;

    public TagNotFoundException() {
        this.message = "Tag Not Found";
    }

    public TagNotFoundException(String id) {
        message = "Tag by id " + id + " is not found";
    }
}
