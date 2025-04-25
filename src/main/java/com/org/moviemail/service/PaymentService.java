package com.org.moviemail.service;

import com.org.moviemail.dto.request.PaymentRequestDto;
import com.org.moviemail.dto.response.PaymentResponseDto;

public interface PaymentService {
    PaymentResponseDto processPayment(PaymentRequestDto request);
}

