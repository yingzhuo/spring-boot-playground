package com.github.yingzhuo.playground.service.impl;

import com.github.yingzhuo.playground.entity.User;
import com.github.yingzhuo.playground.mapper.UserMapper;
import com.github.yingzhuo.playground.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import spring.turbo.module.jdbc.ds.DataSourceSwitch;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Nullable
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @DataSourceSwitch("slave")
    public User tryToLogin(String username, String password) {

        var user = userMapper.findByUsername(username);
        if (user == null) {
            return null;
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return null;
        }

        if (!user.getEnabled()) {
            return null;
        }

        user.setPassword(null);
        return user;
    }
}
