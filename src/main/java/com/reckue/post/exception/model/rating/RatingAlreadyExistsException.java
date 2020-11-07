package com.reckue.post.exception.model.rating;

import com.reckue.post.exception.ModelAlreadyExistsException;
import lombok.Getter;

/**
 * Class RatingAlreadyExistsException is responsible for throwing
 * exception when the created Rating already exists in database.
 *
 * @author Artur Magomedov
 */
@Getter
@SuppressWarnings("unused")
public class RatingAlreadyExistsException extends ModelAlreadyExistsException {

    private final String message;

    public RatingAlreadyExistsException() {
        this.message = "Rating Already Exists";
    }

    public RatingAlreadyExistsException(String id) {
        this.message = "Rating by id '" + id + "' already exists";
    }
}
