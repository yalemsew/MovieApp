package com.org.moviemail.securityconfig.service;

import com.org.moviemail.securityconfig.dto.AuthenticationRequest;
import com.org.moviemail.securityconfig.dto.AuthenticationResponse;
import com.org.moviemail.securityconfig.dto.RegisterRequest;
import com.org.moviemail.securityconfig.dto.RegisterResponse;
import com.org.moviemail.securityconfig.entity.Role;
import com.org.moviemail.securityconfig.entity.User;
import com.org.moviemail.securityconfig.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserServiceImpl userService;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        UserDetails user = userService.loadUserByUsername(request.username());
        String jwt = jwtService.generateToken(user);

        return new AuthenticationResponse(jwt);
    }

    public RegisterResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.valueOf(request.role().toUpperCase()))
                .build();

        userRepository.save(user);

        return new RegisterResponse("User registered successfully");
    }
}
