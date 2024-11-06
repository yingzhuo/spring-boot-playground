package com.github.yingzhuo.playground.dao;

import com.github.yingzhuo.playground.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, Long>, UserDaoExt {

    @Nullable
    public User findByUsername(String username);

}
