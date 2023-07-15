package com.arifyusufyilmaz.portfolioTrackingApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

@Entity
public abstract class FinancialAssetTransaction extends Transaction{
    //TODO : could be passing FinancialAsset class instead of its properties
    private String symbol;
    private BigDecimal quantity;
    private BigDecimal cost;

    @ManyToOne
    private FinancialAsset financialAsset;

    public FinancialAssetTransaction(String symbol, BigDecimal quantity, BigDecimal cost) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.cost = cost;
    }

    public FinancialAssetTransaction() {
    }
    public abstract void generateFinancialAssetTransaction(FinancialAsset financialAsset);


    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
