package com.github.yingzhuo.playground.mapper.impl;

import com.github.yingzhuo.playground.entity.User;
import com.github.yingzhuo.playground.mapper.UserMapperExt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapperExt {

    private final SqlSession sqlSession;

    @Override
    @Nullable
    public User findByUsername(@Nullable String username) {
        if (!StringUtils.hasText(username)) {
            return null;
        }
        var params = Map.of("username", username);
        return sqlSession.selectOne("findByUsername", params);
    }

    @Override
    @Nullable
    public User findByUsernameAndPassword(@Nullable String username, @Nullable String password) {
        if (!StringUtils.hasText(username)) {
            return null;
        }

        if (!StringUtils.hasText(password)) {
            return null;
        }

        var params = Map.of(
                "username", username,
                "password", password
        );

        return sqlSession.selectOne("findByUsernameAndPassword", params);
    }

}
