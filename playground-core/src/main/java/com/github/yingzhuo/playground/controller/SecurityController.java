package com.github.yingzhuo.playground.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.yingzhuo.playground.controller.data.LoginForm;
import com.github.yingzhuo.playground.include.model.ApiResult;
import com.github.yingzhuo.playground.properties.JWTProperties;
import com.github.yingzhuo.playground.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.turbo.exception.BusinessException;
import spring.turbo.exception.DataBindingException;
import spring.turbo.module.jackson.view.BaseView;
import spring.turbo.module.jwt.JwtData;
import spring.turbo.module.jwt.JwtService;
import spring.turbo.util.UUIDGenerators;

import java.util.Map;

@RestController
@RequestMapping("/security")
@RequiredArgsConstructor
public class SecurityController {

    private final JWTProperties jwtProperties;
    private final JwtService jwtService;
    private final LoginService loginService;

    @JsonView(BaseView.class)
    @PostMapping("/login")
    public Object login(
            @Validated LoginForm loginForm,
            BindingResult bindingResult
    ) {
        DataBindingException.raiseIfNecessary(bindingResult);

        var user = loginService.tryToLogin(loginForm.getUsername(), loginForm.getPassword());
        if (user == null) {
            throw new BusinessException("用户或密码错误");
        }

        var roles = StringUtils.commaDelimitedListToSet(user.getAuthorities())
                .stream()
                .filter(s -> s.startsWith("ROLE_"))
                .toList();

        var token = jwtService.createToken(
                JwtData.newInstance()
                        .addPayload("userId", user.getId())
                        .addPayload("username", user.getUsername())
                        .addPayload("roles", roles)
                        .addPayloadJwtId(UUIDGenerators::timeBased32)
                        .addPayloadIssuer(jwtProperties.getIssuer())
                        .addPayloadExpiresAtFuture(jwtProperties.getTtl())
        );

        var data = Map.of(
                "token", token,
                "username", loginForm.getUsername()
        );
        return ApiResult.of(data);
    }

}
