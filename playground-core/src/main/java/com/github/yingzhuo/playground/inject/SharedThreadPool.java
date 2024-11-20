package com.github.yingzhuo.playground.inject;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Qualifier("sharedThreadPool")
public @interface SharedThreadPool {
}
