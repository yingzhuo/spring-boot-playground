package com.github.yingzhuo.playground.task;

import com.github.yingzhuo.playground.include.exp.BigData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashSet;
import java.util.Set;

@Slf4j
//@Component
public class MemLeakingTask {

    private final Set<BigData> wastedMemory = new HashSet<>();

    @Scheduled(fixedRate = 10000L)
    public void doExecute() {
        log.debug("浪费1mb内存");
        wastedMemory.add(BigData._1mb());
    }

    public Set<BigData> getWastedMemory() {
        return wastedMemory;
    }

}
