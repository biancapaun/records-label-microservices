package com.labelapp.production_service.service;

import com.labelapp.production_service.domain.Production;
import com.labelapp.production_service.dto.ProductionDTO;
import com.labelapp.production_service.feign.ProducerClient;
import com.labelapp.production_service.repository.ProductionRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionServiceImpl implements ProductionService {

    private static final Logger logger = LoggerFactory.getLogger(ProductionServiceImpl.class);

    private final ProducerClient producerClient;
    private final ModelMapper modelMapper;
    private final ProductionRepository productionRepository;

    @Autowired
    public ProductionServiceImpl(ProducerClient producerClient, ModelMapper modelMapper, ProductionRepository productionRepository) {
        this.producerClient = producerClient;
        this.modelMapper = modelMapper;
        this.productionRepository = productionRepository;
    }

    @Override
    public List<ProductionDTO> findProductionsByProducerId(Long producerId) {
        List<Production> productions = productionRepository.findByProducerId(producerId);
        return productions.stream()
                .map(prod -> modelMapper.map(prod, ProductionDTO.class))
                .toList();
    }
}

