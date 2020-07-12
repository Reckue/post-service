package com.reckue.post.handlers;

import com.reckue.post.exceptions.ReckueException;
import com.reckue.post.transfers.errors.ErrorResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.reckue.post.exceptions.ExceptionDistributor.codeErrors;
import static com.reckue.post.exceptions.StatusDistributor.httpStatuses;

/**
 * Class CustomExceptionHandler allows to handle all exceptions.
 *
 * @author Artur Magomedov
 */
@RestControllerAdvice
@SuppressWarnings("unused")
public class CustomExceptionHandler {

    @ExceptionHandler(ReckueException.class)
    public ResponseEntity<?> handleReckueException(ReckueException e) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .code(codeErrors.get(e.getClass()))
                .message(e.getMessage())
                .cause(ExceptionUtils.getStackTrace(e))
                .build(), httpStatuses.get(e.getClass()));
    }

    // TODO... Handler for IllegalArgumentException class
}
