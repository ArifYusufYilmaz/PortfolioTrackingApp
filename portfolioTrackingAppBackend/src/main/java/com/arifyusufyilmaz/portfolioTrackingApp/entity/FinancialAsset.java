package com.arifyusufyilmaz.portfolioTrackingApp.entity;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name="financial_assets")
public class FinancialAsset {
    @Id
    @GeneratedValue(generator = "FinancialAsset",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "FinancialAsset",sequenceName = "FINANCIAL_ASSET_ID_SEQ")
    private Long id;


    private String assetName;
    private String assetSymbol;
    private BigDecimal assetQuantity;
    private BigDecimal assetCost;

    private Date assetOwningDate; //TODO

    @ManyToOne
    private Portfolio portfolio;

    @OneToMany(mappedBy = "financialAsset")
    private List<FinancialAssetTransaction> transactions;

    @OneToMany(mappedBy = "financialAsset")
    private List<DailyMarketProfit> dailyMarketProfits;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetSymbol() {
        return assetSymbol;
    }

    public void setAssetSymbol(String assetSymbol) {
        this.assetSymbol = assetSymbol;
    }

    public BigDecimal getAssetQuantity() {
        return assetQuantity;
    }

    public void setAssetQuantity(BigDecimal assetQuantity) {
        this.assetQuantity = assetQuantity;
    }

    public BigDecimal getAssetCost() {
        return assetCost;
    }

    public void setAssetCost(BigDecimal assetCost) {
        this.assetCost = assetCost;
    }

    public Date getAssetOwningDate() {
        return assetOwningDate;
    }

    public void setAssetOwningDate(Date assetOwningDate) {
        this.assetOwningDate = assetOwningDate;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public List<DailyMarketProfit> getDailyMarketProfits() {
        return dailyMarketProfits;
    }

    public void setDailyMarketProfits(List<DailyMarketProfit> dailyMarketProfits) {
        this.dailyMarketProfits = dailyMarketProfits;
    }

    public List<FinancialAssetTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<FinancialAssetTransaction> transactions) {
        this.transactions = transactions;
    }
}
