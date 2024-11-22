package com.github.yingzhuo.playground.include.exp;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * 测试用巨型对象
 */
public final class BigData implements Serializable {

    private static final SecureRandom RANDOM = new SecureRandom();
    private final byte[] data;

    private BigData(int size) {
        this.data = new byte[size];
        RANDOM.nextBytes(this.data);
    }

    public static BigData _1mb() {
        return new BigData(1024 * 1024);
    }

    public static BigData _2mb() {
        return new BigData(1024 * 1024 * 2);
    }

    public static BigData _10mb() {
        return new BigData(1024 * 1024 * 10);
    }

    public int getSize() {
        return this.data.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BigData bigData = (BigData) o;

        return Arrays.equals(data, bigData.data);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }

}
