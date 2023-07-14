package com.arifyusufyilmaz.portfolioTrackingApp.service.abstracts;

import com.arifyusufyilmaz.portfolioTrackingApp.dto.PortfolioResponseDto;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.PortfolioSaveDto;
import com.arifyusufyilmaz.portfolioTrackingApp.entity.Portfolio;

import java.util.List;

public interface PortfolioService {
    PortfolioResponseDto createPortfolio(Long userId, PortfolioSaveDto portfolioSaveDto);
    List<PortfolioResponseDto> getAllPortfolios(Long userId);
    PortfolioResponseDto getPortfolio(Long id);
    // TODO : delete, update
}
