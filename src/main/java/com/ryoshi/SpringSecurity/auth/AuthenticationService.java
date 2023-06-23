package com.ryoshi.SpringSecurity.auth;

import com.ryoshi.SpringSecurity.config.JwtService;
import com.ryoshi.SpringSecurity.user.Role;
import com.ryoshi.SpringSecurity.user.User;
import com.ryoshi.SpringSecurity.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationRequest register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token();//TODO 1:52:00
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

    }

}
