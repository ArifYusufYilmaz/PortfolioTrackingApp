package com.arifyusufyilmaz.portfolioTrackingApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "financial_asset_daily_market_profits")
public class FinancialAssetDailyMarketProfit extends DailyMarketProfit{

    private BigDecimal currentPrice;

    @ManyToOne
    private FinancialAsset financialAsset;


    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public FinancialAsset getFinancialAsset() {
        return financialAsset;
    }

    public void setFinancialAsset(FinancialAsset financialAsset) {
        this.financialAsset = financialAsset;
    }
}
