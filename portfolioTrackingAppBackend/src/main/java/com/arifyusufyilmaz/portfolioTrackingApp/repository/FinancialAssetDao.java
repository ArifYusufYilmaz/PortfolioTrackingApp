package com.arifyusufyilmaz.portfolioTrackingApp.repository;

import com.arifyusufyilmaz.portfolioTrackingApp.entity.FinancialAsset;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FinancialAssetDao extends JpaRepository<FinancialAsset, Long> {
    List<FinancialAsset> findAllByPortfolioId(Long portfolioId);


    Optional<FinancialAsset> findByAssetSymbolAndPortfolio_Id(String assetSymbol, Long portfolioId);
}
