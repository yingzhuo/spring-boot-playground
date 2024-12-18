package com.github.yingzhuo.playground.mapper;

import com.github.yingzhuo.playground.entity.User;
import org.springframework.lang.Nullable;

public interface UserMapperExt {

    @Nullable
    public User findByUsername(@Nullable String username);

    @Nullable
    public User findByUsernameAndPassword(@Nullable String username, @Nullable String password);

}
