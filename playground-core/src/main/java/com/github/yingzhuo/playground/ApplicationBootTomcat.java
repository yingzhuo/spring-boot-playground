package com.github.yingzhuo.playground;

import com.github.yingzhuo.playground.properties.ServerAdditionalProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.turbo.module.webmvc.tomcat.AdditionalPortTomcatWebServerCustomizer;

@Configuration
public class ApplicationBootTomcat {

    @Bean
    public AdditionalPortTomcatWebServerCustomizer tomcatWebServerCustomizer(ServerAdditionalProperties props) {
        var c = new AdditionalPortTomcatWebServerCustomizer();
        c.setPort(props.getAdditionalPort());
        c.setProtocol(props.getAdditionalProtocol());
        return c;
    }

}
