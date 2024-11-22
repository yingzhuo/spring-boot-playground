package com.github.yingzhuo.playground.properties;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "playground.server")
@Validated
public class ServerAdditionalProperties implements Serializable {

    @Min(value = 1, message = "configuration 'server.additional-port' is invalid")
    @Max(value = 65535, message = "configuration 'server.additional-port' is invalid")
    private int additionalPort = -1;

    @NotBlank(message = "configuration 'server.additional-protocol' is null or blank")
    private String additionalProtocol;

}
