package org.example.gateway.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullUnmarked;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/************************
 * Made by [MR Ferry™]  *
 * on Desember 2025     *
 ************************/

@Component
public class JwtAuthFilter implements GatewayFilter{

	private static final String SECRET = "very-secret-key-1234567890very-secret-key-1234567890";

	@NullUnmarked
	@Override
	public Mono<@NonNull Void> filter(@NonNull ServerWebExchange exchange, @NonNull GatewayFilterChain chain) {

		String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}

		String token = authHeader.substring(7);

		try {
			Claims claims = Jwts.parserBuilder()
					.setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes()))
					.build()
					.parseClaimsJws(token)
					.getBody();

			String username = claims.getSubject();
			System.out.println("username = " + username);

			// forward username to downstream service
			ServerHttpRequest mutated = exchange.getRequest()
					.mutate()
					.header("X-User", username)
					.build();

			return chain.filter(exchange.mutate().request(mutated).build());

		} catch (Exception e) {
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}
	}
}
