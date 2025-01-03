package com.github.yingzhuo.playground.util;

import org.springframework.stereotype.Component;
import spring.turbo.util.UUIDGenerators;

@Component
public class IDGeneratorImpl implements IDGenerator {

    @Override
    public String generate() {
        return UUIDGenerators.timeBased32();
    }

}
