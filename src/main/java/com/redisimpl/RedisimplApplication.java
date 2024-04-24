package com.redisimpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisimplApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisimplApplication.class, args);
		System.setProperty("spring.devtools.restart.enabled", "false");
	}

}
