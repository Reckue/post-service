package com.reckue.post.exceptions.models.post;

import com.reckue.post.exceptions.ModelAlreadyExistsException;
import lombok.Getter;

/**
 * Class PostAlreadyExistException is responsible for throwing
 * exception when the created Post already exists in database.
 *
 * @author Artur Magomedov
 */
@Getter
public class PostAlreadyExistException extends ModelAlreadyExistsException {

    private final String message;

    public PostAlreadyExistException() {
        message = "Post Already Exist";
    }

    public PostAlreadyExistException(String id) {
        message = "Post by id '" + id + "' already exist";
    }
}
