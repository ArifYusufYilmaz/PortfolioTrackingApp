package com.arifyusufyilmaz.portfolioTrackingApp.dto;

import com.arifyusufyilmaz.portfolioTrackingApp.entity.DailyMarketProfit;
import com.arifyusufyilmaz.portfolioTrackingApp.entity.FinancialAsset;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class PortfolioSaveDto {
    private String portfolioName;

}
