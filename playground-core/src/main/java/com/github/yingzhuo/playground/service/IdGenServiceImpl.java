package com.github.yingzhuo.playground.service;

import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.turbo.util.id.SnowflakeGenerator;

@Service
@RequiredArgsConstructor
public class IdGenServiceImpl implements IdGenService {

    private final SnowflakeGenerator snowflakeGenerator;

    @Override
    @Observed(name = "idGenService")
    public Long nextId() {
        return snowflakeGenerator.nextId();
    }

}
