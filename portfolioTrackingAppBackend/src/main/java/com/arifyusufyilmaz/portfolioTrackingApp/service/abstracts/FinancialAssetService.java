package com.arifyusufyilmaz.portfolioTrackingApp.service.abstracts;

import com.arifyusufyilmaz.portfolioTrackingApp.dto.FinancialAssetResponseDto;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.FinancialAssetSaveDto;

import java.util.List;

public interface FinancialAssetService {
    FinancialAssetResponseDto createFinancialAsset(Long portfolioId, FinancialAssetSaveDto financialAssetSaveDto);

    List<FinancialAssetResponseDto> getAllFinancialAssets(Long portfolioId);
}
