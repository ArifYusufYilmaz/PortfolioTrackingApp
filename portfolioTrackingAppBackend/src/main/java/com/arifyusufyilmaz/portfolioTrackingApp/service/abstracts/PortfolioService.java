package com.arifyusufyilmaz.portfolioTrackingApp.service.abstracts;

import com.arifyusufyilmaz.portfolioTrackingApp.dto.PortfolioResponseDto;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.PortfolioSaveDto;
import com.arifyusufyilmaz.portfolioTrackingApp.entity.Portfolio;

public interface PortfolioService {
    PortfolioResponseDto createPortfolio(PortfolioSaveDto portfolioSaveDto);

}
