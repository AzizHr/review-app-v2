package com.example.reviewappv2.services.impl;

import com.example.reviewappv2.repositories.UserRepository;
import com.example.reviewappv2.security.authenticators.UserAuthenticator;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        var user = userRepository.findUserByEmail(username);

        return user.map(UserAuthenticator::new)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found "+username));
    }
}
