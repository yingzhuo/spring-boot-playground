package com.github.yingzhuo.playground.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserAddressMapperTestCase {

    @Autowired
    private UserAddressMapper mapper;

    @Test
    public void test1() {
        var list = mapper.findByUserId("0194012571d540289f74940125710000");

        for (var it : list) {
            System.out.println(it);
        }
    }

}
