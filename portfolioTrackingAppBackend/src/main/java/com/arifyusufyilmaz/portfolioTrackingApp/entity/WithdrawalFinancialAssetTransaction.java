package com.arifyusufyilmaz.portfolioTrackingApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "withdrawal_financial_asset_transactions")
public class WithdrawalFinancialAssetTransaction extends FinancialAssetTransaction{
    public WithdrawalFinancialAssetTransaction(String symbol, BigDecimal quantity, BigDecimal cost) {
        super(symbol, quantity, cost);
    }

    public WithdrawalFinancialAssetTransaction() {
    }

    @Override
    public void generateFinancialAssetTransaction(FinancialAsset financialAsset) {
        BigDecimal amount = financialAsset.getAssetQuantity().multiply(financialAsset.getAssetCost());
        financialAsset.getPortfolio().credit(amount);
    }


}
