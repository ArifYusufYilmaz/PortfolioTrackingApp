package com.arifyusufyilmaz.portfolioTrackingApp.service.concretes;

import com.arifyusufyilmaz.portfolioTrackingApp.entity.Portfolio;
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

       BigDecimal totalV =  portfolioOpt.get().getFinancialAssets()
                .stream()
                .map(fA-> this.dailyMarketProfitDao.findFirstByFinancialAssetIdOrderByMarketTransactionDateDesc(fA.getId()).get().getMarketTotalValue())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total Value : " +  totalV );
        // tarihe göre sıralayıp son total value yu almak gerek.
    }
}
