package com.example.demo.starter.service.auth;

import com.example.demo.starter.dto.user.LoginDto;
import com.example.demo.starter.dto.user.UserDto;
import com.example.demo.starter.util.response.NoContent;
import com.example.demo.starter.util.response.ServiceResponse;

public interface AuthService {
    ServiceResponse<String> login(LoginDto request);
    ServiceResponse<NoContent> register(UserDto request);
}
