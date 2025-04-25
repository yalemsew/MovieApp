package com.org.moviemail.service;

import com.org.moviemail.dto.request.PaymentRequestDto;
import com.org.moviemail.dto.response.PaymentResponseDto;
import com.org.moviemail.entity.*;
import com.org.moviemail.exception.internal.SubscriptionNotFoundException;
import com.org.moviemail.external.EpayClient;
import com.org.moviemail.repository.SubscriptionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final SubscriptionRepository subscriptionRepository;
    private final EpayClient epayClient;

    @Override
    public PaymentResponseDto processPayment(PaymentRequestDto request) {
        Subscription subscription = subscriptionRepository.findById(request.subscriptionId())
                .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found"));

        if (request.method() == PaymentMethod.CREDIT_CARD) {
            if (!epayClient.isAvailable()) {
                return new PaymentResponseDto(false, "Epay.com is currently unavailable");
            }

            if (!epayClient.validateCard(request.cardNumber())) {
                return new PaymentResponseDto(false, "Invalid credit card number");
            }

            if (!epayClient.processPayment(request.cardNumber())) {
                return new PaymentResponseDto(false, "Credit card payment failed");
            }

            subscription.setActive(true);
            subscriptionRepository.save(subscription);
            return new PaymentResponseDto(true, "Credit card payment successful, subscription activated");

        } else if (request.method() == PaymentMethod.BANK_TRANSFER) {
            // Here we only store the bank account and wait for manual confirmation (simulate)
            subscription.setActive(true); // Simulate instant approval for now
            subscriptionRepository.save(subscription);
            return new PaymentResponseDto(true, "Bank transfer recorded, subscription activated manually");
        }

        return new PaymentResponseDto(false, "Unsupported payment method");
    }
}

