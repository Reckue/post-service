package com.reckue.post.exception.model.user;

import com.reckue.libs.exception.ReckueException;

/**
 * Class UserNotFoundException is responsible for throwing
 * exception when the received User is not found in the database.
 *
 * @author Artur Magomedov
 */
@SuppressWarnings("unused")
public class UserNotFoundException extends ReckueException {

    private final String message;

    public UserNotFoundException() {
        message = "User Not Found";
    }

    public UserNotFoundException(String id) {
        message = "User by id '" + id + "' is not found";
    }
}
