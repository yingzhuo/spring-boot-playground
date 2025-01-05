package com.github.yingzhuo.playground.security;

import lombok.Data;
import org.springframework.lang.Nullable;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import spring.turbo.module.jackson.util.JsonUtils;
import spring.turbo.module.jwt.JwtAssertions;
import spring.turbo.module.jwt.JwtService;
import spring.turbo.module.security.authentication.TokenToUserConverter;
import spring.turbo.module.security.jwt.AbstractJwtTokenToUserConverter;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class JwtTokenToUserConverter extends AbstractJwtTokenToUserConverter implements TokenToUserConverter {

    public JwtTokenToUserConverter(JwtService jwtService) {
        super(jwtService);
    }

    @Nullable
    @Override
    protected JwtAssertions getJwtAssertions() {
        return null;
    }

    @Nullable
    @Override
    protected UserDetails doAuthenticate(String rawToken, String headerJson, String payloadJson) throws AuthenticationException {
        return JsonUtils.parseJson(payloadJson, UserAttributes.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Data
    public static class UserAttributes implements UserDetails {

        private static final String NO_PASS = UserAttributes.class.getName() + "#NO_PASS";

        private String userId;
        private String username;
        private String[] roles;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            var roles = this.getRoles();
            if (roles == null) {
                return List.of();
            }

            return Arrays.stream(roles)
                    .map(SimpleGrantedAuthority::new)
                    .toList();
        }

        @Override
        public String getPassword() {
            return NO_PASS;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }

}
