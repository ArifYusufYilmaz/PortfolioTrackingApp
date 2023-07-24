package com.arifyusufyilmaz.portfolioTrackingApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PortfolioTrackingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioTrackingAppApplication.class, args);
	}

}
