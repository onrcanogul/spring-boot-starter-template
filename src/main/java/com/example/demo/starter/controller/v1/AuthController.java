package com.example.demo.starter.controller.v1;

import com.example.demo.starter.controller.base.BaseController;
import com.example.demo.starter.dto.user.LoginDto;
import com.example.demo.starter.dto.user.RegisterDto;
import com.example.demo.starter.service.auth.AuthService;
import com.example.demo.starter.util.response.AuthResponse;
import com.example.demo.starter.util.response.ServiceResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController extends BaseController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @Operation(summary = "Basic Login with username and password", description = "Basic Login with username and password")
    public ResponseEntity<ServiceResponse<AuthResponse>> login(@RequestBody LoginDto model) {
        return controllerResponse(authService.login(model));
    }

    @PostMapping("/refresh-token-login/{refreshToken}")
    @Operation(summary = "Basic Login With Refresh Token", description = "Basic Login With Refresh Token")
    public ResponseEntity<ServiceResponse<AuthResponse>> login(@PathVariable String refreshToken) {
        return controllerResponse(authService.loginWithRefreshToken(refreshToken));
    }

    @PostMapping("/register")
    @Operation(summary = "Basic Register", description = "Basic Register")
    public ResponseEntity<ServiceResponse<String>> register(@RequestBody RegisterDto model) {
        return controllerResponse(authService.register(model));
    }


}
