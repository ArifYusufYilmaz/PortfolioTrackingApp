package com.arifyusufyilmaz.portfolioTrackingApp.controller;

import com.arifyusufyilmaz.portfolioTrackingApp.service.abstracts.PortfolioDailyMarketService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/portfolio-daily-market-profits")
public class PortfolioDailyMarketProfitController {
    private final PortfolioDailyMarketService portfolioDailyMarketService;

    public PortfolioDailyMarketProfitController(PortfolioDailyMarketService portfolioDailyMarketService) {
        this.portfolioDailyMarketService = portfolioDailyMarketService;
    }


    @GetMapping
  //  @Scheduled(cron = "1 * * * * *")
    public void getPortfolioDailyMarketProfit(){
        //TODO
        System.out.println("portfolio calisti");
        portfolioDailyMarketService.generatePortfolioDailyMarketProfit(202L);

    }
}
