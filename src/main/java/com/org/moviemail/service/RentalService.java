package com.org.moviemail.service;

import com.org.moviemail.dto.response.DVDResponseDto;

import java.util.List;

public interface RentalService {
    DVDResponseDto rentNextDVD(Long customerId);
    DVDResponseDto returnDVD(String scanCode);
    List<DVDResponseDto> getDVDsAtHome(Long customerId);
    List<DVDResponseDto> getReturnedDVDs(Long customerId);
}
