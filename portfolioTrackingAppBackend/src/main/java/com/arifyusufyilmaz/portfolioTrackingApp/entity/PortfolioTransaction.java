package com.arifyusufyilmaz.portfolioTrackingApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

@Entity
public abstract class PortfolioTransaction extends Transaction{

    private BigDecimal amount;

    @ManyToOne
    private Portfolio portfolio;

    public PortfolioTransaction(BigDecimal amount) {
        this.amount = amount;
    }

    public PortfolioTransaction() {
    }

    public abstract void generatePortfolioTransaction(Portfolio portfolio);


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }
}
