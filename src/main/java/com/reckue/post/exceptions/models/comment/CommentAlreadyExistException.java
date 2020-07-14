package com.reckue.post.exceptions.models.comment;

import com.reckue.post.exceptions.ModelAlreadyExistsException;

/**
 * Class CommentAlreadyExistException is responsible for throwing
 * exception when the created Comment already exists in database.
 *
 * @author Artur Magomedov
 */
public class CommentAlreadyExistException extends ModelAlreadyExistsException {

    private final String message;

    public CommentAlreadyExistException() {
        message = "Comment Already Exist";
    }

    public CommentAlreadyExistException(String id) {
        message = "Comment by id " + id + " already exist";
    }
}
