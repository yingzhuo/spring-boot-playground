package com.github.yingzhuo.playground.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yingzhuo.playground.entity.UserAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserAddressMapper extends BaseMapper<UserAddress> {

    public List<UserAddress> findByUserId(@Param("userId") String userId);

}
