package com.reckue.post.handlers;

import com.reckue.post.exceptions.NoteNotFoundException;
import com.reckue.post.models.transfers.ErrorTransfer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(NoteNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NoteNotFoundException e) {
        return new ResponseEntity<>(new ErrorTransfer(e.getMessage(), e.getHttpStatus().value()), e.getHttpStatus());
    }
}
