package com.github.yingzhuo.playground.mapper;

import com.github.yingzhuo.playground.entity.User;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;

@RequiredArgsConstructor
class UserMapperImpl implements UserMapperExt {

    private final SqlSession sqlSession;

    @Override
    public User findByUsername(String username) {
        return sqlSession.selectOne("findByUsername", username);
    }

}
