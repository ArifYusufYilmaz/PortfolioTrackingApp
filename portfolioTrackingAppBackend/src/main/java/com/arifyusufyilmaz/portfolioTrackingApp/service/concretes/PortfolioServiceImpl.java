package com.arifyusufyilmaz.portfolioTrackingApp.service.concretes;

import com.arifyusufyilmaz.portfolioTrackingApp.converter.PortfolioMapper;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.PortfolioResponseDto;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.PortfolioSaveDto;
import com.arifyusufyilmaz.portfolioTrackingApp.entity.Portfolio;
import com.arifyusufyilmaz.portfolioTrackingApp.repository.PortfolioDao;
import com.arifyusufyilmaz.portfolioTrackingApp.service.abstracts.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioDao portfolioDao;

    public PortfolioServiceImpl(PortfolioDao portfolioDao) {
        this.portfolioDao = portfolioDao;
    }

    @Override
    public PortfolioResponseDto createPortfolio(Long userId,PortfolioSaveDto portfolioSaveDto) {
        if(portfolioSaveDto == null){
            // Todo throw exc.
        }
        if(userId == null){

        }

        Portfolio portfolio =  PortfolioMapper.INSTANCE.mapPortfolioSaveDtoToPortfolio(portfolioSaveDto);

        portfolioDao.save(portfolio);
        PortfolioResponseDto portfolioResponseDto = PortfolioMapper.INSTANCE.mapPortfolioToPortfolioResponseDto(portfolio);
        return portfolioResponseDto;
    }

    @Override
    public List<PortfolioResponseDto> getAllPortfolios(Long userId) {
        return null;
    }

    @Override
    public PortfolioResponseDto getPortfolio(Long id) {
        return null;
    }
}
