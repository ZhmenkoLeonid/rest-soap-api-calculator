package com.example.restsoap.adapter.controller;

import com.example.restsoap.adapter.exception.BadRequestException;
import com.example.restsoap.adapter.exception.BadResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CalculatorExceptionHandler {
    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String badRequestHandler(BadRequestException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(BadResponseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String badResponseHandler(BadResponseException ex) {
        return ex.getMessage();
    }
}