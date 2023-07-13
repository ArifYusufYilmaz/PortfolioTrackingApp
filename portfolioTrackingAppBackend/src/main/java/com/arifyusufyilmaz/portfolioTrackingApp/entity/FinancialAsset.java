package com.arifyusufyilmaz.portfolioTrackingApp.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="financial_assets")
public class FinancialAsset {
    @Id
    @GeneratedValue(generator = "FinancialAsset",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "FinancialAsset",sequenceName = "FINANCIAL_ASSET_ID_SEQ")
    private Long id;


    private String assetName;
    private String assetSymbol;
    private BigDecimal assetQuantity;
    private BigDecimal assetOwningCost;
    private Date assetOwningDate; //TODO

    @ManyToOne
    private Portfolio portfolio;

    @OneToMany(mappedBy = "financialAsset")
    private List<DailyMarketProfit> dailyMarketProfits;


}
