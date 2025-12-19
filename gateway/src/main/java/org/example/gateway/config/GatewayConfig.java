package org.example.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/************************
 * Made by [MR Ferry™]  *
 * on Desember 2025     *
 ************************/

@Configuration
public class GatewayConfig {

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder, JwtAuthFilter jwtAuthFilter, UserKeyResolver keyResolver, IpKeyResolver ipKeyResolver,
	                           AuthRateLimiter authRateLimiter, UserRateLimiter userRateLimiter) {
		return builder.routes()
				.route("auth", r -> r
						.path("/auth/**")
						.filters(f -> f
								.requestRateLimiter(c -> c
										.setRateLimiter(authRateLimiter)
										.setKeyResolver(ipKeyResolver)))
						.uri("http://localhost:4081"))
				.route("users", r -> r
						.path("/users/**")
						.filters(f -> f
								.filter(jwtAuthFilter)
								.requestRateLimiter(c -> c
										.setRateLimiter(userRateLimiter)
										.setKeyResolver(keyResolver))
								.circuitBreaker(cb -> cb
										.setName("usersCircuitBreaker")
										.setFallbackUri("forward:/fallback/user")))
						.uri("http://localhost:4082"))
				.build();
	}

	@Bean
	@Primary
	public AuthRateLimiter authRateLimiter() {
		return new AuthRateLimiter();
	}

	@Bean
	public UserRateLimiter userRateLimiter() {
		return new UserRateLimiter();
	}

}
