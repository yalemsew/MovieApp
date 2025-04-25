package com.org.moviemail.dto.request;

import jakarta.validation.constraints.*;

public record SubscriptionRequestDto(
        @NotBlank
        String name,
        @Min(1)
        int dvdsAtHome,
        @Min(1)
        int dvdsPerMonth,
        @DecimalMin(value = "0.0", inclusive = false)
        double pricePerMonth,
        @NotNull
        Long customerId
) {}

