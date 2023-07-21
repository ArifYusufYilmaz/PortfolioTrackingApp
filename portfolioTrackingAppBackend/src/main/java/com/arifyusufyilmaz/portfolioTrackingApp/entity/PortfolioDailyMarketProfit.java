package com.arifyusufyilmaz.portfolioTrackingApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "portfolio_daily_market_profits")
public class PortfolioDailyMarketProfit extends DailyMarketProfit{
    @ManyToOne
    private Portfolio portfolio;

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }
}
