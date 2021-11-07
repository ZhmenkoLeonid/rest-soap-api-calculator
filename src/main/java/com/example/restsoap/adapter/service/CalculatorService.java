package com.example.restsoap.adapter.service;

import com.example.restsoap.adapter.exception.BadRequestException;

public interface CalculatorService {
    int add(int firstNumber, int secondNumber);

    int subtract(int firstNumber, int secondNumber);

    int divide(int firstNumber, int secondNumber);

    int multiply(int firstNumber, int secondNumber);
}
