package com.arifyusufyilmaz.portfolioTrackingApp.repository;

import com.arifyusufyilmaz.portfolioTrackingApp.entity.DailyMarketProfit;
import com.arifyusufyilmaz.portfolioTrackingApp.entity.FinancialAssetDailyMarketProfit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface DailyMarketProfitDao extends JpaRepository<DailyMarketProfit, Long> {

    Optional<FinancialAssetDailyMarketProfit> findByFinancialAsset_IdAndMarketTransactionDate(Long financialAssetId, Date marketTransactionDate);
     Optional<FinancialAssetDailyMarketProfit> findFirstByFinancialAssetIdOrderByMarketTransactionDateDesc(Long financialAssetId);
}
