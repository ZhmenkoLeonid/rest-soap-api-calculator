package com.example.restsoap.adapter.model;

import org.springframework.stereotype.Component;

@Component
public class Operation {
    String firstNumber;
    String secondNumber;

    public String getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(String firstNumber) {
        this.firstNumber = firstNumber;
    }

    public String getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(String secondNumber) {
        this.secondNumber = secondNumber;
    }
}