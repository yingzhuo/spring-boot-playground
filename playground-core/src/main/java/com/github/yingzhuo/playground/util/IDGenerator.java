package com.github.yingzhuo.playground.util;

import java.util.function.Supplier;

@FunctionalInterface
public interface IDGenerator extends Supplier<String> {

    @Override
    public default String get() {
        return generate();
    }

    public String generate();

}
