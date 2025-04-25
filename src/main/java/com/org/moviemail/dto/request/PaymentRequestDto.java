package com.org.moviemail.dto.request;

import com.org.moviemail.entity.PaymentMethod;
import jakarta.validation.constraints.NotNull;

public record PaymentRequestDto(
        @NotNull
        Long subscriptionId,
        @NotNull
        PaymentMethod method,
        String cardNumber,
        String bankAccountNumber
) {}

