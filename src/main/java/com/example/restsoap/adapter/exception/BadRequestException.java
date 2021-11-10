package com.example.restsoap.adapter.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        super("BAD REQUEST");
    }

    public BadRequestException(String msg) {
        super("BAD REQUEST:\n" + msg);
    }
}