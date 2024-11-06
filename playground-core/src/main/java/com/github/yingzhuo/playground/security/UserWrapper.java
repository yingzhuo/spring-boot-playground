package com.github.yingzhuo.playground.security;

import com.github.yingzhuo.playground.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

record UserWrapper(User user) implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var authoritiesString = user.getAuthorities();
        return StringUtils.hasText(authoritiesString) ?
                AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesString) :
                Set.of();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isEnabled() {
        return Optional.ofNullable(user.getEnabled())
                .orElse(Boolean.TRUE);
    }

    @Override
    public boolean isAccountNonExpired() {
        var expiredTime = user.getExpiredTime();
        if (expiredTime == null) {
            return true;
        }
        return LocalDateTime.now().isBefore(expiredTime);
    }

    @Override
    public boolean isAccountNonLocked() {
        return !Optional.ofNullable(user.getAccountLocked())
                .orElse(Boolean.FALSE);
    }

}
