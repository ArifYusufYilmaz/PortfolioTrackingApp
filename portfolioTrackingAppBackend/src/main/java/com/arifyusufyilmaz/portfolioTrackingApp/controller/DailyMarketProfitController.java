package com.arifyusufyilmaz.portfolioTrackingApp.controller;

import com.arifyusufyilmaz.portfolioTrackingApp.service.abstracts.DailyMarketProfitService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/daily-market-profits")
public class DailyMarketProfitController {
    private final DailyMarketProfitService dailyMarketProfitService;

    public DailyMarketProfitController(DailyMarketProfitService dailyMarketProfitService) {
        this.dailyMarketProfitService = dailyMarketProfitService;
    }
    //Todo:  there should not be a post/delete/update method for this.

}
