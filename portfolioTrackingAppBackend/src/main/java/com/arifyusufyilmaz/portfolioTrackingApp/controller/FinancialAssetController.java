package com.arifyusufyilmaz.portfolioTrackingApp.controller;

import com.arifyusufyilmaz.portfolioTrackingApp.dto.FinancialAssetResponseDto;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.FinancialAssetSaveDto;
import com.arifyusufyilmaz.portfolioTrackingApp.service.abstracts.FinancialAssetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/financial-assets")
public class FinancialAssetController {
    private final FinancialAssetService financialAssetService;

    public FinancialAssetController(FinancialAssetService financialAssetService) {
        this.financialAssetService = financialAssetService;
    }

    @PostMapping("/financial-asset/buy/{portfolioId}")
    public FinancialAssetResponseDto buyFinancialAsset(@PathVariable Long portfolioId, @RequestBody FinancialAssetSaveDto financialAssetSaveDto)
    {
        return this.financialAssetService.buyFinancialAsset(portfolioId, financialAssetSaveDto);
    }
    @PostMapping("/financial-asset/sell/{portfolioId}")
    public FinancialAssetResponseDto sellFinancialAsset(@PathVariable Long portfolioId, @RequestBody FinancialAssetSaveDto financialAssetSaveDto)
    {
        return this.financialAssetService.sellFinancialAsset(portfolioId, financialAssetSaveDto);

    }
    @GetMapping("/all/{portfolioId}")
    public List<FinancialAssetResponseDto> getAllFinancialAssets(@PathVariable Long portfolioId){
        return this.financialAssetService.getAllFinancialAssets(portfolioId);
    }
    // TODO : other stuff(owningcost calculations etc).


}
