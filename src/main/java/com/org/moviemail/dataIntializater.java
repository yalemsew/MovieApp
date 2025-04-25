package com.org.moviemail;

import com.org.moviemail.entity.*;
import com.org.moviemail.repository.CustomerRespository;
import com.org.moviemail.repository.DVDRepository;
import com.org.moviemail.repository.SubscriptionRepository;
import com.org.moviemail.repository.WatchlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class DataInitializer implements CommandLineRunner {

    private final CustomerRespository customerRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final DVDRepository dvdRepository;
    private final WatchlistRepository watchlistRepository;

    @Override
    public void run(String... args) {
        // --- Customers ---
        Customer alice = customerRepository.save(new Customer(null, "Alice", "alice@example.com", null));
        Customer bob = customerRepository.save(new Customer(null, "Bob", "bob@example.com", null));
        Customer charlie = customerRepository.save(new Customer(null, "Charlie", "charlie@example.com", null));
        Customer dana = customerRepository.save(new Customer(null, "Dana", "dana@example.com", null));

        // --- Subscriptions ---
        subscriptionRepository.saveAll(List.of(
                new Subscription(null, "Gold", 3, 5, 13.00, alice, true),
                new Subscription(null, "Silver", 2, 4, 11.00, bob, true),
                new Subscription(null, "Platina", 4, 6, 15.00, charlie, true),
                new Subscription(null, "Basic", 1, 2, 7.00, dana, true)
        ));

        // --- DVDs ---
        DVD dvd1 = DVD.builder().scanCode("DVD001").title("Inception").genre("Sci-Fi").releaseYear(2010).status(DVDStatus.AVAILABLE).rentalCount(3).build();
        DVD dvd2 = DVD.builder().scanCode("DVD002").title("The Matrix").genre("Action").releaseYear(1999).status(DVDStatus.RETURNED).rentalCount(5).build();
        DVD dvd3 = DVD.builder().scanCode("DVD003").title("Interstellar").genre("Sci-Fi").releaseYear(2014).status(DVDStatus.RENTED).rentalCount(2).build();
        DVD dvd4 = DVD.builder().scanCode("DVD004").title("Avengers: Endgame").genre("Superhero").releaseYear(2019).status(DVDStatus.AVAILABLE).rentalCount(1).build();
        DVD dvd5 = DVD.builder().scanCode("DVD005").title("Titanic").genre("Romance").releaseYear(1997).status(DVDStatus.RENTED).rentalCount(8).build();
        DVD dvd6 = DVD.builder().scanCode("DVD006").title("The Godfather").genre("Crime").releaseYear(1972).status(DVDStatus.AVAILABLE).rentalCount(0).build();
        DVD dvd7 = DVD.builder().scanCode("DVD007").title("Shrek").genre("Animation").releaseYear(2001).status(DVDStatus.RETURNED).rentalCount(6).build();
        DVD dvd8 = DVD.builder().scanCode("DVD008").title("Parasite").genre("Thriller").releaseYear(2019).status(DVDStatus.AVAILABLE).rentalCount(4).build();

        dvdRepository.saveAll(List.of(dvd1, dvd2, dvd3, dvd4, dvd5, dvd6, dvd7, dvd8));

        // --- Watchlist Entries ---
        watchlistRepository.saveAll(List.of(
                // Alice
                WatchListEntry.builder().customer(alice).dvd(dvd1).priority(1).build(),
                WatchListEntry.builder().customer(alice).dvd(dvd4).priority(2).build(),

                // Bob
                WatchListEntry.builder().customer(bob).dvd(dvd2).priority(1).build(),
                WatchListEntry.builder().customer(bob).dvd(dvd5).priority(2).build(),

                // Charlie
                WatchListEntry.builder().customer(charlie).dvd(dvd3).priority(1).build(),
                WatchListEntry.builder().customer(charlie).dvd(dvd6).priority(2).build(),
                WatchListEntry.builder().customer(charlie).dvd(dvd7).priority(3).build(),

                // Dana
                WatchListEntry.builder().customer(dana).dvd(dvd8).priority(1).build()
        ));

        System.out.println("✅ Extended sample data initialized successfully.");
    }
}

