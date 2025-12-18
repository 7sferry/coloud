package org.example.gateway.config;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullUnmarked;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

/************************
 * Made by [MR Ferry™]  *
 * on Desember 2025     *
 ************************/

@Component
@Primary
public class IpKeyResolver implements KeyResolver{

	@Override
	@NullUnmarked
	public Mono<@NonNull String> resolve(@NonNull ServerWebExchange exchange) {
		return Mono.just(
				Objects.requireNonNull(exchange.getRequest()
								.getRemoteAddress())
						.getAddress()
						.getHostAddress()
		);
	}
}
