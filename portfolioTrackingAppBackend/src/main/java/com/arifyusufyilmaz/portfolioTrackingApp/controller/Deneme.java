package com.arifyusufyilmaz.portfolioTrackingApp.controller;

import com.arifyusufyilmaz.portfolioTrackingApp.service.abstracts.FinancialAssetDailyMarketProfitService;
import com.arifyusufyilmaz.portfolioTrackingApp.service.concretes.BistApiImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/deneme")
public class Deneme {
    private final FinancialAssetDailyMarketProfitService financialAssetDailyMarketProfitService;

    public Deneme(FinancialAssetDailyMarketProfitService financialAssetDailyMarketProfitService) {
        this.financialAssetDailyMarketProfitService = financialAssetDailyMarketProfitService;
    }

    @GetMapping
    @Scheduled(cron = "* * * * * *")
    // second- minute- hour- day(of the month)- month- day(of the week)
    public void getResponse(){
        BigDecimal value = financialAssetDailyMarketProfitService.getSomeValueTrying();
        System.out.println(value);

    }
}
