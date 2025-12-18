package org.example.user.controller;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.util.Map;

/************************
 * Made by [MR Ferry™]  *
 * on Desember 2025     *
 ************************/

@RestController
@RequestMapping("/users")
public class UserController {

	@GetMapping("/me")
	@SneakyThrows
	public Map<String, String> me(@RequestHeader("X-User") String username) {
		return Map.of(
				"username", username,
				"message", "This is a protected resource",
				"ip", InetAddress.getLocalHost().getHostAddress()
		);
	}
}
