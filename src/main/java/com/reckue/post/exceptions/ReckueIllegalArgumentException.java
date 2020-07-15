package com.reckue.post.exceptions;

import lombok.Getter;

/**
 * Class ReckueIllegalArgumentException thrown to indicate that a method has been passed an illegal or
 * inappropriate argument.
 *
 * @author Artur Magomedov
 */
@Getter
public class ReckueIllegalArgumentException extends ReckueException {

    private final String message;

    public ReckueIllegalArgumentException(String message) {
        this.message = message;
    }
}
