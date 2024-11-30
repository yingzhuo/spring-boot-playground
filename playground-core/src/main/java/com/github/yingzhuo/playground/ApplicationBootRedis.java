package com.github.yingzhuo.playground;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class ApplicationBootRedis {

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

    @Bean
    public RedisTemplate<String, Object> stringObjectTemplate(RedisConnectionFactory connectionFactory, ObjectMapper objectMapper) {

        // @formatter:off
        var objectMapperInUse = objectMapper.copy()
                .configure(SerializationFeature.INDENT_OUTPUT, false);  // 不要缩进以减少Redis内存消耗
        // @formatter:on

        var serializer = new Jackson2JsonRedisSerializer<>(objectMapperInUse, Object.class);

        var bean = new RedisTemplate<String, Object>();
        bean.setConnectionFactory(connectionFactory);
        bean.setDefaultSerializer(RedisSerializer.string());
        bean.setKeySerializer(RedisSerializer.string());
        bean.setValueSerializer(serializer);
        bean.setHashKeySerializer(RedisSerializer.string());
        bean.setHashValueSerializer(serializer);
        return bean;
    }

}
