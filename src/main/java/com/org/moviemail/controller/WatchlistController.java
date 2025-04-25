package com.org.moviemail.controller;

import com.org.moviemail.dto.request.WatchlistRequestDto;
import com.org.moviemail.dto.response.WatchlistResponseDto;
import com.org.moviemail.service.WatchlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/watchlist")
@RequiredArgsConstructor
public class WatchlistController {

    private final WatchlistService watchlistService;

    @PostMapping
    public ResponseEntity<WatchlistResponseDto> addToWatchlist(@RequestBody WatchlistRequestDto watchlistRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(watchlistService.addToWatchlist(watchlistRequestDto));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<WatchlistResponseDto>> getWatchlist(@PathVariable Long customerId) {
        return ResponseEntity.ok(watchlistService.getWatchlistForCustomer(customerId));
    }

    @DeleteMapping
    public ResponseEntity<Void> removeFromWatchlist(
            @RequestParam Long customerId,
            @RequestParam Long dvdId
    ) {
        watchlistService.removeFromWatchlist(customerId, dvdId);
        return ResponseEntity.noContent().build();
    }
}

