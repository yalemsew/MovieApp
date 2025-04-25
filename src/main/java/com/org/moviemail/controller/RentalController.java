package com.org.moviemail.controller;

import com.org.moviemail.dto.response.DVDResponseDto;
import com.org.moviemail.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    @PostMapping("/rent/{customerId}")
    public ResponseEntity<DVDResponseDto> rentNextDVD(@PathVariable Long customerId) {
        return ResponseEntity.ok(rentalService.rentNextDVD(customerId));
    }

    @PostMapping("/return/{scanCode}")
    public ResponseEntity<DVDResponseDto> returnDVD(@PathVariable String scanCode) {
        return ResponseEntity.ok(rentalService.returnDVD(scanCode));
    }

    @GetMapping("/at-home/{customerId}")
    public ResponseEntity<List<DVDResponseDto>> getDVDsAtHome(@PathVariable Long customerId) {
        return ResponseEntity.ok(rentalService.getDVDsAtHome(customerId));
    }

    @GetMapping("/returned/{customerId}")
    public ResponseEntity<List<DVDResponseDto>> getReturnedDVDs(@PathVariable Long customerId) {
        return ResponseEntity.ok(rentalService.getReturnedDVDs(customerId));
    }
}

