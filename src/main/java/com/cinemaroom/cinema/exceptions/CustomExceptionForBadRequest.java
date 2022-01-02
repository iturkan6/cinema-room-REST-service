package com.cinemaroom.cinema.exceptions;

public class CustomExceptionForBadRequest extends RuntimeException {
    public CustomExceptionForBadRequest(String message) {
        super(message);
    }

    public CustomExceptionForBadRequest(String message, Throwable cause) {
        super(message, cause);
    }
}
