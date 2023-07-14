package com.arifyusufyilmaz.portfolioTrackingApp.dto;

import java.math.BigDecimal;
import java.util.Date;

public class FinancialAssetResponseDto {
    private Long id;
    private String assetName;
    private String assetSymbol;
    private BigDecimal assetQuantity;
    private BigDecimal assetOwningCost;
    private Date assetOwningDate; //TODO

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

    public BigDecimal getAssetOwningCost() {
        return assetOwningCost;
    }

    public void setAssetOwningCost(BigDecimal assetOwningCost) {
        this.assetOwningCost = assetOwningCost;
    }

    public Date getAssetOwningDate() {
        return assetOwningDate;
    }

    public void setAssetOwningDate(Date assetOwningDate) {
        this.assetOwningDate = assetOwningDate;
    }
}
