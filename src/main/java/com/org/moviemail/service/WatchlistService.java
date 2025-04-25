package com.org.moviemail.service;

import com.org.moviemail.dto.request.WatchlistRequestDto;
import com.org.moviemail.dto.response.WatchlistResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WatchlistService {
    WatchlistResponseDto addToWatchlist(WatchlistRequestDto dto);
    List<WatchlistResponseDto> getWatchlistForCustomer(Long customerId);
    void removeFromWatchlist(Long customerId, Long dvdId);
}
