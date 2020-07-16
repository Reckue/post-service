package com.reckue.post.exceptions.models.nodes.pollnode;

import com.reckue.post.exceptions.ModelAlreadyExistsException;
import lombok.Getter;

/**
 * Class PollNodeAlreadyExistsException is responsible for throwing
 * exception when the created PollNode already exists in database.
 *
 * @author Artur Magomedov
 */
@Getter
public class PollNodeAlreadyExistsException extends ModelAlreadyExistsException {

    private final String message;

    public PollNodeAlreadyExistsException() {
        this.message = "PollNode Already Exist";
    }

    public PollNodeAlreadyExistsException(String id) {
        this.message = "PollNode by id '" + id + "' already exist";
    }
}
