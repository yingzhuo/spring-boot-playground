package com.github.yingzhuo.playground;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.github.yingzhuo.playground.mapper"})
@RequiredArgsConstructor
public class ApplicationBootDatabase {

    private final DataSource dataSource;

    @Bean
    public TransactionManager transactionManager() {
        return new JdbcTransactionManager(dataSource);
    }

}
