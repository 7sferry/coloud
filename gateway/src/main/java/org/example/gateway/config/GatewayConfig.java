package org.example.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/************************
 * Made by [MR Ferry™]  *
 * on Desember 2025     *
 ************************/

@Configuration
public class GatewayConfig {

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder, JwtAuthFilter jwtAuthFilter) {
		return builder.routes()

				.route("auth", r -> r
						.path("/auth/**")
						.uri("http://localhost:4081"))

				.route("users", r -> r
						.path("/users/**")
						.filters(f -> f.filter(jwtAuthFilter))
						.uri("http://localhost:4082"))

				.build();
	}
}
