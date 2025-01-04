package com.github.yingzhuo.playground.include.jdbc;

import org.springframework.boot.autoconfigure.jdbc.JdbcConnectionDetails;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;

import javax.sql.DataSource;
import java.util.Objects;

@SuppressWarnings("unchecked")
public final class DataSourceFactories {

    private DataSourceFactories() {
    }

    public static <T> T createDataSource(JdbcConnectionDetails connectionDetails, Class<? extends DataSource> type) {
        return createDataSource(connectionDetails, type, ClassUtils.getDefaultClassLoader());
    }

    public static <T> T createDataSource(JdbcConnectionDetails connectionDetails, Class<? extends DataSource> type, @Nullable ClassLoader classLoader) {
        // @formatter:off
        return (T) DataSourceBuilder.create(Objects.requireNonNullElse(classLoader, ClassUtils.getDefaultClassLoader()))
                .type(type)
                .driverClassName(connectionDetails.getDriverClassName())
                .url(connectionDetails.getJdbcUrl())
                .username(connectionDetails.getUsername())
                .password(connectionDetails.getPassword())
                .build();
        // @formatter:on
    }

}
