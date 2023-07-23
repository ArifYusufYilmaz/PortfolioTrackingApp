package com.arifyusufyilmaz.portfolioTrackingApp.controller;

import com.arifyusufyilmaz.portfolioTrackingApp.service.abstracts.FinancialAssetDailyMarketProfitService;
import com.arifyusufyilmaz.portfolioTrackingApp.service.concretes.BistApiImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deneme")
public class Deneme {
    private final FinancialAssetDailyMarketProfitService financialAssetDailyMarketProfitService;

    public Deneme(FinancialAssetDailyMarketProfitService financialAssetDailyMarketProfitService) {
        this.financialAssetDailyMarketProfitService = financialAssetDailyMarketProfitService;
    }

    @GetMapping
    public void getResponse(){
        double value = financialAssetDailyMarketProfitService.getSomeValueTrying();
        System.out.println(value);

    }
}
