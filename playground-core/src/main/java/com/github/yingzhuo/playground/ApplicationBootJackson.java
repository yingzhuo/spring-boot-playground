package com.github.yingzhuo.playground;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBootJackson {

    @Autowired
    public void setupObjectMapper(ObjectMapper objectMapper) {
    }

}
