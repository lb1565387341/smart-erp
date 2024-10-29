package com.smart.erp.framework.ratelimiter.config;

import com.smart.erp.framework.ratelimiter.core.aop.RateLimiterAspect;
import com.smart.erp.framework.ratelimiter.core.keyresolver.RateLimiterKeyResolver;
import com.smart.erp.framework.ratelimiter.core.keyresolver.impl.ClientIpRateLimiterKeyResolver;
import com.smart.erp.framework.ratelimiter.core.keyresolver.impl.DefaultRateLimiterKeyResolver;
import com.smart.erp.framework.ratelimiter.core.keyresolver.impl.ExpressionRateLimiterKeyResolver;
import com.smart.erp.framework.ratelimiter.core.keyresolver.impl.ServerNodeRateLimiterKeyResolver;
import com.smart.erp.framework.ratelimiter.core.keyresolver.impl.UserRateLimiterKeyResolver;
import com.smart.erp.framework.ratelimiter.core.redis.RateLimiterRedisDAO;
import com.smart.erp.framework.redis.config.SmartErpRedisAutoConfiguration;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;

@AutoConfiguration(after = SmartErpRedisAutoConfiguration.class)
public class SmartErpRateLimiterConfiguration {

    @Bean
    public RateLimiterAspect rateLimiterAspect(List<RateLimiterKeyResolver> keyResolvers, RateLimiterRedisDAO rateLimiterRedisDAO) {
        return new RateLimiterAspect(keyResolvers, rateLimiterRedisDAO);
    }

    @Bean
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public RateLimiterRedisDAO rateLimiterRedisDAO(RedissonClient redissonClient) {
        return new RateLimiterRedisDAO(redissonClient);
    }

    // ========== 各种 RateLimiterRedisDAO Bean ==========

    @Bean
    public DefaultRateLimiterKeyResolver defaultRateLimiterKeyResolver() {
        return new DefaultRateLimiterKeyResolver();
    }

    @Bean
    public UserRateLimiterKeyResolver userRateLimiterKeyResolver() {
        return new UserRateLimiterKeyResolver();
    }

    @Bean
    public ClientIpRateLimiterKeyResolver clientIpRateLimiterKeyResolver() {
        return new ClientIpRateLimiterKeyResolver();
    }

    @Bean
    public ServerNodeRateLimiterKeyResolver serverNodeRateLimiterKeyResolver() {
        return new ServerNodeRateLimiterKeyResolver();
    }

    @Bean
    public ExpressionRateLimiterKeyResolver expressionRateLimiterKeyResolver() {
        return new ExpressionRateLimiterKeyResolver();
    }

}
