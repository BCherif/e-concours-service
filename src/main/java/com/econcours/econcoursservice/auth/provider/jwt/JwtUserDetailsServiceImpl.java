package com.econcours.econcoursservice.auth.provider.jwt;

import com.econcours.econcoursservice.auth.entity.User;
import com.econcours.econcoursservice.auth.exception.AuthException;
import com.econcours.econcoursservice.auth.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;

@Slf4j
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public JwtUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (Objects.isNull(user)) throw new UsernameNotFoundException(username);
        if (!ofNullable(user.isActive()).orElse(false)) throw new AuthException("Ce compte utilisateur a été désactivé");

        return org.springframework.security.core.userdetails
                .User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .disabled(!ofNullable(user.isActive()).orElse(false))
                .authorities(emptyList())
                .build();
    }
}
