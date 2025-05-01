package com.labelapp.production_service.controller;

import com.labelapp.production_service.dto.ProductionDTO;
import com.labelapp.production_service.service.ProductionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productions")
public class ProductionController {

    private final ProductionService productionService;

    public ProductionController(ProductionService productionService) {
        this.productionService = productionService;
    }

    @GetMapping("/producer/{producerId}")
    public ResponseEntity<List<ProductionDTO>> getProductionsByProducerId(@PathVariable Long producerId) {
        List<ProductionDTO> productions = productionService.findProductionsByProducerId(producerId);
        return ResponseEntity.ok(productions);
    }
}


