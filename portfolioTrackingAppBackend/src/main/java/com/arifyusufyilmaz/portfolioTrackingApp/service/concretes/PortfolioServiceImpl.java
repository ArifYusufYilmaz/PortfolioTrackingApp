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
import java.util.Optional;

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
        List<Portfolio> portfolioList = portfolioDao.findAll();
        return PortfolioMapper.INSTANCE.mapPortfolioListToPortfolioResponseDtoList(portfolioList);
    }

    @Override
    public PortfolioResponseDto getPortfolio(Long id) {
        //TODO check if exist
        Optional<Portfolio> portfolio = portfolioDao.findById(id);
        if(portfolio.isPresent() == false){
            //TODO
        }
        return PortfolioMapper.INSTANCE.mapPortfolioToPortfolioResponseDto(portfolio.get());
    }
}
