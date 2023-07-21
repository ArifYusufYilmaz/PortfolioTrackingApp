package com.arifyusufyilmaz.portfolioTrackingApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "financial_asset_daily_market_profits")
public class FinancialAssetDailyMarketProfit extends DailyMarketProfit{

    @ManyToOne
    private FinancialAsset financialAsset;

    public FinancialAsset getFinancialAsset() {
        return financialAsset;
    }

    public void setFinancialAsset(FinancialAsset financialAsset) {
        this.financialAsset = financialAsset;
    }
}
