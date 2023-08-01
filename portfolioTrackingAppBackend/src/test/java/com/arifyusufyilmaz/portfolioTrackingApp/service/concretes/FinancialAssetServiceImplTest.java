package com.arifyusufyilmaz.portfolioTrackingApp.service.concretes;

import com.arifyusufyilmaz.portfolioTrackingApp.dto.FinancialAssetResponseDto;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.FinancialAssetSaveDto;
import com.arifyusufyilmaz.portfolioTrackingApp.entity.FinancialAsset;
import com.arifyusufyilmaz.portfolioTrackingApp.entity.Portfolio;
import com.arifyusufyilmaz.portfolioTrackingApp.repository.FinancialAssetDao;
import com.arifyusufyilmaz.portfolioTrackingApp.repository.PortfolioDao;
import com.arifyusufyilmaz.portfolioTrackingApp.repository.TransactionDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class FinancialAssetServiceImplTest {
    @Mock
    private  FinancialAssetDao financialAssetDao;
    @Mock
    private  PortfolioDao portfolioDao;
    @Mock
    private  TransactionDao transactionDao;

    @InjectMocks
    private FinancialAssetServiceImpl financialAssetServiceImpl;
    @Test
    void buyFinancialAsset() {
        //given
        BigDecimal assetQuantity = BigDecimal.valueOf(200);
        BigDecimal assetCost = BigDecimal.valueOf(150);
        BigDecimal addedAssetQuantity = BigDecimal.valueOf(400);
        BigDecimal afterBuyingAssetCost = BigDecimal.valueOf(250);

        FinancialAssetSaveDto financialAssetSaveDto = new FinancialAssetSaveDto();
        financialAssetSaveDto.setAssetCost(afterBuyingAssetCost);
        financialAssetSaveDto.setAssetSymbol("ASELS");
        financialAssetSaveDto.setAssetName("ASELSAN");
        financialAssetSaveDto.setAssetQuantity(addedAssetQuantity);
        financialAssetSaveDto.setAssetOwningDate(new Date());


        Portfolio portfolio = new Portfolio(2L,"Portföy", BigDecimal.valueOf(4500));
        FinancialAsset financialAsset = new FinancialAsset(1L,"ASELSAN","ASELS",assetQuantity,assetCost,new Date(),portfolio);
        //when
        Mockito.when(portfolioDao.findById(anyLong())).thenReturn(Optional.of(portfolio));
        Mockito.when(financialAssetDao.findByAssetSymbolAndPortfolio_Id(financialAssetSaveDto.getAssetSymbol(), 2L)).thenReturn(Optional.of(financialAsset));
        Mockito.when(financialAssetDao.save(Mockito.any(FinancialAsset.class))).thenReturn(financialAsset);
        //then
        FinancialAssetResponseDto financialAssetResponseDto = financialAssetServiceImpl.buyFinancialAsset(2L, financialAssetSaveDto);
        assertEquals(BigDecimal.valueOf(216.67),financialAssetResponseDto.getAssetCost());
    }

    @Test
    void sellFinancialAsset() {
    }

    @Test
    void getAllFinancialAssetsByPortfolioId() {
    }

    @Test
    void getAllFinancialAssets() {
    }
}