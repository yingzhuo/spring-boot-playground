package com.github.yingzhuo.playground.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.yingzhuo.playground.include.model.ApiResult;
import com.github.yingzhuo.playground.service.IdGenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.turbo.module.jackson.view.BaseView;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class IdController {

    private final IdGenService idGenService;

    @GetMapping("/id")
    @JsonView(BaseView.class)
    public Object id() {
        var data = Map.of("id", idGenService.nextId());
        return ApiResult.of(data);
    }

}
