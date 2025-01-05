package com.github.yingzhuo.playground.service;

import com.github.yingzhuo.playground.entity.User;
import org.springframework.lang.Nullable;

public interface LoginService {

    @Nullable
    public User tryToLogin(String username, String password);

}
