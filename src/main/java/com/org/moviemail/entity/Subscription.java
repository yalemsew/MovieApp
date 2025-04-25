package com.org.moviemail.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name", "customer_id"})
        }
)
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int dvdsAtHome;
    private int dvdsPerMonth;
    private double pricePerMonth;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private boolean active;
}

