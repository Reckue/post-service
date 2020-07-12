package com.reckue.post.exceptions.models.comment;

import com.reckue.post.exceptions.ModelAlreadyExistsException;

public class CommentAlreadyExistException extends ModelAlreadyExistsException {

    private final String message;

    public CommentAlreadyExistException() {
        message = "Comment Already Exist";
    }

    public CommentAlreadyExistException(String id) {
        message = "Comment by id " + id + " already exist";
    }
}
