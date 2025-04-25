package com.org.moviemail.repository;

import com.org.moviemail.entity.WatchListEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface WatchlistRepository extends JpaRepository<WatchListEntry, Long> {
    long countByCustomerId(Long customerId);
    List<WatchListEntry> findByCustomerIdOrderByPriorityAsc(Long customerId);
    Optional<WatchListEntry> findByCustomerIdAndDvdId(Long customerId, Long dvdId);
}

