package com.arifyusufyilmaz.portfolioTrackingApp.dto.collectApiDtos;


import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class BistAssetDto {

    private String currency;
    private String name;
    private String pricestr;
    private double price;
    private double rate;
    private String time;



    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPricestr() {
        return pricestr;
    }

    public void setPricestr(String pricestr) {
        this.pricestr = pricestr;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
