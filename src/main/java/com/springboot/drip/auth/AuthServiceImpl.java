package com.springboot.drip.auth;

import com.springboot.drip.config.JwtUtils;
import com.springboot.drip.dto.JwtAuthResponse;
import com.springboot.drip.dto.SignInRequest;
import com.springboot.drip.dto.SignUpRequest;
import com.springboot.drip.enums.Role;
import com.springboot.drip.model.User;
import com.springboot.drip.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtils jwtService;
    @Autowired
    private AuthenticationManager authenticationManagerBean;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public JwtAuthResponse signup(SignUpRequest request) {
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CUSTOMER).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthResponse login(SignInRequest request) {
        authenticationManagerBean.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        return JwtAuthResponse.builder().token(jwt).build();
    }
}