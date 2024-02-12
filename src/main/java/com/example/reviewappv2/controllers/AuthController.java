package com.example.reviewappv2.controllers;

import com.example.reviewappv2.dtos.request.LoginRequest;
import com.example.reviewappv2.dtos.request.RegisterRequest;
import com.example.reviewappv2.dtos.response.AuthResponse;
import com.example.reviewappv2.services.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final UserAuthService userAuthService;

    @RequestMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(userAuthService.login(loginRequest), HttpStatus.OK);
    }

    @RequestMapping("/register")
    public ResponseEntity<AuthResponse> login(@RequestBody RegisterRequest registerRequest) {
        return new ResponseEntity<>(userAuthService.register(registerRequest), HttpStatus.OK);
    }

}
