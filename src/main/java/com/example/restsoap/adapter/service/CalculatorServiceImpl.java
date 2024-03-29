package com.example.restsoap.adapter.service;

import com.example.restsoap.adapter.exception.BadResponseException;
import com.example.restsoap.adapter.soap.SOAPCalculator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tempuri.*;

@Service
@Qualifier("soapCalculatorService")
public class CalculatorServiceImpl implements CalculatorService {
    private SOAPCalculator soapCalculator;

    public CalculatorServiceImpl(SOAPCalculator soapCalculator) {
        this.soapCalculator = soapCalculator;
    }

    @Override
    @Cacheable("add")
    public int add(int firstNumber, int secondNumber) {
        Add addRequest = new Add();
        addRequest.setIntA(firstNumber);
        addRequest.setIntB(secondNumber);

        AddResponse addResponse = soapCalculator.add(addRequest);
        if (addResponse == null)
            throw new BadResponseException("SOAP response in add operation is null");

        return addResponse.getAddResult();
    }

    @Override
    @Cacheable("subtract")
    public int subtract(int firstNumber, int secondNumber) {
        Subtract subtractRequest = new Subtract();
        subtractRequest.setIntA(firstNumber);
        subtractRequest.setIntB(secondNumber);

        SubtractResponse subtractResponse = soapCalculator.subtract(subtractRequest);
        if (subtractResponse == null)
            throw new BadResponseException("SOAP response in subtract operation is null");

        return subtractResponse.getSubtractResult();
    }

    @Override
    @Cacheable("divide")
    public int divide(int firstNumber, int secondNumber) {
        Divide divideRequest = new Divide();
        divideRequest.setIntA(firstNumber);
        divideRequest.setIntB(secondNumber);

        DivideResponse divideResponse = soapCalculator.divide(divideRequest);
        if (divideResponse == null)
            throw new BadResponseException("SOAP response in divide operation is null");

        return divideResponse.getDivideResult();
    }

    @Override
    @Cacheable("multiply")
    public int multiply(int firstNumber, int secondNumber) {
        Multiply multiplyRequest = new Multiply();
        multiplyRequest.setIntA(firstNumber);
        multiplyRequest.setIntB(secondNumber);

        MultiplyResponse multiplyResponse = soapCalculator.multiply(multiplyRequest);
        if (multiplyResponse == null)
            throw new BadResponseException("SOAP response in multiply operation is null");

        return multiplyResponse.getMultiplyResult();
    }
}