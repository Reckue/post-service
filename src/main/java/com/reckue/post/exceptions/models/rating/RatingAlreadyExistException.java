package com.reckue.post.exceptions.models.rating;

import com.reckue.post.exceptions.ModelAlreadyExistsException;

public class RatingAlreadyExistException extends ModelAlreadyExistsException {

    private final String message;

    public RatingAlreadyExistException() {
        this.message = "Rating Already Exist";
    }

    public RatingAlreadyExistException(String id) {
        this.message = "Rating by id " + id + " already exist";
    }
}
