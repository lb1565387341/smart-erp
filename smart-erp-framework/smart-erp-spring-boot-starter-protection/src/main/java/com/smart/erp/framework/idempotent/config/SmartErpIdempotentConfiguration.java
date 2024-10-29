package com.smart.erp.framework.idempotent.config;

import com.smart.erp.framework.idempotent.core.aop.IdempotentAspect;
import com.smart.erp.framework.idempotent.core.keyresolver.IdempotentKeyResolver;
import com.smart.erp.framework.idempotent.core.keyresolver.impl.DefaultIdempotentKeyResolver;
import com.smart.erp.framework.idempotent.core.keyresolver.impl.ExpressionIdempotentKeyResolver;
import com.smart.erp.framework.idempotent.core.keyresolver.impl.UserIdempotentKeyResolver;
import com.smart.erp.framework.idempotent.core.redis.IdempotentRedisDAO;
import com.smart.erp.framework.redis.config.SmartErpRedisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

@AutoConfiguration(after = SmartErpRedisAutoConfiguration.class)
public class SmartErpIdempotentConfiguration {

    @Bean
    public IdempotentAspect idempotentAspect(List<IdempotentKeyResolver> keyResolvers, IdempotentRedisDAO idempotentRedisDAO) {
        return new IdempotentAspect(keyResolvers, idempotentRedisDAO);
    }

    @Bean
    public IdempotentRedisDAO idempotentRedisDAO(StringRedisTemplate stringRedisTemplate) {
        return new IdempotentRedisDAO(stringRedisTemplate);
    }

    // ========== 各种 IdempotentKeyResolver Bean ==========

    @Bean
    public DefaultIdempotentKeyResolver defaultIdempotentKeyResolver() {
        return new DefaultIdempotentKeyResolver();
    }

    @Bean
    public UserIdempotentKeyResolver userIdempotentKeyResolver() {
        return new UserIdempotentKeyResolver();
    }

    @Bean
    public ExpressionIdempotentKeyResolver expressionIdempotentKeyResolver() {
        return new ExpressionIdempotentKeyResolver();
    }

}
