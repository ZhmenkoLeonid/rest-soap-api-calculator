package com.example.restsoap.adapter.config;

import com.example.restsoap.adapter.soap.SOAPCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@org.springframework.context.annotation.Configuration
public class SOAPConfiguration {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("tempuri");
        return marshaller;
    }

    @Bean
    public SOAPCalculator calculatorSOAPClient(Jaxb2Marshaller marshaller) {
        SOAPCalculator soapCalculator = new SOAPCalculator();
        soapCalculator.setMarshaller(marshaller);
        soapCalculator.setUnmarshaller(marshaller);
        return soapCalculator;
    }
}
