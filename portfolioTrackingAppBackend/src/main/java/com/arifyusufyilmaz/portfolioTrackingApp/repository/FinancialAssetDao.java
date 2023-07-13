package com.arifyusufyilmaz.portfolioTrackingApp.repository;

import com.arifyusufyilmaz.portfolioTrackingApp.entity.FinancialAsset;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialAssetDao extends JpaRepository<FinancialAsset, Long> {
}
