package com.example.zaman_gateway_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    AuthenticationFilter authenticationFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route("applicationModule", r -> r.path("/api/users/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config("Foo"))))
                        .uri("http://zaman-application-service:8081/"))
                .route("applicationModule", r -> r.path("/api/appointments/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config("Foo"))))
                        .uri("http://zaman-application-service:8081/"))
                .route("applicationModule", r -> r.path("/api/register/**")
                        .uri("http://zaman-application-service:8081/"))
                .route("applicationModule", r -> r.path("/api/login/**")
                        .uri("http://zaman-application-service:8081/"))
                .build();
    }
}
