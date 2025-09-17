package com.urbanride.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                // Route for authentication service (handles login/signup)
                .route("auth-service", r -> r.path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://auth-service"))

                // Route for review service
                .route("review-service", r -> r.path("/review/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://review-service"))

                // Route for location service
                .route("location-service", r -> r.path("/location/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://location-service"))

                // Route for booking service
                .route("booking-service", r -> r.path("/booking/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://booking-service"))

                .build();
    }
}