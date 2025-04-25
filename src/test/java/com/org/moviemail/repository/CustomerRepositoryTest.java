package com.org.moviemail.repository;

import com.org.moviemail.entity.Customer;
import com.org.moviemail.entity.Subscription;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {

    @Autowired
    private CustomerRespository customerRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = Customer.builder()
                .name("Alice")
                .email("alice@example.com")
                .build();

        Subscription subscription = Subscription.builder()
                .name("Gold")
                .dvdsAtHome(3)
                .dvdsPerMonth(5)
                .pricePerMonth(13.00)
                .active(true)
                .customer(customer)
                .build();

        customer.setSubscriptions(List.of(subscription));
    }

    @Test
    @DisplayName("Save and retrieve customer")
    void givenValidCustomer_whenSave_thenFindByEmail() {
        Customer savedCustomer = customerRepository.saveAndFlush(customer);

        Optional<Customer> found = customerRepository.findByEmail(savedCustomer.getEmail());

        assertTrue(found.isPresent());
        assertEquals("Alice", found.get().getName());
        assertEquals("alice@example.com", found.get().getEmail());
    }

    @Test
    @DisplayName("Duplicate email should not be allowed")
    void givenDuplicateEmail_whenSave_thenThrowException() {
        customerRepository.saveAndFlush(customer);

        Customer duplicate = Customer.builder()
                .name("Alice2")
                .email("alice@example.com") // same email
                .build();

        assertThrows(DataIntegrityViolationException.class, () -> customerRepository.saveAndFlush(duplicate));
    }

    @Test
    @DisplayName("Delete existing customer by email")
    void givenExistingCustomer_whenDelete_thenRemoved() {
        Customer savedCustomer = customerRepository.saveAndFlush(customer);

        customerRepository.deleteByEmail(savedCustomer.getEmail());
        testEntityManager.flush();

        Optional<Customer> found = customerRepository.findByEmail(savedCustomer.getEmail());
        assertFalse(found.isPresent());
    }
}

