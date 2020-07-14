package com.reckue.post.exceptions.models.post;

import com.reckue.post.exceptions.ModelAlreadyExistsException;

/**
 * Class PostAlreadyExistException is responsible for throwing
 * exception when the created Post already exists in database.
 *
 * @author Artur Magomedov
 */
public class PostAlreadyExistException extends ModelAlreadyExistsException {

    private final String message;

    public PostAlreadyExistException() {
        message = "Post Already Exist";
    }

    public PostAlreadyExistException(String id) {
        message = "Post by id " + id + " already exist";
    }
}
