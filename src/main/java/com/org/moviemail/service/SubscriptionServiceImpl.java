package com.org.moviemail.service;

import com.org.moviemail.dto.request.SubscriptionRequestDto;
import com.org.moviemail.dto.response.SubscriptionResponseDto;
import com.org.moviemail.entity.Customer;
import com.org.moviemail.entity.Subscription;
import com.org.moviemail.exception.internal.CustomerNotFoundException;
import com.org.moviemail.exception.internal.SubscriptionNotFoundException;
import com.org.moviemail.mapper.SubscriptionMapper;
import com.org.moviemail.repository.CustomerRespository;
import com.org.moviemail.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final CustomerRespository customerRepository;
    private final SubscriptionMapper subscriptionMapper;

    @Override
    public SubscriptionResponseDto createSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        Customer customer = customerRepository.findById(subscriptionRequestDto.customerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        Subscription subscription = subscriptionMapper.subscriptionRequestDTOToSubscription(subscriptionRequestDto);
        subscription.setCustomer(customer);
        subscription.setActive(true);

        return subscriptionMapper.subscriptionToSubscriptionResponseDTO(subscriptionRepository.save(subscription));
    }

    @Override
    public List<SubscriptionResponseDto> getAllSubscriptions() {
        return subscriptionMapper.subscriptionToSubscriptionResponseDTOList(subscriptionRepository.findAll());
    }

    @Override
    public SubscriptionResponseDto getSubscriptionById(Long id) {
        return subscriptionMapper.subscriptionToSubscriptionResponseDTO(
                subscriptionRepository.findById(id)
                        .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found"))
        );
    }

    @Override
    public void deleteSubscription(Long id) {
        if (!subscriptionRepository.existsById(id)) {
            throw new SubscriptionNotFoundException("Subscription not found");
        }
        subscriptionRepository.deleteById(id);
    }
}
