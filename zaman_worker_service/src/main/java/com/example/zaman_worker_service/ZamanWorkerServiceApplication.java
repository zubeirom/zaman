package com.example.zaman_worker_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ConfigurationPropertiesScan("com.example.zaman_resource_service")
@EntityScan("com.example.zaman_resource_service.entity")
public class ZamanWorkerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZamanWorkerServiceApplication.class, args);
    }
}
