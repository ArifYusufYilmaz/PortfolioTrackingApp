package com.arifyusufyilmaz.portfolioTrackingApp.repository;

import com.arifyusufyilmaz.portfolioTrackingApp.entity.DailyMarketProfit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyMarketProfitDao extends JpaRepository<DailyMarketProfit, Long> {
}
