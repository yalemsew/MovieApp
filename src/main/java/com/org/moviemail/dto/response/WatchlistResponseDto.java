package com.org.moviemail.dto.response;

public record WatchlistResponseDto(
        Long id,
        Long customerId,
        Long dvdId,
        String dvdTitle,
        int priority
) {}

