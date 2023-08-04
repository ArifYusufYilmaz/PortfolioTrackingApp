package com.arifyusufyilmaz.portfolioTrackingApp.dto;

import java.math.BigDecimal;

public class RatioOfAssetToPortfolioDto {
    private String symbol;
    private BigDecimal ratio;

    public RatioOfAssetToPortfolioDto(String symbol, BigDecimal ratio) {
        this.symbol = symbol;
        this.ratio = ratio;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }
}
