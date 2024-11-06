package com.github.yingzhuo.playground;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationBootMvc implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
    }

}
