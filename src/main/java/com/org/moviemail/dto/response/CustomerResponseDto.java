package com.org.moviemail.dto.response;

import java.util.List;


public record CustomerResponseDto(
        Long id,
        String name,
        String email,
        List<Long> subscriptionIds
) {}

