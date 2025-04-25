package com.org.moviemail.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record CustomerRequestDto(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Email is required") @Email(message = "Invalid email")
        String email
) {}
