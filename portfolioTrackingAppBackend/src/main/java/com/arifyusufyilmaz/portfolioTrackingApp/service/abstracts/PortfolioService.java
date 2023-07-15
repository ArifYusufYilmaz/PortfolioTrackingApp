package com.arifyusufyilmaz.portfolioTrackingApp.service.abstracts;

import com.arifyusufyilmaz.portfolioTrackingApp.dto.PortfolioCreditDto;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.PortfolioDebitDto;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.PortfolioResponseDto;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.PortfolioSaveDto;
import com.arifyusufyilmaz.portfolioTrackingApp.entity.Portfolio;

import java.util.List;

public interface PortfolioService {
    PortfolioResponseDto createPortfolio(Long userId, PortfolioSaveDto portfolioSaveDto);
    List<PortfolioResponseDto> getAllPortfolios(Long userId);
    PortfolioResponseDto getPortfolio(Long id);

    PortfolioResponseDto debit(Long id, PortfolioDebitDto portfolioDebitDto);

    PortfolioResponseDto credit(Long id, PortfolioCreditDto portfolioCreditDto);

    // TODO : delete, update
}
