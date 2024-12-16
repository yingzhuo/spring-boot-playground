package com.github.yingzhuo.playground.task;

import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GreetingTask {

    @XxlJob("greetingTask")
    public void execute() throws Throwable {
        log.debug("hello");
    }

}
