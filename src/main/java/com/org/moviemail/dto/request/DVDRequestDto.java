package com.org.moviemail.dto.request;

import jakarta.validation.constraints.*;

public record DVDRequestDto(
        @NotBlank
        String scanCode,
        @NotBlank
        String title,
        String genre,
        @Min(1900)
        int releaseYear
) {}

