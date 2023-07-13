package com.arifyusufyilmaz.portfolioTrackingApp.converter;

import com.arifyusufyilmaz.portfolioTrackingApp.dto.PortfolioResponseDto;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.PortfolioSaveDto;
import com.arifyusufyilmaz.portfolioTrackingApp.entity.Portfolio;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE )
public interface PortfolioMapper {
    PortfolioMapper INSTANCE = Mappers.getMapper(PortfolioMapper.class);

    Portfolio mapPortfolioSaveDtoToPortfolio(PortfolioSaveDto portfolioSaveDto);
    PortfolioResponseDto mapPortfolioToPortfolioResponseDto(Portfolio portfolio);
}
