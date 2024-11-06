package com.github.yingzhuo.playground;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class ApplicationBootDatabase {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.HSQL)
                .setScriptEncoding("utf-8")
                .ignoreFailedDrops(true)
                .addScript("classpath:/hsqldb/1-schema.sql")
                .addScript("classpath:/hsqldb/2-data.sql")
                .build();
    }

}
