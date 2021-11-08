package com.example.restsoap.adapter.exception;

import org.springframework.validation.ObjectError;

import java.util.List;

public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        super("BAD REQUEST");
    }
    public BadRequestException(String msg) {
        super("BAD REQUEST: " + msg);
    }
}