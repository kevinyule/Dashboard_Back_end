package com.dashboard_gk.dashboard_gk.controller;

import com.dashboard_gk.dashboard_gk.dto.auth.AuthenticationRequestDTO;
import com.dashboard_gk.dashboard_gk.dto.auth.AuthenticationResponse;
import com.dashboard_gk.dashboard_gk.dto.auth.RegisterRequestDTO;
import com.dashboard_gk.dashboard_gk.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody RegisterRequestDTO request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @Valid @RequestBody AuthenticationRequestDTO request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}