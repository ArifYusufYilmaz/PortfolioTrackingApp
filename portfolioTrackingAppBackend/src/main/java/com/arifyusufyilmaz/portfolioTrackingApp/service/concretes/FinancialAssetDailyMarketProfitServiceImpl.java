package com.arifyusufyilmaz.portfolioTrackingApp.service.concretes;

import com.arifyusufyilmaz.portfolioTrackingApp.dto.collectApiDtos.BistAssetDto;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.collectApiDtos.CollectApiBistDataDto;
import com.arifyusufyilmaz.portfolioTrackingApp.service.abstracts.FinancialAssetDailyMarketProfitService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FinancialAssetDailyMarketProfitServiceImpl implements FinancialAssetDailyMarketProfitService {
    private final BistApiImpl bistApi;
    private int count = 0;
    public FinancialAssetDailyMarketProfitServiceImpl(BistApiImpl bistApi) {
        this.bistApi = bistApi;
    }

    private List<BistAssetDto> fetchDataFromApi(){
        if(!bistApi.getBistApiResponse().isSuccess()){
            // todo throw
        }
        return bistApi.getBistApiResponse().getResult();

    }
    public void printToConsole(){
        System.out.println("consola yaziyorum");
    }

    public BigDecimal getSomeValueTrying(){
        List<BistAssetDto> list = fetchDataFromApi();
        BigDecimal value = BigDecimal.valueOf(0);

        /*for (BistAssetDto bistAsset : list) {
             value =  bistAsset.getPrice();
             break;
        }*/
        for (int i = 0; i< list.size(); i++){
            if(i == count){
                value = list.get(i).getPrice();
                count++;
                break;
            }

        }
        return value;
    }


}
