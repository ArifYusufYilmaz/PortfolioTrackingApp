package com.arifyusufyilmaz.portfolioTrackingApp.service.concretes;

import com.arifyusufyilmaz.portfolioTrackingApp.entity.DailyMarketProfit;
import com.arifyusufyilmaz.portfolioTrackingApp.entity.Portfolio;
import com.arifyusufyilmaz.portfolioTrackingApp.entity.PortfolioDailyMarketProfit;
import com.arifyusufyilmaz.portfolioTrackingApp.repository.DailyMarketProfitDao;
import com.arifyusufyilmaz.portfolioTrackingApp.repository.PortfolioDao;
import com.arifyusufyilmaz.portfolioTrackingApp.service.abstracts.PortfolioDailyMarketService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PortfolioDailyMarketServiceImpl implements PortfolioDailyMarketService {
    private final DailyMarketProfitDao dailyMarketProfitDao;
    private final PortfolioDao portfolioDao;

    public PortfolioDailyMarketServiceImpl(DailyMarketProfitDao dailyMarketProfitDao, PortfolioDao portfolioDao) {
        this.dailyMarketProfitDao = dailyMarketProfitDao;
        this.portfolioDao = portfolioDao;
    }


    @Override
    public void generatePortfolioDailyMarketProfit(Long portfolioId) {
        Optional<Portfolio> portfolioOpt = this.portfolioDao.findById(portfolioId);
        if (!portfolioOpt.isPresent()) {
        // todo throw
        }

        BigDecimal marketTotalValue = getMarketTotalValueFromDb(portfolioOpt.get());

         BigDecimal dailyProfit = calculateDailyProfitAsTry(portfolioOpt.get(), marketTotalValue);
         savePortfolioDailyMarketProfit(portfolioOpt.get(), marketTotalValue, dailyProfit);
    }
    private BigDecimal calculateDailyProfitAsTry(Portfolio portfolio,  BigDecimal marketTotalValue){
        Optional<PortfolioDailyMarketProfit> dailyMarketProfitOpt =  this.dailyMarketProfitDao.findFirstByPortfolioIdOrderByMarketTransactionDateDesc(portfolio.getId());
        BigDecimal dailyProfit;
        BigDecimal previousTotalValue;
        if(!dailyMarketProfitOpt.isPresent()){
            previousTotalValue = portfolio.getFinancialAssets()
                    .stream()
                    .map(fA-> fA.getAssetQuantity().multiply(fA.getAssetCost()))
                    .reduce(BigDecimal.ZERO, BigDecimal:: add);
        }else{
            previousTotalValue = dailyMarketProfitOpt.get().getMarketTotalValue();
        }
        return dailyProfit = marketTotalValue.subtract(previousTotalValue);
    }
    private PortfolioDailyMarketProfit savePortfolioDailyMarketProfit(Portfolio portfolio, BigDecimal marketTotalValue, BigDecimal dailyProfit){
        PortfolioDailyMarketProfit portfolioDailyMarketProfit = new PortfolioDailyMarketProfit();
        portfolioDailyMarketProfit.setPortfolio(portfolio);
        portfolioDailyMarketProfit.setCashBalance(portfolio.getPortfolioCashBalance());
        portfolioDailyMarketProfit.setMarketTotalValue(marketTotalValue);//todo could be removed.
        portfolioDailyMarketProfit.setMarketProfitAsTurkishLira(dailyProfit);
       return this.dailyMarketProfitDao.save(portfolioDailyMarketProfit);
    }
    private BigDecimal getMarketTotalValueFromDb(Portfolio portfolio){

        // tarihe göre sıralayıp son total value yu almak gerek.
        BigDecimal marketTotalValue =  portfolio.getFinancialAssets()
                .stream()
                .map(fA-> this.dailyMarketProfitDao.findFirstByFinancialAssetIdOrderByMarketTransactionDateDesc(fA.getId()).get().getMarketTotalValue())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return marketTotalValue;
    }




}
