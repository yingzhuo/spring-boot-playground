package com.github.yingzhuo.playground;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class ApplicationBootMvc implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
    }

}
