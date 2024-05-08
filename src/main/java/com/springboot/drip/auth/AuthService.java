package com.springboot.drip.auth;

public interface AuthService {
    JwtAuthResponse signup(SignUpRequest request);

    JwtAuthResponse login(SignInRequest request);
}
