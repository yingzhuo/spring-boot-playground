package com.github.yingzhuo.playground.include.jdbc.annotation;

import com.github.yingzhuo.playground.include.jdbc.RoutingDataSourceLookup;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;

@Aspect
@RequiredArgsConstructor
public class DataSourceSwitchAdvice implements Ordered {

    private final int order;

    @Around("@annotation(com.github.yingzhuo.playground.include.jdbc.annotation.DataSourceSwitch)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        var signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature methodSignature) {
            var annotation = methodSignature.getMethod().getAnnotation(DataSourceSwitch.class);
            var dataSourceName = annotation.value();
            RoutingDataSourceLookup.set(dataSourceName);
            try {
                return joinPoint.proceed();
            } finally {
                RoutingDataSourceLookup.remove();
            }
        } else {
            return joinPoint.proceed();
        }
    }

    @Override
    public int getOrder() {
        return this.order;
    }

}
