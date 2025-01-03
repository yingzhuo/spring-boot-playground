package com.github.yingzhuo.playground.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserPasswordHistoryTestCase {

    @Autowired
    private UserPasswordHistoryMapper mapper;

    @Test
    public void test1() {
        var his = mapper.findByUserIdLimit("0194012571d540289f74940125710000", 5);
        System.out.println(his);
    }

    @Test
    public void test2() {
        var his = mapper.findByUserIdOrderByCreateTimeDesc("0194012571d540289f74940125710000");
        System.out.println(his);
    }

}
