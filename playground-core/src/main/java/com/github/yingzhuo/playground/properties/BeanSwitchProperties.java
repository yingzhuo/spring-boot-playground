package com.github.yingzhuo.playground.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "playground.bean-switch")
public class BeanSwitchProperties implements Serializable {

    private boolean slowAsyncTask = false;

    private boolean MemoryLeakingTask = false;

}
