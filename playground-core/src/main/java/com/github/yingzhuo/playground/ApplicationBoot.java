package com.github.yingzhuo.playground;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ConfigurationPropertiesScan
@ImportResource("classpath*:/spring/spring-*.xml")
public class ApplicationBoot {

    public static void main(String[] args) {
        // @formatter:off
        new SpringApplicationBuilder(ApplicationBoot.class)
                .run(args);
        // @formatter:on
    }

}
