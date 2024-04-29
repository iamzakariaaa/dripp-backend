package com.springboot.drip.auth;

import com.springboot.drip.dto.JwtAuthResponse;
import com.springboot.drip.dto.SignInRequest;
import com.springboot.drip.dto.SignUpRequest;

public interface AuthService {
    JwtAuthResponse signup(SignUpRequest request);

    JwtAuthResponse login(SignInRequest request);
}
