package com.org.moviemail.service;


import com.org.moviemail.dto.DVDPopularityDto;

import java.util.List;

public interface ReportingService {
    List<DVDPopularityDto> getTopPopularDVDs(int limit);
    List<DVDPopularityDto> getLeastPopularDVDs(int limit);
    double getTotalRevenue();
}

