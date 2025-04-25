package com.org.moviemail.dto.request;

import jakarta.validation.constraints.NotNull;

public record WatchlistRequestDto(
        @NotNull
        Long customerId,
        @NotNull
        Long dvdId,
        int priority
) {}

