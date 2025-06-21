package com.example.demo.starter.service.auth.impl;

import com.example.demo.starter.dto.user.LoginDto;
import com.example.demo.starter.dto.user.UserDto;
import com.example.demo.starter.entity.refreshToken.RefreshToken;
import com.example.demo.starter.entity.user.User;
import com.example.demo.starter.exception.types.BadRequestException;
import com.example.demo.starter.exception.types.NotFoundException;
import com.example.demo.starter.repository.RefreshTokenRepository;
import com.example.demo.starter.repository.UserRepository;
import com.example.demo.starter.service.auth.AuthService;
import com.example.demo.starter.service.jwt.JwtService;
import com.example.demo.starter.service.user.CustomUserDetailsService;
import com.example.demo.starter.util.response.AuthResponse;
import com.example.demo.starter.util.response.NoContent;
import com.example.demo.starter.util.response.ServiceResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final CustomUserDetailsService customUserDetailsService;

    public AuthServiceImpl(AuthenticationManager authManager, PasswordEncoder passwordEncoder, UserRepository userRepository, JwtService jwtService, RefreshTokenRepository refreshTokenRepository, CustomUserDetailsService customUserDetailsService) {
        this.authManager = authManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.refreshTokenRepository = refreshTokenRepository;
        this.customUserDetailsService = customUserDetailsService;
    }

    public ServiceResponse<AuthResponse> login(LoginDto request) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails user = (UserDetails) auth.getPrincipal();
        String token = jwtService.generateToken(user);

        String refreshToken = UUID.randomUUID().toString();

        RefreshToken refreshTokenEntity = new RefreshToken();
        refreshTokenEntity.setUser(userRepository.findByUsername(request.getUsername()).orElseThrow());
        refreshTokenEntity.setToken(refreshToken);
        refreshTokenEntity.setExpiration(LocalDateTime.now().plusDays(7));

        refreshTokenRepository.save(refreshTokenEntity);

        AuthResponse authResponse = new AuthResponse(token, refreshTokenEntity.getToken());

        return ServiceResponse.success(authResponse, 200);
    }

    public ServiceResponse<AuthResponse> loginWithRefreshToken(String refreshToken) {
        RefreshToken refreshTokenEntity = refreshTokenRepository.findByToken(refreshToken).orElseThrow(
                () -> new NotFoundException("Token not found"));

        if (refreshTokenEntity.getExpiration().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("RefreshTokenExpired");
        }

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(refreshTokenEntity.getUser().getUsername());
        String accessToken = jwtService.generateToken(userDetails);

        refreshTokenEntity.setToken(UUID.randomUUID().toString());
        refreshTokenEntity.setExpiration(LocalDateTime.now().plusDays(7));

        RefreshToken updatedRefreshToken = refreshTokenRepository.save(refreshTokenEntity);

        AuthResponse authResponse = new AuthResponse(accessToken, updatedRefreshToken.getToken());
        return ServiceResponse.success(authResponse, 200);
    }

    public ServiceResponse<NoContent> register(UserDto request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new BadRequestException("Username already taken.");
        }

        User user = new User();
        user.setRoles(List.of("USER"));
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return ServiceResponse.success(201);
    }
}
