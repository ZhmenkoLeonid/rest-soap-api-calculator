package com.example.restsoap.adapter.soap;

import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import tempuri.*;

public class SOAPCalculator extends WebServiceTemplate {

    private static final String URI = "http://www.dneonline.com/calculator.asmx";
    private static final String HTTP_TEMP_URI_ORG_ADD = "http://tempuri.org/Add";
    private static final String HTTP_TEMP_URI_ORG_DIVIDE = "http://tempuri.org/Divide";
    private static final String HTTP_TEMP_URI_ORG_MULTIPLY = "http://tempuri.org/Multiply";
    private static final String HTTP_TEMP_URI_ORG_SUBTRACT = "http://tempuri.org/Subtract";

    public AddResponse add(Add addRequest) {
        return (AddResponse) marshalSendAndReceive(
                        URI,
                        addRequest,
                        new SoapActionCallback(HTTP_TEMP_URI_ORG_ADD)
                );
    }

    public DivideResponse divide(Divide divideRequest) {
        return (DivideResponse) marshalSendAndReceive(
                        URI,
                        divideRequest,
                        new SoapActionCallback(HTTP_TEMP_URI_ORG_DIVIDE)
                );
    }

    public MultiplyResponse multiply(Multiply multiplyRequest) {
        return (MultiplyResponse) marshalSendAndReceive(
                URI,
                multiplyRequest,
                new SoapActionCallback(HTTP_TEMP_URI_ORG_MULTIPLY)
        );
    }

    public SubtractResponse subtract(Subtract subtractRequest) {
        return (SubtractResponse) marshalSendAndReceive(
                        URI,
                        subtractRequest,
                        new SoapActionCallback(HTTP_TEMP_URI_ORG_SUBTRACT)
                );
    }
}
