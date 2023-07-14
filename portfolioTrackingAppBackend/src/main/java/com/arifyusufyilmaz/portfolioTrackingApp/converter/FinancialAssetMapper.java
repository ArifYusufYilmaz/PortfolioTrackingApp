package com.arifyusufyilmaz.portfolioTrackingApp.converter;

import com.arifyusufyilmaz.portfolioTrackingApp.dto.FinancialAssetResponseDto;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.FinancialAssetSaveDto;
import com.arifyusufyilmaz.portfolioTrackingApp.entity.FinancialAsset;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FinancialAssetMapper {
    FinancialAssetMapper INSTANCE = Mappers.getMapper(FinancialAssetMapper.class);
    FinancialAsset mapFinancialAssetSaveDtoToFinancialAsset(FinancialAssetSaveDto financialAssetSaveDto);
    FinancialAssetResponseDto mapFinancialAssetToFinancialAssetResponseDto(FinancialAsset financialAsset);

    List<FinancialAssetResponseDto> mapFinancialAssetListToFinancialAssetResponseDtoList(List<FinancialAsset> financialAssets);
}
