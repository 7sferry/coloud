package org.example.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;

/************************
 * Made by [MR Ferry™]  *
 * on Desember 2025     *
 ************************/

public class AuthRateLimiter extends RedisRateLimiter{
	public AuthRateLimiter(){
		super(1, 5);
	}
}
