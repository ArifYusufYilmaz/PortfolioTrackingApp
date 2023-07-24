package com.arifyusufyilmaz.portfolioTrackingApp.controller;

import com.arifyusufyilmaz.portfolioTrackingApp.dto.FinancialAssetResponseDto;
import com.arifyusufyilmaz.portfolioTrackingApp.service.abstracts.FinancialAssetDailyMarketProfitService;
import com.arifyusufyilmaz.portfolioTrackingApp.service.abstracts.FinancialAssetService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
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
    @Scheduled(cron = "0 * * * * *")
    private void doIt(){
        List<FinancialAssetResponseDto> faR =  financialAssetService.getAllFinancialAssets(1L);

        faR.forEach( f -> financialAssetDailyMarketProfitService.generateDailyProfits(f.getId()) );
        System.out.println("bittim");
    }

}
