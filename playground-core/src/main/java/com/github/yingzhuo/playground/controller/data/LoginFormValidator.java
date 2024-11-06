package com.github.yingzhuo.playground.controller.data;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import spring.turbo.databinding.AbstractValidator;

@Component
public class LoginFormValidator extends AbstractValidator<LoginForm> {

    public LoginFormValidator() {
        super(LoginForm.class);
    }

    @Override
    protected void doValidate(LoginForm target, Errors errors) {
//        rejectIfEmptyOrWhitespace(errors, "username", "LoginForm.username.empty", "用户名缺失");
//        rejectIfEmptyOrWhitespace(errors, "password", "LoginForm.password.empty", "密码缺失");
    }

}
