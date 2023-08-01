package com.arifyusufyilmaz.portfolioTrackingApp.service.concretes;

import com.arifyusufyilmaz.portfolioTrackingApp.converter.PortfolioMapper;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.PortfolioCreditDto;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.PortfolioDebitDto;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.PortfolioResponseDto;
import com.arifyusufyilmaz.portfolioTrackingApp.entity.Portfolio;
import com.arifyusufyilmaz.portfolioTrackingApp.entity.PortfolioTransaction;
import com.arifyusufyilmaz.portfolioTrackingApp.entity.WithdrawalFinancialAssetTransaction;
import com.arifyusufyilmaz.portfolioTrackingApp.entity.WithdrawalPortfolioTransaction;
import com.arifyusufyilmaz.portfolioTrackingApp.repository.PortfolioDao;
import com.arifyusufyilmaz.portfolioTrackingApp.repository.TransactionDao;
import com.arifyusufyilmaz.portfolioTrackingApp.repository.UserDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import javax.sound.sampled.Port;
import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.withSettings;

@ExtendWith(MockitoExtension.class)
class PortfolioServiceImplTest {

    @Mock
    private PortfolioDao portfolioDao;
    @Mock
    private PortfolioMapper portfolioMapper;
    @Mock
    private TransactionDao transactionDao;
    @Mock
    private UserDao userDao;
    @InjectMocks
    private PortfolioServiceImpl portfolioServiceImpl;
    @Test
    void createPortfolio() {

    }

    @Test
    void getAllPortfolios() {

    }

    @Test
    void shouldReturnPortfolioResponseDtoWithPortfolioNameWhileGettingPortfolio() {
        //given
        String portfolioName = "Portföy";
        Portfolio portfolio =  Mockito.mock(Portfolio.class);
        portfolio.setPortfolioName(portfolioName);
        // when
         Mockito.when(portfolioDao.findById(anyLong())).thenReturn(Optional.of(portfolio));

         PortfolioResponseDto portfolioResponseDto = portfolioServiceImpl.getPortfolio(1L);
        //then
        assertEquals(portfolio.getPortfolioName(), portfolioResponseDto.getPortfolioName());
    }


    @Test
    void shouldReturnPortfolioResponseDtoWithSubtractedValueOfPortfolioAmountWhileDebiting() {
        //given
        BigDecimal initialPortfolioAmount = BigDecimal.valueOf(4250);
        BigDecimal debitAmount = BigDecimal.valueOf(850);

        PortfolioDebitDto portfolioDebitDto = new PortfolioDebitDto();
        portfolioDebitDto.setCashAmount(debitAmount);
        Portfolio portfolio = new Portfolio(2L, "Portföy", initialPortfolioAmount);

        // when
        Mockito.when(portfolioDao.findById(anyLong())).thenReturn(Optional.of(portfolio));
        Mockito.when(portfolioDao.save(any(Portfolio.class))).thenReturn(portfolio);

        //then
        PortfolioResponseDto portfolioResponseDto = portfolioServiceImpl.debit(2L, portfolioDebitDto);
        assertEquals(BigDecimal.valueOf(3400), portfolioResponseDto.getPortfolioAvailableCash());

    }

    @Test
    void credit() {
    }
}