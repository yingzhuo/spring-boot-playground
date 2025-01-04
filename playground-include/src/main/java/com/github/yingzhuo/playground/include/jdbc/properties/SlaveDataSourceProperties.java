package com.github.yingzhuo.playground.include.jdbc.properties;

import com.github.yingzhuo.playground.include.jdbc.DataSourceNames;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.autoconfigure.jdbc.JdbcConnectionDetails;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "datasource.slave")
@Validated
public class SlaveDataSourceProperties implements JdbcConnectionDetails, Serializable {

    @NotEmpty(message = "'datasource.slave.jdbcUrl' 缺失")
    private String jdbcUrl;

    @NotEmpty(message = "'datasource.slave.username' 缺失")
    private String username;

    @NotEmpty(message = "'datasource.slave.password' 缺失")
    private String password;

    @NotEmpty(message = "'datasource.slave.driver-class-name' 缺失")
    private String driverClassName;

    @NotEmpty(message = "'datasource.slave.pool-name' 缺失")
    private String poolName = DataSourceNames.SLAVE;

}
