package com.redisimpl.config;

//import java.lang.reflect.Method;
//import java.time.Duration;
//
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.interceptor.KeyGenerator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializationContext;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Configuration
//@EnableCaching
//@Slf4j
//public class RedisCacheConfig extends CachingConfigurerSupport {
//
//	@Bean
//	public RedisConnectionFactory redisConnectionFactory() {
//		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
//		configuration.setHostName("localhost");
//		configuration.setPort(6379);
//		log.info(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
//		log.info("::::::::::::::::::::::::::REDIS CONNECTION IN ACTION::::::::::::::::::::::::::::");
//		return new LettuceConnectionFactory(configuration);
//	}
//
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
//        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofMinutes(50));
//        return RedisCacheManager.builder(connectionFactory)
//                .cacheDefaults(cacheConfiguration)
//                .build();
//    }
//
//	@Bean
//	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
//		RedisTemplate<String, Object> template = new RedisTemplate<>();
//		template.setConnectionFactory(connectionFactory);
//		template.setKeySerializer(new StringRedisSerializer());
//		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//		return template;
//	}
//
////    @Bean("customKeyGenerator")
////    public KeyGenerator keyGenerator() {
////        return new KeyGenerator() {
////			@Override
////			public Object generate(Object target, Method method, Object... params) {
////				 return target.getClass().getSimpleName() + "_"
////	                        + method.getName() + "_"
////	                        + params[0];
////			}
////        };
////    }
//}

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

@Configuration
@EnableCaching
public class RedisCacheConfig {

    @Bean(name = "RedisCacheManager")
    public CacheManager getManager(RedisConnectionFactory connectionFactory) {
        JdkSerializationRedisSerializer contextAwareRedisSerializer = new JdkSerializationRedisSerializer(getClass().getClassLoader());

        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues()
                .entryTtl(Duration.ofHours(12))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(contextAwareRedisSerializer));

        return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(connectionFactory)
                .cacheDefaults(redisCacheConfiguration)
                .build();
    }

}
