package com.reckue.post.handlers;

import com.reckue.post.exceptions.ModelAlreadyExistsException;
import com.reckue.post.exceptions.ModelNotFoundException;
import com.reckue.post.transfers.errors.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Class CustomExceptionHandler allows to handle all exceptions.
 *
 * @author Kamila Meshcheryakova
 */
@RestControllerAdvice
@SuppressWarnings("unused")
public class CustomExceptionHandler {

    @ExceptionHandler(ModelAlreadyExistsException.class)
    public ResponseEntity<?> handleModelAlreadyExistsException(ModelAlreadyExistsException e) {
        return new ResponseEntity<>(new ErrorResponse(
                e.getMessage(), HttpStatus.CONFLICT, HttpStatus.CONFLICT.value()),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<?> handleModelNotFoundException(ModelNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse(
                e.getMessage(), HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(new ErrorResponse(
                e.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value()),
                HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> handleException(Exception e) {
//        return new ResponseEntity<>(new ErrorResponse(
//                e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value()),
//                HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
