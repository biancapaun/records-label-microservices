package com.labelapp.contractservice.controller;

import com.labelapp.contractservice.dto.ContractDTO;
import com.labelapp.contractservice.service.ContractService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractController {


    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping("/artist/{artistId}")
    public List<ContractDTO> getContractsByArtistId(@PathVariable Long artistId) {
        return contractService.findContractsById(artistId);
    }
}


