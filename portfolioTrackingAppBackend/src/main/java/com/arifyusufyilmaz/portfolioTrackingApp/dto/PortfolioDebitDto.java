package com.arifyusufyilmaz.portfolioTrackingApp.dto;

import java.math.BigDecimal;

public class PortfolioDebitDto {
    private BigDecimal cashAmount;

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }
}
