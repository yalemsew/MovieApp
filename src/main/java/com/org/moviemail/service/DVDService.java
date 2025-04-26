package com.org.moviemail.service;

import com.org.moviemail.dto.request.DVDRequestDto;
import com.org.moviemail.dto.response.DVDResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DVDService {
    DVDResponseDto createDVD(DVDRequestDto dto);
    Page<DVDResponseDto> getAllDVDs(int page, int size);
    DVDResponseDto getDVDByScanCode(String scanCode);
    void deleteDVDByScanCode(String scanCode);
}
