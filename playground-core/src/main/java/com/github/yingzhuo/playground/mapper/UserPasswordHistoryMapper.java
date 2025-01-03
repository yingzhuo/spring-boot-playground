package com.github.yingzhuo.playground.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yingzhuo.playground.entity.UserPasswordHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserPasswordHistoryMapper extends BaseMapper<UserPasswordHistory> {

    public List<UserPasswordHistory> findByUserIdLimit(
            @Param("userId") String userId,
            @Param("limit") int limit
    );

    public List<UserPasswordHistory> findByUserIdOrderByCreateTimeDesc(@Param("userId") String userId);

}
