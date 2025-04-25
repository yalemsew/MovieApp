package com.org.moviemail.mapper;

import com.org.moviemail.dto.response.WatchlistResponseDto;
import com.org.moviemail.entity.WatchListEntry;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface WatchlistMapper {

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "dvd.id", target = "dvdId")
    @Mapping(source = "dvd.title", target = "dvdTitle")
    WatchlistResponseDto toResponseDTO(WatchListEntry watchListEntry);

    List<WatchlistResponseDto> toResponseDTOList(List<WatchListEntry> entries);
}

