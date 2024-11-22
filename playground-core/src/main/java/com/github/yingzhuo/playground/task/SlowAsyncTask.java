package com.github.yingzhuo.playground.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import spring.turbo.util.RandomUtils;
import spring.turbo.util.concurrent.SleepUtils;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@Slf4j
@Component
@ConditionalOnProperty(prefix = "playground.bean-switch", name = "slow-async-task", havingValue = "true")
@RequiredArgsConstructor
public class SlowAsyncTask {

    private final ThreadPoolTaskExecutor threadPool;

    @Scheduled(fixedRate = 5000L)
    public void doExec() {
        var f = CompletableFuture.supplyAsync(new SlowAction(), threadPool);

        log.debug("异步计算结果: {}", f.join());
    }

    private static class SlowAction implements Supplier<Long> {
        @Override
        public Long get() {
            var l = RandomUtils.nextLong(1000, 3501);
            SleepUtils.sleep(Duration.ofMillis(l));
            return l;
        }
    }

}
