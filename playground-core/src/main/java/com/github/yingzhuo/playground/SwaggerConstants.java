package com.github.yingzhuo.playground;

/**
 * 一些常量
 *
 * @author 应卓
 */
public class SwaggerConstants {

    /**
     * 私有构造方法
     */
    private SwaggerConstants() {
    }

    /**
     * Swagger资源的AntPattern
     */
    static final String[] SWAGGER_ANT_PATTERNS = {
            "/swagger.html",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**"
    };

    public static final String JWT_REQUIRED = "Bearer Authentication";
}
