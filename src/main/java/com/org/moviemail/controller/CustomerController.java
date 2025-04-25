package com.org.moviemail.controller;

import com.org.moviemail.dto.request.CustomerRequestDto;
import com.org.moviemail.dto.response.CustomerResponseDto;
import com.org.moviemail.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(
            @RequestBody @Valid CustomerRequestDto dto) {
        CustomerResponseDto customerResponseDto = customerService.createCustomer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerResponseDto);
    }

}
