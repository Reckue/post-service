package com.reckue.post.exception.model.post;

import com.reckue.post.exception.ModelAlreadyExistsException;
import lombok.Getter;

/**
 * Class PostAlreadyExistsException is responsible for throwing
 * exception when the created Post already exists in database.
 *
 * @author Artur Magomedov
 */
@Getter
@SuppressWarnings("unused")
public class PostAlreadyExistsException extends ModelAlreadyExistsException {

    private final String message;

    public PostAlreadyExistsException() {
        message = "Post Already Exists";
    }

    public PostAlreadyExistsException(String id) {
        message = "Post by id '" + id + "' already exists";
    }
}
