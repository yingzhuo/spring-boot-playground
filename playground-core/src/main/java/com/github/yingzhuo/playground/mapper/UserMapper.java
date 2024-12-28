package com.github.yingzhuo.playground.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yingzhuo.playground.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.lang.Nullable;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Nullable
    public User findByIdForUpdate(@Param("id") String id);

    @Nullable
    public User findByUsername(@Param("username") String username);

    @Nullable
    public User findByUsernameAndPassword(@Param("username") String username,
                                          @Param("password") String password);

}
