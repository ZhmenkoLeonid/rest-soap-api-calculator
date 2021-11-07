package com.example.restsoap.adapter;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@OpenAPIDefinition
@EnableCaching
public class RestSoapAdapterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestSoapAdapterApplication.class, args);
    }

}