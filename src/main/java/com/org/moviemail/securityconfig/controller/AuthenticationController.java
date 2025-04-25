package com.org.moviemail.securityconfig.controller;

import com.org.moviemail.securityconfig.dto.AuthenticationRequest;
import com.org.moviemail.securityconfig.dto.AuthenticationResponse;
import com.org.moviemail.securityconfig.dto.RegisterRequest;
import com.org.moviemail.securityconfig.dto.RegisterResponse;
import com.org.moviemail.securityconfig.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.register(request));
    }
}

