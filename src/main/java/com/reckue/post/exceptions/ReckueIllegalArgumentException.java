package com.reckue.post.exceptions;

public class ReckueIllegalArgumentException extends ReckueException {

    private final String message;

    public ReckueIllegalArgumentException(String message) {
        this.message = message;
    }
}
