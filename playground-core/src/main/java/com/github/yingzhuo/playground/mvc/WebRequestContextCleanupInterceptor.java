package com.github.yingzhuo.playground.mvc;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import spring.turbo.module.jdbc.ds.RoutingDataSourceLookup;

@Slf4j
public class WebRequestContextCleanupInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) {
        log.debug("清理Web请求上下文");
        RoutingDataSourceLookup.remove();
    }

}
