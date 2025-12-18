package org.example.gateway.config;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullUnmarked;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/************************
 * Made by [MR Ferry™]  *
 * on Desember 2025     *
 ************************/

@Component
public class UserKeyResolver implements KeyResolver{

	@Override
	@NullUnmarked
	public Mono<@NonNull String> resolve(@NonNull ServerWebExchange exchange) {
		return Mono.justOrEmpty(
				exchange.getRequest().getHeaders().getFirst("X-User")
		).switchIfEmpty(Mono.just("anonymous"));
	}
}
