package com.reckue.post.exceptions.models.tag;

import com.reckue.post.exceptions.ModelNotFoundException;
import lombok.Getter;

/**
 * Class TagNotFoundException is responsible for throwing
 * exception when the received Tag is not found in the database.
 *
 * @author Artur Magomedov
 */
@Getter
public class TagNotFoundException extends ModelNotFoundException {

    private final String message;

    public TagNotFoundException() {
        message = "Tag Not Found";
    }

    public TagNotFoundException(String id) {
        message = "Tag by id " + id + " is not found";
    }
}
