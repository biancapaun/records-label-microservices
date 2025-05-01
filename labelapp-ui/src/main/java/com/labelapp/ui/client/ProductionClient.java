package com.labelapp.ui.client;

import com.labelapp.ui.dto.ProductionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "production-service")
public interface ProductionClient {

    @GetMapping("/productions/producer/{producerId}")
    List<ProductionDTO> getProductionsByProducerId(@PathVariable Long producerId);



}