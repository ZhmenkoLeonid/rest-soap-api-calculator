package com.example.restsoap.adapter;

import com.example.restsoap.adapter.exception.BadResponseException;
import com.example.restsoap.adapter.service.CalculatorService;
import com.example.restsoap.adapter.soap.SOAPCalculator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import tempuri.*;

@Component
@Qualifier("testSOAPCalculatorService")
class TestCalculatorServiceImpl implements CalculatorService {

    private SOAPCalculator soapCalculator;

    public TestCalculatorServiceImpl(SOAPCalculator soapCalculator) {
        this.soapCalculator = soapCalculator;
    }

    @Override
    @Cacheable("add")
    public int add(int firstNumber, int secondNumber) {
        System.out.println("Calling add for " + firstNumber + " and " + secondNumber);
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
        System.out.println("Calling subtract for " + firstNumber + " and " + secondNumber);
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
        System.out.println("Calling divide for " + firstNumber + " and " + secondNumber);
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
        System.out.println("Calling multiply for " + firstNumber + " and " + secondNumber);
        Multiply multiplyRequest = new Multiply();
        multiplyRequest.setIntA(firstNumber);
        multiplyRequest.setIntB(secondNumber);

        MultiplyResponse multiplyResponse = soapCalculator.multiply(multiplyRequest);
        if (multiplyResponse == null)
            throw new BadResponseException("SOAP response in multiply operation is null");

        return multiplyResponse.getMultiplyResult();
    }
}
