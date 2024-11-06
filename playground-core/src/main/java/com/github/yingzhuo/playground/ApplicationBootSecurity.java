package com.github.yingzhuo.playground;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import spring.turbo.module.security.authentication.TokenToUserConverter;
import spring.turbo.module.security.exception.SecurityExceptionHandler;
import spring.turbo.module.security.filter.factory.JwtTokenAuthenticationFilterFactoryBean;

@Configuration
@EnableMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class ApplicationBootSecurity {

    private static final AuthorizationManager<RequestAuthorizationContext> SHUT_DOWN_AUTHORIZATION_MANAGER
            = new WebExpressionAuthorizationManager("hasAuthority('ROLE_X509') and hasIpAddress('127.0.0.1/32')");

    @Bean
    public JwtTokenAuthenticationFilterFactoryBean jwtTokenAuthenticationFilterFactoryBean(
            TokenToUserConverter tokenToUserConverter,
            SecurityExceptionHandler exceptionHandler
    ) {
        var factory = new JwtTokenAuthenticationFilterFactoryBean();
        factory.setTokenToUserConverter(tokenToUserConverter);
        factory.setSecurityExceptionHandler(exceptionHandler);
        return factory;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        var context = http.getSharedObject(ApplicationContext.class);

        final var exceptionHandler = context.getBean(SecurityExceptionHandler.class);

        http.securityMatcher("/**");

        // enabled
        http.requiresChannel(c ->
                c.anyRequest().requiresSecure()
        );

        // enabled
        // default role: 'ROLE_ANONYMOUS'
        http.anonymous(Customizer.withDefaults());

        // enabled
        http.cors(Customizer.withDefaults());

        // stateless
        http.sessionManagement(c ->
                c.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        // disabled
        http.csrf(AbstractHttpConfigurer::disable);

        // disabled
        http.httpBasic(Customizer.withDefaults());

        // disabled
        http.jee(AbstractHttpConfigurer::disable);

        // disabled
        http.formLogin(AbstractHttpConfigurer::disable);

        // disabled
        http.logout(AbstractHttpConfigurer::disable);

        // disabled
        http.rememberMe(AbstractHttpConfigurer::disable);

        // disabled
        http.requestCache(RequestCacheConfigurer::disable);

        // enabled
        http.headers(Customizer.withDefaults());

        // enable
        http.cors(Customizer.withDefaults());

        // enabled
        http.x509(c ->
                c.subjectPrincipalRegex("CN=(.*?)(?:,|$)")
                        .userDetailsService(context.getBean(UserDetailsService.class))
        );

        // handle errors
        http.exceptionHandling(c ->
                c.authenticationEntryPoint(exceptionHandler)
                        .accessDeniedHandler(exceptionHandler)
        );

        // 权限管理
        http.authorizeHttpRequests(c ->
                c.requestMatchers(HttpMethod.POST, "/security/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/favicon.ico").permitAll()
                        .requestMatchers(HttpMethod.POST, "/actuator/shutdown").access(SHUT_DOWN_AUTHORIZATION_MANAGER)
                        .requestMatchers(HttpMethod.GET, "/actuator", "/actuator/**").permitAll()
                        .anyRequest().hasAuthority("ROLE_USER")
        );

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(Environment environment) {
        return web -> web.debug(
                environment.acceptsProfiles(Profiles.of("develop"))
        );
    }

}
