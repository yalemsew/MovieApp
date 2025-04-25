package com.org.moviemail.dto.response;


import com.org.moviemail.entity.DVDStatus;

public record DVDResponseDto(
        String scanCode,
        String title,
        String genre,
        int releaseYear,
        DVDStatus status
) {}

