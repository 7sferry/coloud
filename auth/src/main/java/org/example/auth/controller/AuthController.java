package org.example.auth.controller;

import lombok.RequiredArgsConstructor;
import org.example.auth.config.JwtUtil;
import org.example.auth.request.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

/************************
 * Made by [MR Ferry™]  *
 * on Desember 2025     *
 ************************/

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final JwtUtil jwtUtil;

	@PostMapping("/login")
	public Map<String, String> login(@RequestBody LoginRequest req) {

		// 🔴 DEMO ONLY (hardcoded user)
		if (!"admin".equals(req.username()) || !"password".equals(req.password())) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}

		String token = jwtUtil.generateToken(req.username());
		return Map.of("token", token);
	}
}

