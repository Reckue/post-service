package com.reckue.post.exception.model.node.pollnode;

import com.reckue.post.exception.ModelAlreadyExistsException;
import lombok.Getter;

/**
 * Class PollNodeAlreadyExistsException is responsible for throwing
 * exception when the created PollNode already exists in database.
 *
 * @author Artur Magomedov
 */
@Getter
@SuppressWarnings("unused")
public class PollNodeAlreadyExistsException extends ModelAlreadyExistsException {

    private final String message;

    public PollNodeAlreadyExistsException() {
        this.message = "PollNode Already Exists";
    }

    public PollNodeAlreadyExistsException(String id) {
        this.message = "PollNode by id '" + id + "' already exists";
    }
}
