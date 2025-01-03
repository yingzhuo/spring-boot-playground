package com.github.yingzhuo.playground.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.yingzhuo.playground.SwaggerConstants;
import com.github.yingzhuo.playground.include.model.ApiResult;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.turbo.module.jackson.view.BaseView;

import java.util.Map;

@RestController
public class MeController {

    @GetMapping("/me")
    @JsonView(BaseView.class)
    @SecurityRequirement(name = SwaggerConstants.JWT_REQUIRED)
    public Object me(@AuthenticationPrincipal UserDetails userDetails) {
        var data = Map.of(
                "username", userDetails.getUsername(),
                "principalType", userDetails.getClass().getName()
        );
        return ApiResult.of(data);
    }

}
