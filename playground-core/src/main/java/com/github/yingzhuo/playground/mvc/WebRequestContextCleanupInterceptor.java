package com.github.yingzhuo.playground.mvc;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import spring.turbo.module.jdbc.ds.RoutingDataSourceLookup;

@Slf4j
public class WebRequestContextCleanupInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        log.trace("清理Web请求上下文");
        RoutingDataSourceLookup.remove();
    }

}
