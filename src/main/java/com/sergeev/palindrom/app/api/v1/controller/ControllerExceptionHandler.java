package com.sergeev.palindrom.app.api.v1.controller;

import com.sergeev.palindrom.app.api.v1.controller.response.RestErrorMessageResponse;
import com.sergeev.palindrom.app.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {

    public static final String NOT_FOUND_ENTITY_MESSAGE = "Not found entity";

    public static final String ILLEGAL_ARGUMENT = "Check the entered data and try again";

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<RestErrorMessageResponse> handleNotFoundException(NotFoundException ex) {
        List<String> errorMessages = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(RestErrorMessageResponse.builder()
                .message(NOT_FOUND_ENTITY_MESSAGE)
                .errors(errorMessages).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<RestErrorMessageResponse> handleNotFoundException(IllegalArgumentException ex) {
        List<String> errorMessages = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(RestErrorMessageResponse.builder()
                .message(ILLEGAL_ARGUMENT)
                .errors(errorMessages).build(), HttpStatus.BAD_REQUEST);
    }
}
