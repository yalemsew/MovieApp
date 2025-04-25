package com.org.moviemail.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.moviemail.dto.request.CustomerRequestDto;
import com.org.moviemail.dto.response.CustomerResponseDto;
import com.org.moviemail.service.CustomerService;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class CustomerControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @MockBean private CustomerService customerService;

    private final CustomerRequestDto requestDTO = new CustomerRequestDto("Alice", "alice@example.com");
    private final CustomerResponseDto responseDTO = new CustomerResponseDto(1L, "Alice", "alice@example.com", List.of());

    @Test
    @DisplayName("POST /customers creates new customer")
    void shouldCreateCustomer() throws Exception {
        Mockito.when(customerService.createCustomer(requestDTO)).thenReturn(responseDTO);

        mockMvc.perform(post("/api/v1/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Alice"))
                .andExpect(jsonPath("$.email").value("alice@example.com"));
    }
}

