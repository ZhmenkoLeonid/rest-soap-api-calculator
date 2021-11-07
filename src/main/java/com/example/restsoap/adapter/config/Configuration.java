package com.example.restsoap.adapter.config;

import com.example.restsoap.adapter.soap.SOAPCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("org.tempuri");
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