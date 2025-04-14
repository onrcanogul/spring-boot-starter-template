package com.example.demo.starter.controller.auth;

import com.example.demo.starter.controller.base.BaseController;
import com.example.demo.starter.dto.user.LoginDto;
import com.example.demo.starter.dto.user.UserDto;
import com.example.demo.starter.service.auth.AuthService;
import com.example.demo.starter.util.response.NoContent;
import com.example.demo.starter.util.response.ServiceResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController extends BaseController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "Basic Register", description = "Basic Register")
    public ResponseEntity<ServiceResponse<NoContent>> register(@RequestBody UserDto model) {
        return controllerResponse(authService.register(model));
    }

    @PostMapping("/login")
    @Operation(summary = "Basic Login with username and password", description = "Basic Login with username and password")
    public ResponseEntity<ServiceResponse<String>> login(@RequestBody LoginDto model) {
        return controllerResponse(authService.login(model));
    }
}
