package com.arifyusufyilmaz.portfolioTrackingApp.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "deposit_financial_asset_transactions")
public class DepositFinancialAssetTransaction extends FinancialAssetTransaction{
    public DepositFinancialAssetTransaction(String symbol, BigDecimal quantity, BigDecimal cost) {
        super(symbol, quantity, cost);
    }

    public DepositFinancialAssetTransaction() {
    }

    @Override
    public void generateFinancialAssetTransaction(FinancialAsset financialAsset) {
        BigDecimal amount = financialAsset.getAssetQuantity().multiply(financialAsset.getAssetCost());
        financialAsset.getPortfolio().debit(amount);
    }
}
