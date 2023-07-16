package com.arifyusufyilmaz.portfolioTrackingApp.service.concretes;

import com.arifyusufyilmaz.portfolioTrackingApp.converter.FinancialAssetMapper;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.FinancialAssetResponseDto;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.FinancialAssetSaveDto;
import com.arifyusufyilmaz.portfolioTrackingApp.entity.DepositFinancialAssetTransaction;
import com.arifyusufyilmaz.portfolioTrackingApp.entity.FinancialAsset;
import com.arifyusufyilmaz.portfolioTrackingApp.entity.FinancialAssetTransaction;
import com.arifyusufyilmaz.portfolioTrackingApp.entity.Portfolio;
import com.arifyusufyilmaz.portfolioTrackingApp.repository.FinancialAssetDao;
import com.arifyusufyilmaz.portfolioTrackingApp.repository.PortfolioDao;
import com.arifyusufyilmaz.portfolioTrackingApp.repository.TransactionDao;
import com.arifyusufyilmaz.portfolioTrackingApp.service.abstracts.FinancialAssetService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        if(portfolio.isPresent() == false){
            //TODO throw
        }
        if(financialAssetSaveDto == null){
            //TODO throw
        }
       /* Optional<FinancialAsset> financialAssetOpt = this.financialAssetDao.findByAssetSymbolAndPortfolio_Id(financialAssetSaveDto.getAssetSymbol(), portfolioId);
        if (financialAssetOpt.isPresent()) { // there is already one in same portfolio.

        }else{

        }*/
        FinancialAsset financialAsset =  FinancialAssetMapper.INSTANCE.mapFinancialAssetSaveDtoToFinancialAsset(financialAssetSaveDto);
        financialAsset.setPortfolio(portfolio.get());

        Map<BigDecimal, BigDecimal> quantityCostMap = new HashMap<>();
        quantityCostMap.put(financialAssetSaveDto.getAssetQuantity(), financialAssetSaveDto.getAssetCost());
        financialAsset.getQuantityCostHistory().offer(quantityCostMap);

        System.out.println(financialAsset.getQuantityCostHistory().peek().get(financialAssetSaveDto.getAssetQuantity()));

        generateFinancialAssetTransaction(financialAsset, new DepositFinancialAssetTransaction(financialAssetSaveDto.getAssetSymbol(),
                financialAssetSaveDto.getAssetQuantity(),
                financialAssetSaveDto.getAssetCost()));

        return FinancialAssetMapper
                                   .INSTANCE
                                   .mapFinancialAssetToFinancialAssetResponseDto(this.financialAssetDao.save(financialAsset));
    }
    @Override
    public FinancialAssetResponseDto sellFinancialAsset(Long portfolioId, FinancialAssetSaveDto financialAssetSaveDto) {
        return null;
    }

    @Override
    public List<FinancialAssetResponseDto> getAllFinancialAssets(Long portfolioId) {
        // TODO check
        List<FinancialAsset> financialAssets =  this.financialAssetDao.findAllByPortfolioId(portfolioId);
        return FinancialAssetMapper.INSTANCE.mapFinancialAssetListToFinancialAssetResponseDtoList(financialAssets);

    }


    // TODO there should be some calculations! owningcost etc.
    private void generateFinancialAssetTransaction(FinancialAsset financialAsset,FinancialAssetTransaction financialAssetTransaction){
         financialAssetTransaction.generateFinancialAssetTransaction(financialAsset);
         //save could be anywhere else
        transactionDao.save(financialAssetTransaction);
    }
    private BigDecimal calculateCost(FinancialAsset financialAsset,FinancialAssetSaveDto financialAssetSaveDto){
        BigDecimal oldTotalValue = financialAsset.getAssetQuantity().multiply(financialAsset.getAssetCost());
        BigDecimal additionalTotalValue = financialAssetSaveDto.getAssetQuantity().multiply(financialAssetSaveDto.getAssetCost());
        BigDecimal totalValue = oldTotalValue.add(additionalTotalValue);
        BigDecimal totalQuantity = financialAsset.getAssetQuantity().add(financialAsset.getAssetQuantity());
        return totalValue.divide(totalQuantity);
    }
}
