package com.reckue.post.exceptions.models.comment;

import com.reckue.post.exceptions.ModelAlreadyExistsException;
import lombok.Getter;

/**
 * Class CommentAlreadyExistsException is responsible for throwing
 * exception when the created Comment already exists in database.
 *
 * @author Artur Magomedov
 */
@Getter
public class CommentAlreadyExistsException extends ModelAlreadyExistsException {

    private final String message;

    public CommentAlreadyExistsException() {
        message = "Comment Already Exists";
    }

    public CommentAlreadyExistsException(String id) {
        message = "Comment by id '" + id + "' already exists";
    }
}
