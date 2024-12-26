package com.github.yingzhuo.playground.service.impl;

import com.github.yingzhuo.playground.service.IdGenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.turbo.util.UUIDGenerators;

@Service
@RequiredArgsConstructor
public class IdGenServiceImpl implements IdGenService {

    @Override
    public String nextId() {
        return UUIDGenerators.timeBased32();
    }

}
