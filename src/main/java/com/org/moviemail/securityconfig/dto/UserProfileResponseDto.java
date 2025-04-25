package com.org.moviemail.securityconfig.dto;

public record UserProfileResponseDto(
        Long id,
        String username,
        String role
) {}

