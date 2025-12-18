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
	public RouteLocator routes(RouteLocatorBuilder builder, JwtAuthFilter jwtAuthFilter, UserKeyResolver userKeyResolver, IpKeyResolver ipKeyResolver,
	                           AuthRateLimiter authRateLimiter, UserRateLimiter userRateLimiter) {
		return builder.routes()
				// Auth route
				.route("auth", r -> r
						.path("/auth/**")
						.filters(f -> f.requestRateLimiter(c -> {
							c.setRateLimiter(authRateLimiter);
							c.setKeyResolver(ipKeyResolver);
						}))
						.uri("http://auth:4081")
				)

				// User route with JWT filter + rate limiter
				.route("user", r -> r
						.path("/users/**")
						.filters(f -> f
								.filter(jwtAuthFilter)
								.requestRateLimiter(c -> {
									c.setRateLimiter(userRateLimiter);
									c.setKeyResolver(userKeyResolver);
								})
						)
						.uri("http://user:4082")
				)
				.build();
	}

	@Bean
	public AuthRateLimiter authRateLimiter() {
		return new AuthRateLimiter();
	}

	@Bean
	@Primary
	public UserRateLimiter userRateLimiter() {
		return new UserRateLimiter();
	}

}
