package com.reckue.post.exceptions.models.comment;

import com.reckue.post.exceptions.ModelAlreadyExistsException;
import lombok.Getter;

/**
 * Class CommentAlreadyExistException is responsible for throwing
 * exception when the created Comment already exists in database.
 *
 * @author Artur Magomedov
 */
@Getter
public class CommentAlreadyExistException extends ModelAlreadyExistsException {

    private final String message;

    public CommentAlreadyExistException() {
        message = "Comment Already Exist";
    }

    public CommentAlreadyExistException(String id) {
        message = "Comment by id '" + id + "' already exist";
    }
}
