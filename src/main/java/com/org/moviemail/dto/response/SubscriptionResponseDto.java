package com.org.moviemail.dto.response;

public record SubscriptionResponseDto(
        String name,
        int dvdsAtHome,
        int dvdsPerMonth,
        double pricePerMonth,
        Long customerId,
        boolean active
) {}

