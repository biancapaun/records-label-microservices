package com.labelapp.production_service.repository;

import com.labelapp.production_service.domain.Production;
import com.labelapp.production_service.domain.ProductionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductionRepository extends JpaRepository<Production, ProductionId> {
    List<Production> findByProducerId(Long producerId);
}
