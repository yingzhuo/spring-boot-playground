package com.github.yingzhuo.playground.properties;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.time.Duration;

@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "playground.jwt")
@Validated
public class JWTProperties implements Serializable {

    @NotEmpty(message = "configuration 'jwt.issuer' is required")
    private String issuer = "playground.com";

    @NotNull(message = "configuration 'jwt.ttl' is required")
    private Duration ttl = Duration.ofHours(2L);

}
