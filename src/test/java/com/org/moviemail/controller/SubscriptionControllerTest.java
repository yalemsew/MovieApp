package com.org.moviemail.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.moviemail.dto.request.SubscriptionRequestDto;
import com.org.moviemail.dto.response.SubscriptionResponseDto;
import com.org.moviemail.service.SubscriptionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class SubscriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SubscriptionService subscriptionService;

    private final SubscriptionRequestDto requestDto =
            new SubscriptionRequestDto("Gold", 3, 5, 13.0, 1L);

    private final SubscriptionResponseDto responseDto =
            new SubscriptionResponseDto("Gold", 3, 5, 13.0, 1L, true);

    @Test
    @DisplayName("POST /subscriptions - Should create a new subscription")
    void shouldCreateSubscription() throws Exception {
        Mockito.when(subscriptionService.createSubscription(requestDto)).thenReturn(responseDto);

        mockMvc.perform(post("/api/v1/subscriptions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Gold"))
                .andExpect(jsonPath("$.dvdsAtHome").value(3))
                .andExpect(jsonPath("$.dvdsPerMonth").value(5))
                .andExpect(jsonPath("$.pricePerMonth").value(13.0))
                .andExpect(jsonPath("$.customerId").value(1L))
                .andExpect(jsonPath("$.active").value(true));
    }
}
