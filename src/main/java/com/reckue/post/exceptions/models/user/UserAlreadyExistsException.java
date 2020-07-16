package com.reckue.post.exceptions.models.user;

import com.reckue.post.exceptions.ModelAlreadyExistsException;

/**
 * Class UserAlreadyExistsException is responsible for throwing
 * exception when the created User already exists in database.
 *
 * @author Artur Magomedov
 */
public class UserAlreadyExistsException extends ModelAlreadyExistsException {

    private final String message;

    public UserAlreadyExistsException() {
        message = "User Already Exists";
    }

    public UserAlreadyExistsException(String id) {
        message = "User by id '" + id + "' already exists";
    }
}
