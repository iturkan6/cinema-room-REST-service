package com.cinemaroom.cinema.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ExceptionPattern {
    private final LocalDateTime TimeZone;
    private final HttpStatus status;
    private final String error;

    public ExceptionPattern(LocalDateTime localDateTime, HttpStatus status, String error) {
        this.TimeZone = localDateTime;
        this.status = status;
        this.error = error;
    }

    public LocalDateTime getTimeZone() {
        return TimeZone;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }
}
