package com.arifyusufyilmaz.portfolioTrackingApp.service.abstracts;

import com.arifyusufyilmaz.portfolioTrackingApp.dto.FinancialAssetResponseDto;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.FinancialAssetSaveDto;

import java.util.List;

public interface FinancialAssetService {
    FinancialAssetResponseDto buyFinancialAsset(Long portfolioId, FinancialAssetSaveDto financialAssetSaveDto);

    List<FinancialAssetResponseDto> getAllFinancialAssetsByPortfolioId(Long portfolioId);
    List<FinancialAssetResponseDto> getAllFinancialAssets();

    FinancialAssetResponseDto sellFinancialAsset(Long portfolioId, FinancialAssetSaveDto financialAssetSaveDto);
}
