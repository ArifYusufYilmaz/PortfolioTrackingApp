package com.arifyusufyilmaz.portfolioTrackingApp.service.concretes;

import com.arifyusufyilmaz.portfolioTrackingApp.dto.collectApiDtos.BistAssetDto;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.collectApiDtos.CollectApiBistDataDto;
import com.arifyusufyilmaz.portfolioTrackingApp.entity.DailyMarketProfit;
import com.arifyusufyilmaz.portfolioTrackingApp.entity.FinancialAsset;
import com.arifyusufyilmaz.portfolioTrackingApp.entity.FinancialAssetDailyMarketProfit;
import com.arifyusufyilmaz.portfolioTrackingApp.repository.DailyMarketProfitDao;
import com.arifyusufyilmaz.portfolioTrackingApp.repository.FinancialAssetDao;
import com.arifyusufyilmaz.portfolioTrackingApp.service.abstracts.FinancialAssetDailyMarketProfitService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FinancialAssetDailyMarketProfitServiceImpl implements FinancialAssetDailyMarketProfitService {
    private final BistApiImpl bistApi;

    private final DailyMarketProfitDao dailyMarketProfitDao;
    private final FinancialAssetDao financialAssetDao;
    private int count = 0;

    public FinancialAssetDailyMarketProfitServiceImpl(BistApiImpl bistApi, DailyMarketProfitDao dailyMarketProfitDao, FinancialAssetDao financialAssetDao) {
        this.bistApi = bistApi;
        this.dailyMarketProfitDao = dailyMarketProfitDao;
        this.financialAssetDao = financialAssetDao;
    }

    private List<BistAssetDto> fetchDataFromApi(){
      /*  if(!bistApi.getBistApiResponse().isSuccess()){
            // todo throw
        }
        return bistApi.getBistApiResponse().getResult();
      */

        return this.getMockData();  //todo ; to use api economically

    }

    public BistAssetDto getAsset(String symbol){
         Optional<BistAssetDto> foundAssetData =  fetchDataFromApi().stream()
                .filter(assetData -> assetData.getName().equals(symbol)).findAny();
         if(!foundAssetData.isPresent()){
             // todo throw
         }
        return foundAssetData.get();
    }

    public void generateDailyProfits(Long financialAssetId){ //todo name could be changed;
        FinancialAsset financialAsset =  getExistingFinancialAsset(financialAssetId);
        BistAssetDto bistAsset =  getAsset(financialAsset.getAssetSymbol());

        System.out.println("calistim");
        FinancialAssetDailyMarketProfit faDailyMarketProfit = new FinancialAssetDailyMarketProfit();
        faDailyMarketProfit.setFinancialAsset(financialAsset);
        faDailyMarketProfit.setCurrentPrice(bistAsset.getPrice());
        faDailyMarketProfit.setMarketTotalValue(calculateMarketTotalValue(financialAsset,bistAsset));
        faDailyMarketProfit.setMarketProfitAsTurkishLira(calculateMarketProfitAsTRY(financialAsset,bistAsset));
        faDailyMarketProfit.setMarketProfitAsPercentage(calculateMarketProfitAsPercentage(financialAsset,bistAsset));
        this.dailyMarketProfitDao.save(faDailyMarketProfit);
    }
    private BigDecimal calculateMarketTotalValue(FinancialAsset financialAsset, BistAssetDto bistAsset){
        // if mtv == 0, then use cost
        return bistAsset.getPrice().multiply(financialAsset.getAssetQuantity());
        // market total value = price* quantity

    }
    private BigDecimal calculateMarketProfitAsTRY(FinancialAsset financialAsset, BistAssetDto bistAsset){

        BigDecimal marketTotalValue = calculateMarketTotalValue(financialAsset, bistAsset);

        BigDecimal marketOldTotalValue = financialAsset.getAssetCost().multiply(financialAsset.getAssetQuantity()); // TODO
        //if() id ve tarihe göre financialAsset tarihine göre arat, yoksa maliyeti kullan. //TODO
        return marketTotalValue.subtract(marketOldTotalValue);
    }
    private BigDecimal calculateMarketProfitAsPercentage(FinancialAsset financialAsset, BistAssetDto bistAsset){
        BigDecimal divided =  bistAsset.getPrice().divide(financialAsset.getAssetCost(),2);
        BigDecimal subtracted = divided.subtract(BigDecimal.valueOf(1));
        return subtracted.multiply(BigDecimal.valueOf(100));
    }
    private boolean checkIfValueAlreadyCalculated(Long financialAssetId, Date marketTransactionDate){

        Optional<FinancialAssetDailyMarketProfit> financialAssetDailyMarketProfit =
                this.dailyMarketProfitDao.findByFinancialAsset_IdAndMarketTransactionDate(financialAssetId, marketTransactionDate);
        if(!financialAssetDailyMarketProfit.isPresent()){
            return false;
        }
        return true;
    }
    private FinancialAsset getExistingFinancialAsset(Long financialAssetId){

        Optional<FinancialAsset> financialAsset = this.financialAssetDao.findById(financialAssetId); // todo query can be changed
        if(!financialAsset.isPresent()){
            //
        }
        return financialAsset.get();
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
    private List<BistAssetDto> getMockData(){
        BistAssetDto data1 = new BistAssetDto();
        data1.setCurrency("TRY");
        data1.setName("ACSEL");
        data1.setPricestr("91.20");
        data1.setPrice(BigDecimal.valueOf(91.2));
        data1.setRate(BigDecimal.valueOf(-0.33));
        data1.setTime("14:13:44");
        BistAssetDto data2 = new BistAssetDto();
        data2.setCurrency("TRY");
        data2.setName("ASTOR");
        data2.setPricestr("106.70");
        data2.setPrice(BigDecimal.valueOf(106.7));
        data2.setRate(BigDecimal.valueOf(10));
        data2.setTime("14:13:49");
        List<BistAssetDto> list = new ArrayList<>();
        list.add(data1);
        list.add(data2);
        return list;
    }

}
