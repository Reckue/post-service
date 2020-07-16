package com.reckue.post.exceptions.models.rating;

import com.reckue.post.exceptions.ModelAlreadyExistsException;
import lombok.Getter;

/**
 * Class RatingAlreadyExistsException is responsible for throwing
 * exception when the created Rating already exists in database.
 *
 * @author Artur Magomedov
 */
@Getter
public class RatingAlreadyExistsException extends ModelAlreadyExistsException {

    private final String message;

    public RatingAlreadyExistsException() {
        this.message = "Rating Already Exists";
    }

    public RatingAlreadyExistsException(String id) {
        this.message = "Rating by id '" + id + "' already exists";
    }
}
