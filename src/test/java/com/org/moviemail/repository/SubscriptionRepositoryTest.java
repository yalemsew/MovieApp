package com.org.moviemail.repository;

import com.org.moviemail.entity.Customer;
import com.org.moviemail.entity.Subscription;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // If using real DB (like MySQL/PostgreSQL)
class SubscriptionRepositoryTest {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private EntityManager entityManager;

    private Customer customer;
    private Subscription subscription;

    @BeforeEach
    void setUp() {
        customer = Customer.builder()
                .name("Alice")
                .email("alice@sub.com")
                .build();

        entityManager.persist(customer); // Save customer
        entityManager.flush();

        subscription = Subscription.builder()
                .name("Gold")
                .dvdsAtHome(3)
                .dvdsPerMonth(5)
                .pricePerMonth(13.00)
                .active(true)
                .customer(customer)
                .build();
    }

    @Test
    @DisplayName("Save and retrieve subscription")
    void whenSave_thenRetrieveByCustomerId() {
        subscriptionRepository.saveAndFlush(subscription);

        List<Subscription> found = subscriptionRepository.findByCustomerId(customer.getId());

        assertFalse(found.isEmpty());
        assertEquals("Gold", found.get(0).getName());
        assertEquals(13.00, found.get(0).getPricePerMonth());
    }

    @Test
    @DisplayName("Duplicate subscription throws exception if unique constraint exists")
    void whenDuplicateSubscription_thenThrowException() {
        subscriptionRepository.saveAndFlush(subscription);

        Subscription duplicate = Subscription.builder()
                .name("Gold")
                .dvdsAtHome(2)
                .dvdsPerMonth(3)
                .pricePerMonth(11.00)
                .active(true)
                .customer(customer)
                .build();

        assertThrows(DataIntegrityViolationException.class, () -> {
            subscriptionRepository.saveAndFlush(duplicate);
        });
    }

    @Test
    @DisplayName("Delete subscription by ID")
    void whenDeleteById_thenRemove() {
        Subscription saved = subscriptionRepository.saveAndFlush(subscription);
        subscriptionRepository.deleteById(saved.getId());

        entityManager.flush();

        List<Subscription> remaining = subscriptionRepository.findByCustomerId(customer.getId());
        assertTrue(remaining.isEmpty());
    }
}
