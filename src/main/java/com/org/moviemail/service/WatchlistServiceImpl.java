package com.org.moviemail.service;

import com.org.moviemail.dto.request.WatchlistRequestDto;
import com.org.moviemail.dto.response.WatchlistResponseDto;
import com.org.moviemail.entity.Customer;
import com.org.moviemail.entity.DVD;
import com.org.moviemail.entity.WatchListEntry;
import com.org.moviemail.exception.internal.CustomerNotFoundException;
import com.org.moviemail.exception.internal.DVDNotFoundException;
import com.org.moviemail.exception.internal.WatchListEntryNotFound;
import com.org.moviemail.exception.internal.WatchListLimitReachedException;
import com.org.moviemail.mapper.WatchlistMapper;
import com.org.moviemail.repository.CustomerRespository;
import com.org.moviemail.repository.DVDRepository;
import com.org.moviemail.repository.WatchlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WatchlistServiceImpl implements WatchlistService {

    private final WatchlistRepository watchlistRepository;
    private final CustomerRespository customerRepository;
    private final DVDRepository dvdRepository;
    private final WatchlistMapper watchlistMapper;

    private static final int MAX_WATCHLIST_SIZE = 50;

    @Override
    public WatchlistResponseDto addToWatchlist(WatchlistRequestDto dto) {
        Customer customer = customerRepository.findById(dto.customerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        DVD dvd = dvdRepository.findById(dto.dvdId())
                .orElseThrow(() -> new DVDNotFoundException("DVD not found"));

        long count = watchlistRepository.countByCustomerId(customer.getId());
        if (count >= MAX_WATCHLIST_SIZE) {
            throw new WatchListLimitReachedException("Watchlist limit of 50 DVDs reached");
        }

        WatchListEntry entry = WatchListEntry.builder()
                .customer(customer)
                .dvd(dvd)
                .priority(dto.priority())
                .build();

        return watchlistMapper.toResponseDTO(watchlistRepository.save(entry));
    }

    @Override
    public List<WatchlistResponseDto> getWatchlistForCustomer(Long customerId) {
        List<WatchListEntry> entries = watchlistRepository.findByCustomerIdOrderByPriorityAsc(customerId);
        return watchlistMapper.toResponseDTOList(entries);
    }

    @Override
    public void removeFromWatchlist(Long customerId, Long dvdId) {
        WatchListEntry entry = watchlistRepository.findByCustomerIdAndDvdId(customerId, dvdId)
                .orElseThrow(() -> new WatchListEntryNotFound("Watchlist entry not found"));
        watchlistRepository.delete(entry);
    }
}

