package com.org.moviemail.service;

import com.org.moviemail.dto.request.DVDRequestDto;
import com.org.moviemail.dto.response.DVDResponseDto;

import java.util.List;

public interface DVDService {
    DVDResponseDto createDVD(DVDRequestDto dto);
    List<DVDResponseDto> getAllDVDs();
    DVDResponseDto getDVDByScanCode(String scanCode);
    void deleteDVDByScanCode(String scanCode);
}
