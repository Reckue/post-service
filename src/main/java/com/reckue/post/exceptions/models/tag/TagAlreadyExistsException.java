package com.reckue.post.exceptions.models.tag;

import com.reckue.post.exceptions.ModelAlreadyExistsException;
import lombok.Getter;

/**
 * Class TagAlreadyExistsException is responsible for throwing
 * exception when the created Tag already exists in database.
 *
 * @author Artur Magomedov
 */
@Getter
public class TagAlreadyExistsException extends ModelAlreadyExistsException {

    private final String message;

    public TagAlreadyExistsException() {
        message = "Tag Already Exists";
    }

    public TagAlreadyExistsException(String id) {
        message = "Tag by id '" + id + "' already exists";
    }
}
