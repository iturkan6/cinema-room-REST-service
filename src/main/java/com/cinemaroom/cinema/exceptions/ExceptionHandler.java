package com.cinemaroom.cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {CustomExceptionForBadRequest.class})
    public ResponseEntity<Object> handler(CustomExceptionForBadRequest exception) {
        ExceptionPattern ex = new ExceptionPattern(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                exception.getMessage());
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {CustomExceptionForUnauthorized.class})
    public ResponseEntity<Object> handler2(CustomExceptionForUnauthorized exception) {
        ExceptionPattern ex = new ExceptionPattern(
                LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED,
                exception.getMessage());
        return new ResponseEntity<>(ex, HttpStatus.UNAUTHORIZED);
    }
}
