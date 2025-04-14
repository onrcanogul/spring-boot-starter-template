package com.example.demo.starter.service.auth.impl;

import com.example.demo.starter.dto.user.LoginDto;
import com.example.demo.starter.dto.user.UserDto;
import com.example.demo.starter.entity.user.User;
import com.example.demo.starter.exception.types.BadRequestException;
import com.example.demo.starter.repository.user.UserRepository;
import com.example.demo.starter.service.auth.AuthService;
import com.example.demo.starter.service.jwt.JwtService;
import com.example.demo.starter.util.response.NoContent;
import com.example.demo.starter.util.response.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authManager;

    private final JwtService jwtService;

    public AuthServiceImpl(AuthenticationManager authManager, PasswordEncoder passwordEncoder, UserRepository userRepository, JwtService jwtService) {
        this.authManager = authManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public ServiceResponse<String> login(LoginDto request) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails user = (UserDetails) auth.getPrincipal();
        String token = jwtService.generateToken(user);
        return ServiceResponse.success(token, 200);
    }

    public ServiceResponse<NoContent> register(UserDto request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new BadRequestException("Username already taken.");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return ServiceResponse.success(201);
    }
}
