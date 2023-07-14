package com.arifyusufyilmaz.portfolioTrackingApp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "portfolios")
public class Portfolio {
    @Id
    @GeneratedValue(generator = "Portfolio", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name ="Portfolio" ,sequenceName = "PORTFOLIO_ID_SEQ")
    private Long id;
    private String portfolioName;
    private BigDecimal portfolioAvailableCash;

    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "portfolio")
    private List<FinancialAsset> financialAssets;

    @OneToMany(mappedBy = "portfolio")
    private List<DailyMarketProfit> dailyMarketProfits;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<FinancialAsset> getFinancialAssets() {
        return financialAssets;
    }

    public void setFinancialAssets(List<FinancialAsset> financialAssets) {
        this.financialAssets = financialAssets;
    }

    public List<DailyMarketProfit> getDailyMarketProfits() {
        return dailyMarketProfits;
    }

    public void setDailyMarketProfits(List<DailyMarketProfit> dailyMarketProfits) {
        this.dailyMarketProfits = dailyMarketProfits;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
