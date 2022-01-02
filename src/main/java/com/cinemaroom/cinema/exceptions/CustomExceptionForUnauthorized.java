package com.cinemaroom.cinema.exceptions;

public class CustomExceptionForUnauthorized extends RuntimeException{
    public CustomExceptionForUnauthorized(String message) {
        super(message);
    }
}
