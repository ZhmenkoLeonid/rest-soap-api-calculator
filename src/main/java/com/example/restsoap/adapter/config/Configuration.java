package com.example.restsoap.adapter.config;

import com.example.restsoap.adapter.soap.SOAPCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Calculator REST-SOAP API",
                "Can execute basic calculator functions via SOAP API",
                "1.0",
                "Terms of service",
                new Contact("Leonid Zhmenko","","zhmenkowork@mail.ru"),
                "",
                "",
                Collections.emptyList());
    }

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