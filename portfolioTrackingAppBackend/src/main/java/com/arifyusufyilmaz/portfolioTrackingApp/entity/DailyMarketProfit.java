package com.arifyusufyilmaz.portfolioTrackingApp.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class DailyMarketProfit {
    @Id
    @GeneratedValue(generator = "DailyMarketProfit", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "DailyMarketProfit", sequenceName = "DAILY_MARKET_PROFIT_ID_SEQ")
    private Long id;

    @CreatedDate
    private Date marketTransactionDate;
    private BigDecimal marketProfitAsTurkishLira;
    private BigDecimal marketProfitAsPercentage;
    private BigDecimal marketTotalValue;

    @PrePersist
    protected void prePersist(){
        if(this.marketTransactionDate == null) marketTransactionDate = new Date();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getMarketTransactionDate() {
        return marketTransactionDate;
    }

    public void setMarketTransactionDate(Date marketTransactionDate) {
        this.marketTransactionDate = marketTransactionDate;
    }

    public BigDecimal getMarketProfitAsTurkishLira() {
        return marketProfitAsTurkishLira;
    }

    public void setMarketProfitAsTurkishLira(BigDecimal marketProfitAsTurkishLira) {
        this.marketProfitAsTurkishLira = marketProfitAsTurkishLira;
    }

    public BigDecimal getMarketProfitAsPercentage() {
        return marketProfitAsPercentage;
    }

    public void setMarketProfitAsPercentage(BigDecimal marketProfitAsPercentage) {
        this.marketProfitAsPercentage = marketProfitAsPercentage;
    }

    public BigDecimal getMarketTotalValue() {
        return marketTotalValue;
    }

    public void setMarketTotalValue(BigDecimal marketTotalValue) {
        this.marketTotalValue = marketTotalValue;
    }


}
