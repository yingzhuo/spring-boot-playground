package com.github.yingzhuo.playground;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@EnableRetry
@Configuration
@RequiredArgsConstructor
public class ApplicationBootRetry {

    @Bean
    public RetryTemplate retryTemplate() {
        var backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(300L);

        var retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(4);

        var bean = new RetryTemplate();
        bean.setBackOffPolicy(backOffPolicy);
        bean.setRetryPolicy(retryPolicy);
        return bean;
    }

}
