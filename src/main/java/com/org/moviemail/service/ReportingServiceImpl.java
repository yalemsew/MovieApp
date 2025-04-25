package com.org.moviemail.service;

import com.org.moviemail.dto.DVDPopularityDto;
import com.org.moviemail.repository.DVDRepository;
import com.org.moviemail.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportingServiceImpl implements ReportingService {

    private final DVDRepository dvdRepository;
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public List<DVDPopularityDto> getTopPopularDVDs(int limit) {
        return dvdRepository.findTopPopularDVDs(limit);
    }

    @Override
    public List<DVDPopularityDto> getLeastPopularDVDs(int limit) {
        return dvdRepository.findLeastPopularDVDs(limit);
    }

    @Override
    public double getTotalRevenue() {
        return subscriptionRepository.calculateTotalRevenue();
    }
}

