package com.labelapp.contractservice.service;


import com.labelapp.contractservice.dto.ContractDTO;

import java.util.List;

public interface ContractService {

    List<ContractDTO> findContractsById(Long id);

}
