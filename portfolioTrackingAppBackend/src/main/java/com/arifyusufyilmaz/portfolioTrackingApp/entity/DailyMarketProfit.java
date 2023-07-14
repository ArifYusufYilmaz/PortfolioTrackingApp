package com.arifyusufyilmaz.portfolioTrackingApp.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "daily_market_profits")
public class DailyMarketProfit {
    @Id
    @GeneratedValue(generator = "DailyMarketProfit", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "DailyMarketProfit", sequenceName = "DAILY_MARKET_PROFIT_ID_SEQ")
    private Long id;

    private Date marketTransactionDate;
    private BigDecimal marketProfitAsTurkishLira;
    private BigDecimal marketProfitAsPercentage;
    private BigDecimal marketTotalValue;
    @ManyToOne
    private FinancialAsset financialAsset;
    @ManyToOne
    private Portfolio portfolio;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getMarketTransactionDate() {
        return marketTransactionDate;
    }

    public void setMarketTransactionDate(Date marketTransactionDate) {
        this.marketTransactionDate = marketTransactionDate;
    }

    public BigDecimal getMarketProfitAsTurkishLira() {
        return marketProfitAsTurkishLira;
    }

    public void setMarketProfitAsTurkishLira(BigDecimal marketProfitAsTurkishLira) {
        this.marketProfitAsTurkishLira = marketProfitAsTurkishLira;
    }

    public BigDecimal getMarketProfitAsPercentage() {
        return marketProfitAsPercentage;
    }

    public void setMarketProfitAsPercentage(BigDecimal marketProfitAsPercentage) {
        this.marketProfitAsPercentage = marketProfitAsPercentage;
    }

    public BigDecimal getMarketTotalValue() {
        return marketTotalValue;
    }

    public void setMarketTotalValue(BigDecimal marketTotalValue) {
        this.marketTotalValue = marketTotalValue;
    }

    public FinancialAsset getFinancialAsset() {
        return financialAsset;
    }

    public void setFinancialAsset(FinancialAsset financialAsset) {
        this.financialAsset = financialAsset;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }
}
