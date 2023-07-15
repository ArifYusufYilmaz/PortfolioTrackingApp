package com.arifyusufyilmaz.portfolioTrackingApp.service.concretes;

import com.arifyusufyilmaz.portfolioTrackingApp.converter.PortfolioMapper;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.PortfolioCreditDto;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.PortfolioDebitDto;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.PortfolioResponseDto;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.PortfolioSaveDto;
import com.arifyusufyilmaz.portfolioTrackingApp.entity.*;
import com.arifyusufyilmaz.portfolioTrackingApp.repository.PortfolioDao;
import com.arifyusufyilmaz.portfolioTrackingApp.repository.TransactionDao;
import com.arifyusufyilmaz.portfolioTrackingApp.repository.UserDao;
import com.arifyusufyilmaz.portfolioTrackingApp.service.abstracts.PortfolioService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioDao portfolioDao;
    private final UserDao userDao;

    private final TransactionDao transactionDao;
    public PortfolioServiceImpl(PortfolioDao portfolioDao, UserDao userDao, TransactionDao transactionDao) {
        this.portfolioDao = portfolioDao;
        this.userDao = userDao;
        this.transactionDao = transactionDao;
    }

    @Override
    public PortfolioResponseDto createPortfolio(Long userId,PortfolioSaveDto portfolioSaveDto) {
        if(portfolioSaveDto == null){
            // Todo throw exc.
        }
        Optional<User> user = this.userDao.findById(userId);
        if(!user.isPresent()){
            // TODO
        }
        Portfolio portfolio =  PortfolioMapper.INSTANCE.mapPortfolioSaveDtoToPortfolio(portfolioSaveDto);
        portfolio.setUser(user.get());

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

    @Override
    public PortfolioResponseDto debit(Long id, PortfolioDebitDto portfolioDebitDto) {

        Optional<Portfolio> portfolio = this.portfolioDao.findById(id);
        if(!portfolio.isPresent()) {
            //todo
        }
        BigDecimal amount = portfolioDebitDto.getCashAmount();
        generatePortfolioTransaction(amount, portfolio.get(), new WithdrawalPortfolioTransaction(amount));

        return PortfolioMapper.INSTANCE.mapPortfolioToPortfolioResponseDto(this.portfolioDao.save(portfolio.get()));
    }

    @Override
    public PortfolioResponseDto credit(Long id, PortfolioCreditDto portfolioCreditDto) {
        Optional<Portfolio> portfolio =  portfolioDao.findById(id);
        if (!portfolio.isPresent()) {
            // todo throw
        }
        BigDecimal amount = portfolioCreditDto.getCashAmount();
        generatePortfolioTransaction(amount, portfolio.get(), new DepositPortfolioTransaction(amount));
        return PortfolioMapper.INSTANCE.mapPortfolioToPortfolioResponseDto(this.portfolioDao.save(portfolio.get()));

    }
    private void generatePortfolioTransaction(BigDecimal amount, Portfolio portfolio, PortfolioTransaction portfolioTransaction){
        portfolioTransaction.generatePortfolioTransaction(portfolio);
        transactionDao.save(portfolioTransaction);
    }

}
