package com.org.moviemail.service;

import com.org.moviemail.dto.request.CustomerRequestDto;
import com.org.moviemail.dto.response.CustomerResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    CustomerResponseDto createCustomer(CustomerRequestDto dto);

}
