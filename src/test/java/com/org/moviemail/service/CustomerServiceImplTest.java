package com.org.moviemail.service;

import com.org.moviemail.dto.request.CustomerRequestDto;
import com.org.moviemail.dto.response.CustomerResponseDto;
import com.org.moviemail.entity.Customer;
import com.org.moviemail.exception.internal.CustomerDuplicateException;
import com.org.moviemail.mapper.CustomerMapper;
import com.org.moviemail.repository.CustomerRespository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRespository customerRepository;
    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks private CustomerServiceImpl customerService;

    private CustomerRequestDto requestDTO;
    private Customer customer;
    private CustomerResponseDto responseDTO;

    @BeforeEach
    void setUp() {
        requestDTO = new CustomerRequestDto("Alice", "alice@example.com");
        customer = new Customer(1L, "Alice", "alice@example.com", new ArrayList<>());
        responseDTO = new CustomerResponseDto(1L, "Alice", "alice@example.com", new ArrayList<>());
    }

    @Test
    @DisplayName("Create customer when email does not exist")
    void shouldCreateCustomer() {
        when(customerRepository.findByEmail(requestDTO.email())).thenReturn(Optional.empty());
        when(customerMapper.customerRequestDTOToCustomer(requestDTO)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.customerToCustomerResponseDTO(customer)).thenReturn(responseDTO);

        CustomerResponseDto result = customerService.createCustomer(requestDTO);

        assertThat(result).isEqualTo(responseDTO);
        verify(customerRepository).save(customer);
    }

    @Test
    @DisplayName("Throw exception if customer email already exists")
    void shouldThrowIfCustomerExists() {
        when(customerRepository.findByEmail(requestDTO.email())).thenReturn(Optional.of(customer));
        assertThrows(CustomerDuplicateException.class, () -> customerService.createCustomer(requestDTO));
        verify(customerRepository, never()).save(any());
    }
}
