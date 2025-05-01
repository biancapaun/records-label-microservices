package com.labelapp.production_service.service;


import com.labelapp.production_service.dto.ProductionDTO;

import java.util.List;

public interface ProductionService {

    List<ProductionDTO> findProductionsByProducerId(Long id);

}
