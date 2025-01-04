package com.github.yingzhuo.playground.include.jdbc;

import com.github.yingzhuo.playground.include.jdbc.annotation.DataSourceSwitchAdvice;
import com.github.yingzhuo.playground.include.jdbc.properties.MasterDataSourceProperties;
import com.github.yingzhuo.playground.include.jdbc.properties.SlaveDataSourceProperties;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

import javax.sql.DataSource;
import java.util.Map;

import static com.github.yingzhuo.playground.include.jdbc.DataSourceNames.MASTER;
import static com.github.yingzhuo.playground.include.jdbc.DataSourceNames.SLAVE;

@EnableConfigurationProperties({MasterDataSourceProperties.class, SlaveDataSourceProperties.class})
public class RoutingDataSourceAutoConfiguration {

    @Bean
    public DataSourceSwitchAdvice dataSourceSwitchAdvice() {
        return new DataSourceSwitchAdvice(Ordered.HIGHEST_PRECEDENCE);
    }

    @Bean
    public DataSource dataSource(
            MasterDataSourceProperties masterDataSourceProperties,
            SlaveDataSourceProperties slaveDataSourceProperties
    ) {
        var master = createMasterDataSource(masterDataSourceProperties);
        var slave = createSlaveDataSource(slaveDataSourceProperties);

        var targetDataSources = Map.of(
                MASTER, master,
                SLAVE, slave
        );

        return new RoutingDataSource(master, targetDataSources);
    }

    private DataSource createMasterDataSource(MasterDataSourceProperties properties) {
        HikariDataSource dataSource = DataSourceFactories.createDataSource(properties, HikariDataSource.class);
        dataSource.setPoolName(properties.getPoolName());
        return dataSource;
    }

    private DataSource createSlaveDataSource(SlaveDataSourceProperties properties) {
        HikariDataSource dataSource = DataSourceFactories.createDataSource(properties, HikariDataSource.class);
        dataSource.setPoolName(properties.getPoolName());
        return dataSource;
    }

}
