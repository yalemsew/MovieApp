package com.org.moviemail.mapper;

import com.org.moviemail.dto.request.DVDRequestDto;
import com.org.moviemail.dto.response.DVDResponseDto;
import com.org.moviemail.entity.DVD;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DVDMapper {
    DVD dvdRequestDTOToDVD(DVDRequestDto dvdRequestDto);

    DVDResponseDto dvdToDVDResponseDTO(DVD dvd);

    List<DVDResponseDto> dvdToDVDResponseDTOList(List<DVD> dvds);
}

