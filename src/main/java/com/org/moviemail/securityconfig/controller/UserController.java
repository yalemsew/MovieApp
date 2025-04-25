package com.org.moviemail.securityconfig.controller;

import com.org.moviemail.securityconfig.service.UserServiceImpl;
import com.org.moviemail.securityconfig.dto.UserProfileResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponseDto> getProfile() {
        return ResponseEntity.ok(userService.getCurrentUserProfile());
    }
}

