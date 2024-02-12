package com.example.reviewappv2.services.impl;

import com.example.reviewappv2.dtos.request.LoginRequest;
import com.example.reviewappv2.dtos.request.RegisterRequest;
import com.example.reviewappv2.dtos.response.AuthResponse;
import com.example.reviewappv2.inums.Role;
import com.example.reviewappv2.models.User;
import com.example.reviewappv2.repositories.UserRepository;
import com.example.reviewappv2.security.JwtService;
import com.example.reviewappv2.security.authenticators.UserAuthenticator;
import com.example.reviewappv2.services.UserAuthService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final ModelMapper modelMapper;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {

        User user = userRepository.findUserByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Email or password incorrect"));

        UserAuthenticator userAuthenticator = new UserAuthenticator(user);
        String jwtToken = jwtService.generateToken(userAuthenticator);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(jwtToken);
        return authResponse;
    }

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        User user = modelMapper.map(registerRequest, User.class);
        user.setRole(Role.ADMINISTRATOR);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userRepository.save(user);
        UserAuthenticator userAuthenticator = new UserAuthenticator(user);
        String jwtToken = jwtService.generateToken(userAuthenticator);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(jwtToken);
        return authResponse;
    }
}
