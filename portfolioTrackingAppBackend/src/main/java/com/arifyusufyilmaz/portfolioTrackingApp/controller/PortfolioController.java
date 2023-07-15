package com.arifyusufyilmaz.portfolioTrackingApp.controller;

import com.arifyusufyilmaz.portfolioTrackingApp.dto.PortfolioCreditDto;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.PortfolioDebitDto;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.PortfolioResponseDto;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.PortfolioSaveDto;
import com.arifyusufyilmaz.portfolioTrackingApp.entity.Portfolio;
import com.arifyusufyilmaz.portfolioTrackingApp.service.abstracts.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/portfolios")
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @PostMapping("/portfolio/{userId}/")
    public PortfolioResponseDto createPortfolio(@PathVariable Long userId,@RequestBody PortfolioSaveDto portfolioSaveDto){
        PortfolioResponseDto portfolioResponseDto = portfolioService.createPortfolio(userId,portfolioSaveDto);
        return portfolioResponseDto; //ResponseEntity.ok(portfolioResponseDto);

    }
    @GetMapping("/all") // TODO users!
    public List<PortfolioResponseDto> getAllPortfolios(){
        return null;
    }

    @GetMapping("/portfolio/{id}")
    public PortfolioResponseDto getPortfolio(@PathVariable Long id)
    {
        return portfolioService.getPortfolio(id);
    }

    //TODO delete,update
    //TODO debit, credit

    @PostMapping("/portfolio/debit/{id}")   // para çekmeyle ilgili
    public PortfolioResponseDto debit(@PathVariable Long id, @RequestBody PortfolioDebitDto portfolioDebitDto){
        return portfolioService.debit(id, portfolioDebitDto);
    }
    @PostMapping("/portfolio/credit/{id}")      // para yatırmayla ilgili
    public PortfolioResponseDto credit(@PathVariable Long id, @RequestBody PortfolioCreditDto portfolioCreditDto) {
        return this.portfolioService.credit(id, portfolioCreditDto);
    }

}
