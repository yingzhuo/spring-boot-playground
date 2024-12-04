package com.github.yingzhuo.playground;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories("com.github.yingzhuo.playground.dao")
@EntityScan("com.github.yingzhuo.playground.entity")
@EnableTransactionManagement
public class ApplicationBootJpa {

    @Bean
    public TransactionManager transactionManager(DataSource dataSource) {
        var bean = new JpaTransactionManager();
        bean.setDataSource(dataSource);
        return bean;
    }

}
