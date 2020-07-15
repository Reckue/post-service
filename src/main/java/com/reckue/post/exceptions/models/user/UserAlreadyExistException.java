package com.reckue.post.exceptions.models.user;

import com.reckue.post.exceptions.ModelAlreadyExistsException;

/**
 * Class UserAlreadyExistException is responsible for throwing
 * exception when the created User already exists in database.
 *
 * @author Artur Magomedov
 */
public class UserAlreadyExistException extends ModelAlreadyExistsException {

    private final String message;

    public UserAlreadyExistException() {
        message = "User Already Exist";
    }

    public UserAlreadyExistException(String id) {
        message = "User by id '" + id + "' already exist";
    }
}
