package com.github.yingzhuo.playground;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class ApplicationBootRedis {

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
        var bean = new RedisTemplate<String, String>();
        bean.setConnectionFactory(connectionFactory);
        bean.setDefaultSerializer(RedisSerializer.string());
        bean.setKeySerializer(RedisSerializer.string());
        bean.setValueSerializer(RedisSerializer.string());
        bean.setHashKeySerializer(RedisSerializer.string());
        bean.setHashValueSerializer(RedisSerializer.string());
        return bean;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

    // LUA 脚本
    // -----------------------------------------------------------------------------------------------------------------

    @Bean
    public RedisScript<String> deleteByPattern() {
        return RedisScript.of(new ClassPathResource("script/redis-lua/delete-by-pattern.lua"), String.class);
    }

}
