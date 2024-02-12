package com.example.reviewappv2.services;

import com.example.reviewappv2.dtos.request.LoginRequest;
import com.example.reviewappv2.dtos.request.RegisterRequest;
import com.example.reviewappv2.dtos.response.AuthResponse;

public interface UserAuthService {

    AuthResponse login(LoginRequest loginRequest);
    AuthResponse register(RegisterRequest registerRequest);

}
