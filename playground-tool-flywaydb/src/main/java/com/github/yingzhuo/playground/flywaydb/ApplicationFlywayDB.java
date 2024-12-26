package com.github.yingzhuo.playground.flywaydb;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ApplicationFlywayDB {

    public static void main(String[] args) {
        // @formatter:off
        new SpringApplicationBuilder(ApplicationFlywayDB.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
        // @formatter:on
    }

}
