package com.reckue.post.exception.model.comment;

import com.reckue.libs.exception.ReckueException;
import lombok.Getter;

/**
 * Class CommentNotFoundException is responsible for throwing
 * exception when the received Comment is not found in the database.
 *
 * @author Artur Magomedov
 */
@Getter
@SuppressWarnings("unused")
public class CommentNotFoundException extends ReckueException {

    private final String message;

    public CommentNotFoundException() {
        this.message = "Comment Not Found";
    }

    public CommentNotFoundException(String id) {
        this.message = "Comment by id '" + id + "' is not found";
    }
}
