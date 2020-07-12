package com.reckue.post.exceptions.models.post;

import com.reckue.post.exceptions.ModelAlreadyExistsException;

public class PostAlreadyExistException extends ModelAlreadyExistsException {

    private final String message;

    public PostAlreadyExistException() {
        message = "Post Already Exist";
    }

    public PostAlreadyExistException(String id) {
        message = "Post by id " + id + " already exist";
    }
}
