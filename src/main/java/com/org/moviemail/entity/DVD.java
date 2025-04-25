package com.org.moviemail.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DVD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String scanCode;

    @Column(nullable = false)
    private String title;

    private String genre;

    private int releaseYear;

    @Enumerated(EnumType.STRING)
    private DVDStatus status;

    @Column(nullable = false)
    private int rentalCount = 0;
}

