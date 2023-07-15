package com.arifyusufyilmaz.portfolioTrackingApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "withdrawal_portfolio_transactions")
public class WithdrawalPortfolioTransaction extends PortfolioTransaction {

    public WithdrawalPortfolioTransaction(BigDecimal amount) {
        super(amount);
    }

    public WithdrawalPortfolioTransaction() {

    }

    @Override
    public void generatePortfolioTransaction(Portfolio portfolio) {
            portfolio.debit(this.getAmount());
            this.setPortfolio(portfolio);
    }
}
