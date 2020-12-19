package com.reckue.post.exception.model.tag;

import com.reckue.libs.exception.ReckueException;
import lombok.Getter;

/**
 * Class TagAlreadyExistsException is responsible for throwing
 * exception when the created Tag already exists in database.
 *
 * @author Artur Magomedov
 */
@Getter
@SuppressWarnings("unused")
public class TagAlreadyExistsException extends ReckueException {

    private final String message;

    public TagAlreadyExistsException() {
        message = "Tag Already Exists";
    }

    public TagAlreadyExistsException(String id) {
        message = "Tag by id '" + id + "' already exists";
    }
}
