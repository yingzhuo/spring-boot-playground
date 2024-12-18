package com.github.yingzhuo.playground;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import spring.turbo.module.redis.aspect.AvoidRepeatedInvocationAdvice;

@Configuration
@EnableAspectJAutoProxy
@RequiredArgsConstructor
public class ApplicationBootAop {

    @Bean
    public AvoidRepeatedInvocationAdvice avoidRepeatedInvocationAdvice(StringRedisTemplate redisTemplate) {
        return new AvoidRepeatedInvocationAdvice(redisTemplate, RuntimeException::new, Ordered.HIGHEST_PRECEDENCE);
    }

}
