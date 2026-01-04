package com.brajesh.catalog.domain.product.headers;

import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
public class AuthHeaderFilter implements WebFilter {
    @Override
    public @NonNull Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        Mono.
        return Mono.just(Objects.requireNonNull(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))).
        map(chain::filter(e->e));
    }

}
