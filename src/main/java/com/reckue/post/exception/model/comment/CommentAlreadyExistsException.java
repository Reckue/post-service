package com.reckue.post.exception.model.comment;

import com.reckue.libs.exception.ReckueException;
import lombok.Getter;

/**
 * Class CommentAlreadyExistsException is responsible for throwing
 * exception when the created Comment already exists in database.
 *
 * @author Artur Magomedov
 */
@Getter
@SuppressWarnings("unused")
public class CommentAlreadyExistsException extends ReckueException {

    private final String message;

    public CommentAlreadyExistsException() {
        message = "Comment Already Exists";
    }

    public CommentAlreadyExistsException(String id) {
        message = "Comment by id '" + id + "' already exists";
    }
}
