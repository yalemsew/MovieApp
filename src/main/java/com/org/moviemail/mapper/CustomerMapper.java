package com.org.moviemail.mapper;

import com.org.moviemail.dto.request.CustomerRequestDto;
import com.org.moviemail.dto.response.CustomerResponseDto;
import com.org.moviemail.entity.Customer;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {
    Customer customerRequestDTOToCustomer(CustomerRequestDto dto);

    CustomerResponseDto customerToCustomerResponseDTO(Customer customer);

    List<CustomerResponseDto> customerToCustomerResponseDTOList(List<Customer> customers);
}

