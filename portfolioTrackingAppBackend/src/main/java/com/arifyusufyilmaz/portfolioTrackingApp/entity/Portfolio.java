package com.arifyusufyilmaz.portfolioTrackingApp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "portfolios")
@Data
public class Portfolio {
    @Id
    @GeneratedValue(generator = "Portfolio", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name ="Portfolio" ,sequenceName = "PORTFOLIO_ID_SEQ")
    private Long id;
    private String portfolioName;
    private BigDecimal portfolioAvailableCash;
    @OneToMany(mappedBy = "portfolio")
    private List<FinancialAsset> financialAssets;

    @OneToMany(mappedBy = "portfolio")
    private List<DailyMarketProfit> dailyMarketProfits;
}
