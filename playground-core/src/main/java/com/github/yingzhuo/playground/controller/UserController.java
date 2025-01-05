package com.github.yingzhuo.playground.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.yingzhuo.playground.SwaggerConstants;
import com.github.yingzhuo.playground.include.model.ApiResult;
import com.github.yingzhuo.playground.security.JwtTokenToUserConverter;
import com.github.yingzhuo.playground.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.turbo.module.jackson.view.BaseView;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping({"/me", "/"})
    @JsonView(BaseView.class)
    @SecurityRequirement(name = SwaggerConstants.JWT_REQUIRED)
    public Object me(@AuthenticationPrincipal UserDetails userDetails) {

        log.debug("userDetails type = {}", userDetails);

        var userAttributes = (JwtTokenToUserConverter.UserAttributes) userDetails;
        var userId = userAttributes.getUserId();
        var user = userService.findByUserById(userId);
        user.setPassword("[****]");
        
        return ApiResult.of(
                Map.of(
                        "user", user
                )
        );
    }

}
