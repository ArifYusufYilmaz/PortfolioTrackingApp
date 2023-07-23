package com.arifyusufyilmaz.portfolioTrackingApp.service.concretes;

import com.arifyusufyilmaz.portfolioTrackingApp.dto.collectApiDtos.BistAssetDto;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.collectApiDtos.CollectApiBistDataDto;
import com.arifyusufyilmaz.portfolioTrackingApp.service.abstracts.FinancialAssetDailyMarketProfitService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FinancialAssetDailyMarketProfitServiceImpl implements FinancialAssetDailyMarketProfitService {
    private final BistApiImpl bistApi;

    public FinancialAssetDailyMarketProfitServiceImpl(BistApiImpl bistApi) {
        this.bistApi = bistApi;
    }
    public double getSomeValueTrying(){
        List<BistAssetDto> list =  bistApi.getBistApiResponse().getResult();
        double value = 0.0;
        for (BistAssetDto bistAsset : list) {
             value = bistAsset.getPrice();
             break;
        }
        return value;
    }


}
