package com.github.yingzhuo.playground.include.exp;

import java.io.Serializable;
import java.security.SecureRandom;

/**
 * 测试用巨型对象
 */
public final class BigData implements Serializable {

    private static final SecureRandom RANDOM = new SecureRandom();

    public static BigData _1mb() {
        return new BigData(1024 * 1024);
    }

    public static BigData _2mb() {
        return new BigData(1024 * 1024 * 2);
    }

    public static BigData _10mb() {
        return new BigData(1024 * 1024 * 10);
    }

    private final byte[] data;

    private BigData(int size) {
        this.data = new byte[size];
        RANDOM.nextBytes(this.data);
    }

    public int getSize() {
        return this.data.length;
    }

}
