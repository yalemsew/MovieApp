package com.org.moviemail.service;


import com.org.moviemail.dto.response.DVDResponseDto;
import com.org.moviemail.entity.Customer;
import com.org.moviemail.entity.DVD;
import com.org.moviemail.entity.DVDStatus;
import com.org.moviemail.entity.WatchListEntry;
import com.org.moviemail.exception.internal.CustomerNotFoundException;
import com.org.moviemail.exception.internal.DVDNotFoundException;
import com.org.moviemail.exception.internal.DVDNotRentedException;
import com.org.moviemail.exception.internal.WatchlistEmptyException;
import com.org.moviemail.mapper.DVDMapper;
import com.org.moviemail.repository.CustomerRespository;
import com.org.moviemail.repository.DVDRepository;
import com.org.moviemail.repository.WatchlistRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {

    private final CustomerRespository customerRepository;
    private final WatchlistRepository watchlistRepository;
    private final DVDRepository dvdRepository;
    private final DVDMapper dvdMapper;

    @Override
    public DVDResponseDto rentNextDVD(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        List<WatchListEntry> watchlist = watchlistRepository.findByCustomerIdOrderByPriorityAsc(customerId);
        for (WatchListEntry entry : watchlist) {
            DVD dvd = entry.getDvd();
            if (dvd.getStatus() == DVDStatus.AVAILABLE) {
                dvd.setStatus(DVDStatus.RENTED);
                dvd.setRentalCount(dvd.getRentalCount() + 1);
                return dvdMapper.dvdToDVDResponseDTO(dvdRepository.save(dvd));
            }
        }

        throw new WatchlistEmptyException("No available DVDs in watchlist to rent.");
    }

    @Override
    public DVDResponseDto returnDVD(String scanCode) {
        DVD dvd = dvdRepository.findByScanCode(scanCode)
                .orElseThrow(() -> new DVDNotFoundException("DVD not found"));

        if (dvd.getStatus() != DVDStatus.RENTED) {
            throw new DVDNotRentedException("DVD is not currently rented.");
        }

        dvd.setStatus(DVDStatus.RETURNED);
        return dvdMapper.dvdToDVDResponseDTO(dvdRepository.save(dvd));
    }

    @Override
    public List<DVDResponseDto> getDVDsAtHome(Long customerId) {
        List<DVD> dvds = dvdRepository.findDVDsByCustomerIdAndStatus(customerId, DVDStatus.RENTED);
        return dvdMapper.dvdToDVDResponseDTOList(dvds);
    }

    @Override
    public List<DVDResponseDto> getReturnedDVDs(Long customerId) {
        List<DVD> dvds = dvdRepository.findDVDsByCustomerIdAndStatus(customerId, DVDStatus.RETURNED);
        return dvdMapper.dvdToDVDResponseDTOList(dvds);
    }
}

