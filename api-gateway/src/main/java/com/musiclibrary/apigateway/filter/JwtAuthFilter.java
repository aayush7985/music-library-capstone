package com.musiclibrary.apigateway.filter;

import com.musiclibrary.apigateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class JwtAuthFilter implements GlobalFilter, Ordered {

    @Autowired
    private JwtUtil jwtUtil;

    private static final List<String> PUBLIC_PATHS = List.of(
            "/api/users/register",
            "/api/users/login",
            "/api/admin/login",
            "/swagger-ui",
            "/v3/api-docs",
            "/actuator"
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        if (isPublicPath(path)) {
            return chain.filter(exchange);
        }

        String authHeader = request.getHeaders().getFirst("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return unauthorizedResponse(exchange);
        }

        String token = authHeader.substring(7);

        if (!jwtUtil.validateToken(token)) {
            return unauthorizedResponse(exchange);
        }

        String email = jwtUtil.extractEmail(token);
        String role = jwtUtil.extractRole(token);
        String userId = jwtUtil.extractUserId(token);

        ServerHttpRequest mutatedRequest = request.mutate()
                .header("X-Auth-User-Email", email)
                .header("X-Auth-User-Role", role)
                .header("X-Auth-User-Id", userId != null ? userId : "")
                .build();

        return chain.filter(exchange.mutate().request(mutatedRequest).build());
    }

    private boolean isPublicPath(String path) {
        return PUBLIC_PATHS.stream().anyMatch(path::startsWith);
    }

    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
