package com.github.yingzhuo.playground.task;

import com.github.yingzhuo.playground.inject.SharedThreadPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import spring.turbo.util.RandomUtils;
import spring.turbo.util.concurrent.SleepUtils;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;

@Slf4j
@Component
public class SlowTask {

    private final ExecutorService threadPool;

    public SlowTask(@SharedThreadPool ExecutorService threadPool) {
        this.threadPool = threadPool;
    }

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
