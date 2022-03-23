package com.example.zaman_mail_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@EntityScan("com.example.zaman_resource_service.entity")
public class ZamanMailServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZamanMailServiceApplication.class, args);
    }
}
