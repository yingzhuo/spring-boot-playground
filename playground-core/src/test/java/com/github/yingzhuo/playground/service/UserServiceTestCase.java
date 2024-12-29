package com.github.yingzhuo.playground.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTestCase {

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("测试修改密码 - 1")
    public void test1() {
        userService.resetPassword("0194012571d540289f74940125710000", "yingzhuo");
    }

}
