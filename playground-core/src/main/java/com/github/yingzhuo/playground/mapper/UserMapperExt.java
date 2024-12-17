package com.github.yingzhuo.playground.mapper;

import com.github.yingzhuo.playground.entity.User;
import org.springframework.lang.Nullable;

interface UserMapperExt {

    @Nullable
    public User findByUsername(String username);

}
