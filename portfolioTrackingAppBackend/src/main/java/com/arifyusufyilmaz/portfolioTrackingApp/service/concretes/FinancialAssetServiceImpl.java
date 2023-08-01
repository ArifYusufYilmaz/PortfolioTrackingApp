package com.arifyusufyilmaz.portfolioTrackingApp.service.concretes;

import com.arifyusufyilmaz.portfolioTrackingApp.converter.FinancialAssetMapper;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.FinancialAssetResponseDto;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.FinancialAssetSaveDto;
import com.arifyusufyilmaz.portfolioTrackingApp.entity.*;
import com.arifyusufyilmaz.portfolioTrackingApp.repository.FinancialAssetDao;
import com.arifyusufyilmaz.portfolioTrackingApp.repository.PortfolioDao;
import com.arifyusufyilmaz.portfolioTrackingApp.repository.TransactionDao;
import com.arifyusufyilmaz.portfolioTrackingApp.service.abstracts.FinancialAssetService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class FinancialAssetServiceImpl implements FinancialAssetService {
    private final FinancialAssetDao financialAssetDao;
    private final PortfolioDao portfolioDao;
    private final TransactionDao transactionDao;

    public FinancialAssetServiceImpl(FinancialAssetDao financialAssetDao, PortfolioDao portfolioDao, TransactionDao transactionDao) {
        this.financialAssetDao = financialAssetDao;
        this.portfolioDao = portfolioDao;
        this.transactionDao = transactionDao;
    }

    @Override
    public FinancialAssetResponseDto buyFinancialAsset(Long portfolioId, FinancialAssetSaveDto financialAssetSaveDto) {
        //Todo check if exist
        Optional<Portfolio> portfolio =  this.portfolioDao.findById(portfolioId);
        if(!portfolio.isPresent()){
            //TODO throw
        }
        if(financialAssetSaveDto == null){
            //TODO throw
        }
        Optional<FinancialAsset> financialAssetOpt = this.financialAssetDao.findByAssetSymbolAndPortfolio_Id(financialAssetSaveDto.getAssetSymbol(),portfolioId);
        FinancialAsset financialAsset;

        if (financialAssetOpt.isPresent()) {
            // there is already one in same portfolio.
            financialAsset = financialAssetOpt.get();
        }
        else{
            financialAsset =  FinancialAssetMapper.INSTANCE.mapFinancialAssetSaveDtoToFinancialAsset(financialAssetSaveDto);
            financialAsset.setPortfolio(portfolio.get());

        }

        generateFinancialAssetTransaction(financialAsset, new DepositFinancialAssetTransaction(financialAssetSaveDto.getAssetSymbol(),
                financialAssetSaveDto.getAssetQuantity(),
                financialAssetSaveDto.getAssetCost()));
        if(financialAssetOpt.isPresent()){
            setCostAndQuantityAfterAdditionalBuying(financialAsset,financialAssetSaveDto);
        }

        return FinancialAssetMapper
                                   .INSTANCE
                                   .mapFinancialAssetToFinancialAssetResponseDto(this.financialAssetDao.save(financialAsset));
    }
    @Override
    public FinancialAssetResponseDto sellFinancialAsset(Long portfolioId, FinancialAssetSaveDto financialAssetSaveDto) {
       if(financialAssetSaveDto == null){
           // todo throw
       }
       Optional<Portfolio> portfolio = this.portfolioDao.findById(portfolioId);
       if(!portfolio.isPresent()){
           //todo throw
       }
       Optional<FinancialAsset> financialAssetOpt = this.financialAssetDao.findByAssetSymbolAndPortfolio_Id(financialAssetSaveDto.getAssetSymbol(),portfolioId);

       if(!financialAssetOpt.isPresent()){
           // todo throw
       }

       generateFinancialAssetTransaction(financialAssetOpt.get(), new WithdrawalFinancialAssetTransaction(
                                                                               financialAssetSaveDto.getAssetSymbol(),
                                                                               financialAssetSaveDto.getAssetQuantity(),
                                                                               financialAssetSaveDto.getAssetCost()));

        setCostAndQuantityAfterSelling(financialAssetOpt.get(),financialAssetSaveDto);
        FinancialAsset financialAsset = this.financialAssetDao.save(financialAssetOpt.get());
        return FinancialAssetMapper.INSTANCE.mapFinancialAssetToFinancialAssetResponseDto(financialAsset);
    }

    @Override
    public List<FinancialAssetResponseDto> getAllFinancialAssetsByPortfolioId(Long portfolioId) {
        // TODO check
        List<FinancialAsset> financialAssets =  this.financialAssetDao.findAllByPortfolioId(portfolioId);
        return FinancialAssetMapper.INSTANCE.mapFinancialAssetListToFinancialAssetResponseDtoList(financialAssets);

    }
    @Override
    public List<FinancialAssetResponseDto> getAllFinancialAssets() {
        // TODO check
        List<FinancialAsset> financialAssets =  this.financialAssetDao.findAll();
        return FinancialAssetMapper.INSTANCE.mapFinancialAssetListToFinancialAssetResponseDtoList(financialAssets);

    }


    // TODO there should be some calculations! owningcost etc.
    private void generateFinancialAssetTransaction(FinancialAsset financialAsset,FinancialAssetTransaction financialAssetTransaction){
         financialAssetTransaction.generateFinancialAssetTransaction(financialAsset);
         //save could be anywhere else
        transactionDao.save(financialAssetTransaction);
    }
    private void setCostAndQuantityAfterAdditionalBuying(FinancialAsset financialAsset,FinancialAssetSaveDto financialAssetSaveDto){
        BigDecimal quantity =  calculateQuantityAfterAdditionalBuying(financialAsset,financialAssetSaveDto);
        financialAsset.setAssetCost(calculateCostAfterAdditionalBuying(financialAsset,financialAssetSaveDto, quantity));
        financialAsset.setAssetQuantity(quantity);
    }
    private BigDecimal calculateCostAfterAdditionalBuying(FinancialAsset financialAsset,FinancialAssetSaveDto financialAssetSaveDto, BigDecimal assetQuantity){
        BigDecimal previousOwningCost = financialAsset.getAssetQuantity().multiply(financialAsset.getAssetCost());
        BigDecimal additionalBuyingCost = financialAssetSaveDto.getAssetQuantity().multiply(financialAssetSaveDto.getAssetCost());
        BigDecimal totalOwningCost = previousOwningCost.add(additionalBuyingCost);

         return totalOwningCost.divide(assetQuantity, 2, RoundingMode.HALF_UP);
    }
    private BigDecimal calculateQuantityAfterAdditionalBuying(FinancialAsset financialAsset,FinancialAssetSaveDto financialAssetSaveDto){
        return  financialAsset.getAssetQuantity().add(financialAssetSaveDto.getAssetQuantity());
    }
    private BigDecimal calculateCostAfterSelling(FinancialAsset financialAsset, FinancialAssetSaveDto financialAssetSaveDto, BigDecimal remainingQuantity){
            // it should be sellDto
        BigDecimal previousOwningCost = financialAsset.getAssetQuantity().multiply(financialAsset.getAssetCost());
        BigDecimal sellingProceeds =financialAssetSaveDto.getAssetQuantity().multiply(financialAssetSaveDto.getAssetCost());
        BigDecimal totalOwningCost = previousOwningCost.subtract(sellingProceeds);
        
        return totalOwningCost.divide(remainingQuantity);
        // TODO : sellDto, checks (cannot smaller than available numbers etc.)
        // - values indicates profit!
    }
    private BigDecimal calculateQuantityAfterSelling(FinancialAsset financialAsset, FinancialAssetSaveDto financialAssetSaveDto){
        if(financialAsset.getAssetQuantity().compareTo(financialAssetSaveDto.getAssetQuantity()) < 0){
            // todo throw
        }
        return financialAsset.getAssetQuantity().subtract(financialAssetSaveDto.getAssetQuantity());
    }
    private void setCostAndQuantityAfterSelling(FinancialAsset financialAsset,FinancialAssetSaveDto financialAssetSaveDto){
        BigDecimal quantity = calculateQuantityAfterSelling(financialAsset, financialAssetSaveDto);
        financialAsset.setAssetCost(calculateCostAfterSelling(financialAsset, financialAssetSaveDto, quantity));
        financialAsset.setAssetQuantity(quantity);
    }
}
