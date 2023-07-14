package com.arifyusufyilmaz.portfolioTrackingApp.service.concretes;

import com.arifyusufyilmaz.portfolioTrackingApp.repository.DailyMarketProfitDao;
import org.springframework.stereotype.Service;

@Service
public class DailyMarketProfitServiceImpl {
    private final DailyMarketProfitDao dailyMarketProfitDao;

    public DailyMarketProfitServiceImpl(DailyMarketProfitDao dailyMarketProfitDao) {
        this.dailyMarketProfitDao = dailyMarketProfitDao;
    }
}
