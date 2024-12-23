package com.github.yingzhuo.playground;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ConfigurationPropertiesScan
@ImportResource("classpath*:/spring/spring-*.xml")
@RequiredArgsConstructor
public class ApplicationBoot {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ApplicationBoot.class)
                .run(args);
    }

}
