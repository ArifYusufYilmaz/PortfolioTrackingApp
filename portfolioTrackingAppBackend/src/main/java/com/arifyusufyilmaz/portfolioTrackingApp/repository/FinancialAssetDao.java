package com.arifyusufyilmaz.portfolioTrackingApp.repository;

import com.arifyusufyilmaz.portfolioTrackingApp.entity.FinancialAsset;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinancialAssetDao extends JpaRepository<FinancialAsset, Long> {
    List<FinancialAsset> findAllByPortfolioId(Long portfolioId);
}
