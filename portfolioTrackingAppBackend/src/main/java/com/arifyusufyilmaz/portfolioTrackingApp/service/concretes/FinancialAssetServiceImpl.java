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
import com.arifyusufyilmaz.portfolioTrackingApp.service.abstracts.FinancialAssetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinancialAssetServiceImpl implements FinancialAssetService {
    private final FinancialAssetDao financialAssetDao;
    private final PortfolioDao portfolioDao;
    public FinancialAssetServiceImpl(FinancialAssetDao financialAssetDao, PortfolioDao portfolioDao) {
        this.financialAssetDao = financialAssetDao;
        this.portfolioDao = portfolioDao;
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
        FinancialAsset financialAsset =  FinancialAssetMapper.INSTANCE.mapFinancialAssetSaveDtoToFinancialAsset(financialAssetSaveDto);
        financialAsset.setPortfolio(portfolio.get());

        FinancialAssetTransaction financialAssetTransaction = new DepositFinancialAssetTransaction(financialAsset.getAssetSymbol(), financialAsset.getAssetQuantity(), financialAsset.getAssetCost());
        financialAssetTransaction.generateFinancialAssetTransaction(financialAsset);

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
}
