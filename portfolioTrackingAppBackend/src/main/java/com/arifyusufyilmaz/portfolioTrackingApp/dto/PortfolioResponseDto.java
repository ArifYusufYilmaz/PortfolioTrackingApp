package com.arifyusufyilmaz.portfolioTrackingApp.dto;

import com.arifyusufyilmaz.portfolioTrackingApp.entity.DailyMarketProfit;
import com.arifyusufyilmaz.portfolioTrackingApp.entity.FinancialAsset;
import jakarta.persistence.OneToMany;

import java.math.BigDecimal;
import java.util.List;


public class PortfolioResponseDto {

    private String portfolioName;
    private BigDecimal portfolioAvailableCash;

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public BigDecimal getPortfolioAvailableCash() {
        return portfolioAvailableCash;
    }

    public void setPortfolioAvailableCash(BigDecimal portfolioAvailableCash) {
        this.portfolioAvailableCash = portfolioAvailableCash;
    }
}
