package com.github.yingzhuo.playground.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.yingzhuo.playground.controller.data.LoginForm;
import com.github.yingzhuo.playground.include.model.ApiResult;
import com.github.yingzhuo.playground.properties.JWTProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
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
import spring.turbo.module.security.authentication.UserDetailsFinder;
import spring.turbo.util.UUIDGenerators;

import java.util.Map;

@RestController
@RequestMapping("/security")
@RequiredArgsConstructor
public class SecurityController {

    private final JWTProperties jwtProperties;
    private final UserDetailsFinder userDetailsFinder;
    private final JwtService jwtService;

    @JsonView(BaseView.class)
    @PostMapping("/login")
    public Object login(
            @Validated LoginForm loginForm,
            BindingResult bindingResult
    ) {
        DataBindingException.raiseIfNecessary(bindingResult);

        var userDetails = userDetailsFinder.loadUserByUsernameAndPassword(loginForm.getUsername(), loginForm.getPassword());
        if (userDetails == null) {
            throw new BusinessException("用户或密码错误");
        }

        var roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .filter(s -> s.startsWith("ROLE_"))
                .toList();

        var token = jwtService.createToken(
                JwtData.newInstance()
                        .addPayload("username", loginForm.getUsername())
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
