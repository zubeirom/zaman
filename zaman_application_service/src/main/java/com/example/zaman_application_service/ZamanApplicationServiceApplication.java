package com.example.zaman_application_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@EntityScan("com.example.zaman_resource_service.entity")
@ConfigurationPropertiesScan("com.example.zaman_resource_service")
public class ZamanApplicationServiceApplication {
    public static void main(String[] args) {SpringApplication.run(ZamanApplicationServiceApplication.class, args);}
}
