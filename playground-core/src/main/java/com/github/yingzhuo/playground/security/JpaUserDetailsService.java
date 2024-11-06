package com.github.yingzhuo.playground.security;

import com.github.yingzhuo.playground.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserDao dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = dao.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("username '%s' not found", username));
        }

        return new UserWrapper(user);
    }

}
