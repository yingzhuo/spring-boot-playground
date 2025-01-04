package com.github.yingzhuo.playground.include.jdbc.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DataSourceSwitch {

    public String value();

}
