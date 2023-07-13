package com.arifyusufyilmaz.portfolioTrackingApp.repository;

import com.arifyusufyilmaz.portfolioTrackingApp.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioDao extends JpaRepository<Portfolio, Long> {
}
