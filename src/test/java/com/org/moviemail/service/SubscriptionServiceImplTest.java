package com.org.moviemail.service;

import com.org.moviemail.dto.request.SubscriptionRequestDto;
import com.org.moviemail.dto.response.SubscriptionResponseDto;
import com.org.moviemail.entity.Customer;
import com.org.moviemail.entity.Subscription;
import com.org.moviemail.exception.internal.CustomerNotFoundException;
import com.org.moviemail.mapper.SubscriptionMapper;
import com.org.moviemail.repository.CustomerRespository;
import com.org.moviemail.repository.SubscriptionRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SubscriptionServiceImplTest {

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @Mock
    private CustomerRespository customerRepository;

    @Mock
    private SubscriptionMapper subscriptionMapper;

    @InjectMocks
    private SubscriptionServiceImpl subscriptionService;

    private Customer customer;
    private Subscription subscription;
    private SubscriptionRequestDto requestDto;
    private SubscriptionResponseDto responseDto;

    @BeforeEach
    void setUp() {
        customer = Customer.builder()
                .id(1L)
                .name("Alice")
                .email("alice@mail.com")
                .build();

        requestDto = new SubscriptionRequestDto("Gold", 3, 5, 13.0, 1L);

        subscription = Subscription.builder()
                .name("Gold")
                .dvdsAtHome(3)
                .dvdsPerMonth(5)
                .pricePerMonth(13.0)
                .customer(customer)
                .active(true)
                .build();

        responseDto = new SubscriptionResponseDto("Gold", 3, 5, 13.0, 1L, true);
    }

    @Test
    @DisplayName("Create subscription with existing customer")
    void createSubscription_withValidCustomer_returnsResponse() {
        when(customerRepository.findById(eq(1L))).thenReturn(Optional.of(customer));
        when(subscriptionMapper.subscriptionRequestDTOToSubscription(requestDto)).thenReturn(subscription);
        when(subscriptionRepository.save(subscription)).thenReturn(subscription);
        when(subscriptionMapper.subscriptionToSubscriptionResponseDTO(subscription)).thenReturn(responseDto);

        SubscriptionResponseDto result = subscriptionService.createSubscription(requestDto);

        assertThat(result).isEqualTo(responseDto);
        verify(customerRepository).findById(1L);
        verify(subscriptionRepository).save(subscription);
    }

    @Test
    @DisplayName("Create subscription with invalid customer should throw exception")
    void createSubscription_withInvalidCustomer_throwsException() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class,
                () -> subscriptionService.createSubscription(requestDto));

        verify(subscriptionRepository, never()).save(any());
    }
}








