package com.arifyusufyilmaz.portfolioTrackingApp.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "daily_market_profits")
public class DailyMarketProfit {
    @Id
    @GeneratedValue(generator = "DailyMarketProfit", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "DailyMarketProfit", sequenceName = "DAILY_MARKET_PROFIT_ID_SEQ")
    private Long id;

    private Date marketTransactionDate;
    private BigDecimal marketProfitAsTurkishLira;
    private BigDecimal marketProfitAsPercentage;
    private BigDecimal marketTotalValue;
    @ManyToOne
    private FinancialAsset financialAsset;
    @ManyToOne
    private Portfolio portfolio;

}
