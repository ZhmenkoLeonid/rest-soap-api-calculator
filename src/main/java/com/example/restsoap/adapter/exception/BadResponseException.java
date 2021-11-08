package com.example.restsoap.adapter.exception;

public class BadResponseException extends RuntimeException {
    public BadResponseException() {
        super("BAD RESPONSE");
    }

    public BadResponseException(String msg) {
        super("BAD RESPONSE: " + msg);
    }
}