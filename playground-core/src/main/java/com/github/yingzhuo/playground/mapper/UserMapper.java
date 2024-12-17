package com.github.yingzhuo.playground.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yingzhuo.playground.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User>, UserMapperExt {
}
