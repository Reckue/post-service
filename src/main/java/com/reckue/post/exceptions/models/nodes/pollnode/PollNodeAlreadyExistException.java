package com.reckue.post.exceptions.models.nodes.pollnode;

import com.reckue.post.exceptions.ModelAlreadyExistsException;

/**
 * Class PollNodeAlreadyExistException is responsible for throwing
 * exception when the created PollNode already exists in database.
 *
 * @author Artur Magomedov
 */
public class PollNodeAlreadyExistException extends ModelAlreadyExistsException {

    private final String message;

    public PollNodeAlreadyExistException() {
        this.message = "PollNode Already Exist";
    }

    public PollNodeAlreadyExistException(String id) {
        this.message = "PollNode by id " + id + " already exist";
    }
}
