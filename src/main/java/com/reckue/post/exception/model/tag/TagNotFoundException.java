package com.reckue.post.exception.model.tag;

import com.reckue.libs.exception.ReckueException;
import lombok.Getter;

/**
 * Class TagNotFoundException is responsible for throwing
 * exception when the received Tag is not found in the database.
 *
 * @author Artur Magomedov
 */
@Getter
@SuppressWarnings("unused")
public class TagNotFoundException extends ReckueException {

    private final String message;

    public TagNotFoundException() {
        message = "Tag Not Found";
    }

    public TagNotFoundException(String id) {
        message = "Tag by id '" + id + "' is not found";
    }
}
