package com.github.yingzhuo.playground.support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import spring.turbo.util.UUIDUtils;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class ScheduledTask {

    @Scheduled(fixedRate = 5L, timeUnit = TimeUnit.MINUTES)
    public void execute() {
        log.trace("random: {}", UUIDUtils.uuid36());
    }

}
