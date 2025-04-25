package com.org.moviemail.service;


import com.org.moviemail.dto.request.SubscriptionRequestDto;
import com.org.moviemail.dto.response.SubscriptionResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubscriptionService {
    SubscriptionResponseDto createSubscription(SubscriptionRequestDto dto);
    List<SubscriptionResponseDto> getAllSubscriptions();
    SubscriptionResponseDto getSubscriptionById(Long id);
    void deleteSubscription(Long id);
}

