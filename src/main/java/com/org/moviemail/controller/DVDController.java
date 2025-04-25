package com.org.moviemail.controller;

import com.org.moviemail.dto.request.DVDRequestDto;
import com.org.moviemail.dto.response.DVDResponseDto;
import com.org.moviemail.service.DVDService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dvds")
@RequiredArgsConstructor
public class DVDController {

    private final DVDService dvdService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<DVDResponseDto> createDVD(@RequestBody DVDRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dvdService.createDVD(dto));
    }

    @GetMapping
    public ResponseEntity<List<DVDResponseDto>> getAllDVDs() {
        return ResponseEntity.ok(dvdService.getAllDVDs());
    }

    @GetMapping("/{scanCode}")
    public ResponseEntity<DVDResponseDto> getDVDByScanCode(@PathVariable String scanCode) {
        return ResponseEntity.ok(dvdService.getDVDByScanCode(scanCode));
    }

    @DeleteMapping("/{scanCode}")
    public ResponseEntity<Void> deleteDVD(@PathVariable String scanCode) {
        dvdService.deleteDVDByScanCode(scanCode);
        return ResponseEntity.noContent().build();
    }
}
