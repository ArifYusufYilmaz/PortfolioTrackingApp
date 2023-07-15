package com.arifyusufyilmaz.portfolioTrackingApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "deposit_portfolio_transactions")
public class DepositPortfolioTransaction extends PortfolioTransaction{

    public DepositPortfolioTransaction(BigDecimal amount) {
        super(amount);
    }

    public DepositPortfolioTransaction() {
    }

    @Override
    public void generatePortfolioTransaction(Portfolio portfolio) {
        portfolio.credit(this.getAmount());
        this.setPortfolio(portfolio);
    }
}

