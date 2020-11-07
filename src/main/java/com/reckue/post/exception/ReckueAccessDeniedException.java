package com.reckue.post.exception;

import lombok.Getter;

@Getter
public class ReckueAccessDeniedException extends ReckueException {

    private final String message;

    public ReckueAccessDeniedException(String message) {
        this.message = message;
    }
}
