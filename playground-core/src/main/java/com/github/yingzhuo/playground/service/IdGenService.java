package com.github.yingzhuo.playground.service;

/**
 * ID生成服务
 *
 * @author 应卓
 */
@FunctionalInterface
public interface IdGenService {

    public Long nextId();

}
