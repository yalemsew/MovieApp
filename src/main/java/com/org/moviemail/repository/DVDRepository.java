package com.org.moviemail.repository;

import com.org.moviemail.dto.DVDPopularityDto;
import com.org.moviemail.entity.DVD;
import com.org.moviemail.entity.DVDStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DVDRepository extends JpaRepository<DVD, Long> {

    Optional<DVD> findByScanCode(String scanCode);

    boolean existsByScanCode(String scanCode);

    @Query("SELECT w.dvd FROM WatchListEntry w WHERE w.customer.id = :customerId AND w.dvd.status = :status")
    List<DVD> findDVDsByCustomerIdAndStatus(@Param("customerId") Long customerId,
                                            @Param("status") DVDStatus status);

    @Query("SELECT new com.org.moviemail.dto.DVDPopularityDto(d.title, d.rentalCount) FROM DVD d ORDER BY d.rentalCount DESC LIMIT :limit")
    List<DVDPopularityDto> findTopPopularDVDs(@Param("limit") int limit);

    @Query("SELECT new com.org.moviemail.dto.DVDPopularityDto(d.title, d.rentalCount) FROM DVD d ORDER BY d.rentalCount ASC LIMIT :limit")
    List<DVDPopularityDto> findLeastPopularDVDs(@Param("limit") int limit);
}

