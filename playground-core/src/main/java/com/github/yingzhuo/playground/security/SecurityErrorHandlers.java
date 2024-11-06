package com.github.yingzhuo.playground.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.yingzhuo.playground.include.model.ApiResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import spring.turbo.module.security.exception.SecurityExceptionHandler;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@Component
@RequiredArgsConstructor
public class SecurityErrorHandlers implements SecurityExceptionHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, RequestRejectedException requestRejectedException) throws IOException {
        response.setStatus(BAD_REQUEST.value());
        writeJson(response, ApiResult.error("400", "请求错误"));
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        writeJson(response, ApiResult.error("401", "认证错误"));
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        writeJson(response, ApiResult.error("403", "鉴权错误"));
    }

    private void writeJson(HttpServletResponse response, Object obj) throws IOException {
        var out = response.getOutputStream();
        var body = objectMapper.writeValueAsString(obj).getBytes(UTF_8);
        response.addHeader(CONTENT_TYPE, "application/json;charset=utf-8");
        FileCopyUtils.copy(body, out);
        out.flush();
    }

}
