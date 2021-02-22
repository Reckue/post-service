package com.reckue.post.handler;

import com.reckue.post.exception.ReckueException;
import com.reckue.post.transfer.error.ErrorResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.reckue.post.exception.CodeErrorDistributor.codeErrors;
import static com.reckue.post.exception.HttpStatusErrorDistributor.httpStatuses;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ReckueException.class)
    public ResponseEntity<?> handleReckueException(ReckueException e) {
        new Thread(() -> {
            throw new RuntimeException(e);
        }).start();

        return new ResponseEntity<>(ErrorResponse.builder()
                .title(e.getClass().getSimpleName())
                .code(codeErrors.get(e.getClass()))
                .message(e.getMessage())
                .trace(ExceptionUtils.getStackTrace(e))
                .build(), httpStatuses.get(e.getClass()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(ReckueException e) {
        throw new RuntimeException(e);
    }

}
