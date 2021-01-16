package com.reckue.post.exception.model.node.pollnode;

import com.reckue.libs.exception.ReckueException;
import lombok.Getter;

/**
 * Class PollNodeNotFoundException is responsible for throwing
 * exception when the received PollNode is not found in the database.
 *
 * @author Artur Magomedov
 */
@Getter
@SuppressWarnings("unused")
public class PollNodeNotFoundException extends ReckueException {

    private final String message;

    public PollNodeNotFoundException() {
        this.message = "PollNode Not Found";
    }

    public PollNodeNotFoundException(String id) {
        this.message = "PollNode by id '" + id + "' is not found";
    }
}
