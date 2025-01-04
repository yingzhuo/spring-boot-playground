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
@ConfigurationProperties(prefix = "datasource.master")
@Validated
public class MasterDataSourceProperties implements JdbcConnectionDetails, Serializable {

    @NotEmpty(message = "'datasource.master.jdbcUrl' 缺失")
    private String jdbcUrl;

    @NotEmpty(message = "'datasource.master.username' 缺失")
    private String username;

    @NotEmpty(message = "'datasource.master.password' 缺失")
    private String password;

    @NotEmpty(message = "'datasource.master.driver-class-name' 缺失")
    private String driverClassName;

    @NotEmpty(message = "'datasource.master.pool-name' 缺失")
    private String poolName = DataSourceNames.MASTER;

}
