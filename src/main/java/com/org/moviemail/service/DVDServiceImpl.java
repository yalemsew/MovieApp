package com.org.moviemail.service;

import com.org.moviemail.dto.request.DVDRequestDto;
import com.org.moviemail.dto.response.DVDResponseDto;
import com.org.moviemail.entity.DVD;
import com.org.moviemail.entity.DVDStatus;
import com.org.moviemail.exception.internal.DVDDuplicateException;
import com.org.moviemail.exception.internal.DVDNotFoundException;
import com.org.moviemail.mapper.DVDMapper;
import com.org.moviemail.repository.DVDRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DVDServiceImpl implements DVDService {

    private final DVDRepository dvdRepository;
    private final DVDMapper dvdMapper;

    @Override
    public DVDResponseDto createDVD(DVDRequestDto dto) {
        if (dvdRepository.existsByScanCode(dto.scanCode())) {
            throw new DVDDuplicateException("DVD with this scan code already exists.");
        }

        DVD dvd = dvdMapper.dvdRequestDTOToDVD(dto);
        dvd.setStatus(DVDStatus.AVAILABLE);

        return dvdMapper.dvdToDVDResponseDTO(dvdRepository.save(dvd));
    }

    @Override
    public List<DVDResponseDto> getAllDVDs() {
        return dvdMapper.dvdToDVDResponseDTOList(dvdRepository.findAll());
    }

    @Override
    public DVDResponseDto getDVDByScanCode(String scanCode) {
        DVD dvd = dvdRepository.findByScanCode(scanCode)
                .orElseThrow(() -> new DVDNotFoundException("DVD not found"));
        return dvdMapper.dvdToDVDResponseDTO(dvd);
    }

    @Override
    public void deleteDVDByScanCode(String scanCode) {
        DVD dvd = dvdRepository.findByScanCode(scanCode)
                .orElseThrow(() -> new DVDNotFoundException("DVD not found"));
        dvdRepository.delete(dvd);
    }
}
