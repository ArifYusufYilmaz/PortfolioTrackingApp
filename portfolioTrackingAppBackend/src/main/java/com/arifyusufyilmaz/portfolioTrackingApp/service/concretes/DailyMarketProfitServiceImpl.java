package com.arifyusufyilmaz.portfolioTrackingApp.service.concretes;

import com.arifyusufyilmaz.portfolioTrackingApp.repository.DailyMarketProfitDao;
import com.arifyusufyilmaz.portfolioTrackingApp.service.abstracts.DailyMarketProfitService;
import org.springframework.stereotype.Service;

@Service
public class DailyMarketProfitServiceImpl implements DailyMarketProfitService {
    private final DailyMarketProfitDao dailyMarketProfitDao;

    public DailyMarketProfitServiceImpl(DailyMarketProfitDao dailyMarketProfitDao) {
        this.dailyMarketProfitDao = dailyMarketProfitDao;
    }
}
