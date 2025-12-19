package org.example.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/************************
 * Made by [MR Ferry™]  *
 * on Desember 2025     *
 ************************/

@RestController
@RequestMapping("/fallback")
public class FallbackController {

	@GetMapping("/user")
	public Mono<ResponseEntity<String>> userFallback() {
		return Mono.just(
				ResponseEntity
						.status(HttpStatus.SERVICE_UNAVAILABLE)
						.body("User service is temporarily unavailable. Please try again later.")
		);
	}
}
