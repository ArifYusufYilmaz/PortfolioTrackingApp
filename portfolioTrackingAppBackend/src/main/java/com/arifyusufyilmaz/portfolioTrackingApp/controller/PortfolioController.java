package com.arifyusufyilmaz.portfolioTrackingApp.controller;

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

    @PostMapping("/portfolio/{userId}/new")
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
}
