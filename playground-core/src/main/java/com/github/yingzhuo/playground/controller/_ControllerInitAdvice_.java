package com.github.yingzhuo.playground.controller;

import com.github.yingzhuo.playground.controller.data.LoginFormValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
@RequiredArgsConstructor
public class _ControllerInitAdvice_ {

    private final LoginFormValidator loginFormValidator;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.addValidators(loginFormValidator);
    }

}
