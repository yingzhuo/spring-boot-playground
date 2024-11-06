package com.github.yingzhuo.playground;

import com.github.yingzhuo.playground.support.PrefixObservationPredicate;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.ObservationTextPublisher;
import io.micrometer.observation.aop.ObservedAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ServerHttpObservationFilter;

@Configuration
public class ApplicationBootObservation {

    private static final Logger log = LoggerFactory.getLogger("observation");

    @Bean
    public ObservationHandler<Observation.Context> observationTextPublisher() {
        return new ObservationTextPublisher(s -> {
            if (log.isDebugEnabled()) {
                log.debug(s);
            }
        });
    }

    @Bean
    public ServerHttpObservationFilter observationFilter(ObservationRegistry registry) {
        return new ServerHttpObservationFilter(registry);
    }

    @Bean
    public ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
        return new ObservedAspect(observationRegistry);
    }

    @Bean
    public ObservationRegistryCustomizer<ObservationRegistry> noSpringSecurityObservations() {
        return (registry) -> registry.observationConfig().observationPredicate(
                new PrefixObservationPredicate("spring.security.")
        );
    }

}
