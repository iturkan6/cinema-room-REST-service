package com.cinemaroom.cinema.entity;

import java.util.Objects;

public class Token {
    private String token;

    public Token() {
    }

    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return String.valueOf(token);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token1 = (Token) o;
        return Objects.equals(token, token1.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }
}
