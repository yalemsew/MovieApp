package com.org.moviemail.service;

import com.org.moviemail.dto.request.CustomerRequestDto;
import com.org.moviemail.dto.response.CustomerResponseDto;
import com.org.moviemail.entity.Customer;
import com.org.moviemail.exception.internal.CustomerDuplicateException;
import com.org.moviemail.mapper.CustomerMapper;
import com.org.moviemail.repository.CustomerRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRespository customerRespository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDTO) {
        Optional<Customer> existing = customerRespository.findByEmail(customerRequestDTO.email());
        if (existing.isPresent()) {
            throw new CustomerDuplicateException("Customer already registered");
        }

        Customer customer = customerMapper.customerRequestDTOToCustomer(customerRequestDTO);
        Customer saved = customerRespository.save(customer);
        return customerMapper.customerToCustomerResponseDTO(saved);
    }
}
