package com.reckue.post.exception.model.user;

import com.reckue.libs.exception.ReckueException;

/**
 * Class UserAlreadyExistsException is responsible for throwing
 * exception when the created User already exists in database.
 *
 * @author Artur Magomedov
 */
@SuppressWarnings("unused")
public class UserAlreadyExistsException extends ReckueException {

    private final String message;

    public UserAlreadyExistsException() {
        message = "User Already Exists";
    }

    public UserAlreadyExistsException(String id) {
        message = "User by id '" + id + "' already exists";
    }
}
