package com.org.moviemail.controller;

import com.org.moviemail.dto.DVDPopularityDto;
import com.org.moviemail.service.ReportingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportingController {

    private final ReportingService reportingService;

    @GetMapping("/popular")
    public ResponseEntity<List<DVDPopularityDto>> getTopPopular(@RequestParam(defaultValue = "5") int limit) {
        return ResponseEntity.ok(reportingService.getTopPopularDVDs(limit));
    }

    @GetMapping("/least-popular")
    public ResponseEntity<List<DVDPopularityDto>> getLeastPopular(@RequestParam(defaultValue = "5") int limit) {
        return ResponseEntity.ok(reportingService.getLeastPopularDVDs(limit));
    }

    @GetMapping("/revenue")
    public ResponseEntity<Double> getTotalRevenue() {
        return ResponseEntity.ok(reportingService.getTotalRevenue());
    }
}

