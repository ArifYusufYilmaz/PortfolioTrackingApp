package com.arifyusufyilmaz.portfolioTrackingApp.controller;

import com.arifyusufyilmaz.portfolioTrackingApp.dto.FinancialAssetResponseDto;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.RatioOfAssetToPortfolioDto;
import com.arifyusufyilmaz.portfolioTrackingApp.service.abstracts.FinancialAssetDailyMarketProfitService;
import com.arifyusufyilmaz.portfolioTrackingApp.service.abstracts.FinancialAssetService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/financial-asset-daily-market-profits")
public class FinancialAssetDailyMarketProfitController {
    private final FinancialAssetDailyMarketProfitService financialAssetDailyMarketProfitService;

    private final FinancialAssetService financialAssetService;

    public FinancialAssetDailyMarketProfitController(FinancialAssetDailyMarketProfitService financialAssetDailyMarketProfitService, FinancialAssetService financialAssetService) {
        this.financialAssetDailyMarketProfitService = financialAssetDailyMarketProfitService;
        this.financialAssetService = financialAssetService;
    }

    @GetMapping
   // @Scheduled(cron = "0 * * * * *")
    private void getFinancialAssetDailyMarketProfit(){
        System.out.println("fa calisti");
        List<FinancialAssetResponseDto> financialAssetResponseDtoList =  financialAssetService.getAllFinancialAssets();

        financialAssetResponseDtoList.forEach( f -> financialAssetDailyMarketProfitService.generateDailyProfits(f.getId()));

    }

    @GetMapping("/ratio-owning-cost/{portfolioId}")
    public List<RatioOfAssetToPortfolioDto> getRatioOfAssetsToPortfolioThroughOwningCost(@PathVariable Long portfolioId){
        return this.financialAssetDailyMarketProfitService.retrieveRatioOfAssetsToPortfolioThroughOwningCost(portfolioId);
     }
    @GetMapping("/ratio-latest-amount/{portfolioId}")
    public List<RatioOfAssetToPortfolioDto> getRatioOfAssetsToPortfolioThroughLatestAmount(@PathVariable Long portfolioId){
        return this.financialAssetDailyMarketProfitService.retrieveRatioOfAssetsToPortfolioThroughLatestAmount(portfolioId);
    }

}
