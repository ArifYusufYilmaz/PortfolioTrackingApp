package com.arifyusufyilmaz.portfolioTrackingApp.service.abstracts;

import com.arifyusufyilmaz.portfolioTrackingApp.dto.RatioOfAssetToPortfolioDto;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.collectApiDtos.BistAssetDto;

import java.math.BigDecimal;
import java.util.List;

public interface FinancialAssetDailyMarketProfitService {
     BigDecimal getSomeValueTrying();
     BistAssetDto getAsset(String symbol);
     void generateDailyProfits(Long financialAssetId);

     List<RatioOfAssetToPortfolioDto> retrieveRatioOfAssetsToPortfolioThroughLatestAmount(Long portfolioId);
     List<RatioOfAssetToPortfolioDto> retrieveRatioOfAssetsToPortfolioThroughOwningCost(Long portfolioId);
}
