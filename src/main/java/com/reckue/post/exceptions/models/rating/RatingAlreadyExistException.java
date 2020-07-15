package com.reckue.post.exceptions.models.rating;

import com.reckue.post.exceptions.ModelAlreadyExistsException;
import lombok.Getter;

/**
 * Class RatingAlreadyExistException is responsible for throwing
 * exception when the created Rating already exists in database.
 *
 * @author Artur Magomedov
 */
@Getter
public class RatingAlreadyExistException extends ModelAlreadyExistsException {

    private final String message;

    public RatingAlreadyExistException() {
        this.message = "Rating Already Exist";
    }

    public RatingAlreadyExistException(String id) {
        this.message = "Rating by id '" + id + "' already exist";
    }
}
