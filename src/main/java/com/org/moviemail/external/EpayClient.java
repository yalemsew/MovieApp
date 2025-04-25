package com.org.moviemail.external;
import org.springframework.stereotype.Component;

@Component
public class EpayClient {

    public boolean isAvailable() {
        return true;
    }

    public boolean validateCard(String cardNumber) {
        return cardNumber != null && cardNumber.length() == 16;
    }

    public boolean processPayment(String cardNumber) {
        return cardNumber.startsWith("4"); // simulate: Visa only
    }
}

