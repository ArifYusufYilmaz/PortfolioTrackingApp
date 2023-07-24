package com.arifyusufyilmaz.portfolioTrackingApp.service.abstracts;

import com.arifyusufyilmaz.portfolioTrackingApp.dto.collectApiDtos.BistAssetDto;

import java.math.BigDecimal;

public interface FinancialAssetDailyMarketProfitService {
     BigDecimal getSomeValueTrying();
     BistAssetDto getAsset(String symbol);
     void generateDailyProfits(Long financialAssetId);
}
