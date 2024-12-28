package com.github.yingzhuo.playground.service.impl;

import com.github.yingzhuo.playground.mapper.UserMapper;
import com.github.yingzhuo.playground.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void resetPassword(String userId, String newPassword) {
        Assert.hasText(userId, () -> "userId is required");
        Assert.hasText(newPassword, "newPassword is required");

        log.debug("userId = {}", userId);
        log.debug("newPassword = {}", newPassword);

        var user = userMapper.findByIdForUpdate(userId);
        if (user == null) {
            return;
        }

        var pwd = passwordEncoder.encode(newPassword);
        log.debug("hashed-password = {}", pwd);
        user.setPassword(pwd);
        userMapper.updateById(user);
    }

}
