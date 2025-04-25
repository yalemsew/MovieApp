package com.org.moviemail.mapper;


import com.org.moviemail.dto.request.SubscriptionRequestDto;
import com.org.moviemail.dto.response.SubscriptionResponseDto;
import com.org.moviemail.entity.Subscription;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubscriptionMapper {
    Subscription subscriptionRequestDTOToSubscription(SubscriptionRequestDto dto);

    @Mapping(source = "customer.id", target = "customerId")
    SubscriptionResponseDto subscriptionToSubscriptionResponseDTO(Subscription subscription);

    List<SubscriptionResponseDto> subscriptionToSubscriptionResponseDTOList(List<Subscription> subscriptions);
}


