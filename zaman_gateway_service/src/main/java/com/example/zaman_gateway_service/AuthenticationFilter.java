package com.example.zaman_gateway_service;

import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RefreshScope
@Component
public class AuthenticationFilter implements GatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private JWTUtil jwtUtil;

    final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0);
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
        Claims claims = jwtUtil.getAllClaimsFromToken(token);
        System.out.println(claims.get("userId"));
        exchange.getRequest().mutate()
                .header("userId", String.valueOf(claims.get("userId")))
                .build();
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            try {
                ServerHttpRequest request = exchange.getRequest();

                if(routeValidator.isSecured.test(request)) {
                    if(this.isAuthMissing(request))
                        return this.onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);

                    final String authHeader = this.getAuthHeader(request);
                    String token = JWTUtil.extractToken(authHeader);

                    this.logger.info("TOKEN RECEIVED: " + token);

                    if(jwtUtil.isInvalid(token))
                        return this.onError(exchange, "Authorization header is invalid", HttpStatus.UNAUTHORIZED);

                    this.populateRequestWithHeaders(exchange, token);
                }
                return chain.filter(exchange);
            } catch (Exception e) {
                return this.onError(exchange, "Unauthorized Request", HttpStatus.UNAUTHORIZED);
            }
        });
    }

    public static class Config {
        private  String baseMessage;

        public Config() {}

        public Config(String baseMessage) {
            this.baseMessage = baseMessage;
        }

        public String getBaseMessage() {
            return baseMessage;
        }

        public void setBaseMessage(String baseMessage) {
            this.baseMessage = baseMessage;
        }
    }

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

    @Override
    public Config newConfig() {
        Config c = new Config();
        return c;
    }

}
