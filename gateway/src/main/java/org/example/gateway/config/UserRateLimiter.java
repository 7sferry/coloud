package org.example.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;

/************************
 * Made by [MR Ferry™]  *
 * on Desember 2025     *
 ************************/

public class UserRateLimiter extends RedisRateLimiter{
	public UserRateLimiter(){
		super(1, 5);
	}
}
