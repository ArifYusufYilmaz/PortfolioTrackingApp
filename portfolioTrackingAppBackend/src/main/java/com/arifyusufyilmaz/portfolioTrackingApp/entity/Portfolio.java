package com.arifyusufyilmaz.portfolioTrackingApp.entity;

import jakarta.persistence.*;

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
    private BigDecimal portfolioCashBalance;

    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    private List<FinancialAsset> financialAssets;

    @OneToMany(mappedBy = "portfolio")
    private List<PortfolioDailyMarketProfit> portfolioDailyMarketProfits;

    @OneToMany(mappedBy = "portfolio")
    private List<PortfolioTransaction> transactions;

    public Portfolio() {
        this.portfolioCashBalance = BigDecimal.valueOf(0);
    }

    public Portfolio(Long id, String portfolioName, BigDecimal portfolioCashBalance) {
        this.id = id;
        this.portfolioName = portfolioName;
        this.portfolioCashBalance = portfolioCashBalance;
    }

    public BigDecimal debit(BigDecimal amount){
        // portfoliocashbalance is null at first!
        if (getPortfolioCashBalance().compareTo(amount) < 0) {
            // todo throw insufficent balance
        }
        setPortfolioCashBalance(getPortfolioCashBalance().subtract(amount));
        return getPortfolioCashBalance();
    }
    public BigDecimal credit(BigDecimal amount){
        setPortfolioCashBalance(getPortfolioCashBalance().add(amount));
        return  getPortfolioCashBalance();
    }

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

    public BigDecimal getPortfolioCashBalance() {
        return portfolioCashBalance;
    }

    public void setPortfolioCashBalance(BigDecimal portfolioCashBalance) {
        this.portfolioCashBalance = portfolioCashBalance;
    }

    public List<FinancialAsset> getFinancialAssets() {
        return financialAssets;
    }

    public void setFinancialAssets(List<FinancialAsset> financialAssets) {
        this.financialAssets = financialAssets;
    }

    public List<PortfolioDailyMarketProfit> getDailyMarketProfits() {
        return portfolioDailyMarketProfits;
    }

    public void setDailyMarketProfits(List<PortfolioDailyMarketProfit> dailyMarketProfits) {
        this.portfolioDailyMarketProfits = dailyMarketProfits;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public List<PortfolioTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<PortfolioTransaction> transactions) {
        this.transactions = transactions;
    }
}
